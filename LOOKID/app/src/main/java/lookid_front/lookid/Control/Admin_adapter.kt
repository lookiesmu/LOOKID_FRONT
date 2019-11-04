package lookid_front.lookid.Control

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
<<<<<<< HEAD
import android.widget.Toast
import lookid_front.lookid.Entity.Admin_Entity
import lookid_front.lookid.R

class Admin_adapter(val context: Context, val adminlist: ArrayList<Admin_Entity>) : RecyclerView.Adapter<Admin_adapter.holder>() {
=======
import lookid_front.lookid.R

class Admin_adapter(val context : Context, val adminlist : ArrayList<Pair<String,Int>>) : RecyclerView.Adapter<Admin_adapter.holder>() {
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_res_admin,p0,false)
        return holder(view)
    }
    override fun getItemCount(): Int { return adminlist.size }
    override fun onBindViewHolder(p0: holder, p1: Int) { p0.bind(context,adminlist[p1],p1) }

    inner class holder(view : View) : RecyclerView.ViewHolder(view) {
        val admin_TextView = view.findViewById<TextView>(R.id.res_group_admin_TextView)
<<<<<<< HEAD
        fun bind(context: Context, admin : Admin_Entity,index : Int) {
            admin_TextView.text = admin.name
        }
    }

    fun add(admin : Admin_Entity){
        if(adminlist.size >= 20){
            Toast.makeText(context,"최대 관리자는 20명 입니다",Toast.LENGTH_SHORT).show()
            return
        }
        if(adminlist.contains(admin)){
            Toast.makeText(context, "이미 존재하는 관리자 입니다",Toast.LENGTH_SHORT).show()
            return
        }
=======
        fun bind(context: Context, admin : Pair<String,Int>,index : Int) {
            admin_TextView.text = admin.first
        }
    }

    fun add(admin : Pair<String,Int>){
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        adminlist.add(admin)
        notifyDataSetChanged()
    }
}