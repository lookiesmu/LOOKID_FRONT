package lookid_front.lookid.Entity

data class Group_Entity(
        var name : String,
        var child_list : ArrayList<String>,
        var admin_list : ArrayList<String>
){
    fun add_child (child : String) {child_list.add(child)}
    fun add_admin(admin : String) {admin_list.add(admin)}
}