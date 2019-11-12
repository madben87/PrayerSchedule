package com.ben.prayerschedule.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, tag: String, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment).addToBackStack(tag)
    }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, container: Int) {
    supportFragmentManager.transact {
        add(container, fragment)
    }
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String, container: Int) {
    supportFragmentManager.transact {
        add(container, fragment, tag)
    }
}

fun AppCompatActivity.addFragmentToActivityBackStack(
    fragment: Fragment,
    tag: String,
    container: Int
) {
    supportFragmentManager.transact {
        add(container, fragment, tag).addToBackStack(tag)
    }
}

fun AppCompatActivity.changeFragmentInActivity(fragment: Fragment, tag: String, container: Int) {
    supportFragmentManager.transact {
        add(container, fragment, tag).addToBackStack(tag)
    }
}


fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this).get(viewModelClass)

fun <T : ViewDataBinding> AppCompatActivity.getDataBinding(res: Int): T {
    return DataBindingUtil.setContentView(this, res)
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

/*
fun AppCompatActivity.userIsValid(): Boolean {
    if (RealmManager.getInstance().getUser(UserPrefs.userId)?.handBasedVerificationStatus == Constants.NON_VERIFIED) {
        var dialog = DialogFactory(
            DialogFactoryType.ERROR,
            this
        ).setInfo(resources.getString(R.string.Guest_nonVerifiedError),
            this.resources.getText(com.example.hologo.R.string.Global_close).toString())

        dialog.setOnDismissListener {
            if (UserPrefs.authToken != "") {
                val navHostFragment =
                    this.supportFragmentManager.findFragmentById(R.id.nav_main_fragment)
                val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

                for (i in 1..backStackEntryCount!!) {
                    this.onBackPressed()
                }
            }
        }

        dialog.show()
        return false
    }else if (RealmManager.getInstance().getUser(UserPrefs.userId)?.handBasedVerificationStatus == Constants.REJECTED) {
        var dialog = DialogFactory(
            DialogFactoryType.ERROR,
            this
        ).setInfo(resources.getString(R.string.Guest_rejectedError),
            this.resources.getText(com.example.hologo.R.string.Global_close).toString())

        dialog.setOnDismissListener {
            if (UserPrefs.authToken != "") {
                val navHostFragment =
                    this.supportFragmentManager.findFragmentById(R.id.nav_main_fragment)
                val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount

                for (i in 1..backStackEntryCount!!) {
                    this.onBackPressed()
                }
            }
        }

        dialog.show()
        return false
    }
    return true
}*/
