package com.internship.firstweekapp.ui.add_card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.databinding.FragmentAddCardBinding
import com.internship.firstweekapp.ui.card_list.navigateBack
import com.internship.firstweekapp.util.models.SensorEntity
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToStream
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCardFragment: BaseFragment<FragmentAddCardBinding>(R.layout.fragment_add_card) {
    override val viewModel: AddCardFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        val test = "{ “room”: “Kitchen”, “type”: “Sensor”, “subtype”: “switch”, “value”: “on” }"
            .replace("”", "\"")
            .replace("“", "\"")

        val onj = SensorEntity("Kitchen2", "Light", "level", (0..10).random().toString())
        val str= Json.encodeToString(SensorEntity.serializer(), onj)

        setFragmentResult("key", Bundle().apply { putString("strKey", str) })
        navigateBack()
    }
}