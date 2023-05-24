package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.carrefourbankchallenge.data.datasource.remote.model.toDomainModel
import com.example.carrefourbankchallenge.data.util.userResponseMock
import com.example.carrefourbankchallenge.domain.usecase.GetGitHubUsersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val useCase: GetGitHubUsersUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `useCase should returns not null content when viewModel getUsers is called`() =
        runTest(testDispatcher) {
            val viewModel = GitHubViewModel(useCase)
            val dataFlow = flowOf(PagingData.from(listOf(userResponseMock.toDomainModel())))

            coEvery { useCase.invoke() } answers { dataFlow }

            viewModel.getUsers().test {
                Assert.assertNotNull(awaitItem())
            }
        }
}