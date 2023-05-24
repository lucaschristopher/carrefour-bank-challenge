package com.example.carrefourbankchallenge.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.carrefourbankchallenge.core.constants.ONE
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import com.example.carrefourbankchallenge.data.service.GitHubService
import retrofit2.HttpException
import java.io.IOException

class GitHubPagingSource(
    private val service: GitHubService
) : PagingSource<Int, UserResponse>() {

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val page = params.key ?: ONE

            val userList = this.service.getUsers(page = page, pageSize = params.loadSize)

            LoadResult.Page(
                data = userList,
                prevKey = if (page == ONE) null else page.minus(ONE),
                nextKey = if (userList.isEmpty()) null else page.plus(ONE)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}