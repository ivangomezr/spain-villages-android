package net.azarquiel.examenpueblos_ivangomez.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.examenpueblos_ivangomez.R
import net.azarquiel.examenpueblos_ivangomez.model.Comunidad
import net.azarquiel.examenpueblos_ivangomez.model.Pueblo
import net.azarquiel.examenpueblos_ivangomez.model.PuebloView

class CustomAdapterPueblos(val context: Context,
                           val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterPueblos.ViewHolder>() {

    private var dataList: List<PuebloView> = emptyList()

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

    internal fun setPueblos(Pueblos: List<PuebloView>) {
        this.dataList = Pueblos
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: PuebloView){

            val ivpueblorow = itemView.findViewById(R.id.ivpueblorow) as ImageView
            val tvpueblorow = itemView.findViewById(R.id.tvpueblorow) as TextView
            val tvprovinciarow = itemView.findViewById(R.id.tvprovinciarow) as TextView
            val ivfavpueblo = itemView.findViewById(R.id.ivfavpueblo) as ImageView

            tvpueblorow.text = dataItem.nombre
            tvprovinciarow.text = dataItem.provinciatxt

            Picasso.get().load(dataItem.imagen).into(ivpueblorow)

            if(dataItem.fav==0) {
                ivfavpueblo.setImageResource(android.R.drawable.star_off)
            } else {
                ivfavpueblo.setImageResource(android.R.drawable.star_on)
            }

            itemView.tag = dataItem
        }
    }
}
