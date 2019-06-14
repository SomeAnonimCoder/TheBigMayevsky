package msu.ug

import android.content.Context
import android.content.SharedPreferences

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences("MY_SP", Context.MODE_PRIVATE)
    var currentChoise : Int
        set(value) {
            sp.edit().putInt("current_choise", value).apply()
        }
        get() {
            return sp.getInt("current_choise", 1)
        }
}