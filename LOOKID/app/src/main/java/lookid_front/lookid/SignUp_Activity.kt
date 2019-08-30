package lookid_front.lookid

import android.app.AlertDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_signup.*
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Entity.User_Entity
import okhttp3.OkHttpClient
import org.json.JSONObject

class SignUp_Activity : AppCompatActivity() {
    var user : User_Entity? = null
    var checkId : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Id_edittext 리스너 장착
        signup_id_EditText.addTextChangedListener(EditListener())

        //bank_name_spinner init
        val bank_list  = resources.getStringArray(R.array.bank_list)
        val bank_name_spinner_adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,bank_list)
        bank_name_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        signup_bank_name_Spinner.adapter = bank_name_spinner_adapter
    }

    inner class SignUp_Control(){
        fun edit_check() : Boolean{
            //필수 사항 EditText id값 array
            var editArray = arrayListOf<Int>(R.id.signup_name_EditText,R.id.signup_pw_EditText, R.id.signup_pw2_EditText, R.id.signup_name_EditText,
                    R.id.signup_phone_EditText, R.id.signup_email_EditText)

            //해당 EditText 값이 비었는지 체크
            for(i in 0 until editArray.size){
                var editText : EditText = findViewById(editArray[i])
                if(editText.text.isNullOrEmpty()) {
                    Toast.makeText(applicationContext,"필수 정보를 입력해주세요",Toast.LENGTH_SHORT).show()
                    return false
                }
            }

            //1차 비밀번호와 2차 비밀번호 같은지 확인
            if(!signup_pw_EditText.text.toString().equals(signup_pw2_EditText.text.toString())){
                Toast.makeText(applicationContext,"1차 2차 비밀번호가 다릅니다",Toast.LENGTH_SHORT).show()
                return false
            }

            return true
        }

        fun GET_Check(id : String){
            val url = getString(R.string.server_url) + getString(R.string.check_id) + id
            Asynctask().execute("0",url)
        }

        fun POST_SignUp(pw : String){
            val url = getString(R.string.server_url) + getString(R.string.sign_up)
            Asynctask().execute("1",url,pw)
        }

        fun Dialog_Signup(){
            var builder = AlertDialog.Builder(this@SignUp_Activity)
            builder.setMessage("회원가입 성공")
            builder.setCancelable(false)
            builder.setPositiveButton("확인") { dialog, which -> finish() }
            builder.show()
        }
    }

    inner class Asynctask : AsyncTask<String, Void, String>(){
        var state : Int = -1 //state == 0 : GET_아이디 중복확인, state == 1 : POST_회원가입
        override fun doInBackground(vararg params: String): String {
            var client = OkHttpClient()
            state = Integer.parseInt(params[0])
            var url = params[1]
            var response : String = ""

            when (state){
                0->response = Okhttp().GET(client,url)
                1->{
                    val pw = params[2] //3번째 파라미터 pw : String
                    response = Okhttp().POST(client, url, Json().signup(user,pw))
                }
            }
            return response
        }

        override fun onPostExecute(response: String) {
            if(response.isEmpty()) {
                Log.d("SignUp_Activity", "null")
                return
            }
            Log.d("SignUp_Activity", response)
            if(!Json().isJson(response)){
                Toast.makeText(applicationContext,"네트워크 통신 오류", Toast.LENGTH_SHORT).show()
                Log.d("SignUp_Activity",response)
                return
            }

            val jsonObj = JSONObject(response)
            when (state){
                0->{
                    if(jsonObj.getBoolean("success")){//중복이 안됨
                        Toast.makeText(applicationContext,"사용가능한 아이디입니다",Toast.LENGTH_SHORT).show()
                        checkId = true
                    }
                    else
                        Toast.makeText(applicationContext, "사용불가능한 아이디입니다",Toast.LENGTH_SHORT).show()
                }
                1->{
                    if(jsonObj.getBoolean("success")){//회원가입 성공
                        //다이얼로그 띄움
                        SignUp_Control().Dialog_Signup()
                    }
                    else{
                        Toast.makeText(applicationContext,"회원가입 실패",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    //버튼 리스너
    fun ButtonListener(view :View){
        when(view.id){
            R.id.signup_idcheck_Button->{
                var id = signup_id_EditText.text.toString()
                if (id.isEmpty())
                    Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                else {
                    SignUp_Control().GET_Check(id)
                }
            }
            R.id.signup_signup_Button->{
                if(!checkId) { //아이디 중복체크 완료여부 체크
                    Toast.makeText(applicationContext,"중복확인이 필요합니다",Toast.LENGTH_SHORT).show()
                    return
                }

                if(SignUp_Control().edit_check()){ //필수 입력 정보 체크
                    val id = signup_id_EditText.text.toString()
                    val name = signup_name_EditText.text.toString()
                    val phone = signup_phone_EditText.text.toString()
                    val email = signup_email_EditText.text.toString()
                    val address = signup_address_EditText.text.toString()
                    val bank_name = signup_bank_name_Spinner.selectedItem.toString()
                    val bank_number = signup_bank_number_EditText.text.toString()
                    val bank_holder = signup_bank_holder_EditText.text.toString()
                    val pw = signup_pw2_EditText.text.toString()
                    user = User_Entity(id,name,phone,email,address,bank_name,bank_number,bank_holder)

                    SignUp_Control().POST_SignUp(pw)
                }
            }
        }
    }

    inner class EditListener : TextWatcher {
        override fun afterTextChanged(s: Editable?) { }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { checkId = false }
    }
}
