package com.example.diosoccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diosoccernews.MainActivity
import com.example.diosoccernews.data.News
import com.example.diosoccernews.databinding.FragmentNewsBinding
import com.example.diosoccernews.ui.NewsAdapter

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val newsViewModel = (this.activity as MainActivity).newsViewModel
        val context = container?.context

        newsViewModel.updateNewsList()
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        newsViewModel.newsList.observe(viewLifecycleOwner) { listNews ->
            binding.rvNews.adapter = NewsAdapter(
                listNews
            ) { favoritedNews: News -> newsViewModel.newsDao.addNews(favoritedNews) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}