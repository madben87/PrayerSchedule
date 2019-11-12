package com.ben.prayerschedule.util.string

/**
 * Created by bpluim on 3/8/18.
 */
object StringValidations {
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }
}