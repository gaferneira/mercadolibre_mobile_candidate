package com.mercadolibre.mobile.factory

import java.util.UUID
import kotlin.random.Random

object DataFactory {

    fun getRandomString(): String {
        return UUID.randomUUID().toString().substring(0, 15)
    }

    fun getRandomInt(): Int {
        return Random.nextInt()
    }

    fun getRandomLong(): Long {
        return Random.nextLong()
    }

    fun getRandomDouble(): Double {
        return Random.nextDouble()
    }

    fun getRandomFloat(): Float {
        return Random.nextFloat()
    }

    fun getRandomBoolean(): Boolean {
        return Random.nextBoolean()
    }
}
