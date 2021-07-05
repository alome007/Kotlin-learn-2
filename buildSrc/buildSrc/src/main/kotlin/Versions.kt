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

@file:Suppress("MayBeConstant", "SpellCheckingInspection")

object Versions {
    // App's Version (To be bumped at each update)
    const val versionName = "3.1.0"
    const val versionCode = 20

    // Kotlin
    const val kotlinVersion = "1.5.10"
    const val coroutinesVersion = "1.5.0"

    // Code Formatting
    const val ktLint = "10.1.0"

    // DI
    const val koin = "3.1.0"

    // Logger
    const val kermit = "0.1.9"

    // Internet
    const val ktor = "1.6.0"

    const val kotlinxSerialization = "1.2.1"

    // Database
    const val sqlDelight = "1.5.0"

    const val sqliteJdbcDriver = "3.34.0"
    const val slf4j = "1.7.31"

    // Android
    const val minSdkVersion = 21
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val androidxLifecycle = "2.3.1"
}

object HostOS {
    // Host OS Properties
    private val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows",true)
    val isMac = hostOs.startsWith("Mac",true)
    val isLinux = hostOs.startsWith("Linux",true)
}

object MultiPlatformSettings {
    const val dep = "com.russhwolf:multiplatform-settings-no-arg:0.7.7"
}

object Koin {
    val core = "io.insert-koin:koin-core:${Versions.koin}"
    val test = "io.insert-koin:koin-test:${Versions.koin}"
    val android = "io.insert-koin:koin-android:${Versions.koin}"
    val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object Androidx {
    const val androidxActivity = "androidx.activity:activity-compose:1.3.0-beta02"
    const val core = "androidx.core:core-ktx:1.5.0"
    const val palette = "androidx.palette:palette-ktx:1.0.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    const val junit = "androidx.test.ext:junit:1.1.2"
    const val expresso = "androidx.test.espresso:espresso-core:3.3.0"

    /*object Compose{
        const val materialIcon = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    }*/
}

object JetBrains {
    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}"
        const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlinVersion}"
        const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersion}"
        const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlinVersion}"
    }

    object Compose {
        // __LATEST_COMPOSE_RELEASE_VERSION__
        const val VERSION = "0.4.0"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
    }
}

object Decompose {
    private const val VERSION = "0.2.6"
    const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
    const val decomposeIosX64 = "com.arkivanov.decompose:decompose-iosx64:$VERSION"
    const val decomposeIosArm64 = "com.arkivanov.decompose:decompose-iosarm64:$VERSION"
    const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
}

object MVIKotlin {
    private const val VERSION = "2.0.4"
    const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
    const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
    const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
    const val coroutines = "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$VERSION"
    const val keepers = "com.arkivanov.mvikotlin:keepers:$VERSION"
    const val mvikotlinMainIosX64 = "com.arkivanov.mvikotlin:mvikotlin-main-iosx64:$VERSION"
    const val mvikotlinMainIosArm64 = "com.arkivanov.mvikotlin:mvikotlin-main-iosarm64:$VERSION"
    const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
    const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
    const val mvikotlinExtensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
}

object Ktor {
    val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
    val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"

    val auth = "io.ktor:ktor-client-auth:${Versions.ktor}"
    val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    val clientCurl = "io.ktor:ktor-client-curl:${Versions.ktor}"
    val clientApache = "io.ktor:ktor-client-apache:${Versions.ktor}"
    val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    val clientCio = "io.ktor:ktor-client-cio:${Versions.ktor}"
    val clientJs = "io.ktor:ktor-client-js:${Versions.ktor}"
}

object Internationalization {
    const val dep = "de.comahe.i18n4k:i18n4k-core:0.1.1"
}

object Extras {
    const val youtubeDownloader = "io.github.shabinder:youtube-api-dl:1.2"
    const val fuzzyWuzzy = "io.github.shabinder:fuzzywuzzy:1.1"
    const val mp3agic = "com.mpatric:mp3agic:0.9.0"
    const val jaudioTagger = "com.github.Shabinder:JAudioTagger-Android:1.0"
    const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    object Android {
        object Acra {
            // Self Hosted Crashlytics (FOSS)
            private const val VERSION = "5.8.3"
            val http = "ch.acra:acra-http:$VERSION"
            val notification = "ch.acra:acra-notification:$VERSION"
        }
        // Self Hosted Analytics (FOSS)
        val matomo = "org.matomo.sdk:tracker:4.1.2"
        val appUpdator = "com.github.amitbd1508:AppUpdater:4.1.0"
    }
}

object Serialization {
    val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
}

object SqlDelight {
    val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
    val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"

    const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
    const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosx64:${Versions.sqlDelight}"
    val jdbcDriver = "org.xerial:sqlite-jdbc:${Versions.sqliteJdbcDriver}"
}
