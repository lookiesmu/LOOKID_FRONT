package lookid_front.lookid.Entity

data class Child_Entity(
        var child_pid: Int, //피보호자 인덱스
        var name: String, //피보호자 이름
        var x: Double, //x좌표
        var y: Double, //y좌표
        var isMissing: Boolean //미아 여부 -> 수정한 부분 (****물어봐야함****)
){
    constructor() : this(0,"",0.0,0.0,false)
}
