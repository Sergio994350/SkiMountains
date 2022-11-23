package com.epicteam1.skimountains.di

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val ioDispatcherName = "IODispatcher"
const val mainDispatcherName = "MainDispatcher"

val commonDispatchers = module {

    single(named(ioDispatcherName)) { IO }
    single(named(mainDispatcherName)) { Main }
}