package com.leehj.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.leehj.domain.entity.Meaning
import com.plcoding.dictionary.feature_dictionary.data.util.JsonParser


/*
    Room 라이브러리에서 Meaning 객체 <-> JSON 변환에 사용되는 타입 컨버터
 */
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun jsonToMeaning(json: String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun meaningsToJson(meanings: List<Meaning>): String{
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }

}