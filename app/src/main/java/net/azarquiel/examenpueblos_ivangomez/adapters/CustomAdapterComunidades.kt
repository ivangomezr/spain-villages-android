package net.azarquiel.examenpueblos_ivangomez.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.model.Comunidad

class CustomAdapterComunidades(val context: Context,
                               val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterComunidades.ViewHolder>() {

    private var dataList: List<Comunidad> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setComunidades(Comunidades: List<Comunidad>) {
        this.dataList = Comunidades
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Comunidad){

            val tvcomunidadrow = itemView.findViewById(R.id.tvpueblorow) as TextView

            tvcomunidadrow.text = dataItem.nombre;

            itemView.tag = dataItem

        }
    }
}
