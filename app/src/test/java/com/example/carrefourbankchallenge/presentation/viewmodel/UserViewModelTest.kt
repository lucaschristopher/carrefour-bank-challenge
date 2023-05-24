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
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockRepository: GitHubRepository = mockk()

    private fun setupViewModel(repository: GitHubRepository) = UserViewModel(repository)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewmodel should returns success when repository getUserDetails method is called`() =
        runTest(testDispatcher) {
            // Given
            val username = "mojombo"
            val viewModel = setupViewModel(mockRepository)
            val dataFlow = flowOf(userResponseMock.toDomainModel())

            // When
            coEvery { mockRepository.getUserDetails(username) } answers { dataFlow }

            viewModel.getUserDetails(username)

            // Then
            viewModel.userDetails.test {
                val result = expectMostRecentItem()

                assertNotNull(result)
                assertTrue { result is Result.Success }
                assertEquals(
                    expected = userResponseMock.toDomainModel().toUiModel(),
                    actual = (result as Result.Success).data
                )
            }
        }

    @Test
    fun `viewmodel should returns error when repository throw a exception`() =
        runTest(testDispatcher) {
            // Given
            val username = "mojombo"
            val viewModel = setupViewModel(fakeRepository)

            // When
            viewModel.getUserDetails(username)

            // Then
            viewModel.userDetails.test {
                val error = awaitItem()
                assertTrue { error is Result.Error }
                assertEquals(expected = Result.Error(ERROR_MESSAGE), actual = error)
            }
        }
}

private const val ERROR_MESSAGE = "An exception is raised"

private val fakeRepository = object : GitHubRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDetails(username: String): Flow<UserModel> {
        return flow { throw IOException(ERROR_MESSAGE) }
    }

    override suspend fun searchUser(username: String): Flow<UserModel> {
        TODO("Not yet implemented")
    }
}