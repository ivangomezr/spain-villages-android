package net.azarquiel.examenpueblos_ivangomez.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "comunidad")
data class Comunidad(
    @PrimaryKey var id: Int,
    var nombre:String): Serializable

@Entity(tableName = "provincia",
    foreignKeys = [ForeignKey(entity = Comunidad::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("comunidad"))])
data class Provincia(
    @PrimaryKey var id: Int,
    var nombre:String,
    var comunidad:Int)

@Entity(tableName = "pueblo",
    foreignKeys = [ForeignKey(entity = Provincia::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("provincia"))])
data class Pueblo(
    @PrimaryKey var id: Int,
    var nombre:String,
    var imagen:String,
    var provincia:Int,
    var link:String,
    var fav:Int): Serializable


data class PuebloView(@PrimaryKey
    var id: Int,
    var nombre:String,
    var imagen:String,
    var provincia:Int,
    var link:String,
    var fav:Int,
    var provinciatxt:String): Serializable

