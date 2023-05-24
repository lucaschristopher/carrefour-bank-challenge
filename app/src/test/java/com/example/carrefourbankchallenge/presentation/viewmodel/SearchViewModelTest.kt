package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.carrefourbankchallenge.data.datasource.remote.model.toDomainModel
import com.example.carrefourbankchallenge.data.util.userResponseMock
import com.example.carrefourbankchallenge.domain.model.UserModel
import com.example.carrefourbankchallenge.domain.model.toUiModel
import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import com.example.carrefourbankchallenge.presentation.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
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

    private fun setupViewModel(repository: GitHubRepository) = SearchViewModel(repository)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should retun sucess when repository retuns sucess in search`() = runTest {
        // Given
        val username = "mojombo"
        val viewModel = setupViewModel(fakeSuccessRepository)

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
    fun `should retun error when repository throw a expection`() = runTest {
        // Given
        val username = "890uaksakns"
        val viewModel = setupViewModel(fakeErrorRepository)

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

private val fakeSuccessRepository = object : GitHubRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetails(username: String): Flow<UserModel> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(username: String): Flow<UserModel> {
        return flow {
            delay(DELAY_TIME)
            emit(userResponseMock.toDomainModel())
        }
    }
}

private val fakeErrorRepository = object : GitHubRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetails(username: String): Flow<UserModel> {
        TODO("Not yet implemented")
    }

    override suspend fun searchUser(username: String): Flow<UserModel> {
        return flow { throw RuntimeException(CODE_MESSAGE) }
    }
}

private const val DELAY_TIME: Long = 1000
private const val CODE_MESSAGE: String = "404"