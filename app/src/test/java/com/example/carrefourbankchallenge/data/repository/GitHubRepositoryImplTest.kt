package com.example.carrefourbankchallenge.data.repository

import androidx.paging.PagingData
import com.example.carrefourbankchallenge.data.datasource.remote.GitHubRemoteDataSource
import com.example.carrefourbankchallenge.data.util.userResponseMock
import com.example.carrefourbankchallenge.domain.model.UserModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubRepositoryImplTest {

    private val remoteDataSource: GitHubRemoteDataSource = mockk(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()

    private fun setupRepository() = GitHubRepositoryImpl(
        remoteDataSource,
        testDispatcher
    )

    @Test
    fun `getUsers should return a flow user list when remoteDataSource getUsers return a flow user list`() =
        runTest {
            // Given
            val pagingDataFlow =
                flowOf(PagingData.from(listOf(userResponseMock))) // Flow<PagingData<UserResponse>>
            val repository = setupRepository()

            // When
            coEvery { remoteDataSource.getUsers() } returns pagingDataFlow
            val result: Flow<PagingData<UserModel>> = repository.getUsers()

            // Then
            assertNotNull(result.first())
        }

    @Test
    fun `getUser should return a flow when remoteDataSource returns sucess`() = runTest {
        // Given
        val username = "mojombo"
        val repository = setupRepository()
        val dataFlow = flowOf(userResponseMock)

        // When
        coEvery { remoteDataSource.getUserDetails(username) } returns dataFlow
        val result = repository.getUserDetails(username)

        // Then
        assertEquals(expected = dataFlow.first().id, actual = result.first().id)
    }
}