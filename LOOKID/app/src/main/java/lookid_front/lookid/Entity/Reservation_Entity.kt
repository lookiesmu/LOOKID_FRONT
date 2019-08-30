package lookid_front.lookid.Entity

import java.util.*
import kotlin.collections.ArrayList

data class Reservation_Entity(
        var resindex : Int,
        var resname : String,
        var rdate : Date,
        var sdate : Date,
        var edate : Date,
        var receipt : Boolean,
        var return_item : Boolean,
        var cost : Int,
        var deposit : Int,
        var state : Int,
        var gruop_list : ArrayList<Group_Entity>,
        var user : User_Entity
){
    fun add_group (group : Group_Entity) {gruop_list.add(group)}
}
