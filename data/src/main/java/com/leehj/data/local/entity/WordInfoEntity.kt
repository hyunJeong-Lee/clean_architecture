package com.leehj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leehj.domain.entity.Meaning
import com.leehj.domain.entity.WordInfo

/*
    Room 을 사용해 DB 에 저장될 엔티티 클래스 정의
 */
@Entity // Room 엔티티로 선언. 클래스의 인스턴스가 DB 테이블의 행으로 저장
data class WordInfoEntity(
    val word: String,
    val phonetic: String?,
    val origin: String?,
    val meanings: List<Meaning>?, //domain layer 에 의존하고 있기 때문에 Meaning 을 불러올 수 있다.
    @PrimaryKey val id: Int? = null //기본키
){
    /*
        WordInfoEntity 객체를 WordInfo 객체로 변환. (DB 에서 가져온 객체를 UI 모델 객체로 변환)
     */
    fun toWordInfo(): WordInfo{
        return WordInfo(
            meanings = meanings,
            word = word,
            origin = origin,
            phonetic = phonetic
        )
    }
}
