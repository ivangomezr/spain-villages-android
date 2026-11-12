package net.azarquiel.examenpueblos_ivangomez.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.adapters.CustomAdapterComunidades
import net.azarquiel.examenpueblos_ivangomez.model.Comunidad
import net.azarquiel.examenpueblos_ivangomez.util.Util
import net.azarquiel.examenpueblos_ivangomez.viewmodel.ComunidadViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter : CustomAdapterComunidades
    private lateinit var rvcomunidades : RecyclerView
    private lateinit var comunidadViewModel: ComunidadViewModel
    private lateinit var comunidadesAL : List<Comunidad>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.inyecta(this, "pueblosbonitos.sqlite")
        rvcomunidades = findViewById(R.id.rvcomunidades)
        comunidadViewModel = ViewModelProvider(this).get(ComunidadViewModel::class.java)

        setTitle("Comunidades")
        initRV()
        getComunidades()
    }

    private fun initRV() {
        adapter = CustomAdapterComunidades(this, R.layout.rowcomunidad)
        rvcomunidades.adapter = adapter
        rvcomunidades.layoutManager = LinearLayoutManager(this)
    }

    private fun getComunidades() {
        comunidadViewModel.getAllComunidades().observe(this, Observer { comunidades ->
            // Update the cached copy of the products in the adapter.
            comunidades?.let {
                for(comunidad in it) {
                    comunidadesAL = it
                    showComunidades()
                }
            }
        })
    }

    private fun showComunidades() {
        adapter.setComunidades(comunidadesAL)
    }

    fun OnClickComunidad(v: View) {
        val comunidad = v.tag as Comunidad
        val intent = Intent(this, ActivityPueblos::class.java)
        intent.putExtra("comunidad", comunidad)
        startActivity(intent)
    }
}