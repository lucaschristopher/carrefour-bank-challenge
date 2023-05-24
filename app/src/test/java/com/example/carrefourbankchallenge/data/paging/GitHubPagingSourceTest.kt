package com.example.carrefourbankchallenge.data.paging

import androidx.paging.PagingSource
import com.example.carrefourbankchallenge.core.constants.ONE
import com.example.carrefourbankchallenge.data.datasource.remote.model.RepositoryResponse
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import com.example.carrefourbankchallenge.data.datasource.remote.paging.GitHubPagingSource
import com.example.carrefourbankchallenge.data.service.GitHubService
import com.example.carrefourbankchallenge.data.util.repositoryResponseMock
import com.example.carrefourbankchallenge.data.util.userResponseMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class GitHubPagingSourceTest {

    private var gitHubService: GitHubService = mockk(relaxed = true)

    private val mockService = object : GitHubService {
        override suspend fun getUsers(page: Int, pageSize: Int): List<UserResponse> {
            return listOf(userResponseMock, userResponseMock)
        }

        override suspend fun getUserDetails(userId: String): UserResponse = userResponseMock

        override suspend fun getUserRepositories(userId: String): List<RepositoryResponse> {
            return listOf(repositoryResponseMock)
        }
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    // Since load is a suspend function, runTest is used to ensure that it
    // runs on the test thread.
    fun `should get same amount of result as specified in paging load data`() = runTest {
        // Given
        val pagingSource = GitHubPagingSource(mockService)
        val mockData = listOf(userResponseMock, userResponseMock)

        assertEquals(
            expected = PagingSource.LoadResult.Page(
                data = mockData,
                prevKey = null,
                nextKey = TEST_NEXT_PAGE
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = TEST_PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `paging source should return error when server returns error`() = runTest {
        // Given
        val pagingSource = GitHubPagingSource(gitHubService)
        val error = RuntimeException(CODE_ERROR, Throwable())

        // When
        coEvery { gitHubService.getUsers(page = ONE, pageSize = TEST_PAGE_SIZE) }.throws(error)

        // Then
        assertFails {
            pagingSource.load(
                params = PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = TEST_PAGE_SIZE,
                    placeholdersEnabled = false
                )
            )
        }
    }
}

private const val CODE_ERROR = "404"
private const val TEST_PAGE_SIZE = 2
private const val TEST_NEXT_PAGE = 2