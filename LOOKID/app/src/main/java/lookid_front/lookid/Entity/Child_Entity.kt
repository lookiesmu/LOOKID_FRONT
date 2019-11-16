package lookid_front.lookid.Entity

import java.io.Serializable

data class Child_Entity(
        var c_pid: Int, //피보호자 인덱스
        var name: String, //피보호자 이름
        var x: Double, //x좌표
        var y: Double //y좌표
) : Serializable {
    constructor() : this(0,"",0.0,0.0)
}