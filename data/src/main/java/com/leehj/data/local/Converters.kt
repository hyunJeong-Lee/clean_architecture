package com.leehj.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.leehj.domain.entity.Meaning


/*
    Room 라이브러리에서 Meaning 객체 <-> JSON 변환에 사용되는 타입 컨버터
 */
class Converters {
    @TypeConverter
    fun jsonToMeaning(json: String): List<Meaning>{
        return Gson().fromJson(json, Array<Meaning>::class.java).toList()
    }

    @TypeConverter
    fun meaningToJson(meanings: List<Meaning>): String{
        return Gson().toJson(meanings)

    }

}