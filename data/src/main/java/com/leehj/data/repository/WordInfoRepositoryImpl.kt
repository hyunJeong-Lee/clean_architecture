package com.leehj.data.repository

import com.leehj.data.local.WordInfoDao
import com.leehj.domain.entity.WordInfo
import com.leehj.domain.repository.WordInfoRepository
import com.leehj.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoRepositoryImpl(
    private val dao: WordInfoDao
): WordInfoRepository  {
    override fun getWordInfo(word: String): Flow<Result<List<WordInfo>>> = flow {
        emit(Result.Loading()) // data Loading...

        //DB에서 word 에 대한 정보를 가져와 map 함수를 사용해 WordInfoEntity(DB Entity) 객체를 WordInfo(Domain Entity) 객체로 변환
        val wordInfo = dao.getWordInfo(word).map { it.toWordInfo() }

        /*
        TODO : 1. API 를 사용해 네트워크에서 단어 정보 가져오기. 2. 가져온 단어 정보 DB 저장.
         */


        emit(Result.Success(data = wordInfo)) // Flow 에 Success 상태 방출 및 로컬 DB 에서 가져온 단어 정보 함께 전달
    }
}