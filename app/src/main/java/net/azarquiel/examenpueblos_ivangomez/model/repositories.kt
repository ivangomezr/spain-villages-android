package net.azarquiel.examenpueblos_ivangomez.model

import android.app.Application
import androidx.lifecycle.LiveData

class ComunidadRepository(application: Application) {

    val comunidadDao = PueblosBonitosDB.getDatabase(application)!!.comunidadDao()
    fun getAllComunidades(): LiveData<List<Comunidad>> {
        return comunidadDao.getAllComunidades()
    }
}

class PuebloRepository(application: Application) {

    val puebloDao = PueblosBonitosDB.getDatabase(application)!!.puebloDao()
    fun getPueblosByComunidad(comunidad:Int): LiveData<List<PuebloView>> {
        return puebloDao.getPueblosByComunidad(comunidad)
    }

    fun getPueblosByComunidadAndFav(comunidad:Int): LiveData<List<PuebloView>> {
        return puebloDao.getPueblosByComunidadAndFav(comunidad)
    }

    fun update(pueblo: Pueblo) {
        puebloDao.update(pueblo)
    }
}