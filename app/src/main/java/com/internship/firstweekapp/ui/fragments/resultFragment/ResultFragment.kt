package com.internship.firstweekapp.ui.fragments.resultFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.FragmentResultBinding
import com.internship.firstweekapp.util.Constants
import org.json.JSONObject
import kotlin.math.min


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args: ResultFragmentArgs by navArgs()
        val arrFromArgs = args.result
        val arrFromJson =
            JSONObject(requireActivity().assets.open(Constants.FILE_NAME).bufferedReader().use {
                it.readText()
            }).getJSONArray(Constants.RIGHT_ANSWER_TAG)
        var counter = 0
        val min = min(arrFromJson.length() - 1, arrFromArgs.size - 1)
        for (i in 0..min) {
            if (arrFromJson[i].toString() == arrFromArgs[i])
                counter++
        }

        binding.resultTxtVw.text = getString(R.string.concate_for_result, counter, min + 1)
        super.onViewCreated(view, savedInstanceState)
    }
}