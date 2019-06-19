package msu.ug

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        switchToChoice()

        val storage = Storage(this)

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
    }

    private fun switchToChoice() {
        val choiceTransaction = supportFragmentManager.beginTransaction()
        choiceTransaction.replace(R.id.fragment_frame, ChoiceFragment(this) {
            val descriptionTransaction = supportFragmentManager.beginTransaction()
            descriptionTransaction.replace(R.id.fragment_frame, DescriptionFragment(this) {
                switchToChoice()
            })
            descriptionTransaction.commit()
        })
        choiceTransaction.commit()

    }
}
