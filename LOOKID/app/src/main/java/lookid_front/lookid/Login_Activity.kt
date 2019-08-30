package lookid_front.lookid

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Control.User_Control
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Entity.User_Entity
import okhttp3.OkHttpClient
import org.json.JSONObject

class Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //자동 로그인 상태 초기화
        login_auto_CheckBox.isChecked = User_Control(applicationContext).get_auto_login()
        //자동 로그인 확인 및 페이지 이동
        if(User_Control(applicationContext).get_auto_login() && !User_Control(applicationContext).get_token().isNullOrEmpty()) {
            startActivity(Intent(applicationContext, Main_Activity::class.java))
            finish()
        }
    }

    inner class Login_Control {
        fun edit_check() : Boolean{
            if(login_id_EditText.text.isNullOrEmpty() && login_pw_EditText.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (login_id_EditText.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "아이디를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            if (login_pw_EditText.text.isNullOrEmpty()){
                Toast.makeText(applicationContext, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                return false
            }
            return true
        }

        fun POST_login(id : String, pw : String){
            val url = getString(R.string.server_url) + getString(R.string.sign_in)
            Asynctask().execute(url,id,pw)
        }
    }

    inner class Asynctask : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String): String {
            var client = OkHttpClient()
            var url = params[0]

            var id = params[1]
            var pw = params[2]

            return Okhttp().POST(client,url, Json().login(id,pw))
        }
        override fun onPostExecute(response: String) {
            //넘어온 값이 없을 때 로그 찍고 리턴
            if(response.isNullOrEmpty()) {
                Toast.makeText(applicationContext,"서버 문제 발생",Toast.LENGTH_SHORT).show()
                Log.d("Login_Activity", "null in")
                return
            }
            //response 값이 json문이 아니면 통신 오류 메세지 출력
            if(!Json().isJson(response)){
                Toast.makeText(applicationContext,"네트워크 통신 오류",Toast.LENGTH_SHORT).show()
                Log.d("Login_Activity",response)
                return
            }

            var jsonObj = JSONObject(response)
            var user = User_Entity(
                    login_id_EditText.text.toString(),
                    jsonObj.getString("name"),
                    jsonObj.getString("phone"),
                    jsonObj.getString("email"),
                    jsonObj.getString("address"),
                    jsonObj.getString("bank_name"),
                    jsonObj.getString("bank_num"),
                    jsonObj.getString("bank_holder")
                    )
            User_Control(applicationContext).set_user(user)
            startActivity(Intent(applicationContext, Main_Activity::class.java))
            finish()
        }
    }

    //클릭리스너
    fun ClickListener(view :View){
        when(view.id){
            R.id.login_login_Button->{
                if(Login_Control().edit_check()) {
                    Login_Control().POST_login(login_id_EditText.text.toString(), login_pw_EditText.text.toString())
                }
            }
            R.id.login_signup_Button->startActivity(Intent(applicationContext, SignUp_Activity::class.java))
            R.id.login_find_info_TextView->startActivity(Intent(applicationContext,FindInfo_Activity::class.java))
            R.id.login_auto_CheckBox-> { User_Control(applicationContext).set_auto_login(login_auto_CheckBox.isChecked) }
        }
    }
}

