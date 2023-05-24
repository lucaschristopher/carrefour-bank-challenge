package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.carrefourbankchallenge.data.datasource.remote.model.toDomainModel
import com.example.carrefourbankchallenge.data.util.userResponseMock
import com.example.carrefourbankchallenge.domain.model.toUiModel
import com.example.carrefourbankchallenge.domain.usecase.SearchGitHubUserUseCase
import com.example.carrefourbankchallenge.presentation.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private fun setupViewModel(useCase: SearchGitHubUserUseCase) = SearchViewModel(useCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return success when useCase returns success in search`() = runTest {
        // Given
        val username = "mojombo"
        val viewModel = setupViewModel(fakeSuccessUseCase)

        // When
        viewModel.searchUser(username)

        // Then
        viewModel.searchStateFlow.test {
            assertEquals(
                expected = Result.Loading,
                actual = awaitItem()
            )
            advanceTimeBy(DELAY_TIME)
            assertEquals(
                expected = Result.Success(userResponseMock.toDomainModel().toUiModel()),
                actual = awaitItem()
            )
            ensureAllEventsConsumed()
        }
    }

    @Test
    fun `should return error when useCase throw a exception`() = runTest {
        // Given
        val username = "890uaksakns"
        val viewModel = setupViewModel(fakeErrorUseCase)

        // When
        viewModel.searchUser(username)

        // Then
        viewModel.searchStateFlow.test {
            val error = awaitItem()
            assertTrue { error is Result.Error }
            assertEquals(expected = Result.Error(CODE_MESSAGE), actual = error)
        }

    }
}

private val fakeSuccessUseCase = SearchGitHubUserUseCase {
    flow {
        delay(DELAY_TIME)
        emit(userResponseMock.toDomainModel())
    }
}

private val fakeErrorUseCase = SearchGitHubUserUseCase {
    flow { throw RuntimeException(CODE_MESSAGE) }
}

private const val DELAY_TIME: Long = 1000
private const val CODE_MESSAGE: String = "404"