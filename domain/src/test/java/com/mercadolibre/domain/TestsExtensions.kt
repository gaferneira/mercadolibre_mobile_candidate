package com.mercadolibre.domain

import io.mockk.mockk

inline fun <reified T : Any> relaxedMockk(block: T.() -> Unit = {}): T {
    return mockk(relaxed = true, relaxUnitFun = true, block = block)
}
