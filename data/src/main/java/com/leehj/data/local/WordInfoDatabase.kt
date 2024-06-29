package com.leehj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.leehj.data.local.entity.WordInfoEntity

// entities : 사용할 엔티티 선언, version : 엔티티 구조 변경 시 구분
@Database(entities = [WordInfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao
}