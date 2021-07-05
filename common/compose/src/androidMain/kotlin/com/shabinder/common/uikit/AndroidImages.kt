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

@file:Suppress("FunctionName")

package com.shabinder.common.uikit

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.shabinder.common.database.R
import com.shabinder.common.translations.Strings
import kotlinx.coroutines.flow.MutableStateFlow

actual fun montserratFont() = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
)

actual fun pristineFont() = FontFamily(
    Font(R.font.pristine_script, FontWeight.Bold)
)

@Composable
actual fun DownloadImageTick() {
    Image(
        painterResource(R.drawable.ic_tick),
        Strings.downloadDone()
    )
}

@Composable
actual fun DownloadImageError() {
    Image(
        painterResource(R.drawable.ic_error),
        Strings.downloadError()
    )
}

@Composable
actual fun DownloadImageArrow(modifier: Modifier) {
    Image(
        painterResource(R.drawable.ic_arrow),
        Strings.downloadStart(),
        modifier
    )
}

@Composable
actual fun DownloadAllImage() = painterResource(R.drawable.ic_download_arrow)

@Composable
actual fun ShareImage() = painterResource(R.drawable.ic_share_open)

@Composable
actual fun PlaceHolderImage() = painterResource(R.drawable.ic_song_placeholder)

@Composable
actual fun SpotiFlyerLogo() = painterResource(R.drawable.ic_spotiflyer_logo)

@Composable
actual fun HeartIcon() = painterResource(R.drawable.ic_heart)

@Composable
actual fun SpotifyLogo() = painterResource(R.drawable.ic_spotify_logo)

@Composable
actual fun SaavnLogo() = painterResource(R.drawable.ic_jio_saavn_logo)

@Composable
actual fun GaanaLogo() = painterResource(R.drawable.ic_gaana)

@Composable
actual fun YoutubeLogo() = painterResource(R.drawable.ic_youtube)

@Composable
actual fun YoutubeMusicLogo() = painterResource(R.drawable.ic_youtube_music_logo)

@Composable
actual fun GithubLogo() = painterResource(R.drawable.ic_github)

@Composable
actual fun PaypalLogo() = painterResource(R.drawable.ic_paypal_logo)

@Composable
actual fun OpenCollectiveLogo() = painterResource(R.drawable.ic_opencollective_icon)

@Composable
actual fun RazorPay() = painterResource(R.drawable.ic_indian_rupee)

@Composable
actual fun Toast(
    flow: MutableStateFlow<String>,
    duration: ToastDuration
) {
    // We Have Android's Implementation of Toast so its just Empty
}
