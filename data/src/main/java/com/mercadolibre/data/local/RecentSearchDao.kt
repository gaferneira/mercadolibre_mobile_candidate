package com.mercadolibre.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mercadolibre.data.entities.RecentSearchEntity

@Dao
interface  RecentSearchDao {

    @Query("SELECT * FROM RecentSearchEntity ORDER BY `lastSearchDate` DESC")
    fun getAll(): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RecentSearchEntity)

}
