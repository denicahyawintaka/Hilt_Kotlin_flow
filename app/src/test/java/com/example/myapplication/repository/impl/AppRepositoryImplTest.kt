package com.example.myapplication.repository.impl

import app.cash.turbine.test
import com.example.myapplication.DummyBuilder
import com.example.myapplication.model.MovieResponse
import com.example.myapplication.model.TvShowResponse
import com.example.myapplication.network.ApiService
import com.example.myapplication.repository.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AppRepositoryImplTest {

    @Mock
    lateinit var mockedApiService: ApiService

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        movieRepository = AppRepositoryImpl(mockedApiService)
    }

    @Test
    fun `fetch movie should return list of movie when success`() = runBlocking {
        val expectedMovies = listOf(DummyBuilder.createMovie())
        whenever(mockedApiService.fetchMovies()).thenReturn(
            MovieResponse(expectedMovies)
        )
        movieRepository.fetchMovie().test {
            assertEquals(expectedMovies, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `fetch movie should return error when failed`() = runBlocking {
        val expectedError = Exception()
        whenever(mockedApiService.fetchMovies()) doAnswer { throw expectedError }
        movieRepository.fetchMovie().test {
            assertEquals(expectedError, expectError())
        }
    }

    @Test
    fun `fetch tvShow should return list of tvShow when success`() = runBlocking {
        val expectedTvShows = listOf(DummyBuilder.createTvShow())
        whenever(mockedApiService.fetchTvShow()).thenReturn(
            TvShowResponse(expectedTvShows)
        )
        movieRepository.fetchTvShow().test {
            assertEquals(expectedTvShows, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `fetch tvShow should return error when failed`() = runBlocking {
        val expectedError = Exception()
        whenever(mockedApiService.fetchTvShow()) doAnswer { throw expectedError }
        movieRepository.fetchTvShow().test {
            assertEquals(expectedError, expectError())
        }
    }

    @Test
    fun `fetch Movie Detail should return movie when success`() = runBlocking {
        val expectedMovie = DummyBuilder.createMovie()
        whenever(mockedApiService.fetchMovieDetail(any())).thenReturn(
            expectedMovie
        )
        movieRepository.fetchMovieDetail(any()).test {
            assertEquals(expectedMovie, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `fetch movie detail should return error when failed`() = runBlocking {
        val expectedError = Exception()
        whenever(mockedApiService.fetchMovieDetail(any())) doAnswer { throw expectedError }
        movieRepository.fetchMovieDetail(any()).test {
            assertEquals(expectedError, expectError())
        }
    }

    @Test
    fun `fetch Tvshow Detail should return Tvshow when success`() = runBlocking {
        val expectedTvShow = DummyBuilder.createTvShow()
        whenever(mockedApiService.fetchTvShowDetail(any())).thenReturn(
            expectedTvShow
        )
        movieRepository.fetchTvShowDetail(any()).test {
            assertEquals(expectedTvShow, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `fetch Tvshow detail should return error when failed`() = runBlocking {
        val expectedError = Exception()
        whenever(mockedApiService.fetchTvShowDetail(any())) doAnswer { throw expectedError }
        movieRepository.fetchTvShowDetail(any()).test {
            assertEquals(expectedError, expectError())
        }
    }
}
