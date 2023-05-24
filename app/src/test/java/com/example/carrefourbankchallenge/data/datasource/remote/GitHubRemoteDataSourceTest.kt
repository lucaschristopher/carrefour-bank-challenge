package com.example.carrefourbankchallenge.data.datasource.remote

import com.example.carrefourbankchallenge.data.service.GitHubService
import com.example.carrefourbankchallenge.data.util.repositoryResponseMock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GitHubRemoteDataSourceTest {

    private val mockService: GitHubService = mockk(relaxed = true)

    private fun setupRemoteDatSource() = GitHubRemoteDataSourceImpl(mockService)

    @Test
    fun `getUser should return a flow when remoteDataSource returns sucess`() = runTest {
        // Given
        val username = "mojombo"
        val remoteDataSource = setupRemoteDatSource()
        val expectedData = listOf(repositoryResponseMock)
        val dataFlow = flowOf(expectedData)

        // When
        coEvery { mockService.getUserRepositories(username) } returns expectedData
        val result = remoteDataSource.getUserRepositories(username)

        // Then
        assertEquals(expected = dataFlow.first().size, actual = result.first().size)
    }

}