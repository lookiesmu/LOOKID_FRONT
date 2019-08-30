package lookid_front.lookid.Control

import lookid_front.lookid.Entity.User_Entity
import org.json.JSONObject

class Json(){
    fun login(id : String, pw : String) : String{
        var jsonObject = JSONObject()
        jsonObject.put("id",id)
        jsonObject.put("pw",pw)
        return jsonObject.toString()
    }

    fun signup(user : User_Entity?,pw : String) : String{
        var jsonObject = JSONObject()
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

    fun isJson(str : String):Boolean{
        if(str[0] != '{' || str[0] != '[')
            return false
        return true
    }
}