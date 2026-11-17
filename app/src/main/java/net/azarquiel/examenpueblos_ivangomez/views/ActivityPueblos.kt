package net.azarquiel.examenpueblos_ivangomez.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.adapters.CustomAdapterPueblos
import net.azarquiel.examenpueblos_ivangomez.databinding.ActivityPueblosBinding
import net.azarquiel.examenpueblos_ivangomez.model.Comunidad
import net.azarquiel.examenpueblos_ivangomez.model.Pueblo
import net.azarquiel.examenpueblos_ivangomez.model.PuebloView
import net.azarquiel.examenpueblos_ivangomez.viewmodel.PuebloViewModel

class ActivityPueblos : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityPueblosBinding
    private var pueblosAL: List<PuebloView> = emptyList()
    private var currentQuery = ""
    private lateinit var comunidad: Comunidad
    private lateinit var adapter : CustomAdapterPueblos
    private lateinit var puebloViewModel : PuebloViewModel
    private lateinit var searchView: SearchView
    private var fav : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPueblosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        comunidad = intent.getSerializableExtra("comunidad") as Comunidad
        puebloViewModel = ViewModelProvider(this).get(PuebloViewModel::class.java)

        binding.fab.setOnClickListener { onClickFav() }

        setTitle(comunidad.nombre)

        initRV()
        getPueblos()
    }

    private fun initRV() {
        adapter = CustomAdapterPueblos(this, R.layout.rowpueblo)
        binding.cm.rvpueblos.adapter = adapter
        binding.cm.rvpueblos.layoutManager = LinearLayoutManager(this)
    }

    private fun getPueblos() {
        comunidad.id?.let { id ->
            puebloViewModel.getPueblosByComunidad(id).removeObservers(this)
            puebloViewModel.getPueblosByComunidadAndFav(id).removeObservers(this)
            val source = if (fav) puebloViewModel.getPueblosByComunidadAndFav(id)
            else puebloViewModel.getPueblosByComunidad(id)
            source.observe(this) { pueblos ->
                pueblosAL = pueblos ?: emptyList()
                showPueblos()
            }
        }
    }

    private fun getPueblosFav() {
        comunidad.id?.let {
            puebloViewModel.getPueblosByComunidadAndFav(it).observe(this, Observer { pueblos ->
                // Update the cached copy of the products in the adapter.
                pueblos?.let {
                    for(pueblo in it) {
                        pueblosAL = it
                        showPueblos()
                    }
                }
            })
        }
    }

    private fun showPueblos() {
        val q = currentQuery.trim().lowercase()
        adapter.setPueblos(
            if (q.isEmpty()) pueblosAL
            else pueblosAL.filter { it.nombre.lowercase().contains(q) }
        )
    }

    fun OnClickPueblo(v: View) {
        val pueblo = v.tag as PuebloView
        val intent = Intent(this, ActivityDetail::class.java)
        intent.putExtra("pueblo", pueblo)
        intent.putExtra("comunidad", comunidad)
        startActivity(intent)
    }

    private fun onClickFav() {
        fav = !fav
        binding.fab.setImageResource(
            if (fav) android.R.drawable.star_big_on else android.R.drawable.star_big_off
        )
        getPueblos()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView.clearFocus()   // oculta el teclado al pulsar "buscar"
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        currentQuery = query.orEmpty()
        showPueblos()
        return true
    }
}