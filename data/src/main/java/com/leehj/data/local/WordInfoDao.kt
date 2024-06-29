package com.leehj.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leehj.data.local.entity.WordInfoEntity

/*
    Room 라이브러리에서 Database Access 를 위한 DAO
 */
@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //동일한 id 가 있을 시 새로운 데이터로 교체
    suspend fun insertWordInfo(wordInfo: List<WordInfoEntity>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>

    @Query("DELETE FROM wordinfoentity WHERE word IN(:word)")
    suspend fun deleteWordInfo(word: List<String>)
}
