package com.internship.firstweekapp.ui.record_detail

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentRecordDetailBinding
import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.retrofit.Record
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.inject

class RecordDetailFragment :
    BaseFragment<FragmentRecordDetailBinding>(R.layout.fragment_record_detail) {

    override val viewModel: RecordDetailFragmentViewModel by viewModel()
    private val args: RecordDetailFragmentArgs by navArgs()
    private val mediaPlayerProvider: MediaPlayerProvider by inject(MediaPlayerProvider::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val record = Json.decodeFromString(Record.serializer(), args.serializedRecordItem)
        binding.recordModel = record
        binding.viewModel = viewModel
        mediaPlayerProvider.setSource(record.file, record.id)
        viewModel.playEvent.observe(viewLifecycleOwner) {
            when (it) {
                true -> mediaPlayerProvider.play()
                false -> mediaPlayerProvider.pause()
            }
        }

        viewModel.downloadEvent.observe(viewLifecycleOwner) {
            if (it) {
                val uri = Uri.parse(record.file.replace("\\", "").replace("www.xeno", "https://xeno"))
                val downloadManager =
                    requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val request = DownloadManager.Request(uri)

                request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    record.file_name
                )

                downloadManager.enqueue(request)
            }
        }

        Glide.with(binding.image.context)
            .load(
                record.sono.large.replace(
                    "\\",
                    "|"
                ).replace("//xe", "https://xe")
            )

            .into(binding.image)
    }
}