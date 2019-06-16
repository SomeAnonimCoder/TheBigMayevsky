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

class ChoiceFragment(private val actContext: Context) : ListFragment() {
    private val storage = Storage(actContext)
    private val toIndices = ArrayList<Int>()
    private val taxons = ArrayList<String>()

    override fun onStart() {
        super.onStart()

        val from = arrayOf(Const.MAP_TEXT_KEY)
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
        if (toIndices[position] == 0) {
            storage.appendPath(taxons[position])
            storage.currentChoise = 1
        } else {
            storage.currentChoise = toIndices[position]
        }

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

    private fun updateDisplayData(displayData: ArrayList<Map<String, Any>>) {
        var path = storage.getPath().joinToString("/") + "/"
        if (path == "/") {
            path = ""
        }
        val file =  path + storage.currentChoise.toString()

        val arr = JSONArray(getPlainText(file))
        toIndices.clear()
        displayData.clear()
        (0 until arr.length()).forEach {i ->
            val map = HashMap<String, Any>()
            val thesa = arr.getJSONObject(i)
            map[Const.MAP_TEXT_KEY] = thesa.getString(Const.JSON_TEXT_KEY)
            toIndices.add(thesa.getInt(Const.TO_KEY))
            taxons.add(thesa.optString(Const.TAXON_KEY, Const.DEFAULT_TAXON))
            displayData.add(map)
        }
    }
}