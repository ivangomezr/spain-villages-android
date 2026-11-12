package net.azarquiel.examenpueblos_ivangomez.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenpueblos_ivangomez.model.*

class ComunidadViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: ComunidadRepository = ComunidadRepository(application)

    fun getAllComunidades(): LiveData<List<Comunidad>> {
        return repository.getAllComunidades()
    }
}

class PuebloViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: PuebloRepository = PuebloRepository(application)

    fun getPueblosByComunidad(comunidad:Int): LiveData<List<PuebloView>> {
        return repository.getPueblosByComunidad(comunidad)
    }

    fun getPueblosByComunidadAndFav(comunidad:Int): LiveData<List<PuebloView>> {
        return repository.getPueblosByComunidadAndFav(comunidad)
    }

    fun update(pueblo: Pueblo) {
        GlobalScope.launch() {
            repository.update(pueblo)
            launch(Dispatchers.Main) {
            }
        }
    }
}