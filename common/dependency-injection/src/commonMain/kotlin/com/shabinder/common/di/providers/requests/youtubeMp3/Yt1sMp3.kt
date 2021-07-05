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

package com.shabinder.common.di.providers.requests.youtubeMp3

import co.touchlab.kermit.Kermit
import com.shabinder.common.models.corsApi
import com.shabinder.common.models.event.coroutines.SuspendableEvent
import com.shabinder.common.models.event.coroutines.flatMap
import com.shabinder.common.models.event.coroutines.map
import com.shabinder.common.requireNotNull
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

/*
* site link: https://yt1s.com/youtube-to-mp3/en1
* Provides Direct Mp3 , No Need For FFmpeg
* */
interface Yt1sMp3 {

    val httpClient: HttpClient
    val logger: Kermit

    /*
    * Downloadable Mp3 Link for YT videoID.
    * */
    suspend fun getLinkFromYt1sMp3(videoID: String): SuspendableEvent<String,Throwable> = getKey(videoID).flatMap { key ->
        getConvertedMp3Link(videoID, key).map {
            it["dlink"].requireNotNull()
                .jsonPrimitive.content.replace("\"", "")
        }
    }

    /*
    * POST:https://yt1s.com/api/ajaxSearch/index
    * Body Form= q:yt video link ,vt:format=mp3
    * */
    private suspend fun getKey(videoID: String): SuspendableEvent<String,Throwable> = SuspendableEvent {
        val response: JsonObject = httpClient.post("${corsApi}https://yt1s.com/api/ajaxSearch/index") {
            body = FormDataContent(
                Parameters.build {
                    append("q", "https://www.youtube.com/watch?v=$videoID")
                    append("vt", "mp3")
                }
            )
        }

        response["kc"].requireNotNull().jsonPrimitive.content
    }

    private suspend fun getConvertedMp3Link(videoID: String, key: String): SuspendableEvent<JsonObject,Throwable> = SuspendableEvent {
        httpClient.post("${corsApi}https://yt1s.com/api/ajaxConvert/convert") {
            body = FormDataContent(
                Parameters.build {
                    append("vid", videoID)
                    append("k", key)
                }
            )
        }
    }
}
