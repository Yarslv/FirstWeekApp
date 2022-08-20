package com.internship.firstweekapp.retrofit

import com.internship.firstweekapp.arch.mapper.Mapper
import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.ui.result_list.RecordListItemModel
import org.koin.java.KoinJavaComponent.inject

class SimplifyRecordListMapper: Mapper<RecordList, ArrayList<RecordListItemModel>> {
    val player: MediaPlayerProvider by inject(MediaPlayerProvider::class.java)

    override fun toDomain(model: RecordList): ArrayList<RecordListItemModel> {
        val out = arrayListOf<RecordListItemModel>()
        model.recordings.forEach {
            out.add(RecordListItemModel(it, player))
        }
        return out
    }
}