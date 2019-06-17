package msu.ug

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_frame, ChoiceFragment(this))
        transaction.commit()

        val backButton = findViewById<Button>(R.id.back_button)

        val storage = Storage(this)

        backButton.setOnClickListener {
            if (!storage.goBack()) {
                Toast.makeText(this, "Вы в начале", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
