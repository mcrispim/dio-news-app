package com.example.diosoccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diosoccernews.data.News

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    init {
        _newsList.value = listOf(
            News(
                1,
                "Formiga se torna a primeira mulher da história a ser eternizada na Calçada da Fama do Mineirão",
                "A jogadora  foi a primeira mulher da história a ser eternizada na Calçada da Fama do Gigante da Pampulha e falou sobre o assunto",
                "https://br.bolavip.com/futebol/-Formiga-se-torna-a-primeira-mulher-da-historia-a-ser-eternizada-na-Calcada-da-Fama-do-Mineirao-20220316-0033.html",
                "https://mcrispim.github.io/dio-women-soccer-news-api/images/image1.jpg"
            ),
            News(
                2,
                "Barcelona aplica goleada no Real Madrid pela Champions Feminina e bate recorde de público na categoria",
                "Clássico espanhol na competição continental lotou o Camp Nou e contou com uma quebra de recorde no futebol feminino; Barça é o atual campeão europeu",
                "https://br.bolavip.com/futebol/Barcelona-aplica-goleada-no-Real-Madrid-pela-Champions-Feminina-e-bate-recorde-de-publico-na-categoria-20220330-0186.html",
                "https://mcrispim.github.io/dio-women-soccer-news-api/images/image2.jpg"
            ),
            News(
                3,
                "'Elas jogam'; Flamengo anuncia  projeto e patrocínio com Mercado Livre para o futebol feminino",
                "O Rubro-Negro anunciou novo patrocino e projeto totalmente voltado ao futebol feminino",
                "https://br.bolavip.com/flamengo/Elas-jogam-Flamengo-anuncia-projeto-e-patrocinio-com-Mercado-Livre-para-o-futebol-feminino-20220317-0037.html",
                "https://mcrispim.github.io/dio-women-soccer-news-api/images/image3.jpg"
            ),
            News(
                4,
                "ESPN investe em futebol feminino com conteúdo sobre campeonato europeu",
                "Canal lança programa especial sobre a Euro feminina",
                "https://natelinha.uol.com.br/televisao/2022/07/12/espn-investe-em-futebol-feminino-com-conteudo-sobre-campeonato-europeu-184447.php",
                "https://mcrispim.github.io/dio-women-soccer-news-api/images/image4.jpg"
            )
        )
    }
    val newsList: LiveData<List<News>> = _newsList
}