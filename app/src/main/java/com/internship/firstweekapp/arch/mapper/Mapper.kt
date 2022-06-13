package com.internship.firstweekapp.arch.mapper

interface Mapper<in Model, out DomainModel> {

    fun toDomain(model: Model): DomainModel
}