package com.internship.firstweekapp.ui.choose_photo

import android.Manifest
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.ChoosePhotoResourceFragmentBinding
import com.internship.firstweekapp.photo_saver.PhotoRepository
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*
import kotlin.io.path.outputStream

class ChoosePhotoFragment :
    BaseFragment<ChoosePhotoResourceFragmentBinding>(R.layout.choose_photo_resource_fragment) {
    override val viewModel: ChoosePhotoFragmentViewModel by viewModel()
    private val photoRepository: PhotoRepository by inject()
    private val activityResultGalery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.data != null) {
            val stream =
                result.data?.data?.let { requireActivity().contentResolver.openInputStream(it) }
            val tempFile = kotlin.io.path.createTempFile()
            stream.use { input ->
                tempFile.outputStream().use { output ->
                    input?.copyTo(output)
                }
            }
            photoRepository.add(tempFile.toFile())
            findNavController().navigate(ChoosePhotoFragmentDirections.actionChoosePhotoFragmentToPhotoEditorFragment())

        }
    }
    val activityResultCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            photoRepository.add(tempUri.toFile())
            findNavController().navigate(ChoosePhotoFragmentDirections.actionChoosePhotoFragmentToPhotoEditorFragment())
        }
    }
    private lateinit var tempUri: Uri

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        photoRepository.forceClear()
        if (requireActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            createImage()
        } else {
            requireActivity().requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 0
            )
            createImage()
        }
    }

    private fun createImage() {
        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            when (it) {
                ImportResource.Gallery -> {
                    activityResultGalery.launch(it.intent)
                }
                ImportResource.Camera -> {
                    val timeStamp: String =
                        SimpleDateFormat(Constants.DATA_TIME_PATTERN, Locale.UK).format(Date())
                    val imageFileName = getString(R.string.filename, timeStamp, Constants.FILE_EXT)
                    val storageDir: File = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES
                    )
                    val pictureImagePath = getString(
                        R.string.image_path,
                        storageDir.absolutePath.toString(), imageFileName
                    )

                    val file = File(pictureImagePath)
                    tempUri = Uri.fromFile(file)

                    activityResultCamera.launch(tempUri)
                }

            }

        }
    }
}