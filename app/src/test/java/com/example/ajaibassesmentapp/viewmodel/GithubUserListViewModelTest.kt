package com.example.ajaibassesmentapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ajaibassesmentapp.DataDummy
import com.example.ajaibassesmentapp.MainDispatcherRule
import com.example.ajaibassesmentapp.data.GithubUserRepository
import com.example.ajaibassesmentapp.data.Result
import com.example.ajaibassesmentapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GithubUserListViewModelTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var repository: GithubUserRepository

    private lateinit var viewModel: GithubUserListViewModel

    @Before
    fun setUp(){
        viewModel = GithubUserListViewModel(repository)
    }

    @Test
    fun `when Get All List User And Return Success`() = runTest {
        val dummyResponse = DataDummy.generateListGithubUserResponse()
        val dummyUser0Response = DataDummy.generateListDetailGithubResponseUser0()
        val dummyUser1Response = DataDummy.generateListDetailGithubResponseUser1()
        `when`(repository.getListGithubUser()).thenReturn(dummyResponse)

        `when`(repository.getUserDetails("username 0")).thenReturn(dummyUser0Response)
        `when`(repository.getUserDetails("username 1")).thenReturn(dummyUser1Response)

        val expectedResult = Result.Success(DataDummy.generateListDetailGithubUserData())

        viewModel.getListUser()
        val actualResult = viewModel.githubListLiveData.getOrAwaitValue()

        assertNotNull(actualResult)
        assertEquals(expectedResult, actualResult)


    }



}