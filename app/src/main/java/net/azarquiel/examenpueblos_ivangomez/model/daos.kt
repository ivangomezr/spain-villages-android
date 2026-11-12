package net.azarquiel.examenpueblos_ivangomez.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface ComunidadDao {
    @Query("select * from comunidad")
    fun getAllComunidades(): LiveData<List<Comunidad>>

}
@Dao
interface PuebloDao {
    @Query("select pb.id id, pb.nombre nombre,pb.imagen imagen, pb.provincia provincia," +
            "pb.link link, pb.fav fav, pr.nombre provinciatxt "+
            "from pueblo pb, provincia pr where pr.id=pb.provincia and pb.provincia in " +
            "(select id from provincia where comunidad in " +
            "(select id from comunidad where id=:comunidadid))")
    fun getPueblosByComunidad(comunidadid:Int): LiveData<List<PuebloView>>

    @Query("select pb.id id, pb.nombre nombre,pb.imagen imagen, pb.provincia provincia, " +
            "pb.link link, pb.fav fav, pr.nombre provinciatxt " +
            "from pueblo pb, provincia pr where fav=1 and pr.id=pb.provincia and pb.provincia in " +
            "(select id from provincia where comunidad in " +
            "(select id  from comunidad where id=:comunidadid))")
    fun getPueblosByComunidadAndFav(comunidadid:Int): LiveData<List<PuebloView>>

    @Update
    fun update(pueblo: Pueblo)
}