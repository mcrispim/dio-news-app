package com.example.diosoccernews.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diosoccernews.R
import com.example.diosoccernews.data.News
import com.example.diosoccernews.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private val listNews: List<News>,
                  private val favoriteListener: (favoritedNews: News) -> Unit )
        : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
        }

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
                val context = holder.itemView.context
                val news = listNews[position]
                Picasso.get()
                        .load(news.imagem)
                        .into(holder.binding.ivImagem)
                holder.binding.tvTitulo.text = news.titulo
                holder.binding.tvTexto.text = news.texto

                // implementação da funcionalidade Link para a Notícia
                holder.binding.btLink.setOnClickListener {
                        val intentOpenURL = Intent(Intent.ACTION_VIEW)
                        intentOpenURL.data = Uri.parse(news.link)
                        context.startActivity(intentOpenURL)
                }

                // implementação da funcionalidade Share da Notícia
                holder.binding.ivShare.setOnClickListener {
                        val intentShareURL = Intent(Intent.ACTION_SEND)
                        intentShareURL.type ="text/plain"
                        intentShareURL.putExtra(Intent.EXTRA_TEXT, news.link)
                        context.startActivity(Intent.createChooser(intentShareURL, "Share via"))
                }

                // Implementação da funcionalidade favorito da notícia
                holder.binding.ivFavorito.setOnClickListener {
                        news.isFavorite = !news.isFavorite
                        favoriteListener(news)
                        notifyItemChanged(position)
                }
                val favoriteColor = if (news.isFavorite) R.color.favorite_active else R.color.favorite_inactive
                holder.binding.ivFavorito.setColorFilter(context.resources.getColor(favoriteColor, null))
        }

        override fun getItemCount() = listNews.size

        inner  class NewsViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)
}