package msu.ug

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.annotation.RequiresApi

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val tv:TextView = findViewById(R.id.info_tv)
        val sp = getString(R.string.info)
        tv.setText(sp)
    }

}
