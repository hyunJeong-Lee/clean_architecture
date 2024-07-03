package com.leehj.data.datasource

import com.leehj.data.local.WordInfoDao
import com.leehj.data.local.entity.WordInfoEntity

class WordInfoLocalSourceImpl(private val dao: WordInfoDao): WordInfoDataSource.local {
    override suspend fun getWordInfo(word: String): List<WordInfoEntity> {
        return dao.getWordInfo(word)
    }

    override suspend fun insertWordInfo(wordInfo: List<WordInfoEntity>) {
        dao.insertWordInfo(wordInfo)
    }

    override suspend fun deleteWordInfo(word: List<String>) {
        dao.deleteWordInfo(word)
    }
}