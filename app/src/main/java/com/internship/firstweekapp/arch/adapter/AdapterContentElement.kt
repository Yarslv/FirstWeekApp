package com.internship.firstweekapp.arch.adapter

interface AdapterContentElement {

    fun areContentsTheSame(other: AdapterContentElement): Boolean
}