package msu.ug

import android.content.Context
import android.content.SharedPreferences

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE)
    private val switchListeners : ArrayList<() -> Unit> = ArrayList()

    fun addSwitchListener(listener : () -> Unit) {
        switchListeners.add(listener)
    }

    var currentChoise : Int
        set(value) {
            if (value > 0) {
                sp.edit().putInt(Const.CUR_CHOICE_KEY, value).apply()
                switchListeners.forEach {
                    it.invoke()
                }
            }
        }
        get() {
            return sp.getInt(Const.CUR_CHOICE_KEY, 1)
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
}