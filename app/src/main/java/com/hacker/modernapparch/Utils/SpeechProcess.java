package com.hacker.modernapparch.Utils;

public class SpeechProcess {

    public static String[] getQueryStringFromSpeech(String speech)
    {
        String result[] = new String[2];
        if (speech.toLowerCase().contains("type"))
        {
            result[0] = "type";
            result[1] = speech.substring(speech.lastIndexOf(" ")+1).toLowerCase();
            return result;
        }

        if (speech.toLowerCase().contains("color") || speech.contains("colour") )
        {
            result[0] = "color";
            result[1] = speech.substring(speech.lastIndexOf(" ")+1).toLowerCase();
            return result;
        }

        if (speech.toLowerCase().contains("price"))
        {
            result[0] = "price";
            result[1] = speech.substring(speech.lastIndexOf(" ")+1).toLowerCase();
            return result;
        }

        if (speech.toLowerCase().contains("stock"))
        {
            result[0] = "stock";
            result[1] = speech.substring(speech.lastIndexOf(" ")+1).toLowerCase();
            return result;
        }


        return result;
    }


}
