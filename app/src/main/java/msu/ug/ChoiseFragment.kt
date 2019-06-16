package msu.ug

import android.content.Context
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class ChoiseFragment(private val actContext: Context) : ListFragment() {
    private val storage = Storage(actContext)
    private val toIndices = ArrayList<Int>()

    override fun onStart() {
        super.onStart()

        val from = arrayOf("text")
        val to = intArrayOf(android.R.id.text1)
        val displayData = ArrayList<Map<String, Any>>()
        updateDisplayData(displayData)
        val listAdapter = SimpleAdapter(context, displayData, android.R.layout.simple_list_item_1, from, to)
        setListAdapter(listAdapter)
        storage.addSwitchListener {
            updateDisplayData(displayData)
            listAdapter.notifyDataSetChanged()
        }
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        storage.currentChoise = toIndices[position]

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

    public fun updateDisplayData(displayData: ArrayList<Map<String, Any>>) {
        val arr = JSONArray(getPlainText(storage.currentChoise.toString()))
        toIndices.clear()
        displayData.clear()
        (0 until arr.length()).forEach {i ->
            val map = HashMap<String, Any>()
            val thesa = arr.getJSONObject(i)
            map["text"] = thesa.getString("text")
            toIndices.add(thesa.getInt("to"))
            displayData.add(map)
        }
    }
}