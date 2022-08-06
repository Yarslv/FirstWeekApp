package com.internship.firstweekapp.ui.add_card

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.setFragmentResult
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.ext.hideKeyboard
import com.internship.firstweekapp.databinding.FragmentAddCardBinding
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import com.internship.firstweekapp.navigateBack
import com.internship.firstweekapp.util.PseudoValue
import com.internship.firstweekapp.util.models.SensorEntity
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCardFragment : BaseFragment<FragmentAddCardBinding>(R.layout.fragment_add_card) {
    override val viewModel: AddCardFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.hideKeyboardEvent.observe(viewLifecycleOwner){
            if (it) hideKeyboard()
        }

        binding.typeTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                SensorType.values()
            )
        )

        binding.typeTextView.setOnItemClickListener { _, _, position, _ ->
            viewModel.type.set(SensorType.values()[position])
            binding.subTypeTextView.setText("")

            val subtypeArr = when (SensorType.values()[position]) {
                SensorType.Sensor -> {
                    arrayListOf(SensorSubtype.switch)
                }
                SensorType.Camera -> {
                    arrayListOf(SensorSubtype.onetime)
                }
                SensorType.Sound -> {
                    arrayListOf(SensorSubtype.onetime, SensorSubtype.level)
                }
                SensorType.Light -> {
                    arrayListOf(SensorSubtype.onetime, SensorSubtype.level)
                }
            }

            binding.subTypeTextView.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_item,
                    subtypeArr
                )
            )
            binding.subTypeTextView.setOnItemClickListener { _, _, pposition, _ ->
                viewModel.subtype.set(subtypeArr[pposition])
            }

        }

        viewModel.navEvent.observe(viewLifecycleOwner) {
            if (it) {
                val onj = SensorEntity(
                    viewModel.roomName.get().toString(),
                    viewModel.type.get().toString(),
                    viewModel.subtype.get().toString(),
                    PseudoValue.getBy(viewModel.type.get(), viewModel.subtype.get())
                )
                val codedDataInString = Json.encodeToString(SensorEntity.serializer(), onj)
                setFragmentResult(
                    Constants.FRAGMENT_RESULT_KEY,
                    Bundle().apply {
                        putString(
                            Constants.FRAGMENT_RESULT_VARIABLE_KEY,
                            codedDataInString
                        )
                    })
                navigateBack()
            }
        }
    }
}

