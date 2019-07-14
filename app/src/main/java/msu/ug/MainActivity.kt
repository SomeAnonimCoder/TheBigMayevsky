package msu.ug

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        val storage = Storage(this)
        storage.addOnTaxSwitchListener {
            setFragment(storage)
        }

        val backButton = findViewById<Button>(R.id.back_button)

        backButton.setOnClickListener {
            if (!storage.goBack()) {
                Toast.makeText(this, "Вы в начале", Toast.LENGTH_SHORT).show()
            }
        }

        val toStartButton = findViewById<Button>(R.id.to_start_button)

        toStartButton.setOnClickListener {
            storage.toStart()
        }

        storage.currentChoice = 1
        setFragment(storage)
    }

    private fun setFragment(storage: Storage) {
        val transaction = supportFragmentManager.beginTransaction()
        if (storage.currentChoice == 0) {
            transaction.replace(R.id.fragment_frame, DescriptionFragment(this, storage))
        } else {
            transaction.replace(R.id.fragment_frame, ChoiceFragment(this, storage))
        }
        Log.e("MAIN", "commiting transaction")
        transaction.commitAllowingStateLoss()
    }

}
