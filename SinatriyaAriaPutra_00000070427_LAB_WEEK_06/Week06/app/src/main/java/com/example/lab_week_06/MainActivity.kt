package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    // Inisialisasi RecyclerView menggunakan lazy untuk efisiensi
    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recycler_view)
    }

    // Inisialisasi adapter untuk RecyclerView dengan Glide sebagai image loader
    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView dan adapter dalam satu blok apply
        recyclerView.apply {
            adapter = catAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // Set data untuk adapter
        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Persian,
                    "Luna",
                    "The queen of the house",
                    "https://cdn2.thecatapi.com/images/4.png"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.MaineCoon,
                    "Leo",
                    "Gentle giant",
                    "https://cdn2.thecatapi.com/images/2.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.Siamese,
                    "Mia",
                    "Chatty and affectionate",
                    "https://cdn2.thecatapi.com/images/3.jpg"
                )
            )
        )
    }

    // Menampilkan dialog ketika item RecyclerView dipilih
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
