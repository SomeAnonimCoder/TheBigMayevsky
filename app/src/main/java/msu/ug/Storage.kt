package msu.ug

import android.content.Context
import android.content.SharedPreferences

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE)

    fun addSwitchListener(listener : () -> Unit) {
        sp.registerOnSharedPreferenceChangeListener { _, s ->
            if (s == Const.STEPS_NUM_KEY) {
                listener.invoke()
            }
        }
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
                sp.edit().putInt(Const.stepKey(stepsNum + 1), value).apply()
                stepsNum += 1
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
        sp.edit().putString(Const.folderKey(pathLen), folder).apply()
        pathLen += 1
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