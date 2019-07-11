package msu.ug

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DescriptionFragment(actContext: Context,
                          private val storage : Storage) : Fragment() {
    private val assetReader = AssetReader(actContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("DESCRIPTION_FRAG", "on create view started")
        val layout = inflater.inflate(R.layout.fragment_description, container, false)

        val data = assetReader.getObj(storage.getCurFile())

        val button = layout.findViewById<Button>(R.id.ok_button)
        if (data.getBoolean(Const.TERMINAL_KEY)) {
            button.visibility = View.GONE
        } else {
            button.setOnClickListener {
                Log.e("DESCRIPTION_FRAG", "i think button is pushed")
                storage.currentChoice = 1
            }
        }

        val name = layout.findViewById<TextView>(R.id.taxon_name_box)
        name.text = data.getString(Const.NAME_KEY)

        val description = layout.findViewById<TextView>(R.id.taxon_description_box)
        description.text = data.getString(Const.DESCRIPTION_KEY)

        Log.e("DESCRIPTION_FRAG", "on create view ends")

        return layout

    }
}