package lookid_front.lookid.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.EditText
import android.widget.TextView
import lookid_front.lookid.R
<<<<<<< HEAD
import lookid_front.lookid.R.id.text
import lookid_front.lookid.R.id.textView

class Address_Dialog(context: Context) : Dialog(context) {
    var textView : TextView? = null
    var editText : EditText? = null
    constructor(context: Context, textView : TextView) : this(context){
        this.textView = textView
    }
    constructor(context: Context, editText: EditText) : this(context){
        this.editText = editText
    }
=======
import lookid_front.lookid.R.id.textView

class Address_Dialog(context: Context, var editText: EditText) : Dialog(context) {
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    var arg : String? = null
    var handler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_address)

        //url 장착 후 동작 해야됨
        //init_Activity()

        handler = Handler()
    }

    fun init_Activity(){
        val webView : WebView = findViewById(R.id.address_dialog_WebView)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.addJavascriptInterface(address_Interface(), "LOOKID")
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("")
    }

    fun getAddress() : String? { return arg }

    inner class address_Interface{
        @android.webkit.JavascriptInterface
        fun setAddress(arg1 : String, arg2 : String, arg3 : String){
            handler!!.post(Runnable {
<<<<<<< HEAD
                arg = "(${arg1}) ${arg2} ${arg3}"
                if(editText != null)
                    editText!!.setText(arg)
                else
                    textView!!.setText(arg)
=======
                arg = "${arg1} ${arg2} ${arg3}"
                editText.setText(arg)
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
                init_Activity()
                dismiss()
            })
        }
    }
}