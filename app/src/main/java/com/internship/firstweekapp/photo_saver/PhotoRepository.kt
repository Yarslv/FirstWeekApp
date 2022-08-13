package com.internship.firstweekapp.photo_saver

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.databinding.ObservableField
import com.internship.firstweekapp.Constants
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class PhotoRepository {


    var fileStack: ArrayDeque<File> = ArrayDeque()
    val out = ObservableField<Bitmap>()

    fun forceClear() {
        fileStack.clear()
        out.set(null)
    }

    fun add(file: File) {
        while (fileStack.size > Constants.MAX_SIZE) {
            fileStack.removeFirst()
        }
        fileStack.addLast(file)
        out.set(getBitmap())
    }

    fun removeLast() {
        fileStack.removeLast()
        out.set(getBitmap())
    }

    fun add(bitmap: Bitmap) {
        val newFile = kotlin.io.path.createTempFile().toFile()
        val os = FileOutputStream(newFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.flush()
        os.close()
        add(newFile)
    }

    fun getBitmap(): Bitmap {
        return BitmapFactory.decodeFile(getImageFile().path)
    }

    private fun getImageFile(): File {
        return fileStack.last()
    }

    fun save(): String {
        val newFile =
            File(fileStack.first().path.replace(Constants.FILE_EXT, Constants.EDITED_FILE_EXT))
        val os = FileOutputStream(newFile.path)
        val buf = BufferedOutputStream(os)
        getBitmap().compress(Bitmap.CompressFormat.JPEG, Constants.OUTPUT_QUALITY, buf)
        buf.flush()
        buf.close()
        return newFile.absolutePath
    }
}