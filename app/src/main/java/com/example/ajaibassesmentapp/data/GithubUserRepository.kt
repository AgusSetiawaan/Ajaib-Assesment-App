package com.example.ajaibassesmentapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.ajaibassesmentapp.api.ApiService
import com.example.ajaibassesmentapp.response.GithubSearchUserResponse
import com.example.ajaibassesmentapp.response.GithubUserDetailResponse
import com.example.ajaibassesmentapp.response.GithubUserReposResponseItem
import com.example.ajaibassesmentapp.response.GithubUserResponse
import javax.inject.Inject

class GithubUserRepository@Inject constructor(private val apiService: ApiService) {

    suspend fun getListGithubUser(): List<GithubUserResponse> = apiService.getListUsers()

    suspend fun getSearchGithubUser(query: String): GithubSearchUserResponse = apiService.searchUsers(query)

    suspend fun getUserDetails(username: String): GithubUserDetailResponse = apiService.getUserDetail(username)

    suspend fun getUserRepos(username: String): List<GithubUserReposResponseItem> = apiService.getUserRepos(username)
}