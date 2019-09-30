package lookid_front.lookid.Control

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import lookid_front.lookid.R

class Admin_adapter(val context : Context, val adminlist : ArrayList<Pair<String,Int>>) : RecyclerView.Adapter<Admin_adapter.holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_res_admin,p0,false)
        return holder(view)
    }
    override fun getItemCount(): Int { return adminlist.size }
    override fun onBindViewHolder(p0: holder, p1: Int) { p0.bind(context,adminlist[p1],p1) }

    inner class holder(view : View) : RecyclerView.ViewHolder(view) {
        val admin_TextView = view.findViewById<TextView>(R.id.res_group_admin_TextView)
        fun bind(context: Context, admin : Pair<String,Int>,index : Int) {
            admin_TextView.text = admin.first
        }
    }

    fun add(admin : Pair<String,Int>){
        adminlist.add(admin)
        notifyDataSetChanged()
    }
}