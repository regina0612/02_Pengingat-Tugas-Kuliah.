package data

import kotlinx.coroutines.flow.Flow

interface Sumber<T> {
    suspend fun ambil(id: String): T
    fun pantau(id: String): Flow<T>
}