package com.ben.prayerschedule.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ben.prayerschedule.R
//import com.ben.prayerschedule.realm.RealmManager
import com.ben.prayerschedule.ui.ProgressActivity
import com.ben.prayerschedule.util.DialogFactory
import com.ben.prayerschedule.util.DialogFactoryType

fun Fragment.showProgressDialog() {
    if (activity != null && activity is ProgressActivity)
        (activity as ProgressActivity).getProgressBar().visibility = View.VISIBLE
}

fun Fragment.dismissDialogs() {
    if (activity != null && activity is ProgressActivity)
        (activity as ProgressActivity).getProgressBar().visibility = View.GONE
}

fun Fragment.showError(errorText: String) {
    if (activity != null && activity is AppCompatActivity) {
        DialogFactory(DialogFactoryType.ERROR, activity as AppCompatActivity).setInfo(
            errorText,
            (activity as AppCompatActivity).getText(R.string.Global_close).toString()
        ).show()
    }
}

/*fun Fragment.showErrorUnAuthorised(message: String) {
    var dialog = DialogFactory(
        DialogFactoryType.ERROR,
        (activity as AppCompatActivity)
    ).setInfo(
        message,
        (activity as AppCompatActivity).resources.getText(com.example.hologo.R.string.Global_signUp).toString(),
        (activity as AppCompatActivity).resources.getText(com.example.hologo.R.string.Global_close).toString()

    )
    dialog.buttonFirst.setOnClickListener {
        UserPrefs.authToken = ""
        UserPrefs.userId = 0
        RealmManager.getInstance().delete(User::class.java)
        dialog.dismiss()
        view?.findNavController()?.navigate(com.example.hologo.R.id.action_global_signOut)
        activity?.finish()
    }

    dialog.buttonSecond.setOnClickListener {
        dialog.dismiss()
    }

    dialog.setOnDismissListener {
        if (UserPrefs.authToken != "") {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_main_fragment)
            val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

            for (i in 1..backStackEntryCount!!) {
                activity?.onBackPressed()
            }
        }
    }

    dialog.show()
}*/

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this).get(viewModelClass)