package com.example.gaurav_jaiswal.top10downloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by gaurav_jaiswal on 3/3/17.
 */

public class parseApplications {

private ArrayList<FeedEntry> applications;

    public parseApplications() {

        this.applications=new ArrayList<>();
    }


    public ArrayList<FeedEntry> getApplications() {

        return applications;
    }

    public boolean parse(String xmlData){

        boolean status=true;
        FeedEntry currentarecord=null;
        boolean inEntry=false;
        String textValue="";
        try {

            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp=factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType=xpp.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){

             String tagName=xpp.getName();
                switch(eventType){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagName);
                        if("entry".equalsIgnoreCase(tagName)){
                            inEntry=true;
                            currentarecord=new FeedEntry();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue=xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagName);
                        if(inEntry){
                            if("entry".equalsIgnoreCase(tagName)){
                                applications.add(currentarecord);
                                status=false;
                            }
                            else if ("name".equalsIgnoreCase(tagName)){
                                currentarecord.setName(textValue);
                            }
                            else if ("artist".equalsIgnoreCase(tagName)){
                                currentarecord.setArtist(textValue);
                            }
                            else if ("releaseddate".equalsIgnoreCase(tagName)){
                                currentarecord.setRelaseDate(textValue);
                            }
                            else if ("summary".equalsIgnoreCase(tagName)){
                                currentarecord.setSummary(textValue);
                            }
                            else if ("image".equalsIgnoreCase(tagName)){
                                currentarecord.setImageURL(textValue);
                            }

                        }
                        break;
                    default:
                }
                eventType=xpp.next();

            }
            for (FeedEntry app:applications){
                Log.d(TAG, "************");
                Log.d(TAG, app.toString());

            }


        }catch (Exception e){
            status=false;
            e.printStackTrace();

        }

   return status;


    }
}
