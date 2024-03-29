package com.example.alphakids.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alphakids.R
import com.example.alphakids.ScanActivity
import com.example.alphakids.databinding.FragmentHomeBinding
import com.example.alphakids.view.adapter.ListBookAdapter
import com.example.alphakids.view.books.Books


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookNames = resources.getStringArray(R.array.data_book)
        val bookDescriptions = resources.getStringArray(R.array.data_deskripsi)
        val bookImages = resources.obtainTypedArray(R.array.data_gambar)

        // Inisialisasi list buku
        val books = ArrayList<Books>()
        for (i in bookNames.indices) {
            val book = Books(bookNames[i], bookDescriptions[i], bookImages.getResourceId(i, -1))
            books.add(book)
        }

        // Inisialisasi RecyclerView dan set adapter
        val recyclerView = binding.rvBook
        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        // Set adapter ke RecyclerView
        recyclerView.adapter = ListBookAdapter(books)

        // Recycle the typed array
        bookImages.recycle()

        binding.buttonScan.setOnClickListener {
            // Navigate to ScanActivity
            navigateToScanActivity()
        }
    }

    private fun navigateToScanActivity() {
        val intent = Intent(activity, ScanActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}