package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val listener: OnClickListener // Parameter untuk mendengarkan klik
) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    // Mutable list untuk menyimpan data kucing
    private val cats = mutableListOf<CatModel>()

    // Fungsi untuk memperbarui data di adapter
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    // Fungsi untuk menghapus item pada posisi tertentu
    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }

    // Membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader)
    }

    // Mengembalikan jumlah item dalam list
    override fun getItemCount() = cats.size

    // Mengikat data ke ViewHolder
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])

        // Set listener untuk item saat diklik
        holder.itemView.setOnClickListener {
            listener.onItemClick(cats[position]) // Panggil fungsi onItemClick
        }
    }

    // Interface untuk mendengarkan klik
    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }

    // Inner class untuk swipe functionality
    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        // Ini digunakan jika item lists dapat dipindahkan
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        // Ini digunakan untuk menentukan arah mana yang diizinkan
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ) = if (viewHolder is CatViewHolder) {
            makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) or
                    makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        } else {
            0
        }

        // Ini digunakan untuk deteksi swipe
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position) // Hapus item saat di-swipe
        }
    }

    // ViewHolder untuk item kucing
    class CatViewHolder(view: View, private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(view) {
        // Fungsi untuk mengikat data kucing ke tampilan
        fun bindData(cat: CatModel) {
            // Implementasi binding data ke tampilan menggunakan imageLoader
            // Contoh: imageLoader.loadImage(cat.imageUrl, imageView)
        }
    }
}
