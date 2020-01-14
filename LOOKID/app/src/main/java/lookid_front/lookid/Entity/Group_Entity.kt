package lookid_front.lookid.Entity

import java.io.Serializable

data class Group_Entity(
        var g_pid: Int, //그룹 인덱스
        //여기 수정한 부분
        //var child_list: ArrayList<String>, //피보호자 리스트
        var child_list: ArrayList<String>, //피보호자 리스트
        var admin_list: ArrayList<Admin_Entity>, //관리자 리스트
        var name: String //그룹명
) : Serializable{
    constructor() : this(0, arrayListOf<String>(), arrayListOf<Admin_Entity>(), "")
    fun isnull() : Boolean{
        if(child_list.size == 0 || admin_list.size == 0)
            return true
        for(i in 0 until child_list.size){
            //여기 수정한 부분
            //if(child_list[i].isEmpty())
            if(child_list[i].isEmpty())
                return true
        }
        for(i in 0 until admin_list.size){
            if(admin_list[i].id.isEmpty())
                return true
        }
        return false
    }
}