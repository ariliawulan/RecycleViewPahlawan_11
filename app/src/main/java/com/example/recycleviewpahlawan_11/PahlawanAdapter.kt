package com.example.recycleviewpahlawan_11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class PahlawanAdapter(private val pahlawanList: ArrayList<Pahlawan>, private val context: Context)
    : RecyclerView.Adapter<PahlawanAdapter.PahlawanViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(pahlawan: Pahlawan)
    }

    private var itemClickListener: OnItemClickListener? = null

    inner class PahlawanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.image_pahlawan)
        val textView: TextView = itemView.findViewById(R.id.text_nama)
        val textView2: TextView = itemView.findViewById(R.id.text_description)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(pahlawan: Pahlawan) {
            imageView.setImageResource(pahlawan.image)
            textView.text = pahlawan.name
            textView2.text = pahlawan.description
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val pahlawan = pahlawanList[position]
                itemClickListener?.onItemClick(pahlawan)
            }
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PahlawanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pahlawan, parent, false)
        return PahlawanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pahlawanList.size
    }

    override fun onBindViewHolder(holder: PahlawanViewHolder, position: Int) {
        holder.bind(pahlawanList[position])
    }

    private fun showToast(namaPahlawan: String) {
        Toast.makeText(context, "Nama Pahlawan: $namaPahlawan", Toast.LENGTH_SHORT).show()
    }

    fun filterList(filteredList: ArrayList<Pahlawan>) {
        pahlawanList.clear()
        pahlawanList.addAll(filteredList)
        notifyDataSetChanged()
    }
}
