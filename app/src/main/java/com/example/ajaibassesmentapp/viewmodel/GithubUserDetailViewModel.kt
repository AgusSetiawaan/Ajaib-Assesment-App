package com.example.ajaibassesmentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajaibassesmentapp.data.GithubUserRepository
import com.example.ajaibassesmentapp.data.Result
import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.data.GithubUserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(private val repository: GithubUserRepository): ViewModel() {

    private val _userDetailLiveData = MutableLiveData<Result<GithubUserData>>()
    val userDetailLiveData: LiveData<Result<GithubUserData>>
    get() = _userDetailLiveData

    fun getDetailUser(data: GithubUserData){
        _userDetailLiveData.value = Result.Loading
        viewModelScope.launch {
            try {
                val response = repository.getUserRepos(data.username)
                val listRepos = response.map {
                    GithubUserRepos(
                        name = it.name?:it.fullName?:"",
                        description = it.description?:"",
                        stargazerCount = it.stargazersCount?:0,
                        updatedAt = it.updatedAt?:""
                    )
                }
                data.listRepos = listRepos
                _userDetailLiveData.postValue(Result.Success(data))
            }
            catch (e: Exception){
                _userDetailLiveData.postValue(Result.Error(e.toString()))
            }

        }
    }
}