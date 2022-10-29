package com.example.mycomposeapplication.data.network.mappers

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}