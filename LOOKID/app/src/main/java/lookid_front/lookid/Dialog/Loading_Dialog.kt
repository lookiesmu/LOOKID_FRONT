package lookid_front.lookid.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import lookid_front.lookid.R

<<<<<<< HEAD
class Loading_Dialog(context: Context) : Dialog(context) {
=======
class Loading_Dialog(context: Context?) : Dialog(context) {
>>>>>>> d285b8a4e1fbb9783320514a6335f9c216ea90e9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        setContentView(R.layout.dialog_loading)
    }
}