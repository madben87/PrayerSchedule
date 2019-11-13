package com.ben.prayerschedule.data.model

import org.threeten.bp.LocalDate

object DateUtil {

    fun of(year: Int, month: Int, dayOfMonth: Int): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }

    fun now(): LocalDate {
        return LocalDate.now()
    }

    fun getKey(): Int {
        val date = LocalDate.now()
        return (date.monthValue + date.year).hashCode()
    }
}