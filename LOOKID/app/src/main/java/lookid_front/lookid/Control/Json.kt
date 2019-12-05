package lookid_front.lookid.Control

import com.google.gson.Gson
import lookid_front.lookid.Entity.Reservation_Entity
import lookid_front.lookid.Entity.User
import org.json.JSONObject

class Json(){
    var jsonObject = JSONObject()

    fun login(id : String, pw : String) : String{
        jsonObject.put("id",id)
        jsonObject.put("pw",pw)
        return jsonObject.toString()
    }

    fun signup(user : User?, pw : String) : String{
        jsonObject.put("id", user!!.id)
        jsonObject.put("pw",pw)
        jsonObject.put("name", user!!.name)
        jsonObject.put("phone", user!!.phone)
        jsonObject.put("mail", user!!.email)
        jsonObject.put("address", user!!.address)
        jsonObject.put("bank_name", user!!.bank_name)
        jsonObject.put("bank_num", user!!.bank_num)
        jsonObject.put("bank_holder", user!!.bank_holder)

        return jsonObject.toString()
    }

    fun modify_user(user: User?) :String{
        jsonObject.put("name", user!!.name)
        jsonObject.put("phone", user!!.phone)
        jsonObject.put("mail", user!!.email)
        jsonObject.put("address", user!!.address)
        jsonObject.put("bank_name", user!!.bank_name)
        jsonObject.put("bank_num", user!!.bank_num)
        jsonObject.put("bank_holder", user!!.bank_holder)

        return jsonObject.toString()
    }

    fun modify_pw(pw : String) : String{
        jsonObject.put("pw",pw)
        return jsonObject.toString()
    }

    fun reservation(reservation_Entity: Reservation_Entity) : String{
        jsonObject = JSONObject(Gson().toJson(reservation_Entity))
        var str = "{\"reservation\":${Gson().toJson(reservation_Entity)}}"
        jsonObject = JSONObject(str)
        var userjson = jsonObject.getJSONObject("reservation").getJSONObject("user")

        jsonObject.getJSONObject("reservation").put("name",userjson.getString("name"))
        jsonObject.getJSONObject("reservation").put("address",userjson.getString("address"))
        jsonObject.getJSONObject("reservation").put("address_detail",userjson.getString("address_detail"))
//        jsonObject.put("address_detail",userjson.getString("address_detail"))
        jsonObject.getJSONObject("reservation").put("bank_holder",userjson.getString("bank_holder"))
        jsonObject.getJSONObject("reservation").put("bank_name",userjson.getString("bank_name"))
        jsonObject.getJSONObject("reservation").put("bank_num",userjson.getString("bank_num"))
        jsonObject.getJSONObject("reservation").put("phone",userjson.getString("phone"))
        jsonObject.getJSONObject("reservation").remove("user")

        str = jsonObject.toString()
        str = str.replace("admin_list","admin")
        str = str.replace("child_list","child")

        return str
    }

    fun isJson(str : String):Boolean{
        if(str[0] != '{' || str[0] != '[')
            return false
        return true
    }
}