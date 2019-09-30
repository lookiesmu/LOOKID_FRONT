package lookid_front.lookid.Control

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import lookid_front.lookid.Activity.ResInfo_Activity
import lookid_front.lookid.Activity.ReservationLast_Activity
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.R
import lookid_front.lookid.R.id.view

class Res_adapter(val context: Context, val resList : ArrayList<Reservation_Entity>) : RecyclerView.Adapter<Res_adapter.holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_res,p0,false)
        return holder(view)
    }
    override fun getItemCount(): Int { return resList.size }
    override fun onBindViewHolder(p0: holder, p1: Int) { p0.bind(resList[p1],p1) }

    inner class holder(view : View) : RecyclerView.ViewHolder(view){
        val res_View = view.findViewById<LinearLayout>(R.id.res_view)
        val resname_TextView = view.findViewById<TextView>(R.id.res_resname)
        val ressdate_TextView = view.findViewById<TextView>(R.id.res_sdate)
        val resedate_TextView = view.findViewById<TextView>(R.id.res_edate)
        val resstate_TextView = view.findViewById<TextView>(R.id.res_state)
        fun bind(res : Reservation_Entity, index : Int){
            resname_TextView.text = res.resname
            ressdate_TextView.text = res.s_date
            resedate_TextView.text = "~ ${res.e_date}"
            when(res.state){ //(1 입금 대기 2 예약 완료 3 배송 중 4 수령 완료 5 반납 대기 6 반납 완료 7 취소대기 8 환불 완료)
                1->resstate_TextView.text = "입금 대기"
                2->resstate_TextView.text = "예약 완료"
                3->resstate_TextView.text = "배송 중"
                4->resstate_TextView.text = "수령 완료"
                5->resstate_TextView.text = "반납 대기"
                6->resstate_TextView.text = "반납 완료"
                7->resstate_TextView.text = "취소 신청"
                8->resstate_TextView.text = "환불 완료"
            }
            res_View.setOnClickListener {
                val intent = Intent(context, ResInfo_Activity::class.java)
                intent.putExtra("res_index", res.resindex)
                context.startActivity(intent)
            }
        }
    }
}