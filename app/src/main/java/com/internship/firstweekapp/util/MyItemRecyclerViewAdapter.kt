package com.internship.firstweekapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.ItemMultiAnswerBinding
import com.internship.firstweekapp.databinding.ItemSingleAnswerBinding
import com.internship.firstweekapp.databinding.ItemWritingAnswerBinding
import com.internship.firstweekapp.models.MultiAnswerQuestionModel
import com.internship.firstweekapp.models.Question
import com.internship.firstweekapp.models.SingleAnswerQuestionModel
import com.internship.firstweekapp.models.TextWritingAnswerQuestionModel

enum class QuestionType(val value: Int) {
    SingleAnswerQuestion(0),
    MultiAnswerQuestion(1),
    TextWritingQuestion(2)
}


class MyItemRecyclerViewAdapter(
    private val values: List<Question>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            QuestionType.SingleAnswerQuestion.value -> ViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_single_answer, parent, false
                )
            )
            QuestionType.MultiAnswerQuestion.value -> ViewHolder2(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_multi_answer, parent, false
                )
            )
            QuestionType.TextWritingQuestion.value -> ViewHolder3(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_writing_answer, parent, false
                )
            )
            else -> {
                ViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_single_answer, parent, false
                    )
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
        when (holder) {
            is ViewHolder -> {
                holder.bind(item as SingleAnswerQuestionModel)
            }
            is ViewHolder2 -> {
                holder.bind(item as MultiAnswerQuestionModel)
            }
            is ViewHolder3 -> {
                holder.bind(item as TextWritingAnswerQuestionModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (values[position] is SingleAnswerQuestionModel) {
            return 0
        } else if (values[position] is MultiAnswerQuestionModel) {
            return 1
        } else if (values[position] is TextWritingAnswerQuestionModel) {
            return 2
        }
        return -1
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private var binding: ItemSingleAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SingleAnswerQuestionModel) {
            binding.model = item
        }
    }

    inner class ViewHolder2(private var binding: ItemMultiAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MultiAnswerQuestionModel) {
            binding.model = item
        }
    }

    inner class ViewHolder3(private var binding: ItemWritingAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TextWritingAnswerQuestionModel) {
            binding.model = item
        }
    }
}
