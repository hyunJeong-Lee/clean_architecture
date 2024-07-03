package com.leehj.data.datasource

import com.leehj.data.remote.DictionaryApi
import com.leehj.data.remote.dto.WordInfoDto
import com.leehj.domain.util.ApiResult

class WordInfoRemoteSourceImpl(private val dictionaryApi: DictionaryApi): WordInfoDataSource.remote {
    override suspend fun getWordInfo(word: String): ApiResult<List<WordInfoDto>> = try{
        val result = dictionaryApi.getWordInfo(word)
        ApiResult.Success(result)
    }catch (e: Exception){
        ApiResult.Error(e)
    }
}