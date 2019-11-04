package lookid_front.lookid.Entity

import java.io.Serializable

data class User_Entity(
    var id : String?, //아이디
    var name : String?, //사용자 이름
    var phone : String?, //사용자 번호
    var email : String?, //사용자 이메일
    var address : String?, //사용자 주소
    var bank_name : String?, //은행명
    var bank_number : String?, //계좌번호
    var bank_holder : String? //예금주
) : Serializable{
<<<<<<< HEAD
    var address_detail : String? = null
    constructor() : this("","","","","","","","")
    fun isresnull() : Boolean{
        if(name.isNullOrEmpty() || phone.isNullOrEmpty() || address.isNullOrEmpty() || bank_name.isNullOrEmpty() ||
=======
    constructor() : this("","","","","","","","")
    fun isresnull() : Boolean{
        if(id.isNullOrEmpty() || name.isNullOrEmpty() || phone.isNullOrEmpty() || address.isNullOrEmpty() || bank_name.isNullOrEmpty() ||
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                bank_number.isNullOrEmpty() || bank_holder.isNullOrEmpty())
            return true
        return false
    }

<<<<<<< HEAD
    fun bank_toString() : String{ return "${bank_name} ${bank_number}\n예금주명 : ${bank_holder}"}
=======
    fun bank_toString() : String{ return "${bank_name} ${bank_number} 예금주명 : ${bank_holder}"}
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
}

