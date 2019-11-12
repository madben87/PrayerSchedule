package com.ben.prayerschedule.util

/**
 * Created by clarksandholtz on 4/6/18.
 */
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.text.TextUtils

object PermissionUtils {

    const val GET_FROM_LIBRARY = 72
    const val GALLERY_REQUEST_CODE = 148

    const val LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
    const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION


//    /**
//     * Extension method on [Context] to check for permissions
//     */
//    @JvmStatic
//    fun Context.hasPermissions(vararg permissions: String): Boolean {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            return permissions
//                    .map { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
//                    .all { it }
//        }
//        return true
//    }

    /**
     * Checks to see if we have the necessary permissions.
     * @param activity A context in the form of an activity
     * @param permissions A string of permissions (we have hard coded values in [PermissionUtils])
     * @return a boolean telling if the user has the necessary permissions
     */
    @JvmStatic
    fun hasPermissions(activity: Context, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return permissions
                .map { activity.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
                .all { it }
        }

        return true
    }

    fun isConnected(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    // Helper to make an array
    @JvmStatic
    fun makeArray(vararg items: String): Array<String> {
        return items as Array<String>
    }

    /**
     * Whether or not all of the permissions were granted.
     * @param grantResults the array returned from [Activity.onRequestPermissionsResult]
     * @return the result telling if all permissions were granted
     */
    @JvmStatic
    fun allPermissionsGrantedResultSummary(grantResults: IntArray): Boolean =
        grantResults.indices.none { grantResults[it] == PackageManager.PERMISSION_DENIED }

    @JvmStatic
    fun permissionGranted(
        permissions: Array<String>,
        grantResults: IntArray,
        permission: String
    ): Boolean {
        var permissionsGranted = false

        if (permissions.size == grantResults.size && !TextUtils.isEmpty(permission)) {
            permissions.indices
                .filter { permissions[it] == permission && grantResults[it] == PackageManager.PERMISSION_GRANTED }
                .forEach { permissionsGranted = true }
        }

        return permissionsGranted
    }
}