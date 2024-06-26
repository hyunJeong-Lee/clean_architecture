package com.leehj.domain.repository

import com.leehj.domain.entity.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Result<List<WordInfo>>>
}