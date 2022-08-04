package com.example.diosoccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diosoccernews.MainActivity
import com.example.diosoccernews.data.News
import com.example.diosoccernews.databinding.FragmentFavoritesBinding
import com.example.diosoccernews.ui.NewsAdapter
import com.example.diosoccernews.ui.ViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val newsViewModel = (this.activity as MainActivity).newsViewModel
        val context = container?.context

        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        loadFavoriteNews(newsViewModel)

        return binding.root
    }

    private fun loadFavoriteNews(newsViewModel: ViewModel) {
        val favoritedNewsList = newsViewModel.newsDao.getFavoriteNews()
        binding.rvFavorites.adapter = NewsAdapter(
            favoritedNewsList
        ) { favoritedNews: News ->
            newsViewModel.newsDao.addNews(favoritedNews)
            loadFavoriteNews(newsViewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}