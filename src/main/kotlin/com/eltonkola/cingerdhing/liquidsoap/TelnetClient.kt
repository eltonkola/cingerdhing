package com.eltonkola.cingerdhing.liquidsoap

import java.io.*
import java.net.Socket

class TelnetClient {

    val ICECAST_SKIP = "icecast.skip"
    val ICECAST_START = "icecast.start"
    val ICECAST_STATUS = "icecast.status"
    val ICECAST_STOP = "icecast.stop"
    val ICECAST_AUTOSTART = "icecast.autostart"
    val ICECAST_METADATA = "icecast.metadata"
    val ICECAST_REMAINING = "icecast.remaining"

    val HARBOR5002_BUFFER_LENGTH = "harbor_5002.buffer_length"
    val HARBOR5002_KICK = "harbor_5002.kick"
    val HARBOR5002_STATUS = "harbor_5002.status"
    val HARBOR5002_TOP = "harbor_5002.stop"

    val JINGLES_STOP = "jingles.stop"
    val JINGLES_NEXT = "jingles.next"
    val JINGLES_RELOAD = "jingles.reload"
    val JINGLES_URI = "jingles.uri "  // + <URI>

    val REQUEST_ALIVE = "request.alive"
    val REQUEST_ALL = "request.all"
    val REQUEST_METADATA = "request.metadata " // <rid>
    val REQUEST_ONAIR = "request.on_air"
    val REQUEST_RESOLVING = "request.resolving"
    val REQUEST_TRACE = "request.trace " //+<rid>

    val SONGS_NEXT = "songs.next"
    val SONGS_RELOAD = "songs.reload"
    val SONGS_URI = "songs.uri " // + [<URI>]

    val VAR_GET = "var.get "//+<variable>
    val VAR_LIST = "var.list"
    val VAR_SET = "var.set "// +<variable> = <value>

    val UPTIME = "uptime"
    val LIST = "list"
    val VERSION = "version"


    fun send(cmd: String) {

        var pingSocket: Socket? = null
        var out: PrintWriter? = null
        var bufferedReader : BufferedReader? = null

        try {

            pingSocket = Socket("127.0.0.1", 1360)
            out = PrintWriter(pingSocket.getOutputStream(), true)
            bufferedReader = BufferedReader(InputStreamReader(pingSocket.getInputStream()))
            out.println(cmd)
            println(bufferedReader.readLine())


        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (out != null) {
                    out.close()
                }
                if (bufferedReader != null) {
                    bufferedReader.close()
                }
                if (pingSocket != null) {
                    pingSocket.close()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

    }

    fun getResponse(cmd: String): String? {

        var pingSocket: Socket? = null
        var out: PrintWriter? = null
        var `in`: BufferedReader? = null

        try {

            pingSocket = Socket("127.0.0.1", 1360)
            out = PrintWriter(pingSocket.getOutputStream(), true)
            `in` = BufferedReader(InputStreamReader(pingSocket.getInputStream()))
            out.println(cmd)
            val resposne = `in`.readLine()
            println(resposne)
            return resposne

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (out != null) {
                    out.close()
                }
                if (`in` != null) {
                    `in`.close()
                }
                if (pingSocket != null) {
                    pingSocket.close()
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }
        return null
    }






}

//fun main(args: Array<String>) {
//    val ex = TelnetClient()
//    ex.send(ex.ICECAST_SKIP)
//}
