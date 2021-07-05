/*
 * Copyright (c)  2021  Shabinder Singh
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.shabinder.common.di.providers

import co.touchlab.kermit.Kermit
import com.shabinder.common.di.Dir
import com.shabinder.common.di.finalOutputDir
import com.shabinder.common.di.providers.requests.gaana.GaanaRequests
import com.shabinder.common.models.DownloadStatus
import com.shabinder.common.models.PlatformQueryResult
import com.shabinder.common.models.SpotiFlyerException
import com.shabinder.common.models.TrackDetails
import com.shabinder.common.models.event.coroutines.SuspendableEvent
import com.shabinder.common.models.gaana.GaanaTrack
import com.shabinder.common.models.spotify.Source
import io.ktor.client.*

class GaanaProvider(
    override val httpClient: HttpClient,
    private val logger: Kermit,
    private val dir: Dir,
) : GaanaRequests {

    private val gaanaPlaceholderImageUrl = "https://a10.gaanacdn.com/images/social/gaana_social.jpg"

    suspend fun query(fullLink: String): SuspendableEvent<PlatformQueryResult,Throwable> = SuspendableEvent {
        // Link Schema: https://gaana.com/type/link
        val gaanaLink = fullLink.substringAfter("gaana.com/")

        val link = gaanaLink.substringAfterLast('/', "error")
        val type = gaanaLink.substringBeforeLast('/', "error").substringAfterLast('/')

        // Error
        if (type == "Error" || link == "Error") {
            throw SpotiFlyerException.LinkInvalid()
        }

        gaanaSearch(
            type,
            link
        )
    }

    private suspend fun gaanaSearch(
        type: String,
        link: String,
    ): PlatformQueryResult {
        val result = PlatformQueryResult(
            folderType = "",
            subFolder = link,
            title = link,
            coverUrl = gaanaPlaceholderImageUrl,
            trackList = listOf(),
            Source.Gaana
        )
        logger.i { "GAANA SEARCH: $type - $link" }
        with(result) {
            when (type) {
                "song" -> {
                    getGaanaSong(seokey = link).tracks.firstOrNull()?.also {
                        folderType = "Tracks"
                        subFolder = ""
                        trackList = listOf(it).toTrackDetailsList(folderType, subFolder)
                        title = it.track_title
                        coverUrl = it.artworkLink.replace("http:", "https:")
                    }
                }
                "album" -> {
                    getGaanaAlbum(seokey = link).also {
                        folderType = "Albums"
                        subFolder = link
                        trackList = it.tracks?.toTrackDetailsList(folderType, subFolder) ?: emptyList()
                        title = link
                        coverUrl = it.custom_artworks.size_480p.replace("http:", "https:")
                    }
                }
                "playlist" -> {
                    getGaanaPlaylist(seokey = link).also {
                        folderType = "Playlists"
                        subFolder = link
                        trackList = it.tracks.toTrackDetailsList(folderType, subFolder)
                        title = link
                        // coverUrl.value = "TODO"
                        coverUrl = gaanaPlaceholderImageUrl
                    }
                }
                "artist" -> {
                    folderType = "Artist"
                    subFolder = link
                    coverUrl = gaanaPlaceholderImageUrl
                    getGaanaArtistDetails(seokey = link).artist.firstOrNull()
                        ?.also {
                            title = it.name
                            coverUrl = it.artworkLink?.replace("http:", "https:") ?: gaanaPlaceholderImageUrl
                        }
                    getGaanaArtistTracks(seokey = link).also {
                        trackList = it.tracks?.toTrackDetailsList(folderType, subFolder) ?: emptyList()
                    }
                }
                else -> {
                    // TODO Handle Error
                }
            }
            return result
        }
    }

    private fun List<GaanaTrack>.toTrackDetailsList(type: String, subFolder: String) = this.map {
        TrackDetails(
            title = it.track_title,
            artists = it.artist.map { artist -> artist?.name.toString() },
            durationSec = it.duration,
            albumArtPath = dir.imageCacheDir() + (it.artworkLink.substringBeforeLast('/').substringAfterLast('/')) + ".jpeg",
            albumName = it.album_title,
            year = it.release_date,
            comment = "Genres:${it.genre?.map { genre -> genre?.name }?.reduceOrNull { acc, s -> acc + s }}",
            trackUrl = it.lyrics_url,
            downloaded = it.updateStatusIfPresent(type, subFolder),
            source = Source.Gaana,
            albumArtURL = it.artworkLink.replace("http:", "https:"),
            outputFilePath = dir.finalOutputDir(it.track_title, type, subFolder, dir.defaultDir()/*,".m4a"*/)
        )
    }

    private fun GaanaTrack.updateStatusIfPresent(folderType: String, subFolder: String): DownloadStatus {
        return if (dir.isPresent(
                dir.finalOutputDir(
                        track_title,
                        folderType,
                        subFolder,
                        dir.defaultDir()
                    )
            )
        ) { // Download Already Present!!
            DownloadStatus.Downloaded.also {
                downloaded = it
            }
        } else downloaded ?: DownloadStatus.NotDownloaded
    }
}
