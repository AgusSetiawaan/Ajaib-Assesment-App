package com.example.ajaibassesmentapp.response

import com.example.ajaibassesmentapp.response.GithubUserResponse
import com.google.gson.annotations.SerializedName

data class GithubSearchUserResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val items: List<GithubUserResponse>
)
