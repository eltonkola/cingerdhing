package com.eltonkola.radioz.liquidsoap;

import java.io.*;
import java.net.Socket;

public class TelnetClient {

    public static String ICECAST_SKIP = "icecast.skip";
    public static String ICECAST_START = "icecast.start";
    public static String ICECAST_STATUS = "icecast.status";
    public static String ICECAST_STOP = "icecast.stop";
    public static String ICECAST_AUTOSTART = "icecast.autostart";
    public static String ICECAST_METADATA = "icecast.metadata";
    public static String ICECAST_REMAINING = "icecast.remaining";

    public static String HARBOR5002_BUFFER_LENGTH = "harbor_5002.buffer_length";
    public static String HARBOR5002_KICK = "harbor_5002.kick";
    public static String HARBOR5002_STATUS = "harbor_5002.status";
    public static String HARBOR5002_TOP = "harbor_5002.stop";

    public static String JINGLES_STOP = "jingles.stop";
    public static String JINGLES_NEXT = "jingles.next";
    public static String JINGLES_RELOAD = "jingles.reload";
    public static String JINGLES_URI = "jingles.uri ";  // + <URI>

    public static String REQUEST_ALIVE = "request.alive";
    public static String REQUEST_ALL = "request.all";
    public static String REQUEST_METADATA = "request.metadata "; // <rid>
    public static String REQUEST_ONAIR = "request.on_air";
    public static String REQUEST_RESOLVING = "request.resolving";
    public static String REQUEST_TRACE = "request.trace "; //+<rid>

    public static String SONGS_NEXT = "songs.next";
    public static String SONGS_RELOAD = "songs.reload";
    public static String SONGS_URI = "songs.uri "; // + [<URI>]

    public static String VAR_GET = "var.get ";//+<variable>
    public static String VAR_LIST = "var.list";
    public static String VAR_SET = "var.set ";// +<variable> = <value>

    public static String UPTIME = "uptime";
    public static String LIST = "list";
    public static String VERSION = "version";

    public static void main(String[] args) {
        TelnetClient ex = new TelnetClient();
        ex.send(ICECAST_SKIP);
    }

    public void send(final String cmd) {

        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {

            pingSocket = new Socket("127.0.0.1", 1360);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
            out.println(cmd);
            System.out.println(in.readLine());


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (pingSocket != null) {
                    pingSocket.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    public String getResponse(String cmd){

        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {

            pingSocket = new Socket("127.0.0.1", 1360);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
            out.println(cmd);
            String resposne = in.readLine();
            System.out.println(resposne);
            return resposne;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (pingSocket != null) {
                    pingSocket.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }



}