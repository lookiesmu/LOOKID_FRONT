package lookid_front.lookid.Activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_nav.*
import kotlinx.android.synthetic.main.navigation_main.*
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Control.Res_adapter
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.R
import lookid_front.lookid.R.id.*
import lookid_front.lookid.Service.ResInfo_Service
import org.json.JSONObject

class Main_Activity : AppCompatActivity() {
    lateinit var res_adapter : Res_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Main_Control().init_Activity()
        Main_Control().GET_checkdate()
    }

    inner class Main_Control{
        //액티비티 초기화
        fun init_Activity(){
            val toggle = ActionBarDrawerToggle(
                    this@Main_Activity, main_drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            main_drawer_layout.addDrawerListener(toggle)
            toggle.syncState()
            res_adapter = Res_adapter(this@Main_Activity, arrayListOf(Reservation_Entity("롯데월드","2019-09-29","2019-09-30",1)))
            main_reslist_RecView.adapter = res_adapter
            main_reslist_RecView.layoutManager = LinearLayoutManager(applicationContext)
        }

        //사용자가 예약한 날짜가 오늘인지 확인 후 실행 activity 결정
        fun GET_checkdate(){
            val url = getString(R.string.server_url)+getString(R.string.check_date)
            //asynctask().execute(url)
        }

        fun GET_signout(){
            val url = getString(R.string.server_url) + getString(R.string.sign_out)
        }
    }
    inner class asynctask : AsyncTask<String, Void, String>(){
        var state : Int = -1

        override fun doInBackground(vararg params: String): String {
            state = params[0].toInt()
            var url = params[1]
            var response = Okhttp().GET(url)

            when(state){
                0->{

                }
                1->{

                }
            }

            return response
        }
        override fun onPostExecute(response: String) {
            //넘어온 값이 없을 때 로그 찍고 리턴
            if(response.isNullOrEmpty()) {
                Toast.makeText(applicationContext,"서버 문제 발생", Toast.LENGTH_SHORT).show()
                Log.d("Main_Activity", "null in")
                return
            }
            //response 값이 json문이 아니면 통신 오류 메세지 출력
            if(!Json().isJson(response)){
                Toast.makeText(applicationContext,"네트워크 통신 오류", Toast.LENGTH_SHORT).show()
                Log.d("Main_Activity",response)
                return
            }

            var jsonObj = JSONObject(response)
            if(jsonObj.getInt("rv_pid").toString().isEmpty())
                startActivity(Intent(applicationContext, Map_Activity::class.java).putExtra("rv_pid",jsonObj.getInt("rv_pid")))
        }
    }

    fun main_Click_Listener(view : View){
        when(view.id){
            R.id.main_reservation_Button ->startActivity(Intent(applicationContext, Reservation_Activity::class.java))
            R.id.main_map_Button ->startActivity(Intent(applicationContext, Map_Activity::class.java))
            R.id.main_checkRes_Button ->startService(Intent(applicationContext,ResInfo_Service::class.java))//startActivity(Intent(applicationContext, ResList_Activity::class.java))
            R.id.main_signout_Button ->Main_Control().GET_signout()
        }
    }

    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}