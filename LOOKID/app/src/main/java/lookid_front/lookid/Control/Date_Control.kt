package lookid_front.lookid.Control

class Date_Control(){
<<<<<<< HEAD
    val dateFormat : String = "yyyy-MM-dd"
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    fun toDateformat(year : Int, month : Int, day : Int) : String{
        var str : String = ""
        str += "$year-"
        if(month.toString().length == 1)
            str += '0'
        str += "$month-"
        if(day.toString().length == 1)
            str += '0'
        str += "$day"
        return str
    }
}