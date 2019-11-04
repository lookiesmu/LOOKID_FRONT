package lookid_front.lookid.Entity

import java.io.Serializable

data class Group_Entity(
<<<<<<< HEAD
        var g_pid: Int, //그룹 인덱스
        var child_list: ArrayList<String>, //피보호자 리스트
        var admin_list: ArrayList<Admin_Entity>, //관리자 리스트
        var name: String //그룹명
) : Serializable{
    constructor() : this(0, arrayListOf<String>(), arrayListOf<Admin_Entity>(), "")
=======
        var index: Int, //그룹 인덱스
        var child_list: ArrayList<String>, //피보호자 리스트
        var admin_list: ArrayList<Pair<String, Int>>, //관리자 리스트
        var name: String //그룹명
) : Serializable{
    constructor() : this(0, arrayListOf<String>(), arrayListOf<Pair<String,Int>>(), "")
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    fun isnull() : Boolean{
        if(child_list.size == 0 || admin_list.size == 0)
            return true
        for(i in 0 until child_list.size){
            if(child_list[i].isEmpty())
                return true
        }
        for(i in 0 until admin_list.size){
<<<<<<< HEAD
            if(admin_list[i].id.isEmpty())
=======
            if(admin_list[i].first.isEmpty())
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                return true
        }
        return false
    }
}