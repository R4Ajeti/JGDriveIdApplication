package myproject2018.org.urlrequesttestapplications;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JGDriveId {
    private String jgsource=null;
    public String JDriveId(String gurl) throws UnsupportedEncodingException, MalformedURLException {
        String gid, start, end=""; int ini,len; Map<String, String> parts;
        int i1 = gurl.indexOf("/edit");
        int i2 = gurl.indexOf("?id=");
        int i3 = gurl.indexOf("/view");
        if( gurl.contains("/edit") ){
            System.out.println("idijaa2--"+i1+"--2--");
            gurl = gurl.replace("/edit","/view");
        }
        else if( gurl.contains("?id=") ) {
            System.out.println("idijaa3--"+i2+"--3--");

            parts = splitQuery(gurl);
            return parts.get("id");
            //return "dsdsdd2";
        } else if ( gurl.contains("/view") ) {
            System.out.println("idijaa4--"+i3+"--4--");
            gurl = gurl + "/view";
        }
        else{ System.out.println("idijaa5--"+i1+"-*-"+i2+"-*-"+i3+"--5--"); }
        start  = "file/d/";
        end    = "/view";
        gurl = " " + gurl;
        ini    = gurl.indexOf(start);
        if (ini == 0) {
            return "";
        }
        ini += start.length();
        len = gurl.indexOf(end, ini) - ini;

        gid=gurl.substring(ini,ini+len);
        return gid;
    }


    @SuppressLint("StaticFieldLeak")
    public String jGSource(String gid){

        String gurlN="https://drive.google.com/uc?export=download&id=" + gid;

        //if(Build.VERSION.SDK_INT >= 11/*HONEYCOMB*/) {
        /*    new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new MyTask().execute();
        }

        new MyTask().execute();*/


        /*new AsyncTask<String, Void, Void>() {
            /*
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }*//*
            @Override
            protected String doInBackground(String gurlN) {
                //Log.v("AsyncTask", "doInBackground");
                //String msg = "";

                String firstPartText="", secondPartText="";
                URL textUrl;

                try{
                    textUrl = new URL(gurlN);
                    InputStream inStream = textUrl.openStream();
                    InputStreamReader inStrReader = new InputStreamReader(inStream);
                    BufferedReader bufferReader = new BufferedReader(inStrReader);
                    String stringbuffer;
                    String stringText = ""; int i=0;
                    while((stringbuffer = bufferReader.readLine()) != null){
                        int lenA= firstPartText.length();//41155
                        int lenB= secondPartText.length();
                        int lenC= stringbuffer.length();//200000
                        if(lenC<200000&&lenA<41155){
                            firstPartText += stringbuffer;
                            System.out.println("Iterimi if: "+i+" gjatesia: 1part-"+lenA+" 2part-"+lenB+" sbuffer-"+lenC);
                        }
                        else{
                            System.out.println("Iterimi else: "+i+" gjatesia: 1part-"+lenA+" 2part-"+lenB+" sbuffer-"+lenC);
                            secondPartText += stringbuffer; }
                        i++;
                    }

                    System.out.println("Vlera i-se: "+i+"textResult10:  -First Part- "+firstPartText);
                    System.out.println(" -Second Part- "+secondPartText +" -FUND");
                    stringText = firstPartText+secondPartText;
                    bufferReader.close();
                    jgsource = stringText;
                    System.out.println("textResult9: -First Part-"+firstPartText+" -Second Part- "+secondPartText +" -FUND");

                }catch(MalformedURLException e){
                    e.printStackTrace();
                    jgsource =e.toString();
                }catch(IOException e){
                    e.printStackTrace();
                    jgsource =e.toString();
                }


                return jgsource;
            }
            /*@Override
            protected void onPostExecute(String msg) {


            }*//*
        }.execute(null);*/


        return jgsource;
    }


    public String JGConfirmCode(String jgsource, int postlen){
        String jgcode="",ini="";
        int start=-1, len=-1, end=-1, prestart=-1, prelen=-1;

        ini="confirm="; len=ini.length(); start = jgsource.indexOf(ini);
        prestart=start+len; end = prestart+postlen;

        jgcode = jgcode+jgsource.substring(prestart, end);

        return jgcode;
    }

    public static String parseJUrl(String uri, int rasti) throws MalformedURLException {
        String parts;
        URL url = new URL(uri);
        switch(rasti){
            case 0 :
                parts = url.getProtocol(); // http
                break;
            case 1 :
                parts = url.getHost(); // hostname
                break;
            case 2 :
                parts = url.getPath(); // /path
                break;
            case 3 :
                parts = url.getPort()+""; // port
                break;
            case 4 :
                parts = url.getQuery(); // arg=value
                break;
            case 5:
                parts = url.getRef(); // anchor
                break;
            default :
                parts = "";
                break;
        }
        return parts;
    }
    public static Map<String, String> splitQuery(String uri) throws UnsupportedEncodingException, MalformedURLException {
        URL url = new URL(uri);
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
    public static Map<String, List<String>> splitQuery2(String uri) throws UnsupportedEncodingException, MalformedURLException {
        URL url = new URL(uri);
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = url.getQuery().split("&");
        for (String pair : pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        return query_pairs;
    }
    /*
    public class MyTask extends AsyncTask<Void, Void, Void>{
        private String gurlN="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
        /*MyTask(String gurl){
            gurlN=gurl;
        }
        */
        /*
        @Override
        protected Void doInBackground(Void... voids) {
            String firstPartText="", secondPartText="";
            URL textUrl;

            try{
                textUrl = new URL(gurlN);
                InputStream inStream = textUrl.openStream();
                InputStreamReader inStrReader = new InputStreamReader(inStream);
                BufferedReader bufferReader = new BufferedReader(inStrReader);
                String stringbuffer;
                String stringText = ""; int i=0;
                while((stringbuffer = bufferReader.readLine()) != null){
                    int lenA= firstPartText.length();//41155
                    int lenB= secondPartText.length();
                    int lenC= stringbuffer.length();//200000
                    if(lenC<200000&&lenA<41155){
                        firstPartText += stringbuffer;
                        System.out.println("Iterimi if: "+i+" gjatesia: 1part-"+lenA+" 2part-"+lenB+" sbuffer-"+lenC);
                    }
                    else{
                        System.out.println("Iterimi else: "+i+" gjatesia: 1part-"+lenA+" 2part-"+lenB+" sbuffer-"+lenC);
                        secondPartText += stringbuffer; }
                    i++;
                }

                System.out.println("Vlera i-se: "+i+"textResult10:  -First Part- "+firstPartText);
                System.out.println(" -Second Part- "+secondPartText +" -FUND");
                stringText = firstPartText+secondPartText;
                bufferReader.close();
                jgsource = stringText;
                System.out.println("textResult9: -First Part-"+firstPartText+" -Second Part- "+secondPartText +" -FUND");

            }catch(MalformedURLException e){
                e.printStackTrace();
                jgsource =e.toString();
            }catch(IOException e){
                e.printStackTrace();
                jgsource =e.toString();
            }

            return null;
        }*/
        /*
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //N.s(MainActivity.this, "All Items were added succesfully");
            super.onPostExecute(aVoid);
        }
    }*/
}
