package com.kamran.newsapp.domain.usecases.appEntry

import com.kamran.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke() : Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}