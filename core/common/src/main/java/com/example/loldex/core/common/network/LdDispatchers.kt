package com.example.loldex.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val ldDispatcher: LdDispatchers)

enum class LdDispatchers {
    Default,
    IO,
}