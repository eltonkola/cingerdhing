package com.eltonkola.crud.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by elton on 5/17/17.
 */
public class StringUtils {

    public static void main(String[] args){

            String url = encodeUrl("http://shkarko.muzikpapare.com/Muzik Shqip 2015/Mihrije Braha - Dhimbje e mall 2015/Mihrije Braha - Dhimbje e mall.MP3");
            url = encodeUrl(url);
            System.out.print(url);

    }

    public static String encodeUrl(String url){
        return url.replaceAll(" ", "%20");
    }

}
