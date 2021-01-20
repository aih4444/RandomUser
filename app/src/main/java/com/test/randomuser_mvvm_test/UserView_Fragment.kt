package com.test.randomuser_mvvm_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.randomuser_mvvm_test.databinding.FragmentFirstBinding
import com.test.randomuser_mvvm_test.domain.User
import com.test.randomuser_mvvm_test.utils.LoadingState
import com.test.randomuser_mvvm_test.view_adapters.UserAdapter
import com.test.randomuser_mvvm_test.view_adapters.UserClick
import com.test.randomuser_mvvm_test.viewmodel.UserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserView_Fragment : Fragment() {

    private var viewModelAdapter: UserAdapter? = null
    private val viewModel by viewModel<UserViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)

        val binding: FragmentFirstBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_first,container,false)
        binding.setLifecycleOwner (viewLifecycleOwner)
        binding.viewmodel = viewModel

        //Sets the adapter of the  RecyclerView with clickHandler lambda that
        // tells the viewModel when our user is clicked
        viewModelAdapter = UserAdapter(UserClick {
            viewModel.displayUserDetails(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                UserView_FragmentDirections.actionFirstFragmentToSecondFragment(it)
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayUserDetailsComplete()
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()


    }

    private fun setUpObserver() {
        viewModel.userResults.observe(viewLifecycleOwner, Observer<List<User>> { user ->
            user?.apply {
                viewModelAdapter?.result = user
            }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> {
                    //progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.msg, Toast.LENGTH_SHORT).show()
                }
                LoadingState.Status.RUNNING -> {
                    //progressBar.visibility = View.VISIBLE
                }
                LoadingState.Status.SUCCESS -> {
                    //progressBar.visibility = View.GONE

                }
            }
        })
    }
}