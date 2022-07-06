package com.anafthdev.musicompose2.data.datasource.local.db

import androidx.room.TypeConverter
import com.anafthdev.musicompose2.data.model.Song
import com.google.gson.Gson

object DatabaseTypeConverter {

	@TypeConverter
	fun songsToJson(songs: List<Song>) = Gson().toJson(songs)!!
	
	@TypeConverter
	fun songsFromJson(json: String): List<Song> {
		return (Gson().fromJson(json, Array<Song>::class.java) ?: emptyArray()).toList()
	}
	
}