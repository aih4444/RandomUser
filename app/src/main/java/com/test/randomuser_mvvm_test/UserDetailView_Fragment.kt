package com.test.randomuser_mvvm_test

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.randomuser_mvvm_test.databinding.FragmentSecondBinding
import com.test.randomuser_mvvm_test.viewmodel.UserDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserDetailView_Fragment : Fragment() {

    val viewModel by viewModel<UserDetailViewModel>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentSecondBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val userProperty = UserDetailView_FragmentArgs.fromBundle(arguments!!).selectedUser
        binding.viewModel = viewModel
        viewModel.setProperty(userProperty)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}