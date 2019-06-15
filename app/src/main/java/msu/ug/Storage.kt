package msu.ug

import android.content.Context
import android.content.SharedPreferences

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences("MY_SP", Context.MODE_PRIVATE)
    private val switchListeners : ArrayList<() -> Unit> = ArrayList()

    fun addSwitchListener(listener : () -> Unit) {
        switchListeners.add(listener)
    }

    var currentChoise : Int
        set(value) {
            if (value > 0) {
                sp.edit().putInt("current_choise", value).apply()
                switchListeners.forEach {
                    it.invoke()
                }
            }
        }
        get() {
            return sp.getInt("current_choise", 1)
        }
}