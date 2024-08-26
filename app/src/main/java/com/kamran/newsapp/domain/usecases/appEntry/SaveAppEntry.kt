package com.kamran.newsapp.domain.usecases.appEntry

import com.kamran.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class SaveAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}