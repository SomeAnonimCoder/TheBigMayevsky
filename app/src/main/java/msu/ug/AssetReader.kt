package msu.ug

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class AssetReader(context: Context) {
    private val assets = context.assets

    fun getArr(file : String) : JSONArray {
        return JSONArray(getPlainText(file))
    }

    fun getObj(file: String) : JSONObject {
        return JSONObject(getPlainText(file))
    }

    private fun getPlainText(file : String) : String {
        val statementStream = assets.open(file)
        val br = BufferedReader(InputStreamReader(statementStream))
        var line : String? = br.readLine()
        val sb = StringBuilder()

        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }

        return sb.toString()
    }
}