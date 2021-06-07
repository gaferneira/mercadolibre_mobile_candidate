package com.mercadolibre.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentSearchEntity(@PrimaryKey val query: String, var lastSearchDate: Long)
