package lookid_front.lookid.Control

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Okhttp() {
    var context : Context? = null
    constructor(context : Context) : this(){
        this.context = context
    }

    fun GET(client: OkHttpClient, url:String):String{
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

    fun POST(client : OkHttpClient, url: String, jsonbody:String):String{
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

    fun DELETE(client: OkHttpClient, url: String, jsonbody: String):String{
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

        try {
            var builder= Request.Builder()
                    .url(url)
                    .delete(RequestBody.create(MediaType.parse("application/json"), jsonbody!!))

            if(!token.isNullOrEmpty())
                builder.header("Authorization",token)

            var request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun PUT(client: OkHttpClient, url:String, jsonbody: String):String{
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