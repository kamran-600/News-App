package com.kamran.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.kamran.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source : Source) : String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String) : Source{
        return string.split(",").let { sourceStringArray ->
            Source(sourceStringArray[0], sourceStringArray[1])
        }
    }


}