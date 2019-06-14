package msu.ug

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IntroActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val context:Context = this
        val classButton = findViewById<Button>(R.id.button_classifier)
        classButton.setOnClickListener {
            val intent =  Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        val aboutButton = findViewById<Button>(R.id.button_about)
        aboutButton.setOnClickListener {
            val intent = Intent(context, AboutActivity::class.java)
            startActivity(intent)
        }

    }
}
