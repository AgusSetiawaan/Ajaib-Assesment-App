package com.example.ajaibassesmentapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ajaibassesmentapp.data.GithubUserRepository
import com.example.ajaibassesmentapp.data.Result
import com.example.ajaibassesmentapp.data.GithubUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserListViewModel @Inject constructor(private val repository: GithubUserRepository): ViewModel() {

    private val _githubListLiveData = MutableLiveData<Result<List<GithubUserData>>>()
    val githubListLiveData: LiveData<Result<List<GithubUserData>>>
    get() = _githubListLiveData

    fun getListUser(query: String = ""){
        viewModelScope.launch {
            _githubListLiveData.value = Result.Loading
            try {
                val response = if(query.isBlank()){
                    repository.getListGithubUser()
                }
                else{
                    repository.getSearchGithubUser(query).items
                }
                val data = response.mapIndexed { index, it ->
                    async {
                        try {
                            val detailResponse = repository.getUserDetails(it.login)
                            return@async GithubUserData(
                                id = index,
                                username = detailResponse.login,
                                name = detailResponse.name ?: "",
                                company = detailResponse.company ?: "",
                                location = detailResponse.location,
                                email = detailResponse.email ?: "",
                                photoUrl = detailResponse.avatarUrl
                            )
                        } catch (e: Exception) {
                            return@async GithubUserData()
                        }


                    }
                }
                val finalList = data.awaitAll().sortedBy { it.id }.filter { it != GithubUserData() }
                _githubListLiveData.postValue(Result.Success(finalList))

            }
            catch (e: Exception){
                _githubListLiveData.postValue(Result.Error(e.toString()))
            }
        }
    }
}