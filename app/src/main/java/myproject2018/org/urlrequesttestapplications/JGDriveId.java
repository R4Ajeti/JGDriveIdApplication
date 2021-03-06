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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JGDriveId {
    public String jgsource=null;
    public String jlinksource=null;
    public String location=null;
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
    public String jGSource(String gurl, String gid){

        String gurlN=gurl+"&id="+ gid;

        new MyTask(gurlN,0).execute();
        int i = 0;
        while( !isJGSourceUpOrDown(this,0) ) {
            i++;
        }

        return jgsource;
    }

    public String getJLink(String gurl, String gid, String jgcode){
        String jgurl = null;
        String gurlN=gurl+"&id="+gid+"&confirm="+jgcode;


        String urlLink="http://www.google.com";
        //URL url = null;




        /*
        try {

            URL obj = new URL("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY");
            URLConnection conn = obj.openConnection();
            Map<String, List<String>> map = conn.getHeaderFields();

            System.out.println("Printing Response Header...\n");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }

            System.out.println("\nGet Response Header By Key ...\n");
            String server = conn.getHeaderField("Server");

            if (server == null) {
                System.out.println("Key 'Server' is not found!");
            } else {
                System.out.println("Server - " + server);
            }

            System.out.println("\n Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
        */



        /*
        try {
            url = new URL(urlLink);
            HttpURLConnection conn  = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            String resultText = "";

            while((line = in.readLine())!= null){
                sb.append(line);
                resultText += line;
            }
            in.close();
            System.out.println(sb.toString());
            jlinksource = resultText;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        /*
        new MyTask(gurlN,1).execute();
        int i = 0;
        while( !isJGSourceUpOrDown(this,1) ) {
            i++;
        }
        */
        new MyTaskN(gurlN, jgcode).execute();

        return jlinksource;
        //return jgurl;
    }

    public boolean isJGSourceUp(JGDriveId jGDriveObject){
        boolean jgsup=false;
        if(!(jGDriveObject.jgsource==null)) {
            System.out.println("jGSourceN4000--" + jGDriveObject.jgsource + "--3--");
            jgsup=true;
        }
        else{
            isJGSourceUp(jGDriveObject);
        }
        return jgsup;
    }

    public boolean isJGSourceUpOrDown(JGDriveId jGDriveObject, int ini){
        boolean jgsup=false;
        if(ini==0){
            if(!(jGDriveObject.jgsource==null)) {
                jgsup = true;
            }
            else{
                jgsup = false;
            }
        }
        else if(ini==1){
            if(!(jGDriveObject.jlinksource==null)) {
                jgsup = true;
            }
            else{
                jgsup = false;
            }
        }
        return jgsup;
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


    private class MyTaskN extends AsyncTask<Void, Void, Void>{
        private String gurlN=null;
        private String jgcodeN;

        public MyTaskN(){
            gurlN="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
        }

        public MyTaskN(String gurl, String jgcode){
            gurlN=gurl;
            jgcodeN=jgcode;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String firstPartText="", secondPartText="";
            URL textUrl;

            try{


                    OkHttpClient client = new OkHttpClient().newBuilder()
                            .followRedirects(true)
                            .followSslRedirects(false)
                            .build();
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    RequestBody body = RequestBody.create(mediaType, "checkCookiesEnabled=true&checkMobileDevice=false&checkStandaloneMode=false&checkTabletDevice=false&portalAccountUsername=username&portalAccountPassword=password");
                    Request request = new Request.Builder()
                            .url("https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY")
                            .post(body)
                            .addHeader("content-type", "application/x-www-form-urlencoded")
                            .addHeader("cache-control", "no-cache")
                            .build();

                    Response response = client.newCall(request).execute();
                    String contentDisposition = response.header("location");


                    location = contentDisposition;


            }catch(MalformedURLException e){
                e.printStackTrace();
                location = e.toString();

            }catch(IOException e){
                e.printStackTrace();
                location = e.toString();

            }

            return null;
        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //N.s(MainActivity.this, "All Items were added succesfully");
            //textMsg.setText(jgsource);
            //textPrompt.setText("Finished");
            MainActivity.updateLogcat(location);
            super.onPostExecute(aVoid);
        }
    }


    private class MyTask extends AsyncTask<Void, Void, Void>{
        private String gurlN=null;
        private int iniN;

        public MyTask(){
            gurlN="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
        }

        public MyTask(String gurl, int ini){
            gurlN=gurl;
            iniN=ini;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String firstPartText="", secondPartText="";
            URL textUrl;

            try{
                textUrl = new URL(gurlN);
                InputStreamReader inStrReader = new InputStreamReader(textUrl.openStream());
                BufferedReader bufferReader = new BufferedReader(inStrReader);
                String stringbuffer;
                String stringText = ""; int i=0;
                while((stringbuffer = bufferReader.readLine()) != null){
                    int lenA= firstPartText.length();//41155
                    int lenB= secondPartText.length();
                    int lenC= stringbuffer.length();//200000
                    if(lenC<20000&&lenA<41155){
                        firstPartText += stringbuffer;
                    }
                    else{
                        secondPartText += stringbuffer; }
                    i++;
                }

                stringText = firstPartText+secondPartText;

                if(iniN==0) {
                    jgsource = stringText;
                } else if(iniN==1){
                    jlinksource = stringText;
                    bufferReader.close();
                }

            }catch(MalformedURLException e){
                e.printStackTrace();
                if(iniN==0) {
                    jgsource =e.toString();
                } else if(iniN==1){
                    jlinksource = e.toString();
                }
            }catch(IOException e){
                e.printStackTrace();
                if(iniN==0) {
                    jgsource = e.toString();
                } else if(iniN==1){
                    jlinksource = e.toString();
                }
            }
            if(iniN==0) {
                System.out.println("jGSourceN--" + jgsource + "--3--");
            } else if(iniN==1){
                System.out.println("jGSourceN--" + jlinksource + "--3--");
            }
            return null;
        }
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //N.s(MainActivity.this, "All Items were added succesfully");
            //textMsg.setText(jgsource);
            //textPrompt.setText("Finished");
            MainActivity.updateLogcat(jgsource);
            super.onPostExecute(aVoid);
        }
    }
}
