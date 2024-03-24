package com.sir.rickandmorty.cache

import com.sir.rickandmorty.cache.room.RickAndMortyDatabase
import com.sir.rickandmorty.repository.interfaces.RickAndMortyCache

class RickAndMortyCacheImpl(
    private val roomDatabase: RickAndMortyDatabase
) : RickAndMortyCache {

}