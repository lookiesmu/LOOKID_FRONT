package lookid_front.lookid.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import lookid_front.lookid.Activity.SignIn_Activity
import lookid_front.lookid.R

class Basic_Dialog(val context: Context, val title : String, val message : String, val postiveListener : DialogInterface.OnClickListener){
    val builder = AlertDialog.Builder(context, R.style.DialogStyle)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인",postiveListener)
            .setIcon(R.drawable.icon_info)
    val dialog = builder.create() as AlertDialog
    fun show(){
        dialog.show()
    }
}