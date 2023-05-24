package com.example.carrefourbankchallenge.data.datasource.remote.model

import com.example.carrefourbankchallenge.core.constants.NO_DESCRIPTION
import com.example.carrefourbankchallenge.core.constants.NO_ID
import com.example.carrefourbankchallenge.domain.model.RepositoryModel
import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(DESCRIPTION)
    val description: String?,
    @SerializedName(URL)
    val url: String,
    @SerializedName(FORKS)
    val forks: Long?,
    @SerializedName(STARGAZERS)
    val stargazers: Long?,
    @SerializedName(OWNER)
    val owner: UserResponse,
) {
    private companion object {
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val URL = "html_url"
        const val FORKS = "forks_count"
        const val STARGAZERS = "stargazers_count"
        const val OWNER = "owner"
    }
}

fun RepositoryResponse.toDomainModel() = RepositoryModel(
    id = this.id,
    name = this.name,
    description = this.description ?: NO_DESCRIPTION,
    url = this.url,
    forks = this.forks ?: NO_ID.toLong(),
    stargazers = this.stargazers ?: NO_ID.toLong()
)