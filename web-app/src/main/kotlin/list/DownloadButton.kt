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

package list

import com.shabinder.common.models.DownloadStatus
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.attrs
import styled.css
import styled.styledDiv
import styled.styledImg

@Suppress("FunctionName")
fun RBuilder.DownloadButton(handler: DownloadButtonProps.() -> Unit): ReactElement {
    return child(downloadButton){
        attrs {
            handler()
        }
    }
}

external interface DownloadButtonProps : RProps {
    var onClick:()->Unit
    var status :DownloadStatus
}

private val downloadButton = functionalComponent<DownloadButtonProps>("Circular-Progress-Bar") { props->
    styledDiv {
        val src = when(props.status){
            is DownloadStatus.NotDownloaded -> "download-gradient.svg"
            is DownloadStatus.Downloaded -> "check.svg"
            is DownloadStatus.Failed -> "error.svg"
            else -> ""
        }
        styledImg(src = src) {
            attrs {
                onClickFunction = {
                    props.onClick()
                }
            }
            css {
                width = (2.5).em
                margin(8.px)
            }
        }
        css {
            classes = mutableListOf("glow-button")
            borderRadius = 100.px
        }
    }
}