package msu.ug

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        val backButton:Button = findViewById(R.id.back)
        backButton.setOnClickListener {
            val storage:Storage = Storage(this)
            storage.currentChoise = 1
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, ChoiseFragment(this))
            transaction.commit()
        }


        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_frame, ChoiseFragment(this))
        transaction.commit()

    }
}
