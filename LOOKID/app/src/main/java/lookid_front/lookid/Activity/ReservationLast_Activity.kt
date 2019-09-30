package lookid_front.lookid.Activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_reservation_last.*
import lookid_front.lookid.Control.Group_adapter_ResLast
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.R
import org.json.JSONObject
import java.text.DecimalFormat

class ReservationLast_Activity : AppCompatActivity(){
    lateinit var reservation_Entity : Reservation_Entity
    var devicenum : Int = 0
    lateinit var group_Adapter : Group_adapter_ResLast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_last)
        ResLast_Control().res_init()
    }

    inner class ResLast_Control(){
        //page 초기화
        fun res_init(){
            val intent = getIntent()
            reservation_Entity = intent.getSerializableExtra("res") as Reservation_Entity
            devicenum = intent.getIntExtra("res_devicenum",0)

            //결제 정보 초기화
            reslast_resname_TextView.text = reservation_Entity.resname
            reslast_name_TextView.text = reservation_Entity.user.name
            reslast_phone_TextView.text = reservation_Entity.user.phone
            reslast_bank_TextView.text = reservation_Entity.user.bank_toString()
            reslast_startdate_TextView.text = reservation_Entity.s_date
            reslast_enddate_TextView.text = reservation_Entity.e_date
            reslast_address_TextView.text = reservation_Entity.user.address
            reslast_devicenum_TextView.text = devicenum.toString()
            val payformat = DecimalFormat("###,###")
            reslast_price_TextView.text = payformat.format(reservation_Entity.cost + reservation_Entity.deposit)
            if(reservation_Entity.receipt_item == 0)
                reslast_rec_TextView.text = "택배"
            else
                reslast_rec_TextView.text = "방문"
            if(reservation_Entity.return_item == 0)
                reslast_ret_TextView.text = "택배"
            else
                reslast_ret_TextView.text = "방문"

            //그룹 리사이클러뷰 초기화
            group_Adapter = Group_adapter_ResLast(this@ReservationLast_Activity,reservation_Entity.group_list)
            reslast_grouplist_RecView.adapter = group_Adapter
            reslast_grouplist_RecView.layoutManager = LinearLayoutManager(applicationContext)
            reslast_grouplist_RecView.setItemViewCacheSize(100)
        }
    }
    inner class asynctask : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String): String {
            //POST_예약하기 (url, jsonStr)
            val url = params[0]
            return Okhttp(applicationContext).POST(url,params[1])
        }

        override fun onPostExecute(response: String) {
            if(response.isEmpty()) {
                Log.d("ResLast_Activity", "null")
                return
            }
            if(!Json().isJson(response)){
                Toast.makeText(applicationContext,"네트워크 통신 오류", Toast.LENGTH_SHORT).show()
                Log.d("ResLast_Activity", response)
                return
            }
            val json  = JSONObject(response)
            val success = json.getInt("success")
            if(success == 1){
                Toast.makeText(applicationContext,"예약 성공", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
                Toast.makeText(applicationContext,"예약 실패", Toast.LENGTH_SHORT).show()
        }
    }

    fun reslast_ClickListener(view : View){
        when(view.id){
            R.id.reslast_cancel_Button ->{
                val intent = Intent(applicationContext, Main_Activity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            R.id.reslast_payment_Button ->{
                //서버에 포스트로 결제정보 보내기
                val intent = Intent(applicationContext, Main_Activity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                Log.d("ResLast_Activity",Gson().toJson(reservation_Entity))
                startActivity(intent)
                finish()
            }
        }
    }
}