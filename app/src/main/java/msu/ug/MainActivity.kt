package msu.ug

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    val storage = Storage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_frame, ChoiceFragment(this))
        transaction.commit()
    }

    fun onBackClick(view : View) {
        if (!storage.goBack()) {
            Toast.makeText(this, "Вы в начале", Toast.LENGTH_SHORT).show()
        }
    }
}
