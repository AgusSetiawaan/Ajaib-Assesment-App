package com.example.ajaibassesmentapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserData(
    val id: Int = -1,
    val username: String = "",
    val name: String = "",
    val company: String = "",
    val location: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    var listRepos: List<GithubUserRepos> = listOf()
): Parcelable

@Parcelize
data class GithubUserRepos(
    val name: String,
    val description: String,
    val stargazerCount: Int,
    val updatedAt: String
): Parcelable
