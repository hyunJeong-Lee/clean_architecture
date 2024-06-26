package com.leehj.domain.usecase

import com.leehj.domain.entity.WordInfo
import com.leehj.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(private val repository: WordInfoRepository) {
    operator fun invoke(word: String): Flow<Result<List<WordInfo>>> {
        if(word.isBlank()){
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}

/*
    1. invoke 연산자 오버로딩
    : invoke 연산자를 오버로딩하여 GetWordInfo 클래스의 인스턴스를 함수처럼 호출할 수 있게 한다.
    -> word 라는 문자열을 입력받아 Flow<Result<List<WordInfo>>> 타입의 값을 반환
 */