package lookid_front.lookid.Control

import android.content.Context
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class Okhttp() {
    var context : Context? = null
    val client : OkHttpClient = OkHttpClient()
<<<<<<< HEAD
    lateinit var response: Response
    var token : String? = null
    init {
        client.newBuilder()
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build()
    }
=======
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    constructor(context : Context) : this(){
        this.context = context
        token = User_Control(context).get_token()
    }

    fun GET(url: String):String{
<<<<<<< HEAD
=======
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        try {
            val builder= Request.Builder()
                    .url(url)
                    .get()
            if(!token.isNullOrEmpty())
                builder.header("Authorization",token!!)

            val request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun POST(url: String, jsonbody: String):String{
<<<<<<< HEAD
=======
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        try {
            val builder= Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonbody))

            if(!token.isNullOrEmpty())
                builder.header("Authorization",token!!)

            val request = builder.build()
            response = client.newCall(request).execute()

            if(!response.header("Authorization").isNullOrEmpty())
                User_Control(context!!).set_token(response.header("Authorization").toString())

            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun DELETE(url: String):String{
<<<<<<< HEAD
=======
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        try {
            val builder= Request.Builder()
                    .url(url)
                    .delete()
            if(!token.isNullOrEmpty())
                builder.header("Authorization",token!!)

            val request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }

    fun PUT(url: String, jsonbody: String):String{
<<<<<<< HEAD
=======
        var response: Response
        var token : String? = null

        if(context != null)
            token = User_Control(context!!).get_token()

>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
        try {
            val builder= Request.Builder()
                    .url(url)
                    .put(RequestBody.create(MediaType.parse("application/json"), jsonbody))
            if(!token.isNullOrEmpty())
                builder.header("Authorization",token!!)

            val request = builder.build()
            response = client.newCall(request).execute()
            return response.body()?.string()!!
        }catch (e: IOException){
            return e.toString()
        }
    }
}