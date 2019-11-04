package lookid_front.lookid.Entity

import java.io.Serializable

data class Admin_Entity(
        var user_pid : Int,
        var id : String,
        var name : String
): Serializable {
}