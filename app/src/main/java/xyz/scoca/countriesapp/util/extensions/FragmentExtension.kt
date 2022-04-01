package xyz.scoca.countriesapp.util.extensions

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_LONG) {
    message?.let {
        Toast.makeText(requireContext(), it, duration).show()
    }
}
fun Fragment.snack(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG,func : ()->Unit) {
    Snackbar.make(view,message,duration).setAction("TRY AGAIN"){
        func()
    }.show()
}
