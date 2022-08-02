package com.example.diosoccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diosoccernews.data.News
import com.example.diosoccernews.databinding.FragmentNewsBinding
import com.example.diosoccernews.ui.NewsAdapter
import com.example.diosoccernews.ui.MyViewModel

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
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val newsMyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val context = container?.context

        binding.rvNews.layoutManager = LinearLayoutManager(context)
        newsMyViewModel.newsList.observe(viewLifecycleOwner) { listNews ->
            binding.rvNews.adapter = NewsAdapter(
                listNews
            ) { favoritedNews: News -> newsMyViewModel.newsDao.addNews(favoritedNews) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}