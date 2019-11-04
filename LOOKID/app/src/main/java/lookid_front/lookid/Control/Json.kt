package lookid_front.lookid.Control

import com.google.gson.Gson
import lookid_front.lookid.Entity.Group_Entity
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.Entity.User_Entity
import org.json.JSONObject

class Json(){
    var jsonObject = JSONObject()

    fun login(id : String, pw : String) : String{
        jsonObject.put("id",id)
        jsonObject.put("pw",pw)
        return jsonObject.toString()
    }

    fun signup(user : User_Entity?,pw : String) : String{
        jsonObject.put("id", user!!.id)
        jsonObject.put("pw",pw)
        jsonObject.put("name", user!!.name)
        jsonObject.put("phone", user!!.phone)
        jsonObject.put("mail", user!!.email)
        jsonObject.put("address", user!!.address)
        jsonObject.put("bank_name", user!!.bank_name)
        jsonObject.put("bank_num", user!!.bank_number)
        jsonObject.put("bank_holder", user!!.bank_holder)

        return jsonObject.toString()
    }

    fun modify_user(user: User_Entity?) :String{
        jsonObject.put("name", user!!.name)
        jsonObject.put("phone", user!!.phone)
        jsonObject.put("mail", user!!.email)
        jsonObject.put("address", user!!.address)
        jsonObject.put("bank_name", user!!.bank_name)
        jsonObject.put("bank_num", user!!.bank_number)
        jsonObject.put("bank_holder", user!!.bank_holder)

        return jsonObject.toString()
    }

    fun modify_pw(pw : String) : String{
        jsonObject.put("pw",pw)
        return jsonObject.toString()
    }

    fun reservation(reservation_Entity: Reservation_Entity) : String{
        jsonObject = JSONObject(Gson().toJson(reservation_Entity))
        return jsonObject.toString()
    }

    fun isJson(str : String):Boolean{
        if(str[0] != '{' || str[0] != '[')
            return false
        return true
    }
}