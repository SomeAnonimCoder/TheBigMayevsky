package msu.ug

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Storage (context : Context) {
    private val sp : SharedPreferences = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE)
    private val listeners = HashSet<(SharedPreferences, String) -> Unit>()

    private fun addOnDBChangeListener(key : String, listener: () -> Unit) {
        val prefListener = { _ : SharedPreferences, s : String ->
            if (s == key) {
                Log.e("STORAGE", "sp listener talking")
                listener.invoke()
            }
        }

        listeners.add(prefListener)
        sp.registerOnSharedPreferenceChangeListener(prefListener)
    }

    fun addOnStepListener(listener : () -> Unit) {
        addOnDBChangeListener(Const.STEPS_NUM_KEY, listener)
    }

    fun addOnTaxSwitchListener(listener: () -> Unit) {
        addOnDBChangeListener(Const.IS_DESCRIPTION_KEY, listener)
    }

    private var stepsNum : Int
        set(value) {
            sp.edit().putInt(Const.STEPS_NUM_KEY, value).apply()
            if (currentChoice == 0) {
                isDescription = true
            } else if (isDescription) {
                isDescription = false
            }
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

    private var isDescription : Boolean
        set(value) {
            sp.edit().putBoolean(Const.IS_DESCRIPTION_KEY, value).apply()
        }
        get() {
            return sp.getBoolean(Const.IS_DESCRIPTION_KEY, false)
        }

    var currentChoice : Int
        set(value) {
            sp.edit().putInt(Const.stepKey(stepsNum + 1), value).apply()
            stepsNum += 1
            Log.e("STORAGE", "switched current choice $currentChoice")
        }
        get() {
            return sp.getInt(Const.stepKey(stepsNum), 1)
        }

    fun getCurFile() : String {
        val path = StringBuilder("")

        for (i in 0 until pathLen) {
            val str = sp.getString(Const.folderKey(i), "")
            if (str != null) {
                path.append(str)
                path.append("/")
            }
        }

        path.append(currentChoice)
        Log.e("STORAGE", path.toString())

        return path.toString()
    }

    fun appendPath(folder : String) {
        sp.edit().putString(Const.folderKey(pathLen), folder).apply()
        pathLen += 1
    }

    fun goBack() : Boolean {
        return if (stepsNum > 0) {
            if (isDescription) {
                pathLen -= 1
            }

            stepsNum -= 1

            true
        } else {
            false
        }
    }

    fun toStart() {
        pathLen = 0
        stepsNum = 0
    }
}