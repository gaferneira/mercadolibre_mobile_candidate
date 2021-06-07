package com.mercadolibre.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mercadolibre.data.entities.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
}
