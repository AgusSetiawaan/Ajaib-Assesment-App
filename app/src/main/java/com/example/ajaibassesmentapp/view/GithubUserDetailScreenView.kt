package com.example.ajaibassesmentapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajaibassesmentapp.adapter.GithubUserDetailAdapter
import com.example.ajaibassesmentapp.viewmodel.GithubUserDetailViewModel
import com.example.ajaibassesmentapp.R
import com.example.ajaibassesmentapp.data.Result
import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.databinding.FragmentDetailUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUserDetailScreenView: Fragment() {

    private lateinit var binding: FragmentDetailUserBinding
    private val viewModel: GithubUserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val githubUser = GithubUserDetailScreenViewArgs.fromBundle(arguments as Bundle).githubUser

        viewModel.getDetailUser(githubUser)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.userDetailLiveData.observe(viewLifecycleOwner){
            when(it){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    initList(it.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun initList(data: GithubUserData){
        val adapter = GithubUserDetailAdapter(data)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvGithubUsers.layoutManager = layoutManager
        binding.rvGithubUsers.adapter = adapter

        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_layer)!!)
        binding.rvGithubUsers.addItemDecoration(itemDecoration)
    }
}