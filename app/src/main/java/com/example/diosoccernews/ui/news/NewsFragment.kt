package com.example.diosoccernews.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.diosoccernews.NewsApplication
import com.example.diosoccernews.data.local.AppDatabase
import com.example.diosoccernews.databinding.FragmentNewsBinding
import com.example.diosoccernews.ui.NewsAdapter

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val context = container?.context



        binding.rvNewsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsViewModel.newsList.observe(viewLifecycleOwner) { listNews ->
            binding.rvNewsRecyclerView.adapter = NewsAdapter(listNews) {

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}