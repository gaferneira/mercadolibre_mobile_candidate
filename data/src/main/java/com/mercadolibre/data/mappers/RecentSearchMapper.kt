package com.mercadolibre.data.mappers

import com.mercadolibre.data.entities.RecentSearchEntity
import com.mercadolibre.domain.entities.RecentSearch
import java.util.*

class RecentSearchMapper : Mapper<RecentSearchEntity, RecentSearch> {

    override fun mapFromEntity(type: RecentSearchEntity): RecentSearch {
        return with(type) {
            RecentSearch(
                query,
                Date(lastSearchDate)
            )
        }
    }

    override fun mapToEntity(type: RecentSearch): RecentSearchEntity {
        return with(type) {
            RecentSearchEntity(
                query,
                lastSearchDate.time
            )
        }
    }
}
