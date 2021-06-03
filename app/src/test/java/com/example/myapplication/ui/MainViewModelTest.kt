package com.example.myapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.myapplication.DummyBuilder
import com.example.myapplication.MainCoroutinesRule
import com.example.myapplication.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class MainViewModelTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockedMovieRepository: MovieRepository

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(mockedMovieRepository)
    }

    @Test
    fun `loadMovieData should return movies when success`() {
        runBlocking {
            val expectedMovies = listOf(DummyBuilder.createMovie())
            whenever(mockedMovieRepository.fetchMovie()) doReturn flowOf(expectedMovies)

            val expectedStartState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = true,
                error = false
            )

            val expectedState = ViewState(
                movies = expectedMovies,
                tvShows = emptyList(),
                isLoading = false,
                error = false
            )

            mainViewModel.viewState.test {
                assertEquals(ViewState.initialValue(), expectItem())
                mainViewModel.loadMovieData()
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadMovieData should return error true when fetch movie failed`() {
        runBlocking {
            whenever(mockedMovieRepository.fetchMovie()) doAnswer { flow { throw Exception() } }

            val expectedStartState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = true,
                error = false
            )

            val expectedState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = false,
                error = true
            )

            mainViewModel.viewState.test {
                assertEquals(ViewState.initialValue(), expectItem())
                mainViewModel.loadMovieData()
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadTvShowData should return tvShows when success`() {
        runBlocking {
            val expectedTvShows = listOf(DummyBuilder.createTvShow())
            whenever(mockedMovieRepository.fetchTvShow()) doReturn flowOf(expectedTvShows)

            val expectedStartState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = true,
                error = false
            )

            val expectedState = ViewState(
                movies = emptyList(),
                tvShows = expectedTvShows,
                isLoading = false,
                error = false
            )

            mainViewModel.viewState.test {
                assertEquals(ViewState.initialValue(), expectItem())
                mainViewModel.loadTvShowData()
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadTvShowData should return error true when fetch tvShow failed`() {
        runBlocking {
            whenever(mockedMovieRepository.fetchTvShow()) doAnswer { flow { throw Throwable() } }

            val expectedStartState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = true,
                error = false
            )

            val expectedState = ViewState(
                movies = emptyList(),
                tvShows = emptyList(),
                isLoading = false,
                error = true
            )

            mainViewModel.viewState.test {
                assertEquals(ViewState.initialValue(), expectItem())
                mainViewModel.loadTvShowData()
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }
}
