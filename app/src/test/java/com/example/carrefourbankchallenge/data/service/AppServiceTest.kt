package com.example.carrefourbankchallenge.data.service

import com.example.carrefourbankchallenge.core.constants.ONE
import com.example.carrefourbankchallenge.core.constants.PAGE_SIZE
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

private const val responseSucessGetUsers = "response_sucess_get_users.json"
private const val responseSucessGetUser = "response_sucess_get_user.json"
private const val responseErrorGetUser = "response_error_get_user.json"

class AppServiceTest : ServiceBaseTests() {

    @Test
    fun `should get users when server gives success response`() = runTest {
        // Given
        val expectedResponse: List<UserResponse> =
            getExpectedResponse(responseSucessGetUsers, Array<UserResponse>::class.java).asList()
        getResponse(responseSucessGetUsers, HttpURLConnection.HTTP_OK)

        // When
        val result = service.getUsers(page = ONE, pageSize = PAGE_SIZE)

        // Then
        Assert.assertEquals(expectedResponse.size, result.size)
        Assert.assertEquals(expectedResponse, result)
    }

    @Test
    fun `should get user when server gives success response`() = runTest {
        // Given
        val username = "mojombo"
        val expectedResponse = getExpectedResponse(responseSucessGetUser, UserResponse::class.java)
        getResponse(responseSucessGetUser, HttpURLConnection.HTTP_OK)

        // When
        val result = service.getUserDetails(username)

        // Then
        Assert.assertEquals(expectedResponse, result)
    }

    @Test
    fun `should returns error when user not found`(): Unit = runTest {
        val username = "as7a8798ajskans"

        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                getResponse(
                    responseErrorGetUser,
                    HttpURLConnection.HTTP_NOT_FOUND
                )
                service.getUserDetails(username)
            }
        }
    }

    @Test
    fun `should throw client exception when server sends 4xx response`(): Unit = runTest {
        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                getResponse(
                    responseSucessGetUsers,
                    HttpURLConnection.HTTP_BAD_REQUEST
                )
                service.getUsers(page = ONE, pageSize = PAGE_SIZE)
            }
        }
    }

    @Test
    fun `should throw no network exception in case of timeout`() {
        Assert.assertThrows(SocketTimeoutException::class.java) {
            runBlocking {
                getTimeout()
                service.getUsers(page = ONE, pageSize = PAGE_SIZE)
            }
        }
    }
}