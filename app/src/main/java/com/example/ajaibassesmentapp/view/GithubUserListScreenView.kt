package com.example.ajaibassesmentapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ajaibassesmentapp.adapter.GithubUserListAdapter
import com.example.ajaibassesmentapp.viewmodel.GithubUserListViewModel
import com.example.ajaibassesmentapp.R
import com.example.ajaibassesmentapp.data.Result
import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.databinding.FragmentListUserBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GithubUserListScreenView: Fragment() {

    private lateinit var binding: FragmentListUserBinding
    private val viewModel: GithubUserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getListUser(query)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    private fun observeViewModel(){
        viewModel.githubListLiveData.observe(viewLifecycleOwner){
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

    private fun initList(list: List<GithubUserData>){
        val adapter = GithubUserListAdapter(){
            val action =
                GithubUserListScreenViewDirections.actionGithubUserListScreenViewToGithubUserDetailScreenView(
                    it
                )
            findNavController().navigate(action)
        }
        adapter.submitList(list)

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvGithubUsers.layoutManager = layoutManager
        binding.rvGithubUsers.adapter = adapter
        binding.cardList.visibility = View.VISIBLE

        val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider_layer)!!)
        binding.rvGithubUsers.addItemDecoration(itemDecoration)

    }

}