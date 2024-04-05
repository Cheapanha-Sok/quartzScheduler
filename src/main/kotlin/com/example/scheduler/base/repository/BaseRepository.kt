package com.example.scheduler.base.repository

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*


@NoRepositoryBean
interface BaseRepository<T , ID> : Repository<T , ID> {

    fun <S : T?> saveAll(entities: Iterable<S>?): Iterable<S>?
    fun findById(id: ID): Optional<T>?
    fun existsById(id: ID): Boolean
    fun findAll(): List<T>?
    fun count(): Long
    fun deleteById(id: ID)
    fun delete(entity: T)
    fun <S : T?> save(entity: S): S
}