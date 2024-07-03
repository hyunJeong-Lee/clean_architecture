package com.leehj.data.datasource

import com.leehj.data.local.entity.WordInfoEntity
import com.leehj.data.remote.dto.WordInfoDto
import com.leehj.domain.util.ApiResult

interface WordInfoDataSource {
    interface remote{
        suspend fun getWordInfo(word: String): ApiResult<List<WordInfoDto>>
    }

    interface local{
        suspend fun getWordInfo(word: String): List<WordInfoEntity>
        suspend fun insertWordInfo(wordInfo: List<WordInfoEntity>)
        suspend fun deleteWordInfo(word: List<String>)
    }
}