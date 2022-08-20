package com.internship.firstweekapp.ui.result_list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentResultListBinding
import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.navigate
import com.internship.firstweekapp.retrofit.Record
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

class ResultListFragment : BaseFragment<FragmentResultListBinding>(R.layout.fragment_result_list) {
    override val viewModel: ResultListFragmentViewModel by viewModel()
    private val args: ResultListFragmentArgs by navArgs()

    private val mediaPlayerProvider: MediaPlayerProvider by KoinJavaComponent.inject(
        MediaPlayerProvider::class.java
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        viewModel.call(args.query)

        viewModel.navigateEvent.observe(viewLifecycleOwner) {
            navigate(
                ResultListFragmentDirections.actionResultListFragmentToRecordDetailFragment(
                    Json.encodeToString(Record.serializer(), it)
                )
            )
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerProvider.stop()
    }
}