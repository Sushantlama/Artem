package com.example.artem.Home.utility;


import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static URL createURl(String url) throws MalformedURLException {
        URL murl=new URL(url);
        return murl;
    }
    private static String  makeHttpRequest(URL url) throws IOException {
        String responce="";
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        if(url!=null){
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if(urlConnection.getResponseCode()==200) {
                inputStream = urlConnection.getInputStream();
                responce = readFromStream(inputStream);
            }
        }
        return responce;

    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();
        if(inputStream!=null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bf = new BufferedReader(inputStreamReader);
            String line = bf.readLine();
            while (line != null) {
                output.append(line);
                line = bf.readLine();
            }

        }
        return output.toString();
    }
    private static List<Arts> extractFeatureFromJson(String eathquakeJSON){
        if(TextUtils.isEmpty(eathquakeJSON)){
            return null;
        };
        List<Arts> art_list=new ArrayList<>();
        try{
            JSONObject BASEJSONRESPONCE=new JSONObject(eathquakeJSON);
            JSONArray earthquakeArray =BASEJSONRESPONCE.getJSONArray("data");
            for(int i=0;i<earthquakeArray.length();i++){
                JSONObject artjson=earthquakeArray.getJSONObject(i);

                long id=artjson.getLong("id");
                String title=artjson.getString("title");
                String artist_title=artjson.getString("artist_title");
                String time_stamp=artjson.getString("timestamp");
                String image_id=artjson.getString("image_id");

                if(title!=null&&artist_title!=null&&!image_id.equals("null")){
                    Arts myart=new Arts(artist_title,title,id,time_stamp,image_id);
                    art_list.add(myart);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return art_list;
    }
    public static List<Arts> fetchArtworksData(String  responce) throws MalformedURLException {
        URL url=createURl(responce);
        String JSONResponce=null;
        try {
            JSONResponce=makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Arts> art_list=extractFeatureFromJson(JSONResponce);
        return art_list;
    }
}
