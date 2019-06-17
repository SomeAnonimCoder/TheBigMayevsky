package msu.ug

import android.content.Context
import android.content.SharedPreferences

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE)
    private val switchListeners : ArrayList<() -> Unit> = ArrayList()

    fun addSwitchListener(listener : () -> Unit) {
        switchListeners.add(listener)
    }

    private var stepsNum : Int
        set(value) {
            sp.edit().putInt(Const.STEPS_NUM_KEY, value).apply()
        }
        get() {
            return sp.getInt(Const.STEPS_NUM_KEY, 0)
        }

    private var pathLen : Int
        set(value) {
            sp.edit().putInt(Const.PATH_LEN_KEY, value).apply()
        }
        get() {
            return sp.getInt(Const.PATH_LEN_KEY, 0)
        }

    var currentChoise : Int
        set(value) {
            if (value > 0) {
                stepsNum += 1
                sp.edit().putInt(Const.stepKey(stepsNum), value).apply()
                switchListeners.forEach {
                    it.invoke()
                }
            }
        }
        get() {
            return sp.getInt(Const.stepKey(stepsNum), 1)
        }

    fun getPath() : List<String> {
        val pathLen = sp.getInt(Const.PATH_LEN_KEY, 0)
        val ret = ArrayList<String>()

        for (i in 0 until pathLen) {
            val str = sp.getString(Const.folderKey(i), "")
            if (str != null) {
                ret.add(str)
            }
        }

        return ret
    }

    fun appendPath(folder : String) {
        val len = sp.getInt(Const.PATH_LEN_KEY, 0)
        sp.edit().putString(Const.folderKey(len), folder).apply()
        sp.edit().putInt(Const.PATH_LEN_KEY, len + 1).apply()
    }

    fun goBack() : Boolean {
        return if (stepsNum > 1) {
            if (currentChoise == 1) {
                pathLen -= 1
            }

            stepsNum -= 1

            true
        } else {
            false
        }
    }
}