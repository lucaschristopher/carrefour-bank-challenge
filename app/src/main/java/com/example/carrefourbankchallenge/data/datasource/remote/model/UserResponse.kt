package com.example.carrefourbankchallenge.data.datasource.remote.model

import com.example.carrefourbankchallenge.core.constants.*
import com.example.carrefourbankchallenge.domain.model.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(ID)
    val id: Int,
    @SerializedName(EMAIL)
    val email: String?,
    @SerializedName(LOGIN)
    val login: String,
    @SerializedName(NAME)
    val name: String?,
    @SerializedName(AVATAR_URL)
    val avatarUrl: String?,
    @SerializedName(URL)
    val url: String?,
    @SerializedName(BIO)
    val bio: String?,
    @SerializedName(LOCATION)
    val location: String?,
    @SerializedName(COMPANY)
    val company: String?,
    var repos: List<RepositoryResponse>? = null
) {
    private companion object {
        const val ID = "id"
        const val EMAIL = "email"
        const val LOGIN = "login"
        const val NAME = "name"
        const val AVATAR_URL = "avatar_url"
        const val URL = "url"
        const val BIO = "bio"
        const val LOCATION = "location"
        const val COMPANY = "company"
    }
}

fun UserResponse.toDomainModel() = UserModel(
    id = this.id,
    email = this.email ?: NO_EMAIL,
    login = this.login,
    name = this.name ?: NO_NAME,
    avatarUrl = this.avatarUrl ?: NO_AVATAR_URL,
    url = this.url ?: NO_URL,
    bio = this.bio ?: NO_BIO,
    location = this.location ?: NO_LOCATION,
    company = this.company ?: NO_COMPANY,
    repos = if (this.repos != null) this.repos!!.map { it.toDomainModel() } else emptyList()
)