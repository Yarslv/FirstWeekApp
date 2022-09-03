package com.internship.firstweekapp.ui.tabs.cities_list.recycler

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter

class CitiesListAdapter(val newListHandler: ListHandler) :
    ListAdapter<CityModel, CitiesListViewHolder>(CitiesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesListViewHolder {
        return CitiesListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CitiesListViewHolder, position: Int) {
        holder.bind(getItem(position).apply {
            setHomeEvent.observe(holder.itemView.context as LifecycleOwner) {
                resubmitList(currentList, position)

            }
        })
    }

    private fun resubmitList(list: List<CityModel>, selected: Int) {
        val new = arrayListOf<CityModel>()
        list.forEach {
            new.add(
                CityModel(
                    id = it.id,
                    current = it.current,
                    location = it.location,
                    day = it.day,
                    selected = false
                )
            )
        }
        new[selected].selected = true
        newListHandler.handle(new)
        submitList(new)
    }

    fun resubmitList(list: List<CityModel>) {
        val new = arrayListOf<CityModel>()
        list.forEach {
            new.add(
                CityModel(
                    id = it.id,
                    current = it.current,
                    location = it.location,
                    day = it.day,
                    selected = it.selected
                )
            )
        }
        submitList(new)
    }
}