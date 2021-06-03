package com.example.myapplication.ui.detail

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
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class DetailViewModelTest {
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockedMovieRepository: MovieRepository

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        detailViewModel = DetailViewModel(mockedMovieRepository)
    }

    @Test
    fun `loadMovieData should return movie when success`() {
        runBlocking {
            val expectedMovies = DummyBuilder.createMovie()
            whenever(mockedMovieRepository.fetchMovieDetail(any())) doReturn flowOf(expectedMovies)

            val expectedStartState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = true,
                isError = false
            )

            val expectedState = DetailViewState(
                movie = expectedMovies,
                tvShow = null,
                isLoading = false,
                isError = false
            )

            detailViewModel.viewState.test {
                assertEquals(DetailViewState.initialValue(), expectItem())
                detailViewModel.loadMovieDetail(any())
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadMovieData should return error true when fetch movie failed`() {
        runBlocking {
            whenever(mockedMovieRepository.fetchMovieDetail(any())) doAnswer { flow { throw Exception() } }

            val expectedStartState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = true,
                isError = false
            )

            val expectedState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = false,
                isError = true
            )

            detailViewModel.viewState.test {
                assertEquals(DetailViewState.initialValue(), expectItem())
                detailViewModel.loadMovieDetail(any())
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadTvShowData should return tvShows when success`() {
        runBlocking {
            val expectedTvShows = DummyBuilder.createTvShow()
            whenever(mockedMovieRepository.fetchTvShowDetail(any())) doReturn flowOf(expectedTvShows)

            val expectedStartState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = true,
                isError = false
            )

            val expectedState = DetailViewState(
                movie = null,
                tvShow = expectedTvShows,
                isLoading = false,
                isError = false
            )

            detailViewModel.viewState.test {
                assertEquals(DetailViewState.initialValue(), expectItem())
                detailViewModel.loadTvShowDetail(any())
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }

    @Test
    fun `loadTvShowData should return error true when fetch tvShow failed`() {
        runBlocking {
            whenever(mockedMovieRepository.fetchTvShow()) doAnswer { flow { throw Throwable() } }

            val expectedStartState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = true,
                isError = false
            )

            val expectedState = DetailViewState(
                movie = null,
                tvShow = null,
                isLoading = false,
                isError = true
            )

            detailViewModel.viewState.test {
                assertEquals(DetailViewState.initialValue(), expectItem())
                detailViewModel.loadTvShowDetail(any())
                assertEquals(expectedStartState, expectItem())
                assertEquals(expectedState, expectItem())
            }
        }
    }
}
