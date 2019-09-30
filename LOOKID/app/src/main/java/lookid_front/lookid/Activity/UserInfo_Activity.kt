package lookid_front.lookid.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_userinfo.*
import lookid_front.lookid.Dialog.Loading_Dialog
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Control.User_Control
import lookid_front.lookid.Entity.User_Entity
import lookid_front.lookid.R
import org.json.JSONObject

class UserInfo_Activity : AppCompatActivity() {
    var editmode : Boolean = false
    var bank_list = arrayOf("")
    var user_upload : User_Entity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userinfo)

        //UserText 초기화
        UserInfo_Control().user_init()
    }

    inner class UserInfo_Control{
        fun user_init(){
            //스피너 초기화
            bank_list = resources.getStringArray(R.array.bank_list)
            val bank_name_spinner_adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,bank_list)
            bank_name_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            signup_bank_name_Spinner.adapter = bank_name_spinner_adapter

            editmode = false
            val editary = arrayListOf<Int>(R.id.userinfo_name_EditText, R.id.userinfo_phone_EditText, R.id.userinfo_email_EditText,
                    R.id.userinfo_address_EditText, R.id.userinfo_bank_number_EditText, R.id.userinfo_bank_holder_EditText)
            var user : User_Entity = User_Control(applicationContext).get_user()

            for(i in 0 until editary.size){
                var editText = findViewById<EditText>(editary[i])
                editText.isEnabled = false
            }
            userinfo_id_EditText.hint = user.id
            userinfo_name_EditText.hint = user.name
            userinfo_phone_EditText.hint = user.phone
            userinfo_email_EditText.hint = user.email
            userinfo_address_EditText.hint = user.address
            userinfo_bank_name_Spinner.setSelection(bank_list.indexOf(user.bank_name))
            userinfo_bank_number_EditText.hint = user.bank_number
            userinfo_bank_holder_EditText.hint = user.bank_holder
        }

        fun user_modify(){
            editmode = true
            val editary = arrayListOf<Int>(R.id.userinfo_name_EditText, R.id.userinfo_phone_EditText, R.id.userinfo_email_EditText,
                    R.id.userinfo_address_EditText, R.id.userinfo_bank_number_EditText, R.id.userinfo_bank_holder_EditText)
            for(i in 0 until editary.size){
                var editText = findViewById<EditText>(editary[i])
                editText.isEnabled = true
            }
        }

        fun user_modfiy_go(){
            if(edit_check()){
                //0. User init
                val user : User_Entity = User_Control(applicationContext).get_user()
                user.name = userinfo_name_EditText.text.toString()
                user.phone = userinfo_phone_EditText.text.toString()
                user.email = userinfo_email_EditText.text.toString()
                user.address = userinfo_address_EditText.text.toString()
                user.bank_name = userinfo_bank_name_Spinner.selectedItem.toString()
                user.bank_number = userinfo_bank_number_EditText.text.toString()
                user.bank_holder = userinfo_bank_holder_EditText.text.toString()
                //1. User_Control update
                User_Control(applicationContext).set_user(user)

                //2. server update
                PUT_user_modify(user)

                user_init()
            }
        }

        fun edit_check() : Boolean{
            val editary = arrayListOf<Int>(R.id.userinfo_name_EditText, R.id.userinfo_phone_EditText, R.id.userinfo_email_EditText)
            for(i in 0 until editary.size){
                var editText = findViewById<EditText>(editary[i])
                if(editText.text.isNullOrBlank()){
                    Toast.makeText(applicationContext,"필수 정보를 입력해주세요",Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            return true
        }

        fun Dialog_pw_change(){
            var builder = AlertDialog.Builder(this@UserInfo_Activity)
            builder.setTitle("비밀번호 변경")
            var view = layoutInflater.inflate(R.layout.dialog_userinfo_pwchange,null)
            builder.setView(view)
            builder.setPositiveButton("확인",null)
            builder.setNegativeButton("취소",null)
            var dialog = builder.create()
            dialog.setOnShowListener(Dialog_Listener())
        }

        fun Dialog_make_sure(){
            var builder = AlertDialog.Builder(this@UserInfo_Activity)
            builder.setMessage("정말로 정보를 수정하시겠습니까?")
            builder.setPositiveButton("변경") { dialog, which ->
                UserInfo_Control().user_modfiy_go()
            }
            builder.setNegativeButton("취소",null)
            builder.show()
        }

        fun PUT_user_modify(user : User_Entity){
            val url = getString(R.string.server_url)
            user_upload = user
            Asynctask().execute("0",url)
        }

        fun PUT_pw_modify(pw : String){
            val url = getString(R.string.server_url)
            Asynctask().execute("1",url,pw)
        }
    }

    inner class Asynctask : AsyncTask<String, Void, String>(){
        var state : Int = -1 //state == 0 : PUT_사용자 정보 변경, state == 1 : POST_사용자 비밀번호 변경
        var loadingDialog = Loading_Dialog(this@UserInfo_Activity)
        override fun onPreExecute() {
            loadingDialog.show()
        }

        override fun doInBackground(vararg params: String): String {
            state = Integer.parseInt(params[0])
            var url = params[1]
            var response : String = ""

            when (state){
                0->response = Okhttp(applicationContext).PUT(url, Json().modify_user(user_upload))
                1-> {
                    val pw = params[2]
                    response = Okhttp(applicationContext).PUT(url, Json().modify_pw(pw))
                }
            }
            return response
        }

        override fun onPostExecute(response: String) {
            if(response.isEmpty()) {
                Log.d("UserInfo_Activity", "null")
                loadingDialog.dismiss()
                return
            }
            if(!Json().isJson(response)){
                Toast.makeText(applicationContext,"네트워크 통신 오류", Toast.LENGTH_SHORT).show()
                Log.d("UserInfo_Activity",response)
                loadingDialog.dismiss()
                return
            }

            val jsonObj = JSONObject(response)
            when (state){
                0->{
                    if(jsonObj.getBoolean("success")) {
                        Toast.makeText(applicationContext, "정보가 변경되었습니다", Toast.LENGTH_LONG).show()
                        UserInfo_Control().user_init()
                    }
                    else Toast.makeText(applicationContext,"사용자 정보 변경 실패",Toast.LENGTH_LONG).show()
                }
                1->{
                    if(jsonObj.getBoolean("success")) {
                        Toast.makeText(applicationContext, "정보가 변경되었습니다", Toast.LENGTH_LONG).show()
                    }
                    else Toast.makeText(applicationContext,"사용자 정보 변경 실패",Toast.LENGTH_LONG).show()
                }
            }
            loadingDialog.dismiss()
        }
    }

    //Activity 클릭 리스너
    fun userinfo_Click_Listener(view : View){
        when (view.id){
            R.id.userinfo_modify_Button ->{
                if(editmode)
                    UserInfo_Control().user_modify()
                else
                    UserInfo_Control().Dialog_make_sure()
            }
            R.id.userinfo_pwchange_Button ->{
                UserInfo_Control().Dialog_pw_change()
            }
        }
    }

    inner class Dialog_Listener : DialogInterface.OnShowListener{
        override fun onShow(dialog: DialogInterface?) {
            var alert = dialog as AlertDialog
            var positiveButton : Button = alert.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                var pw1 = alert.findViewById<EditText>(R.id.userinfo_d_pc1_EditText).text.toString()
                var pw2 = alert.findViewById<EditText>(R.id.userinfo_d_pc2_EditText).text.toString()
                if(checkpw(pw1,pw2)){
                    UserInfo_Control().PUT_pw_modify(pw1)
                    alert.dismiss()
                }
            }
        }

        fun checkpw(pw1 : String, pw2 : String) : Boolean{
            if(pw1.isEmpty() || pw2.isEmpty()) {
                Toast.makeText(applicationContext,"비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
                return false
            }
            if(!pw1.equals(pw2)){
                Toast.makeText(applicationContext,"1차 2차 비밀번호가 다릅니다",Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}