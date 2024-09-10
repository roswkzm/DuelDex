package com.example.dueldex.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val ldDispatcher: LdDispatchers)

enum class LdDispatchers {
    Default,
    IO,
}