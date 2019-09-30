package lookid_front.lookid.Control

import android.content.Context
import okhttp3.*
import java.io.IOException

class Okhttp() {
    var context : Context? = null
    val client : OkHttpClient = OkHttpClient()
    constructor(context : Context) : this(){
        this.context = context
    }

    fun GET(url: String):String{
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

        try {
            var builder= Request.Builder()
                    .url(url)
                    .get()
            if(!token.isNullOrEmpty())
                builder.header("Authorization",token)

            var request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun POST(url: String, jsonbody: String):String{
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

        try {
            var builder= Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonbody!!))

            if(!token.isNullOrEmpty())
                builder.header("Authorization",token)

            var request = builder.build()
            response = client.newCall(request).execute()

            if(!response.header("Authorization").isNullOrEmpty())
                User_Control(context!!).set_token(response.header("Authorization").toString())

            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun DELETE(url: String):String{
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

        try {
            var builder= Request.Builder()
                    .url(url)
                    .delete()
            if(!token.isNullOrEmpty())
                builder.header("Authorization",token)

            var request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun PUT(url: String, jsonbody: String):String{
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

        try {
            var builder= Request.Builder()
                    .url(url)
                    .put(RequestBody.create(MediaType.parse("application/json"), jsonbody!!))

            if(!token.isNullOrEmpty())
                builder.header("Authorization",token)

            var request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }
}