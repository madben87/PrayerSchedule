package com.ben.prayerschedule.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.ben.prayerschedule.R

class DialogUtil {

    private var context: Context? = null
    private var title: String? = null
    private var message: String? = null

    private var positiveText: Int = 0
    private var negativeText: Int = 0
    private var dialog: AlertDialog? = null

    constructor()

    constructor(context: Context) {
        this.context = context
    }

    constructor(context: Context, title: Int, message: Int) : this(context) {
        this.title = if (title == 0) null else context.getString(title)
        this.message = if (message == 0) null else context.getString(message)
    }

    constructor(context: Context, title: Int, message: Int, posText: Int, negText: Int) : this(
        context,
        title,
        message
    ) {
        positiveText = posText
        negativeText = negText
    }

    constructor(context: Context, title: String?, message: String?) : this(context) {
        this.title = title
        this.message = message
    }

    private fun createBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(context!!, R.style.Theme_AppCompat_Light_Dialog_Alert)
    }

    fun show(callback: DialogPositiveCallback?) {
        Logs.d("$title $message")
        val dialogBuilder = createBuilder()
        dialogBuilder.setCancelable(false).setTitle(title).setMessage(message)
        val onClickListener = DialogInterface.OnClickListener { dialogInterface, which ->
            if (which == DialogInterface.BUTTON_POSITIVE)
                callback?.onAgree()
            dialogInterface.dismiss()
        }
        dialogBuilder.setPositiveButton(
            if (positiveText == 0) android.R.string.ok else positiveText,
            onClickListener
        )
        if (negativeText != 0)
            dialogBuilder.setNegativeButton(negativeText, onClickListener)
        dialog = dialogBuilder.create()
        try {
            if (!(context as Activity).isDestroyed && !dialog?.isShowing!!)
                dialog!!.show()
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }

    }

    fun show(callbackPos: DialogPositiveCallback?, callbackNeg: DialogNegativeCallback?) {
        Logs.d("$title $message")
        val dialogBuilder = createBuilder()
        dialogBuilder.setCancelable(true).setTitle(title).setMessage(message)
        val onClickListenerPos = DialogInterface.OnClickListener { dialogInterface, which ->
            if (which == DialogInterface.BUTTON_POSITIVE)
                callbackPos?.onAgree()
            dialogInterface.dismiss()
        }
        dialogBuilder.setPositiveButton(
            if (positiveText == 0) android.R.string.ok else positiveText,
            onClickListenerPos
        )

        if (negativeText != 0) {
            val onClickListenerNeg = DialogInterface.OnClickListener { dialogInterface, which ->
                if (which == DialogInterface.BUTTON_NEGATIVE)
                    callbackNeg?.onCancel()
                dialogInterface.dismiss()
            }
            dialogBuilder.setNegativeButton(negativeText, onClickListenerNeg)
        }

        dialog = dialogBuilder.create()
        try {
            dialog!!.show()
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }

    }

    fun showCustom(view: View) {
        showCustom(view, true, 0)
    }

    fun showCustom(view: View, isCancelable: Boolean, style: Int) {
        val dialogBuilder = if (style == 0)
            createBuilder()
        else
            AlertDialog.Builder(context!!, style)
        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog!!.setCancelable(isCancelable)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog != null)
            dialog!!.dismiss()
    }

    fun isShowing(): Boolean {
        return if (dialog != null) dialog!!.isShowing else false
    }


    interface DialogNegativeCallback {

        fun onCancel()
    }

    interface DialogPositiveCallback {

        fun onAgree()
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun setContext(context: Context) {
        this.context = context
    }

}