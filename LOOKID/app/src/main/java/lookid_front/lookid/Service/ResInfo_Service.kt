package lookid_front.lookid.Service

import android.app.Service
<<<<<<< HEAD
import android.content.Context
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import android.util.Log
import android.widget.Toast
<<<<<<< HEAD
import lookid_front.lookid.Activity.*
=======
import lookid_front.lookid.Activity.ResInfo_Activity
import lookid_front.lookid.Activity.ReservationLast_Activity
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
import lookid_front.lookid.Control.Json
import lookid_front.lookid.Control.Okhttp
import lookid_front.lookid.Dialog.Loading_Dialog
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.R
import org.json.JSONObject

<<<<<<< HEAD
class ResInfo_Service : Service() {
    var res = Reservation_Entity()

    override fun onBind(intent: Intent): IBinder? {
        Log.d("ResInfo_Service","onBind")

=======
class ResInfo_Service(var resindex : Int) : Service() {
    var res = Reservation_Entity()
    var loadingDialog = Loading_Dialog(this)

    override fun onBind(intent: Intent): IBinder? {
        Log.d("ResInfo_Service","onBind")
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        return null
    }

    override fun onCreate() {
        Log.d("ResInfo_Service","onCreate")
<<<<<<< HEAD
        res.state = 2
        val intent = Intent(applicationContext,ResList_Activity::class.java)
        intent.putExtra("res",res)
        startActivity(intent)
        /*loadingDialog.show()
        var response : String = ""
        val url : String = getString(R.string.server_url) + getString(R.string.selete_res_detail)
        url.replace("{rv_pid}",index.toString())
=======
        loadingDialog.show()
        var response : String = ""
        val url : String = getString(R.string.server_url) + getString(R.string.selete_res_detail)
        url.replace("{rv_pid}",resindex.toString())
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        response = Okhttp(applicationContext).GET(url)
        if(!Json().isJson(response)){
            Toast.makeText(applicationContext,"통신 오류 발생",Toast.LENGTH_SHORT).show()
        }
        else{
            val json = JSONObject(response)
            //가공 및 res에 넣기

            val intent = Intent(applicationContext, ResInfo_Activity::class.java)
            intent.putExtra("res", res)
            startActivity(intent)
        }
<<<<<<< HEAD
        loadingDialog.dismiss()*/
=======
        loadingDialog.dismiss()
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        stopSelf()
    }

    override fun onDestroy() {
        Log.d("ResInfo_Service","onDestroy")
    }

    inner class asynctask : AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {
            var response : String = ""


            return response
        }

        override fun onPostExecute(result: String?) {
            stopSelf()
        }
    }
}
