package lookid_front.lookid.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_reslist.*
import lookid_front.lookid.Control.Res_adapter
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.R

class ResList_Activity : AppCompatActivity() {
    lateinit var res_adapter : Res_adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reslist)
        ResList_Control().init_Activity()
    }

    inner class ResList_Control(){
        fun init_Activity(){
            res_adapter = Res_adapter(this@ResList_Activity, arrayListOf(Reservation_Entity("롯데월드","2019-09-29","2019-09-30",1)))
            reslist_res_RecView.adapter = res_adapter
        }
    }
}