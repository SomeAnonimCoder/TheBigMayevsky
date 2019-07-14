package msu.ug

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment

class ChoiceFragment(private val actContext: Context,
                     private val storage : Storage) : ListFragment() {
    private val toIndices = ArrayList<Int>()
    private val taxons = ArrayList<String>()
    private val displayData = ArrayList<Map<String, Any>>()

    override fun onStart() {
        super.onStart()

        val from = arrayOf(Const.MAP_TEXT_KEY)
        val to = intArrayOf(android.R.id.text1)
        updateChoiceData()
        val listAdapter = SimpleAdapter(context, displayData, android.R.layout.simple_list_item_1, from, to)
        setListAdapter(listAdapter)
        storage.addOnStepListener {
            if (storage.currentChoice != 0) {
                updateChoiceData()
                listAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Log.e("CHOICE", "click listener talking")
        if (toIndices[position] == 0) {
            storage.appendPath(taxons[position])
            //that means we are going deeper - to child taxon
        }

        storage.currentChoice = toIndices[position]
    }



    private fun updateChoiceData() {
        val assetReader = AssetReader(actContext)

        val arr = assetReader.getArr(storage.getCurFile())
        toIndices.clear()
        displayData.clear()
        (0 until arr.length()).forEach {i ->
            val thesa = arr.getJSONObject(i)

            val map = HashMap<String, Any>()
            map[Const.MAP_TEXT_KEY] = thesa.getString(Const.JSON_TEXT_KEY)
            displayData.add(map)

            toIndices.add(thesa.getInt(Const.TO_KEY))
            taxons.add(thesa.optString(Const.TAXON_KEY, Const.DEFAULT_TAXON))
        }
    }
}