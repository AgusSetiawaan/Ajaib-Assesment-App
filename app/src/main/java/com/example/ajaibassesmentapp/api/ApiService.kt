package com.example.ajaibassesmentapp.api


import com.example.ajaibassesmentapp.response.GithubUserResponse
import com.example.ajaibassesmentapp.response.GithubSearchUserResponse
import com.example.ajaibassesmentapp.response.GithubUserDetailResponse
import com.example.ajaibassesmentapp.response.GithubUserReposResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getListUsers(): List<GithubUserResponse>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): GithubSearchUserResponse

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): GithubUserDetailResponse

    @GET("users/{username}/{followState}")
    suspend fun getFollowingFollowers(
        @Path("username") username: String,
        @Path("followState") followState: String): List<GithubUserResponse>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String
    ): List<GithubUserReposResponseItem>
}