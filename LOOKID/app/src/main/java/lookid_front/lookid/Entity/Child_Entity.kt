package lookid_front.lookid.Entity

data class Child_Entity(
<<<<<<< HEAD
        var child_pid: Int, //피보호자 인덱스
        var name: String, //피보호자 이름
        var x: Double, //x좌표
        var y: Double //y좌표
=======
        var childindex : Int, //피보호자 인덱스
        var name : String, //피보호자 이름
        var x : Double, //x좌표
        var y : Double //y좌표
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
){
    constructor() : this(0,"",0.0,0.0)
}