package com.example.myapplication

import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow
import net.bytebuddy.utility.RandomString
import kotlin.random.Random

object DummyBuilder {

    fun createMovie(): Movie {
        return Movie(
            adult = false,
            backdrop_path = RandomString.make(2),
            genre_ids = emptyList(),
            id = Random.nextInt(),
            original_language = RandomString.make(2),
            original_title = RandomString.make(2),
            overview = RandomString.make(2),
            popularity = Random.nextDouble(),
            poster_path = RandomString.make(2),
            release_date = RandomString.make(2),
            title = RandomString.make(2),
            video = Random.nextBoolean(),
            vote_average = Random.nextDouble(),
            vote_count = Random.nextInt()
        )
    }

    fun createTvShow(): TvShow {
        return TvShow(
            backdrop_path = RandomString.make(2),
            first_air_date = RandomString.make(2),
            genre_ids = emptyList(),
            id = Random.nextInt(),
            name = RandomString.make(2),
            origin_country = emptyList(),
            original_language = RandomString.make(2),
            original_name = RandomString.make(2),
            overview = RandomString.make(2),
            popularity = Random.nextDouble(),
            poster_path = RandomString.make(2),
            vote_average = Random.nextDouble(),
            vote_count = Random.nextInt()
        )
    }
}
