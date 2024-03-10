package com.lokodom.sportsuperstars.domain.model

data class Sports(
    val name: String,
    val image: String,
    val leagues: List<League>
)

val footballLeagues = listOf<League>(
    League("NFL","https://cdn.freebiesupply.com/logos/large/2x/nfl-1-logo-png-transparent.png", "american football", "united states", "4391"))
val soccerLeagues = listOf<League>(
    League("liga Santander","https://static.wikia.nocookie.net/futbolpredicciones/images/c/cc/Laliga-v-1200x1200.png/revision/latest?cb=20200316142035&path-prefix=es", "soccer", "spain", "4335"),
    League("Premier League","https://static.wikia.nocookie.net/universecwsports/images/3/37/PLeng.png/revision/latest?cb=20200404132848&path-prefix=es", "soccer", "england", "4328"),
    League("Bundesliga","https://c0.klipartz.com/pngpicture/489/415/gratis-png-logo-de-bundesliga-logo-de-bundesliga-thumbnail.png", "soccer", "germany", "4331"))
val basketballLeagues = listOf<League>(
    League("NBA","https://1000logos.net/wp-content/uploads/2017/04/Logo-NBA.png", "basketball", "united states", "4387"),
    League("ACB","https://www.acb.com/Documentos/Cabeceras/logo-acb-4.png", "basketball", "spain", "4408"))
val hockeyLeagues = listOf<League>(
    League("UHL","https://1000logos.net/wp-content/uploads/2019/01/United-Hockey-League-Logo-1997.png", "ice hockey", "united states",""))
val baseballLeagues = listOf<League>(
    League("MLB","https://1000logos.net/wp-content/uploads/2017/04/MLB-Logo.png", "baseball", "united states","4424"))


val sportsList: List<Sports> = listOf(
    Sports("Football", "https://www.pngitem.com/pimgs/b/18-186502_american-football-ball-png-balon-de-futbol-americano.png", footballLeagues ),
    Sports("Soccer", "https://www.freeiconspng.com/thumbs/soccer-ball-png/soccer-ball-png-33.png", soccerLeagues ),
    Sports("Basketball", "https://static.vecteezy.com/system/resources/thumbnails/011/421/099/small_2x/realistic-vector-basketball-isolated-png.png", basketballLeagues ),
    Sports("Hockey", "https://static.vecteezy.com/system/resources/previews/013/516/055/original/ice-hockey-stick-png.png", hockeyLeagues ),
    Sports("Baseball", "https://i.pinimg.com/originals/2f/05/88/2f0588e0123d53f5634f12ef7b1eb4fc.png", baseballLeagues ),
)