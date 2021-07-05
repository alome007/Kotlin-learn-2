/*
 *  * Copyright (c)  2021  Shabinder Singh
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  *  You should have received a copy of the GNU General Public License
 *  *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.shabinder.common.di

import android.util.Log
import com.mpatric.mp3agic.ID3v1Tag
import com.mpatric.mp3agic.ID3v24Tag
import com.mpatric.mp3agic.Mp3File
import com.shabinder.common.models.DownloadResult
import com.shabinder.common.models.TrackDetails
import kotlinx.coroutines.flow.collect
import java.io.File
import java.io.FileInputStream

fun Mp3File.removeAllTags(): Mp3File {
    removeId3v1Tag()
    removeId3v2Tag()
    removeCustomTag()
    return this
}

fun Mp3File.setId3v1Tags(track: TrackDetails): Mp3File {
    val id3v1Tag = ID3v1Tag().apply {
        artist = track.artists.joinToString(", ")
        title = track.title
        album = track.albumName
        year = track.year
        comment = "Genres:${track.comment}"
    }
    this.id3v1Tag = id3v1Tag
    return this
}

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun Mp3File.setId3v2TagsAndSaveFile(track: TrackDetails) {
    val id3v2Tag = ID3v24Tag().apply {

        artist = track.artists.joinToString(", ")
        title = track.title
        album = track.albumName
        year = track.year
        comment = "Genres:${track.comment}"
        lyrics = "Gonna Implement Soon"
        url = track.trackUrl
    }
    try {
        val art = File(track.albumArtPath)
        val bytesArray = ByteArray(art.length().toInt())
        val fis = FileInputStream(art)
        fis.read(bytesArray) // read file into bytes[]
        fis.close()
        id3v2Tag.setAlbumImage(bytesArray, "image/jpeg")
        this.id3v2Tag = id3v2Tag
        saveFile(track.outputFilePath)
    } catch (e: java.io.FileNotFoundException) {
        Log.e("Error", "Couldn't Write Cached Mp3 Album Art, Downloading And Trying Again, error: ${e.message}")
        try {
            // Image Still Not Downloaded!
            // Lets Download Now and Write it into Album Art
            downloadFile(track.albumArtURL).collect {
                when (it) {
                    is DownloadResult.Error -> {} // Error
                    is DownloadResult.Success -> {
                        id3v2Tag.setAlbumImage(it.byteArray, "image/jpeg")
                        this.id3v2Tag = id3v2Tag
                        saveFile(track.outputFilePath)
                    }
                    is DownloadResult.Progress -> {} // Nothing for Now , no progress bar to show
                }
            }
        } catch (e: Exception) {
            Log.e("Error", "Couldn't Write Mp3 Album Art, error:")
            e.printStackTrace()
        }
    }
}

fun Mp3File.saveFile(filePath: String) {
    save(filePath.substringBeforeLast('.') + ".new.mp3")
    val m4aFile = File(filePath)
    m4aFile.delete()
    val newFile = File((filePath.substringBeforeLast('.') + ".new.mp3"))
    newFile.renameTo(File(filePath.substringBeforeLast('.') + ".mp3"))
}
