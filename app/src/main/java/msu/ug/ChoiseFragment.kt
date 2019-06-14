package msu.ug

import android.content.Context
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class ChoiseFragment(private val actContext: Context) : ListFragment() {
    private val storage = Storage(actContext)

    override fun onStart() {
        super.onStart()

        val arr = JSONArray(getPlainText(storage.currentChoise.toString()))

        val displayData = ArrayList<Map<String, Any>>(2)

        (0 until arr.length()).forEach {i ->
            val map = HashMap<String, Any>()
            map["text"] = arr.getJSONObject(i).getString("text")
            displayData.add(map)
        }

        val from = arrayOf("text")
        val to = intArrayOf(android.R.id.text1)
        val listAdapter = SimpleAdapter(context, displayData, android.R.layout.simple_list_item_1, from, to)
        setListAdapter(listAdapter)
    }

    private fun getPlainText(statementFile : String) : String {
        val statementStream = actContext.assets.open(statementFile)
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