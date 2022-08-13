package com.internship.firstweekapp.ui.choose_photo

import android.content.Intent
import android.provider.MediaStore

enum class ImportResource(val intent: Intent) {
    Gallery(
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
    ),
    Camera(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
}