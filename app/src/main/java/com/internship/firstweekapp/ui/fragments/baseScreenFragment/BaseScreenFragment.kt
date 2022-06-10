package com.internship.firstweekapp.ui.fragments.baseScreenFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.databinding.FragmentBaseScreenBinding
import com.internship.firstweekapp.util.Constants
import org.json.JSONObject

class BaseScreenFragment : Fragment() {
    private val navArgs by navArgs<BaseScreenFragmentArgs>()
    private lateinit var binding: FragmentBaseScreenBinding
    private val vmodel: BaseScreenFragmentViewModel by viewModels {
        BaseScreenFragmentViewModelFactory(
            JSONObject(
                requireActivity().assets.open(Constants.FILE_NAME).bufferedReader().use {
                    it.readText()
                }).getJSONArray(navArgs.page.toString())
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vmodel = vmodel
        vmodel.getNavigate().observe(
            viewLifecycleOwner
        ) {
            if (it) {
                val arrayList = arrayListOf<String>()

                for (i in navArgs.result) {
                    arrayList.add(i)
                }
                arrayList.addAll(vmodel.getUserAnswersArray())
                val action = when (navArgs.page + 1 < 4) {
                    true -> BaseScreenFragmentDirections
                        .actionFirstScreenFragmentToSecondScreenFragment(
                            navArgs.page + 1,
                            arrayList.toTypedArray()
                        )
                    false -> BaseScreenFragmentDirections
                        .actionFirstScreenFragmentToResultFragment2(
                            arrayList.toTypedArray()
                        )
                }

                findNavController()
                    .navigate(action)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}