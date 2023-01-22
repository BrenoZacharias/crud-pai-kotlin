package br.com.crudpai.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.crudpai.data.db.entity.ClienteEntity

@Dao
interface ClienteDAO {
    @Insert
    suspend fun insert(cliente : ClienteEntity): Long

    @Update
    suspend fun update(cliente: ClienteEntity)

    @Query("DELETE FROM cliente WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM cliente")
    suspend fun deleteAll()

    @Query("SELECT * FROM cliente")
    fun getAll(): LiveData<List<ClienteEntity>>
}