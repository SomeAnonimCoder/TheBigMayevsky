package msu.ug

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val tv:TextView = findViewById(R.id.info_tv)
        val sp = getString(R.string.info)
        tv.text = sp
    }

}
