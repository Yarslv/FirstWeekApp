package com.internship.firstweekapp.ui.notes_list

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.NotesRecyclerBinding
import com.internship.firstweekapp.util.navigate
import com.internship.firstweekapp.ui.main.MainScreen
import com.internship.firstweekapp.ui.notes_list.note_adapter.NotesRecyclerAdapter
import com.internship.firstweekapp.util.AddOrEditFlag
import com.internship.firstweekapp.util.Direction
import com.internship.firstweekapp.util.SortBy
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesListFragment : BaseFragment<NotesRecyclerBinding>(R.layout.notes_recycler) {
    override val viewModel: NotesListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.recycler.adapter = NotesRecyclerAdapter()
        viewModel.navigationEvent.observe(
            viewLifecycleOwner
        ) {
            navigate(
                NotesListFragmentDirections.actionNotesListFragmentToAddEditNoteFragment(
                    AddOrEditFlag.Add.name
                )
            )
        }

        attachDeleteSwipe()

        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as MainScreen).showDrawer()
        }
        setDrawerIconClick()
        setToolbarItemClickedListener()
    }

    override fun onResume() {

        super.onResume()
        if(viewModel.direction.value != null)
            viewModel.setList(viewModel.direction.value!!)
    }

    private fun attachDeleteSwipe() {
        val swipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.remove(viewModel.list.removeAt(viewHolder.adapterPosition))
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val background = ColorDrawable().apply {
                    color = Color.RED
                    setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }
                background.draw(canvas)

                val icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_delete_24)

                val itemHeight = itemView.bottom - itemView.top

                val deleteIconTop = itemView.top + itemHeight / 5 // 5 = 20%
                val deleteIconMargin = (itemHeight - icon!!.intrinsicHeight) / 2
                val deleteIconLeft = itemView.right - deleteIconMargin - itemHeight / 2
                val deleteIconRight = itemView.right - deleteIconMargin
                val deleteIconBottom = itemView.bottom - itemHeight / 5

                icon.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
                icon.draw(canvas)

                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        ItemTouchHelper(swipe).attachToRecyclerView(binding.recycler)
    }

    private fun setDrawerIconClick() {
        (requireActivity() as MainScreen).getNavigationView().setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.toList -> {}
                R.id.toEmergency -> {}
                R.id.toHelp -> {}
            }
            true
        }
    }

    private fun setToolbarItemClickedListener() {
        binding.toolbar.setOnMenuItemClickListener { it_main ->
            when (it_main.itemId) {
                R.id.date -> {
                    viewModel.sortBy.postValue(SortBy.Date)
                    setDirections(it_main)
                }
                R.id.title -> {
                    viewModel.sortBy.postValue(SortBy.Title)
                    setDirections(it_main)
                }
                R.id.color -> {
                    viewModel.sortBy.postValue(SortBy.Color)
                    setDirections(it_main)
                }
                R.id.content -> {
                    viewModel.sortBy.postValue(SortBy.Content)
                    setDirections(it_main)
                }
                else -> {}

            }
            true
        }
    }

    private fun setDirections(it_main: MenuItem) {
        when (viewModel.direction.value) {
            Direction.Normal -> {
                it_main.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_expand_less_24)
                viewModel.direction.postValue(Direction.Reverse)
            }
            Direction.Reverse -> {
                it_main.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_expand_more_24)
                viewModel.direction.postValue(Direction.Normal)
            }
            null -> {}
        }
    }
}
