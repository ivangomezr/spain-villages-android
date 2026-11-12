package net.azarquiel.examenpueblos_ivangomez.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.model.Pueblo
import net.azarquiel.examenpueblos_ivangomez.model.PuebloView

class ActivityDetailWeb : AppCompatActivity() {
    private lateinit var pueblo : PuebloView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_web)

        pueblo = intent.getSerializableExtra("pueblo") as PuebloView

        setTitle("PueblosBonitos: " + pueblo.nombre + " Web")

        val wv = findViewById<WebView>(R.id.wv)
        wv.loadUrl(pueblo.link!!)
    }
}