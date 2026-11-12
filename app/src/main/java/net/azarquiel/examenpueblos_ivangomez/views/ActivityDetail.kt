package net.azarquiel.examenpueblos_ivangomez.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.squareup.picasso.Picasso
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.databinding.ActivityDetailBinding
import net.azarquiel.examenpueblos_ivangomez.model.Comunidad
import net.azarquiel.examenpueblos_ivangomez.model.Pueblo
import net.azarquiel.examenpueblos_ivangomez.model.PuebloView
import net.azarquiel.examenpueblos_ivangomez.viewmodel.PuebloViewModel

class ActivityDetail : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailBinding
    private lateinit var pueblo : PuebloView
    private lateinit var comunidad : Comunidad
    private var fav : Boolean = false
    private lateinit var puebloViewModel : PuebloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        pueblo = intent.getSerializableExtra("pueblo") as PuebloView
        comunidad = intent.getSerializableExtra("comunidad") as Comunidad

        puebloViewModel = ViewModelProvider(this).get(PuebloViewModel::class.java)

        binding.fab.setOnClickListener { OnClickFav() }

        setTitle(pueblo.nombre)
        getViews()

        val tvmasdetail = findViewById(R.id.tvmasdetail) as TextView

        tvmasdetail.setOnClickListener{ OnClickMas(pueblo) }

    }

    private fun getViews() {
        val ivpueblodetail = findViewById(R.id.ivpueblodetail) as ImageView
        val tvpueblodetail = findViewById(R.id.tvpueblodetail) as TextView
        val tvprovinciacomunidad = findViewById(R.id.tvprovinciacomunidad) as TextView

        tvpueblodetail.text = pueblo.nombre
        tvprovinciacomunidad.text = pueblo.provinciatxt + " ("+comunidad.nombre+")"

        Picasso.get().load(pueblo.imagen).into(ivpueblodetail)

        if(pueblo.fav==0) {
            binding.fab.setImageResource(android.R.drawable.star_big_off)
        } else {
            binding.fab.setImageResource(android.R.drawable.star_big_on)
        }
    }


    private fun OnClickMas(pueblo : PuebloView) {
        val intent = Intent(this, ActivityDetailWeb::class.java)
        intent.putExtra("pueblo", pueblo)
        startActivity(intent)
    }

    private fun OnClickFav() {
        if(pueblo.fav==0) {
            fav=true
            binding.fab.setImageResource(android.R.drawable.star_big_on)
            pueblo.fav=1
        } else {
            fav=false
            binding.fab.setImageResource(android.R.drawable.star_big_off)
            pueblo.fav=0
        }
        val pueblonew = Pueblo(pueblo.id, pueblo.nombre, pueblo.imagen, pueblo.provincia, pueblo.link, pueblo.fav)
        puebloViewModel.update(pueblonew)
    }

}