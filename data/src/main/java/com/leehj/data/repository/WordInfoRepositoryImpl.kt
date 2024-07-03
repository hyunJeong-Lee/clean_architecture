package com.leehj.data.repository

import com.leehj.data.datasource.WordInfoDataSource
import com.leehj.domain.entity.WordInfo
import com.leehj.domain.repository.WordInfoRepository
import com.leehj.domain.util.Result
import com.leehj.domain.util.getResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordInfoRepositoryImpl(
    private val localDataSource: WordInfoDataSource.local,
    private val remoteDataSource: WordInfoDataSource.remote
): WordInfoRepository  {
    override fun getWordInfo(word: String): Flow<Result<List<WordInfo>>> = flow {
        emit(Result.Loading()) // data Loading...

        //DB에서 word 에 대한 정보를 가져와 map 함수를 사용해 WordInfoEntity(DB Entity) 객체를 WordInfo(Domain Entity) 객체로 변환
        val localWordInfo = localDataSource.getWordInfo(word).map { it.toWordInfo() }
        emit(Result.Loading(data = localWordInfo))

        remoteDataSource.getWordInfo(word).getResult(
            success = { apiResult ->
                val remoteWordInfo = apiResult.data.map { it.toWordInfoEntity() }
                localDataSource.deleteWordInfo(remoteWordInfo.map { it.word })
                localDataSource.insertWordInfo(remoteWordInfo)

                val newWordInfo = localDataSource.getWordInfo(word).map { it.toWordInfo() }
                emit(Result.Success(newWordInfo))
            },
            error = { apiError ->
                emit(Result.Error(
                        message = apiError.error.message,
                        data = localWordInfo
                ))
            }
        )
    }
}