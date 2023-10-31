package com.example.recycleviewpahlawan_11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pahlawanList: ArrayList<Pahlawan>
    private lateinit var pahlawanAdapter: PahlawanAdapter
    private lateinit var searchView: SearchView

    private var originalPahlawanList: ArrayList<Pahlawan> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_pahlawan)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        pahlawanList = ArrayList()

        pahlawanList.add(Pahlawan(R.drawable.bjhabibi, "B. J. Habibie", "Presiden ketiga Republik Indonesia yang diangkat setelah Presiden Soeharto mundur. Beliau dikenal juga sebagai bapak pesawat karena berhasil menciptakan pesawat pertama Indonesia."))
        pahlawanList.add(Pahlawan(R.drawable.antasari, "Pangeran Antasari", "Pangeran Antasari hidup di tengah rakyat sebagai jelata. Ia tidak dikenal Belanda yang berada di lingkungan istana Martapura sehingga sejarah Banjar juga tidak pernah mengenal bentuk wajahnya yang sebenarnya."))
        pahlawanList.add(Pahlawan(R.drawable.hagussalim, "Haji Agus Salim", "Agus Salim dikenal sebagai seorang politikus, jurnalis, dan diplomat dengan julukan \"The Grand Old Man\". Beliau dijuluki \"The Grand Old Man\", karena prestasinya di bidang diplomasi & kefasihannya dalam berbahasa asing."))
        pahlawanList.add(Pahlawan(R.drawable.kihajar, "Ki Hajar Dewantara", "Ki Hadjar Dewantara juga mendirikan perguruan Taman Siswa. Ia terkenal dengan ucapannya, \"Ing Ngarsa Sung Tuladha, Ing Madya Mangun Karsa, Tut Wuri Handayani\" yang kini menjadi semboyan pendidikan Indonesia."))
        pahlawanList.add(Pahlawan(R.drawable.sukarno, "Ir. Soekarno", "Sosok besar dalam sejarah Indonesia. Dia adalah bapak Proklamator Indonesia yang juga Presiden RI pertama, Ir. Soekarno atau akrab disapa Bung Karno. Bung Karno dilahirkan di pada tanggal 6 Juni 1901."))
        pahlawanList.add(Pahlawan(R.drawable.mohhatta, "Mohammad Hatta", "Tokoh yang berperan penting dalam perjuangan kemerdekaan dan merupakan wakil presiden pertama Indonesia. Moh Hatta adalah salah satu pemikir terhebat yang pernah dimiliki oleh Indonesia."))
        pahlawanList.add(Pahlawan(R.drawable.sutansjahrir, "Sutan Sjahrir", "Seorang pemimpin dan perdana menteri kemerdekaan revolusioner Indonesia. Ia seorang intelektual Indonesia yang idealis karena lebih mementingkan kepentingan bersama daripada kepentingan politiknya."))

        originalPahlawanList.addAll(pahlawanList)

        pahlawanAdapter = PahlawanAdapter(pahlawanList, this)
        pahlawanAdapter.setOnItemClickListener(object : PahlawanAdapter.OnItemClickListener {
            override fun onItemClick(pahlawan: Pahlawan) {
                showToast(pahlawan.name)
            }
        })
        recyclerView.adapter = pahlawanAdapter

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterPahlawan(newText)
                return true
            }
        })
    }

    private fun showToast(namaPahlawan: String) {
        Toast.makeText(this, "Nama Pahlawan: $namaPahlawan", Toast.LENGTH_SHORT).show()
    }

    private fun filterPahlawan(query: String?) {
        val filteredList = java.util.ArrayList<Pahlawan>()
        if (query.isNullOrBlank()) {
            filteredList.addAll(originalPahlawanList)
        }

        else {
            val lowerCaseQuery = query.toLowerCase(Locale.getDefault())
            for (pahlawan in originalPahlawanList) {
                if (pahlawan.name.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    filteredList.add(pahlawan)
                }
            }
        }

        if (filteredList.isEmpty()) {
            showToast("Belum Terdata")
        }

        pahlawanAdapter.filterList(filteredList)
    }
}