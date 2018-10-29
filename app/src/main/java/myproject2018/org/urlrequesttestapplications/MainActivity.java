package myproject2018.org.urlrequesttestapplications;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.transition.Scene;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.Buffer;
import java.security.Security;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //Android Play Video From URL Using VideoView Example
    VideoView video;
    String video_url = "http://download.itcuties.com/teaser/itcuties-teaser-480.mp4";
    ProgressDialog pd;

    TextView textMsg, textPrompt;
    String jgsource=null;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        textPrompt = (TextView)findViewById(R.id.textprompt);
        textMsg = (TextView) findViewById(R.id.textmsg);
        textMsg.setFocusable(true);
        textMsg.setEnabled(true);
        //textMsg.setClickable(true);
        //textMsg.setFocusableInTouchMode(true);
        textMsg.setMovementMethod(new ScrollingMovementMethod());

        textPrompt.setText("Wait...");
        new MyTask().execute();

        //play a video from relative path(existing local file)
        VideoView video =(VideoView) findViewById(R.id.VideoView1);
        //Get path of video it is
        Uri videoPath = Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.enigma);
        //Add video file within raw folder
        //Uri vidPN=Uri.parse("https://drive.google.com/file/d/11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY/preview?autoplay=1");
        Uri vidPN=Uri.parse("http://download.itcuties.com/teaser/itcuties-teaser-480.mp4");
        video.setVideoURI(videoPath);

        video.start();//As we do not use button to start it will play automatically when app open.

        /*VideoView videoView = (VideoView) findViewById(R.id.VideoView);

        String vidAddress = "http://download.itcuties.com/teaser/itcuties-teaser-480.mp4";

        Uri videoUri = Uri.parse(vidAddress);

        MediaController mediaController = new MediaController(this); mediaController.setAnchorView(videoView); videoView.setMediaController(mediaController);

        videoView.setVideoURI(videoUri); videoView.start();*/

        //Using Web View
        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"420\" height=\"315\"  src=\"https://drive.google.com/file/d/VIDEO_ID/preview\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        WebView myWebView = (WebView) findViewById(R.id.WebView);
        /*
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadData(frameVideo, "text/html", "utf-8");

        WebView wv = (WebView)findViewById(R.id.webview);*/
        myWebView.setWebViewClient(new WebViewClient(){
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        });
        myWebView.loadUrl(frameVideo);

        try {
            //JGDriveId jGDriveObject = new JGDriveId();
            //System.out.println("idijaa4--"+jGDriveObject.JDriveId("https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY"));

            String gurl="https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
            JGDriveId jGDriveObject = new JGDriveId();

            String gid=jGDriveObject.JDriveId(gurl);
            System.out.println("idijaa4--"+gid);

            //String jgsource1=jGDriveObject.jGSource(gid);
            System.out.println("jGSource--" + jgsource + "--3--");

            String jgsource2="<!DOCTYPE html><html><head><title>Google Drive - Virus scan warning</title><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/><link href=&#47;static&#47;doclist&#47;client&#47;css&#47;2794100970&#45;untrustedcontent.css rel=\"stylesheet\"><style nonce=\"7+QFZRHv3R6ExbW03Ty1Jg458l4\">#gb{font:13px/27px Arial,sans-serif;height:30px}#gbz,#gbg{position:absolute;white-space:nowrap;top:0;height:30px;z-index:1000}#gbz{left:0;padding-left:4px}#gbg{right:0;padding-right:5px}#gbs{background:transparent;position:absolute;top:-999px;visibility:hidden;z-index:998;right:0}.gbto #gbs{background:#fff}#gbx3,#gbx4{background-color:#2d2d2d;background-image:none;_background-image:none;background-position:0 -138px;background-repeat:repeat-x;border-bottom:1px solid #000;font-size:24px;height:29px;_height:30px;opacity:1;filter:alpha(opacity=100);position:absolute;top:0;width:100%;z-index:990}#gbx3{left:0}#gbx4{right:0}#gbb{position:relative}#gbbw{left:0;position:absolute;top:30px;width:100%}.gbtcb{position:absolute;visibility:hidden}#gbz .gbtcb{right:0}#gbg .gbtcb{left:0}.gbxx{display:none !important}.gbxo{opacity:0 !important;filter:alpha(opacity=0) !important}.gbm{position:absolute;z-index:999;top:-999px;visibility:hidden;text-align:left;border:1px solid #bebebe;background:#fff;-moz-box-shadow:-1px 1px 1px rgba(0,0,0,.2);-webkit-box-shadow:0 2px 4px rgba(0,0,0,.2);box-shadow:0 2px 4px rgba(0,0,0,.2)}.gbrtl .gbm{-moz-box-shadow:1px 1px 1px rgba(0,0,0,.2)}.gbto .gbm,.gbto #gbs{top:29px;visibility:visible}#gbz .gbm{left:0}#gbg .gbm{right:0}.gbxms{background-color:#ccc;display:block;position:absolute;z-index:1;top:-1px;left:-2px;right:-2px;bottom:-2px;opacity:.4;-moz-border-radius:3px;filter:progid:DXImageTransform.Microsoft.Blur(pixelradius=5);*opacity:1;*top:-2px;*left:-5px;*right:5px;*bottom:4px;-ms-filter:\"progid:DXImageTransform.Microsoft.Blur(pixelradius=5)\";opacity:1\\0/;top:-4px\\0/;left:-6px\\0/;right:5px\\0/;bottom:4px\\0/}.gbma{position:relative;top:-1px;border-style:solid dashed dashed;border-color:transparent;border-top-color:#c0c0c0;display:-moz-inline-box;display:inline-block;font-size:0;height:0;line-height:0;width:0;border-width:3px 3px 0;padding-top:1px;left:4px}#gbztms1,#gbi4m1,#gbi4s,#gbi4t{zoom:1}.gbtc,.gbmc,.gbmcc{display:block;list-style:none;margin:0;padding:0}.gbmc{background:#fff;padding:10px 0;position:relative;z-index:2;zoom:1}.gbt{position:relative;display:-moz-inline-box;display:inline-block;line-height:27px;padding:0;vertical-align:top}.gbt{*display:inline}.gbto{box-shadow:0 2px 4px rgba(0,0,0,.2);-moz-box-shadow:0 2px 4px rgba(0,0,0,.2);-webkit-box-shadow:0 2px 4px rgba(0,0,0,.2)}.gbzt,.gbgt{cursor:pointer;display:block;text-decoration:none !important}span#gbg6,span#gbg4{cursor:default}.gbts{border-left:1px solid transparent;border-right:1px solid transparent;display:block;*display:inline-block;padding:0 5px;position:relative;z-index:1000}.gbts{*display:inline}.gbzt .gbts{display:inline;zoom:1}.gbto .gbts{background:#fff;border-color:#bebebe;color:#36c;padding-bottom:1px;padding-top:2px}.gbz0l .gbts{color:#fff;font-weight:bold}.gbtsa{padding-right:9px}#gbz .gbzt,#gbz .gbgt,#gbg .gbgt{color:#ccc!important}.gbtb2{display:block;border-top:2px solid transparent}.gbto .gbzt .gbtb2,.gbto .gbgt .gbtb2{border-top-width:0}.gbtb .gbts{background:url(https://ssl.gstatic.com/gb/images/b_8d5afc09.png);_background:url(https://ssl.gstatic.com/gb/images/b8_3615d64d.png);background-position:-27px -22px;border:0;font-size:0;padding:29px 0 0;*padding:27px 0 0;width:1px}.gbzt:hover,.gbzt:focus,.gbgt-hvr,.gbgt:focus{background-color:#4c4c4c;background-image:none;_background-image:none;background-position:0 -102px;background-repeat:repeat-x;outline:none;text-decoration:none !important}.gbpdjs .gbto .gbm{min-width:99%}.gbz0l .gbtb2{border-top-color:#dd4b39!important}#gbi4s,#gbi4s1{font-weight:bold}#gbg6.gbgt-hvr,#gbg6.gbgt:focus{background-color:transparent;background-image:none}.gbg4a{font-size:0;line-height:0}.gbg4a .gbts{padding:27px 5px 0;*padding:25px 5px 0}.gbto .gbg4a .gbts{padding:29px 5px 1px;*padding:27px 5px 1px}#gbi4i,#gbi4id{left:5px;border:0;height:24px;position:absolute;top:1px;width:24px}.gbto #gbi4i,.gbto #gbi4id{top:3px}.gbi4p{display:block;width:24px}#gbi4id{background-position:-44px -101px}#gbmpid{background-position:0 0}#gbmpi,#gbmpid{border:none;display:inline-block;height:48px;width:48px}#gbmpiw{display:inline-block;line-height:9px;padding-left:20px;margin-top:10px;position:relative}#gbmpi,#gbmpid,#gbmpiw{*display:inline}#gbg5{font-size:0}#gbgs5{padding:5px !important}.gbto #gbgs5{padding:7px 5px 6px !important}#gbi5{background:url(https://ssl.gstatic.com/gb/images/b_8d5afc09.png);_background:url(https://ssl.gstatic.com/gb/images/b8_3615d64d.png);background-position:0 0;display:block;font-size:0;height:17px;width:16px}.gbto #gbi5{background-position:-6px -22px}.gbn .gbmt,.gbn .gbmt:visited,.gbnd .gbmt,.gbnd .gbmt:visited{color:#dd8e27 !important}.gbf .gbmt,.gbf .gbmt:visited{color:#900 !important}.gbmt,.gbml1,.gbmlb,.gbmt:visited,.gbml1:visited,.gbmlb:visited{color:#36c !important;text-decoration:none !important}.gbmt,.gbmt:visited{display:block}.gbml1,.gbmlb,.gbml1:visited,.gbmlb:visited{display:inline-block;margin:0 10px}.gbml1,.gbmlb,.gbml1:visited,.gbmlb:visited{*display:inline}.gbml1,.gbml1:visited{padding:0 10px}.gbml1-hvr,.gbml1:focus{outline:none;text-decoration:underline !important}#gbpm .gbml1{display:inline;margin:0;padding:0;white-space:nowrap}.gbmlb,.gbmlb:visited{line-height:27px}.gbmlb-hvr,.gbmlb:focus{outline:none;text-decoration:underline !important}.gbmlbw{color:#ccc;margin:0 10px}.gbmt{padding:0 20px}.gbmt:hover,.gbmt:focus{background:#eee;cursor:pointer;outline:0 solid black;text-decoration:none !important}.gbm0l,.gbm0l:visited{color:#000 !important;font-weight:bold}.gbmh{border-top:1px solid #bebebe;font-size:0;margin:10px 0}#gbd4 .gbmc{background:#f5f5f5;padding-top:0}#gbd4 .gbsbic::-webkit-scrollbar-track:vertical{background-color:#f5f5f5;margin-top:2px}#gbmpdv{background:#fff;border-bottom:1px solid #bebebe;-moz-box-shadow:0 2px 4px rgba(0,0,0,.12);-o-box-shadow:0 2px 4px rgba(0,0,0,.12);-webkit-box-shadow:0 2px 4px rgba(0,0,0,.12);box-shadow:0 2px 4px rgba(0,0,0,.12);position:relative;z-index:1}#gbd4 .gbmh{margin:0}.gbmtc{padding:0;margin:0;line-height:27px}.GBMCC:last-child:after,#GBMPAL:last-child:after{content:'\\0A\\0A';white-space:pre;position:absolute}#gbmps{*zoom:1}#gbd4 .gbpc,#gbmpas .gbmt{line-height:17px}#gbd4 .gbpgs .gbmtc{line-height:27px}#gbd4 .gbmtc{border-bottom:1px solid #bebebe}#gbd4 .gbpc{display:inline-block;margin:16px 0 10px;padding-right:50px;vertical-align:top}#gbd4 .gbpc{*display:inline}.gbpc .gbps,.gbpc .gbps2{display:block;margin:0 20px}#gbmplp.gbps{margin:0 10px}.gbpc .gbps{color:#000;font-weight:bold}.gbpc .gbpd{margin-bottom:5px}.gbpd .gbmt,.gbpd .gbps{color:#666 !important}.gbpd .gbmt{opacity:.4;filter:alpha(opacity=40)}.gbps2{color:#666;display:block}.gbp0{display:none}.gbp0 .gbps2{font-weight:bold}#gbd4 .gbmcc{margin-top:5px}.gbpmc{background:#fef9db}.gbpmc .gbpmtc{padding:10px 20px}#gbpm{border:0;*border-collapse:collapse;border-spacing:0;margin:0;white-space:normal}#gbpm .gbpmtc{border-top:none;color:#000 !important;font:11px Arial,sans-serif}#gbpms{*white-space:nowrap}.gbpms2{font-weight:bold;white-space:nowrap}#gbmpal{*border-collapse:collapse;border-spacing:0;border:0;margin:0;white-space:nowrap;width:100%}.gbmpala,.gbmpalb{font:13px Arial,sans-serif;line-height:27px;padding:10px 20px 0;white-space:nowrap}.gbmpala{padding-left:0;text-align:left}.gbmpalb{padding-right:0;text-align:right}#gbmpasb .gbps{color:#000}#gbmpal .gbqfbb{margin:0 20px}.gbp0 .gbps{*display:inline}a.gbiba{margin:8px 20px 10px}.gbmpiaw{display:inline-block;padding-right:10px;margin-bottom:6px;margin-top:10px}.gbxv{visibility:hidden}.gbmpiaa{display:block;margin-top:10px}.gbmpia{border:none;display:block;height:48px;width:48px}.gbmpnw{display:inline-block;height:auto;margin:10px 0;vertical-align:top}\n" +
                    ".gbqfb,.gbqfba,.gbqfbb{-moz-border-radius:2px;-webkit-border-radius:2px;border-radius:2px;cursor:default !important;display:inline-block;font-weight:bold;height:29px;line-height:29px;min-width:54px;*min-width:70px;padding:0 8px;text-align:center;text-decoration:none !important;-moz-user-select:none;-webkit-user-select:none}.gbqfb:focus,.gbqfba:focus,.gbqfbb:focus{border:1px solid #4d90fe;-moz-box-shadow:inset 0 0 0 1px rgba(255, 255, 255, 0.5);-webkit-box-shadow:inset 0 0 0 1px rgba(255, 255, 255, 0.5);box-shadow:inset 0 0 0 1px rgba(255, 255, 255, 0.5);outline:none}.gbqfb-hvr:focus,.gbqfba-hvr:focus,.gbqfbb-hvr:focus{-webkit-box-shadow:inset 0 0 0 1px #fff,0 1px 1px rgba(0,0,0,.1);-moz-box-shadow:inset 0 0 0 1px #fff,0 1px 1px rgba(0,0,0,.1);box-shadow:inset 0 0 0 1px #fff,0 1px 1px rgba(0,0,0,.1)}.gbqfb-no-focus:focus{border:1px solid #3079ed;-moz-box-shadow:none;-webkit-box-shadow:none;box-shadow:none}.gbqfb-hvr,.gbqfba-hvr,.gbqfbb-hvr{-webkit-box-shadow:0 1px 1px rgba(0,0,0,.1);-moz-box-shadow:0 1px 1px rgba(0,0,0,.1);box-shadow:0 1px 1px rgba(0,0,0,.1)}.gbqfb::-moz-focus-inner,.gbqfba::-moz-focus-inner,.gbqfbb::-moz-focus-inner{border:0}.gbqfba,.gbqfbb{border:1px solid #dcdcdc;border-color:rgba(0,0,0,.1);color:#444 !important;font-size:11px}.gbqfb{background-color:#4d90fe;background-image:-webkit-gradient(linear,left top,left bottom,from(#4d90fe),to(#4787ed));background-image:-webkit-linear-gradient(top,#4d90fe,#4787ed);background-image:-moz-linear-gradient(top,#4d90fe,#4787ed);background-image:-ms-linear-gradient(top,#4d90fe,#4787ed);background-image:-o-linear-gradient(top,#4d90fe,#4787ed);background-image:linear-gradient(top,#4d90fe,#4787ed);filter:progid:DXImageTransform.Microsoft.gradient(startColorStr='#4d90fe',EndColorStr='#4787ed');border:1px solid #3079ed;color:#fff!important;margin:0 0}.gbqfb-hvr{border-color:#2f5bb7}.gbqfb-hvr:focus{border-color:#2f5bb7}.gbqfb-hvr,.gbqfb-hvr:focus{background-color:#357ae8;background-image:-webkit-gradient(linear,left top,left bottom,from(#4d90fe),to(#357ae8));background-image:-webkit-linear-gradient(top,#4d90fe,#357ae8);background-image:-moz-linear-gradient(top,#4d90fe,#357ae8);background-image:-ms-linear-gradient(top,#4d90fe,#357ae8);background-image:-o-linear-gradient(top,#4d90fe,#357ae8);background-image:linear-gradient(top,#4d90fe,#357ae8)}.gbqfb:active{background-color:inherit;-webkit-box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.3);-moz-box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.3);box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.3)}.gbqfba{background-color:#f5f5f5;background-image:-webkit-gradient(linear,left top,left bottom,from(#f5f5f5),to(#f1f1f1));background-image:-webkit-linear-gradient(top,#f5f5f5,#f1f1f1);background-image:-moz-linear-gradient(top,#f5f5f5,#f1f1f1);background-image:-ms-linear-gradient(top,#f5f5f5,#f1f1f1);background-image:-o-linear-gradient(top,#f5f5f5,#f1f1f1);background-image:linear-gradient(top,#f5f5f5,#f1f1f1);filter:progid:DXImageTransform.Microsoft.gradient(startColorStr='#f5f5f5',EndColorStr='#f1f1f1')}.gbqfba-hvr,.gbqfba-hvr:active{background-color:#f8f8f8;background-image:-webkit-gradient(linear,left top,left bottom,from(#f8f8f8),to(#f1f1f1));background-image:-webkit-linear-gradient(top,#f8f8f8,#f1f1f1);background-image:-moz-linear-gradient(top,#f8f8f8,#f1f1f1);background-image:-ms-linear-gradient(top,#f8f8f8,#f1f1f1);background-image:-o-linear-gradient(top,#f8f8f8,#f1f1f1);background-image:linear-gradient(top,#f8f8f8,#f1f1f1);filter:progid:DXImageTransform.Microsoft.gradient(startColorStr='#f8f8f8',EndColorStr='#f1f1f1')}.gbqfbb{background-color:#fff;background-image:-webkit-gradient(linear,left top,left bottom,from(#fff),to(#fbfbfb));background-image:-webkit-linear-gradient(top,#fff,#fbfbfb);background-image:-moz-linear-gradient(top,#fff,#fbfbfb);background-image:-ms-linear-gradient(top,#fff,#fbfbfb);background-image:-o-linear-gradient(top,#fff,#fbfbfb);background-image:linear-gradient(top,#fff,#fbfbfb);filter:progid:DXImageTransform.Microsoft.gradient(startColorStr='#ffffff',EndColorStr='#fbfbfb')}.gbqfbb-hvr,.gbqfbb-hvr:active{background-color:#fff;background-image:-webkit-gradient(linear,left top,left bottom,from(#fff),to(#f8f8f8));background-image:-webkit-linear-gradient(top,#fff,#f8f8f8);background-image:-moz-linear-gradient(top,#fff,#f8f8f8);background-image:-ms-linear-gradient(top,#fff,#f8f8f8);background-image:-o-linear-gradient(top,#fff,#f8f8f8);background-image:linear-gradient(top,#fff,#f8f8f8);filter:progid:DXImageTransform.Microsoft.gradient(startColorStr='#ffffff',EndColorStr='#f8f8f8')}.gbqfba-hvr,.gbqfba-hvr:active,.gbqfbb-hvr,.gbqfbb-hvr:active{border-color:#c6c6c6;-webkit-box-shadow:0 1px 1px rgba(0,0,0,.1);-moz-box-shadow:0 1px 1px rgba(0,0,0,.1);box-shadow:0 1px 1px rgba(0,0,0,.1);color:#222 !important}.gbqfba:active,.gbqfbb:active{-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,.1);-moz-box-shadow:inset 0 1px 2px rgba(0,0,0,.1);box-shadow:inset 0 1px 2px rgba(0,0,0,.1)}\n" +
                    "#gbmpas{max-height:220px}#gbmm{max-height:530px}.gbsb{-webkit-box-sizing:border-box;display:block;position:relative;*zoom:1}.gbsbic{overflow:auto}.gbsbis .gbsbt,.gbsbis .gbsbb{-webkit-mask-box-image:-webkit-gradient(linear,left top,right top,color-stop(0,rgba(0,0,0,.1)),color-stop(.5,rgba(0,0,0,.8)),color-stop(1,rgba(0,0,0,.1)));left:0;margin-right:0;opacity:0;position:absolute;width:100%}.gbsb .gbsbt:after,.gbsb .gbsbb:after{content:\"\";display:block;height:0;left:0;position:absolute;width:100%}.gbsbis .gbsbt{background:-webkit-gradient(linear,left top,left bottom,from(rgba(0,0,0,.2)),to(rgba(0,0,0,0)));background-image:-webkit-linear-gradient(top,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-moz-linear-gradient(top,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-ms-linear-gradient(top,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-o-linear-gradient(top,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:linear-gradient(top,rgba(0,0,0,.2),rgba(0,0,0,0));height:6px;top:0}.gbsb .gbsbt:after{border-top:1px solid #ebebeb;border-color:rgba(0,0,0,.3);top:0}.gbsb .gbsbb{-webkit-mask-box-image:-webkit-gradient(linear,left top,right top,color-stop(0,rgba(0,0,0,.1)),color-stop(.5,rgba(0,0,0,.8)),color-stop(1,rgba(0,0,0,.1)));background:-webkit-gradient(linear,left bottom,left top,from(rgba(0,0,0,.2)),to(rgba(0,0,0,0)));background-image:-webkit-linear-gradient(bottom,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-moz-linear-gradient(bottom,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-ms-linear-gradient(bottom,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:-o-linear-gradient(bottom,rgba(0,0,0,.2),rgba(0,0,0,0));background-image:linear-gradient(bottom,rgba(0,0,0,.2),rgba(0,0,0,0));bottom:0;height:4px}.gbsb .gbsbb:after{border-bottom:1px solid #ebebeb;border-color:rgba(0,0,0,.3);bottom:0}\n" +
                    "</style><script nonce=\"7+QFZRHv3R6ExbW03Ty1Jg458l4\">(function(){try{var e=this,aa=function(a){var b=typeof a;if(\"object\"==b)if(a){if(a instanceof Array)return\"array\";if(a instanceof Object)return b;var c=Object.prototype.toString.call(a);if(\"[object Window]\"==c)return\"object\";if(\"[object Array]\"==c||\"number\"==typeof a.length&&\"undefined\"!=typeof a.splice&&\"undefined\"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable(\"splice\"))return\"array\";if(\"[object Function]\"==c||\"undefined\"!=typeof a.call&&\"undefined\"!=typeof a.propertyIsEnumerable&&!a.propertyIsEnumerable(\"call\"))return\"function\"}else return\"null\";\n" +
                    "else if(\"function\"==b&&\"undefined\"==typeof a.call)return\"object\";return b};var ba=function(a,b,c,d){d=d||{};d._sn=[\"cfg\",b,c].join(\".\");window.gbar.logger.ml(a,d)};var ca=function(a,b){var c=b||document.body,d=document.createElement(\"img\");d.style.display=\"block\";d.style.visibility=\"hidden\";d.onload=function(){c.removeChild(d)};a=[\"https://www.google.com/gen_204?atyp=i\",\"zx=\"+Date.now(),\"ogefn=\"+a];d.src=a.join(\"&\");c.appendChild(d)},da=\"si so sos sa sp paa prm pcm pca pti rtl addextralink addlink has gpca gpcr setcontinuecb qs spd spn spp sps mls op\".split(\" \");var g=window.gbar=window.gbar||{},h=window.gbar.i=window.gbar.i||{},ea;function _tvn(a,b){a=parseInt(a,10);return isNaN(a)?b:a}function _tvf(a,b){a=parseFloat(a);return isNaN(a)?b:a}function _tvv(a){return!!a}function p(a,b,c){(c||g)[a]=0<=da.indexOf(a)&&\"function\"==aa(b)?function(){ca(a);return b.apply(void 0,arguments)}:b}g.bv={n:_tvn(\"2\",0),r:\"\",f:\".66.41.\",e:\"1300102,3700306,3700489,3700521\",m:_tvn(\"1\",1)};\n" +
                    "function fa(a,b,c){var d=\"on\"+b;if(a.addEventListener)a.addEventListener(b,c,!1);else if(a.attachEvent)a.attachEvent(d,c);else{var f=a[d];a[d]=function(){var a=f.apply(this,arguments),b=c.apply(this,arguments);return void 0==a?b:void 0==b?a:b&&a}}}var ha=function(a){return function(){return g.bv.m==a}},ia=ha(1),ja=ha(2);p(\"sb\",ia);p(\"kn\",ja);h.a=_tvv;h.b=_tvf;h.c=_tvn;h.i=ba;var q=window.gbar.i.i;var r=function(){},ka=function(){},na=function(a){var b=new Image,c=la;b.onerror=b.onload=b.onabort=function(){try{delete ma[c]}catch(d){}};ma[c]=b;b.src=a;la=c+1},ma=[],la=0;p(\"logger\",{il:ka,ml:r,log:na});var u=window.gbar.logger;var v={},oa={},w=[],pa=h.b(\"0.1\",.1),qa=h.a(\"1\",!0),ra=function(a,b){w.push([a,b])},sa=function(a,b){v[a]=b},ta=function(a){return a in v},x={},A=function(a,b){x[a]||(x[a]=[]);x[a].push(b)},B=function(a){A(\"m\",a)},ua=function(a,b){var c=document.createElement(\"script\");c.src=a;c.async=qa;Math.random()<pa&&(c.onerror=function(){c.onerror=null;r(Error(\"Bundle load failed: name=\"+(b||\"UNK\")+\" url=\"+a))});(document.getElementById(\"xjsc\")||document.getElementsByTagName(\"body\")[0]||\n" +
                    "document.getElementsByTagName(\"head\")[0]).appendChild(c)},D=function(a){for(var b=0,c;(c=w[b])&&c[0]!=a;++b);!c||c[1].l||c[1].s||(c[1].s=!0,va(2,a),c[1].url&&ua(c[1].url,a),c[1].libs&&C&&C(c[1].libs))},wa=function(a){A(\"gc\",a)},xa=null,ya=function(a){xa=a},va=function(a,b,c){if(xa){a={t:a,b:b};if(c)for(var d in c)a[d]=c[d];try{xa(a)}catch(f){}}};p(\"mdc\",v);p(\"mdi\",oa);p(\"bnc\",w);p(\"qGC\",wa);p(\"qm\",B);p(\"qd\",x);p(\"lb\",D);p(\"mcf\",sa);p(\"bcf\",ra);p(\"aq\",A);p(\"mdd\",\"\");\n" +
                    "p(\"has\",ta);p(\"trh\",ya);p(\"tev\",va);if(h.a(\"m;/_/scs/abc-static/_/js/k=gapi.gapi.en.BstDmEhpa_Q.O/rt=j/d=1/rs=AHpOoo-oqKdOJIcyAi5YyA6pahSkp_ojsw/m=__features__\")){var F=function(a,b){return za?a||b:b},Aa=h.a(\"1\"),Ba=h.a(\"\"),Ca=h.a(\"\"),za=h.a(\"\"),Da=window.gapi=F(window.gapi,{}),Ea=function(a,b){var c=function(){g.dgl(a,b)};Aa?B(c):(A(\"gl\",c),D(\"gl\"))},Fa={},Ga=function(a){a=a.split(\":\");for(var b;(b=a.pop())&&Fa[b];);return!b},C=function(a){function b(){for(var b=a.split(\":\"),d=0,f;f=b[d];++d)Fa[f]=1;for(b=0;d=w[b];++b)d=d[1],(f=d.libs)&&!d.l&&d.i&&\n" +
                    "Ga(f)&&d.i()}g.dgl(a,b)},G=window.___jsl=F(window.___jsl,{});G.h=F(G.h,\"m;/_/scs/abc-static/_/js/k=gapi.gapi.en.BstDmEhpa_Q.O/rt=j/d=1/rs=AHpOoo-oqKdOJIcyAi5YyA6pahSkp_ojsw/m=__features__\");G.ms=F(G.ms,\"https://apis.google.com\");G.m=F(G.m,\"\");G.l=F(G.l,[]);Aa||w.push([\"gl\",{url:\"//ssl.gstatic.com/gb/js/abc/glm_e7bb39a7e1a24581ff4f8d199678b1b9.js\"}]);var Ha={pu:Ba,sh:\"\",si:Ca,hl:\"en\"};v.gl=Ha;za?Da.load||p(\"load\",Ea,Da):p(\"load\",Ea,Da);p(\"dgl\",Ea);p(\"agl\",Ga);h.o=Aa};var Ia=h.b(\"0.1\",.001),Ja=0;\n" +
                    "function _mlToken(a,b){try{if(1>Ja){Ja++;var c=a;b=b||{};var d=encodeURIComponent,f=[\"//www.google.com/gen_204?atyp=i&zx=\",(new Date).getTime(),\"&jexpid=\",d(\"28834\"),\"&srcpg=\",d(\"prop=49\"),\"&jsr=\",Math.round(1/Ia),\"&ogev=\",d(\"3B7WW476HK2t5wLv1pygAQ\"),\"&ogf=\",g.bv.f,\"&ogrp=\",d(\"\"),\"&ogv=\",d(\"217074232.0\"),\"&oggv=\"+d(\"es_plusone_gc_20181009.0_p0\"),\"&ogd=\",d(\"com\"),\"&ogc=\",d(\"XKK\"),\"&ogl=\",d(\"en\")];b._sn&&(b._sn=\n" +
                    "\"og.\"+b._sn);for(var k in b)f.push(\"&\"),f.push(d(k)),f.push(\"=\"),f.push(d(b[k]));f.push(\"&emsg=\");f.push(d(c.name+\":\"+c.message));var l=f.join(\"\");Ka(l)&&(l=l.substr(0,2E3));var m=l;var n=window.gbar.logger._aem(a,m);na(n)}}catch(t){}}var Ka=function(a){return 2E3<=a.length},La=function(a,b){return b};function Ma(a){r=a;p(\"_itl\",Ka,u);p(\"_aem\",La,u);p(\"ml\",r,u);a={};v.er=a}h.a(\"\")?Ma(function(a){throw a;}):h.a(\"1\")&&Math.random()<Ia&&Ma(_mlToken);var _E=\"left\",Na=h.a(\"\"),J=function(a,b){var c=a.className;H(a,b)||(a.className+=(\"\"!=c?\" \":\"\")+b)},K=function(a,b){var c=a.className;b=new RegExp(\"\\\\s?\\\\b\"+b+\"\\\\b\");c&&c.match(b)&&(a.className=c.replace(b,\"\"))},H=function(a,b){b=new RegExp(\"\\\\b\"+b+\"\\\\b\");a=a.className;return!(!a||!a.match(b))},Oa=function(a,b){H(a,b)?K(a,b):J(a,b)},Pa=function(a,b){a[b]=function(c){var d=arguments;g.qm(function(){a[b].apply(this,d)})}},Qa=function(){return\"1\"},\n" +
                    "Ra=function(a){a=[\"//www.gstatic.com\",\"/og/_/js/d=1/k=\",\"og.og.en_US.Titvsspg-iA.O\",\"/rt=j/m=\",a,\"/rs=\",\"AA2YrTsRqPA-NqEaBoXWxEW2Ait9VULQpQ\"];Na&&a.push(\"?host=www.gstatic.com&bust=og.og.en_US.dtCzv7WYrEI.DU\");a=a.join(\"\");ua(a)};p(\"ca\",J);p(\"cr\",K);p(\"cc\",H);h.k=J;h.l=K;h.m=H;h.n=Oa;h.p=Ra;h.q=Pa;h.r=Qa;var Sa=[\"gb_71\",\"gb_155\"],Ta;function Ua(a){Ta=a}function Va(a){var b=Ta&&!a.href.match(/.*\\/accounts\\/ClearSID[?]/)&&encodeURIComponent(Ta());b&&(a.href=a.href.replace(/([?&]continue=)[^&]*/,\"$1\"+b))}function Wa(a){window.gApplication&&(a.href=window.gApplication.getTabUrl(a.href))}function Xa(a){try{var b=(document.forms[0].q||\"\").value;b&&(a.href=a.href.replace(/([?&])q=[^&]*|$/,function(a,d){return(d||\"&\")+\"q=\"+encodeURIComponent(b)}))}catch(c){q(c,\"sb\",\"pq\")}}\n" +
                    "var Ya=function(){for(var a=[],b=0,c;c=Sa[b];++b)(c=document.getElementById(c))&&a.push(c);return a},Za=function(){var a=Ya();return 0<a.length?a[0]:null},$a=function(){return document.getElementById(\"gb_70\")},L={},M={},ab={},N={},O=void 0,fb=function(a,b){try{var c=document.getElementById(\"gb\");J(c,\"gbpdjs\");P();bb(document.getElementById(\"gb\"))&&J(c,\"gbrtl\");if(b&&b.getAttribute){var d=b.getAttribute(\"aria-owns\");if(d.length){var f=document.getElementById(d);if(f){var k=b.parentNode;if(O==d)O=void 0,\n" +
                    "K(k,\"gbto\");else{if(O){var l=document.getElementById(O);if(l&&l.getAttribute){var m=l.getAttribute(\"aria-owner\");if(m.length){var n=document.getElementById(m);n&&n.parentNode&&K(n.parentNode,\"gbto\")}}}cb(f)&&db(f);O=d;J(k,\"gbto\")}}}}B(function(){g.tg(a,b,!0)});eb(a)}catch(t){q(t,\"sb\",\"tg\")}},gb=function(a){B(function(){g.close(a)})},hb=function(a){B(function(){g.rdd(a)})},bb=function(a){var b,c=document.defaultView;c&&c.getComputedStyle?(a=c.getComputedStyle(a,\"\"))&&(b=a.direction):b=a.currentStyle?\n" +
                    "a.currentStyle.direction:a.style.direction;return\"rtl\"==b},jb=function(a,b,c){if(a)try{var d=document.getElementById(\"gbd5\");if(d){var f=d.firstChild,k=f.firstChild,l=document.createElement(\"li\");l.className=b+\" gbmtc\";l.id=c;a.className=\"gbmt\";l.appendChild(a);if(k.hasChildNodes()){c=[[\"gbkc\"],[\"gbf\",\"gbe\",\"gbn\"],[\"gbkp\"],[\"gbnd\"]];d=0;var m=k.childNodes.length;f=!1;for(var n=-1,t=0,E;E=c[t];t++){for(var U=0,I;I=E[U];U++){for(;d<m&&H(k.childNodes[d],I);)d++;if(I==b){k.insertBefore(l,k.childNodes[d]||\n" +
                    "null);f=!0;break}}if(f){if(d+1<k.childNodes.length){var V=k.childNodes[d+1];H(V.firstChild,\"gbmh\")||ib(V,E)||(n=d+1)}else if(0<=d-1){var W=k.childNodes[d-1];H(W.firstChild,\"gbmh\")||ib(W,E)||(n=d)}break}0<d&&d+1<m&&d++}if(0<=n){var y=document.createElement(\"li\"),z=document.createElement(\"div\");y.className=\"gbmtc\";z.className=\"gbmt gbmh\";y.appendChild(z);k.insertBefore(y,k.childNodes[n])}g.addHover&&g.addHover(a)}else k.appendChild(l)}}catch(Hb){q(Hb,\"sb\",\"al\")}},ib=function(a,b){for(var c=b.length,\n" +
                    "d=0;d<c;d++)if(H(a,b[d]))return!0;return!1},kb=function(a,b,c){jb(a,b,c)},lb=function(a,b){jb(a,\"gbe\",b)},mb=function(){B(function(){g.pcm&&g.pcm()})},nb=function(){B(function(){g.pca&&g.pca()})},ob=function(a,b,c,d,f,k,l,m,n,t){B(function(){g.paa&&g.paa(a,b,c,d,f,k,l,m,n,t)})},pb=function(a,b){L[a]||(L[a]=[]);L[a].push(b)},qb=function(a,b){M[a]||(M[a]=[]);M[a].push(b)},rb=function(a,b){ab[a]=b},sb=function(a,b){N[a]||(N[a]=[]);N[a].push(b)},eb=function(a){a.preventDefault&&a.preventDefault();a.returnValue=\n" +
                    "!1;a.cancelBubble=!0},tb=null,db=function(a,b){P();if(a){ub(a,\"Opening&hellip;\");Q(a,!0);b=\"undefined\"!=typeof b?b:1E4;var c=function(){vb(a)};tb=window.setTimeout(c,b)}},wb=function(a){P();a&&(Q(a,!1),ub(a,\"\"))},vb=function(a){try{P();var b=a||document.getElementById(O);b&&(ub(b,\"This service is currently unavailable.%1$sPlease try again later.\",\"%1$s\"),Q(b,!0))}catch(c){q(c,\"sb\",\"sdhe\")}},ub=function(a,b,c){if(a&&b){var d=cb(a);if(d){if(c){d.innerHTML=\"\";b=b.split(c);c=0;for(var f;f=b[c];c++){var k=document.createElement(\"div\");\n" +
                    "k.innerHTML=f;d.appendChild(k)}}else d.innerHTML=b;Q(a,!0)}}},Q=function(a,b){(b=void 0!==b?b:!0)?J(a,\"gbmsgo\"):K(a,\"gbmsgo\")},cb=function(a){for(var b=0,c;c=a.childNodes[b];b++)if(H(c,\"gbmsg\"))return c},P=function(){tb&&window.clearTimeout(tb)},xb=function(a){var b=\"inner\"+a;a=\"offset\"+a;return window[b]?window[b]:document.documentElement&&document.documentElement[a]?document.documentElement[a]:0},yb=function(){return!1},zb=function(){return!!O};p(\"so\",Za);p(\"sos\",Ya);p(\"si\",$a);p(\"tg\",fb);\n" +
                    "p(\"close\",gb);p(\"rdd\",hb);p(\"addLink\",kb);p(\"addExtraLink\",lb);p(\"pcm\",mb);p(\"pca\",nb);p(\"paa\",ob);p(\"ddld\",db);p(\"ddrd\",wb);p(\"dderr\",vb);p(\"rtl\",bb);p(\"op\",zb);p(\"bh\",L);p(\"abh\",pb);p(\"dh\",M);p(\"adh\",qb);p(\"ch\",N);p(\"ach\",sb);p(\"eh\",ab);p(\"aeh\",rb);ea=h.a(\"\")?Wa:Xa;p(\"qs\",ea);p(\"setContinueCb\",Ua);p(\"pc\",Va);p(\"bsy\",yb);h.d=eb;h.j=xb;var Ab={};v.base=Ab;w.push([\"m\",{url:\"//ssl.gstatic.com/gb/js/sem_02ac7b4e3a166bbe5127bb4e327ae9ec.js\"}]);g.sg={c:\"\"};p(\"wg\",{rg:{}});var Bb={tiw:h.c(\"15000\",0),tie:h.c(\"30000\",0)};v.wg=Bb;var Cb={thi:h.c(\"10000\",0),thp:h.c(\"180000\",0),tho:h.c(\"5000\",0),tet:h.b(\"0.5\",0)};v.wm=Cb;if(h.a(\"1\")){var Db=h.a(\"\");w.push([\"gc\",{auto:Db,url:\"//ssl.gstatic.com/gb/js/abc/gci_91f30755d6a6b787dcc2a4062e6e9824.js\",libs:\"googleapis.client:plusone:gapi.iframes\"}]);var Eb={version:\"gci_91f30755d6a6b787dcc2a4062e6e9824.js\",index:\"\",lang:\"en\"};v.gc=Eb;var Fb=function(a){window.googleapis&&window.iframes?a&&a():(a&&wa(a),D(\"gc\"))};p(\"lGC\",Fb);h.a(\"1\")&&p(\"lPWF\",Fb)};window.__PVT=\"\";if(h.a(\"1\")&&h.a(\"1\")){var Gb=function(a){Fb(function(){A(\"pw\",a);D(\"pw\")})};p(\"lPW\",Gb);w.push([\"pw\",{url:\"//ssl.gstatic.com/gb/js/abc/pwm_45f73e4df07a0e388b0fa1f3d30e7280.js\"}]);var Ib=[],Jb=function(a){Ib[0]=a},Kb=function(a,b){b=b||{};b._sn=\"pw\";r(a,b)},Lb={signed:Ib,elog:Kb,base:\"https://plusone.google.com/u/0\",loadTime:(new Date).getTime()};v.pw=Lb;var Mb=function(a,b){var c=b.split(\".\");b=function(){var b=arguments;a(function(){for(var a=g,d=0,f=c.length-1;d<f;++d)a=a[c[d]];a[c[d]].apply(a,b)})};for(var d=g,f=0,k=c.length-1;f<\n" +
                    "k;++f)d=d[c[f]]=d[c[f]]||{};return d[c[f]]=b};Mb(Gb,\"pw.clk\");Mb(Gb,\"pw.hvr\");p(\"su\",Jb,g.pw)};var Nb=[1,2,3,4,5,6,9,10,11,13,14,28,29,30,34,35,37,38,39,40,41,42,43,48,49,500];var Ob=h.b(\"0.001\",1E-4),Pb=h.b(\"1\",1),Qb=!1,Rb=!1;if(h.a(\"1\")){var Sb=Math.random();Sb<Ob&&(Qb=!0);Sb<Pb&&(Rb=!0)}var R=null;\n" +
                    "function Tb(a,b){var c=Ob,d=Qb;var f=a;if(!R){R={};for(var k=0;k<Nb.length;k++){var l=Nb[k];R[l]=!0}}if(f=!!R[f])c=Pb,d=Rb;if(d){d=encodeURIComponent;if(g.rp){var m=g.rp();m=\"-1\"!=m?m:\"\"}else m=\"\";f=(new Date).getTime();k=d(\"28834\");l=d(\"3B7WW476HK2t5wLv1pygAQ\");var n=g.bv.f,t=d(\"49\");m=d(m);c=Math.round(1/c);var E=d(\"217074232.0\"),U=\"&oggv=\"+d(\"es_plusone_gc_20181009.0_p0\"),I=d(\"com\"),V=d(\"en\"),W=\n" +
                    "d(\"XKK\");var y=0;h.a(\"\")&&(y|=1);h.a(\"\")&&(y|=2);h.a(\"\")&&(y|=4);a=[\"//www.google.com/gen_204?atyp=i&zx=\",f,\"&oge=\",a,\"&ogex=\",k,\"&ogev=\",l,\"&ogf=\",n,\"&ogp=\",t,\"&ogrp=\",m,\"&ogsr=\",c,\"&ogv=\",E,U,\"&ogd=\",I,\"&ogl=\",V,\"&ogc=\",W,\"&ogus=\",y];if(b){\"ogw\"in b&&(a.push(\"&ogw=\"+b.ogw),delete b.ogw);f=[];for(z in b)0!=f.length&&f.push(\",\"),f.push(Ub(z)),f.push(\".\"),f.push(Ub(b[z]));var z=f.join(\"\");\"\"!=z&&(a.push(\"&ogad=\"),a.push(d(z)))}na(a.join(\"\"))}}\n" +
                    "function Ub(a){\"number\"==typeof a&&(a+=\"\");return\"string\"==typeof a?a.replace(\".\",\"%2E\").replace(\",\",\"%2C\"):a}ka=Tb;p(\"il\",ka,u);var Vb={};v.il=Vb;var Wb=function(a,b,c,d,f,k,l,m,n,t){B(function(){g.paa(a,b,c,d,f,k,l,m,n,t)})},Xb=function(){B(function(){g.prm()})},Yb=function(a){B(function(){g.spn(a)})},Zb=function(a){B(function(){g.sps(a)})},$b=function(a){B(function(){g.spp(a)})},ac={\"27\":\"https://ssl.gstatic.com/gb/images/silhouette_24.png\",\"27\":\"https://ssl.gstatic.com/gb/images/silhouette_24.png\",\"27\":\"https://ssl.gstatic.com/gb/images/silhouette_24.png\"},bc=function(a){return(a=ac[a])||\"https://ssl.gstatic.com/gb/images/silhouette_24.png\"},\n" +
                    "cc=function(){B(function(){g.spd()})};p(\"spn\",Yb);p(\"spp\",$b);p(\"sps\",Zb);p(\"spd\",cc);p(\"paa\",Wb);p(\"prm\",Xb);pb(\"gbd4\",Xb);\n" +
                    "if(h.a(\"\")){var dc={d:h.a(\"\"),e:\"\",sanw:h.a(\"1\"),p:\"https://ssl.gstatic.com/gb/images/silhouette_96.png\",cp:\"1\",xp:h.a(\"1\"),mg:\"%1$s (delegated)\",md:\"%1$s (default)\",mh:\"220\",s:\"1\",pp:bc,ppl:h.a(\"\"),ppa:h.a(\"\"),\n" +
                    "ppm:\"Google+ page\"};v.prf=dc};var S,ec,T,fc,X=0,gc=function(a,b,c){if(a.indexOf)return a.indexOf(b,c);if(Array.indexOf)return Array.indexOf(a,b,c);for(c=null==c?0:0>c?Math.max(0,a.length+c):c;c<a.length;c++)if(c in a&&a[c]===b)return c;return-1},Y=function(a,b){return-1==gc(a,X)?(q(Error(X+\"_\"+b),\"up\",\"caa\"),!1):!0},ic=function(a,b){Y([1,2],\"r\")&&(S[a]=S[a]||[],S[a].push(b),2==X&&window.setTimeout(function(){b(hc(a))},0))},jc=function(a,b,c){if(Y([1],\"nap\")&&c){for(var d=0;d<c.length;d++)ec[c[d]]=!0;g.up.spl(a,b,\"nap\",c)}},kc=\n" +
                    "function(a,b,c){if(Y([1],\"aop\")&&c){if(T)for(var d in T)T[d]=T[d]&&-1!=gc(c,d);else for(T={},d=0;d<c.length;d++)T[c[d]]=!0;g.up.spl(a,b,\"aop\",c)}},lc=function(){try{if(X=2,!fc){fc=!0;for(var a in S)for(var b=S[a],c=0;c<b.length;c++)try{b[c](hc(a))}catch(d){q(d,\"up\",\"tp\")}}}catch(d){q(d,\"up\",\"mtp\")}},hc=function(a){if(Y([2],\"ssp\")){var b=!ec[a];T&&(b=b&&!!T[a]);return b}};fc=!1;S={};ec={};T=null;X=1;\n" +
                    "var mc=function(a){var b=!1;try{b=a.cookie&&a.cookie.match(\"PREF\")}catch(c){}return!b},nc=function(){try{return!!e.localStorage&&\"object\"==typeof e.localStorage}catch(a){return!1}},oc=function(a){return a&&a.style&&a.style.behavior&&\"undefined\"!=typeof a.load},pc=function(a,b,c,d){try{mc(document)||(d||(b=\"og-up-\"+b),nc()?e.localStorage.setItem(b,c):oc(a)&&(a.setAttribute(b,c),a.save(a.id)))}catch(f){f.code!=DOMException.QUOTA_EXCEEDED_ERR&&q(f,\"up\",\"spd\")}},qc=function(a,b,c){try{if(mc(document))return\"\";\n" +
                    "c||(b=\"og-up-\"+b);if(nc())return e.localStorage.getItem(b);if(oc(a))return a.load(a.id),a.getAttribute(b)}catch(d){d.code!=DOMException.QUOTA_EXCEEDED_ERR&&q(d,\"up\",\"gpd\")}return\"\"},rc=function(a,b,c){a.addEventListener?a.addEventListener(b,c,!1):a.attachEvent&&a.attachEvent(\"on\"+b,c)},sc=function(a){for(var b=0,c;c=a[b];b++){var d=g.up;c=c in d&&d[c];if(!c)return!1}return!0},tc=function(a,b){try{if(mc(a))return-1;var c=a.cookie.match(/OGPC=([^;]*)/);if(c&&c[1]){var d=c[1].match(new RegExp(\"\\\\b\"+\n" +
                    "b+\"-([0-9]+):\"));if(d&&d[1])return parseInt(d[1],10)}}catch(f){f.code!=DOMException.QUOTA_EXCEEDED_ERR&&q(f,\"up\",\"gcc\")}return-1};p(\"up\",{r:ic,nap:jc,aop:kc,tp:lc,ssp:hc,spd:pc,gpd:qc,aeh:rc,aal:sc,gcc:tc});var Z=function(a,b){a[b]=function(c){var d=arguments;g.qm(function(){a[b].apply(this,d)})}};Z(g.up,\"sl\");Z(g.up,\"si\");Z(g.up,\"spl\");Z(g.up,\"dpc\");Z(g.up,\"iic\");g.mcf(\"up\",{sp:h.b(\"0.01\",1),tld:\"com\",prid:\"49\"});function uc(){function a(){for(var b;(b=k[l++])&&\"m\"!=b[0]&&!b[1].auto;);b&&(va(2,b[0]),b[1].url&&ua(b[1].url,b[0]),b[1].libs&&C&&C(b[1].libs));l<k.length&&setTimeout(a,0)}function b(){0<f--?setTimeout(b,0):a()}var c=h.a(\"1\"),d=h.a(\"\"),f=3,k=w,l=0,m=window.gbarOnReady;if(m)try{m()}catch(n){q(n,\"ml\",\"or\")}d?p(\"ldb\",a):c?fa(window,\"load\",b):b()}p(\"rdl\",uc);}catch(e){window.gbar&&gbar.logger&&gbar.logger.ml(e,{\"_sn\":\"cfg.init\"});}})();\n" +
                    "(function(){try{var a=window.gbar;a.mcf(\"pm\",{p:\"\"});}catch(e){window.gbar&&gbar.logger&&gbar.logger.ml(e,{\"_sn\":\"cfg.init\"});}})();\n" +
                    "(function(){try{var a=window.gbar;a.mcf(\"mm\",{s:\"1\"});}catch(e){window.gbar&&gbar.logger&&gbar.logger.ml(e,{\"_sn\":\"cfg.init\"});}})();\n" +
                    "(function(){try{var a=this;var b=window.gbar;var c=b.i;var d=c.a,e=c.c,f={cty:\"XKK\",cv:\"217074232\",dbg:d(\"\"),ecv:\"0\",ei:e(\"3B7WW476HK2t5wLv1pygAQ\"),ele:d(\"1\"),esr:e(\"0.1\"),evts:[\"mousedown\",\"touchstart\",\"touchmove\",\"wheel\",\"keydown\"],gbl:\"es_plusone_gc_20181009.0_p0\",hd:\"com\",hl:\"en\",irp:d(\"\"),pid:e(\"49\"),\n" +
                    "snid:e(\"28834\"),to:e(\"300000\"),u:e(\"\"),vf:\".66.41.\"},g=f,h=[\"bndcfg\"],k=a;h[0]in k||\"undefined\"==typeof k.execScript||k.execScript(\"var \"+h[0]);for(var l;h.length&&(l=h.shift());)h.length||void 0===g?k=k[l]&&k[l]!==Object.prototype[l]?k[l]:k[l]={}:k[l]=g;}catch(e){window.gbar&&gbar.logger&&gbar.logger.ml(e,{\"_sn\":\"cfg.init\"});}})();\n" +
                    "(function(){try{window.gbar.rdl();}catch(e){window.gbar&&gbar.logger&&gbar.logger.ml(e,{\"_sn\":\"cfg.init\"});}})();\n" +
                    "</script><link rel=\"icon\" href=\"https://ssl.gstatic.com/docs/doclist/images/infinite_arrow_favicon_4.ico\"/></head><body><div id=gb><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>window.gbar&&gbar.eli&&gbar.eli()</script><div id=gbw><div id=gbz><span class=gbtcb></span><ol id=gbzc class=gbtc><li class=gbt><a target=_blank class=gbzt id=gb_119 href=\"https://plus.google.com/?gpsrc=ogpy0&tab=oX\"><span class=gbtb2></span><span class=gbts>Google+</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_119').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:119}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_1 href=\"https://www.google.com/webhp?tab=ow\"><span class=gbtb2></span><span class=gbts>Search</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_1').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:1}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_2 href=\"http://www.google.com/imghp?hl=en&tab=oi\"><span class=gbtb2></span><span class=gbts>Images</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_2').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:2}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_8 href=\"https://maps.google.com/maps?hl=en&tab=ol\"><span class=gbtb2></span><span class=gbts>Maps</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_8').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:8}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_78 href=\"https://play.google.com/?hl=en&tab=o8\"><span class=gbtb2></span><span class=gbts>Play</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_78').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:78}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_36 href=\"https://www.youtube.com/?gl=US&tab=o1\"><span class=gbtb2></span><span class=gbts>YouTube</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_36').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:36}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_5 href=\"https://news.google.com/nwshp?hl=en&tab=on\"><span class=gbtb2></span><span class=gbts>News</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_5').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:5}); });</script></li><li class=gbt><a target=_blank class=gbzt id=gb_23 href=\"https://mail.google.com/mail/?tab=om\"><span class=gbtb2></span><span class=gbts>Gmail</span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_23').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:23}); });</script></li><li class=gbt><a class=gbgt id=gbztm href=\"https://www.google.com/intl/en/options/\"  aria-haspopup=true aria-owns=gbd><span class=gbtb2></span><span id=gbztms class=\"gbts gbtsa\"><span id=gbztms1>More</span><span class=gbma></span></span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gbztm').addEventListener('click', function clickHandler() { gbar.tg(event,this); });</script><div class=gbm id=gbd aria-owner=gbztm><div id=gbmmb class=\"gbmc gbsb gbsbis\"><ol id=gbmm class=\"gbmcc gbsbic\"><li class=gbmtc><a class=\"gbmt gbp1 gbm0l\" id=gb_49 href=\"https://drive.google.com/?tab=oo\">Drive</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_49').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:49});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_24 href=\"https://www.google.com/calendar?tab=oc\">Calendar</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_24').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:24});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_51 href=\"https://translate.google.com/?hl=en&tab=oT\">Translate</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_51').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:51});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_17 href=\"http://www.google.com/mobile/?hl=en&tab=oD\">Mobile</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_17').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:17});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_10 href=\"https://books.google.com/bkshp?hl=en&tab=op\">Books</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_10').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:10});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_6 href=\"http://www.google.com/shopping?hl=en&tab=of\">Shopping</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_6').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:6});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_30 href=\"https://www.blogger.com/?tab=oj\">Blogger</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_30').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:30});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_27 href=\"https://www.google.com/finance?tab=oe\">Finance</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_27').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:27});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_31 href=\"https://photos.google.com/?tab=oq&pageId=none\">Photos</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_31').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:31});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_12 href=\"http://video.google.com/?hl=en&tab=ov\">Videos</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_12').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:12});; });</script></li><li class=gbmtc><a target=_blank class=gbmt id=gb_25 href=\"https://docs.google.com/document/?usp=docs_alc\">Docs</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gb_25').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:25});; });</script></li><li class=gbmtc><div class=\"gbmt gbmh\"></div></li><li class=gbmtc><a target=_blank  href=\"https://www.google.com/intl/en/options/\" class=gbmt>Even more &raquo;</a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.querySelector('li > a.gbmt').addEventListener('click', function clickHandler() { gbar.logger.il(1,{t:66});; });</script></li></ol><div class=gbsbt></div><div class=gbsbb></div></div></div></li></ol></div><div id=gbg><h2 class=gbxx>Account Options</h2><span class=gbtcb></span><ol class=gbtc><li class=gbt><a target=_top href=\"https://accounts.google.com/ServiceLogin?hl=en&passive=true&continue=https://drive.google.com/uc%3Fexport%3Ddownload%26id%3D11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY&service=writely\" onclick=\"gbar.logger.il(9,{l:'i'})\" id=gb_70 class=gbgt><span class=gbtb2></span><span id=gbgs4 class=gbts><span id=gbi4s1>Sign in</span></span></a></li><li class=\"gbt gbtb\"><span class=gbts></span></li><li class=gbt><a class=gbgt id=gbg5 href=\"http://www.google.com/preferences?hl=en\" title=\"Options\" aria-haspopup=true aria-owns=gbd5><span class=gbtb2></span><span id=gbgs5 class=gbts><span id=gbi5></span></span></a><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>document.getElementById('gbg5').addEventListener('click', function clickHandler() { gbar.tg(event,this); });</script><div class=gbm id=gbd5 aria-owner=gbg5><div class=gbmc><ol id=gbom class=gbmcc><li class=\"gbkc gbmtc\"><a target=\"_self\" class=gbmt href=\"/settings?hl=en_US\">Settings</a></li><li class=\"gbkc gbmtc\"><a target=_blank  class=gbmt href=\"//support.google.com/drive/?p=web_home&hl=en_US\">Help</a></li></ol></div></div></li></ol></div></div><div id=gbx3></div><div id=gbx4></div><script nonce='7+QFZRHv3R6ExbW03Ty1Jg458l4'>window.gbar&&gbar.elp&&gbar.elp()</script></div><div class=\"uc-main\"><div id=\"uc-dl-icon\" class=\"image-container\"><div class=\"drive-sprite-aux-download-file\"></div></div><div id=\"uc-text\"><p class=\"uc-warning-caption\">Google Drive can't scan this file for viruses.</p><p class=\"uc-warning-subcaption\"><span class=\"uc-name-size\"><a href=\"https://drive.google.com/open?id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY\">Copy of Mad Max Fury Road 2015 HD 720p.mp4</a> (922M)</span> is too large for Google to scan for viruses. Would you still like to download this file?</p><a id=\"uc-download-link\" class=\"goog-inline-block jfk-button jfk-button-action\" href=\"/uc?export=download&amp;confirm=wNxV&amp;id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY\">Download anyway</a></div></div><div class=\"uc-footer\"><hr class=\"uc-footer-divider\">&copy; 2018 Google - <a class=\"goog-link\" href=\"//support.google.com/drive/?p=web_home\">Help</a> - <a class=\"goog-link\" href=\"//support.google.com/drive/bin/answer.py?hl=en_US&amp;answer=2450387\">Privacy & Terms</a></div></body></html>";

            String jgcode=jGDriveObject.JGConfirmCode(jgsource2, 4);
            System.out.println("jgcode--" + jgcode + "--3--");

            SocketConnection sc= new SocketConnection();
            System.out.println("start SC t2");

            /*String sourceCode = sc.getURLSource("http://www.fotomac.com.tr/Yazarlar/Olcay%20%C3%87ak%C4%B1r/2011/11/23/hesap-makinesi");
            System.out.println("Source Code--"+sourceCode+"end SC");*/
            /*URL yahoo = new URL("https://www.yahoo.com/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yahoo.openStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();*/
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        //Android Play Video From URL Using VideoView Example
            /*
            video = (VideoView)findViewById(R.id.VideoView);

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Buffering video please wait...");
            pd.show();

            Uri uri = Uri.parse(video_url);
            video.setVideoURI(uri);
            video.start();

            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener(pd) {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //close the progress dialog when buffering is done
                    pd.dismiss();
                }
            });*/


        //System.out.println("Kjo eshte videoPath: "+videoPath);
        //System.out.println("Kjo eshte vidPN: "+vidPN);
        //System.out.println("Kjo eshte video2: "+video2);
        //System.out.println("Kjo eshte vidUri: "+vidUri);

        //play a video from URL
        /*try {
            setContentView(R.layout.activity_main);
            String link="https://drive.google.com/file/d/11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
            VideoView videoView = (VideoView) findViewById(R.id.VideoView);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            Uri video = Uri.parse(link);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
            videoView.start();
        } catch (Exception e) {
            // TODO: handle exception
            Toast.makeText(this, "Error connecting", Toast.LENGTH_SHORT).show();
        }*/
    }
    /*private fun checkConnectivity() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (!isConnected) {
            Toast.makeText(this, "Check network connection", Toast.LENGTH_SHORT).show();
        }
    }*/

    /*
    public class MyTask extends AsyncTask<Void, Void, Void> {
        String textResult="",firstPartText="",secondPartText="";
        final String textSource="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
        @Override
        protected Void doInBackground(Void... params){
            URL textUrl;

            try{
                textUrl = new URL(textSource);
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
                String stringbuffer;
                String stringText = ""; int i=0;
                while((stringbuffer = bufferReader.readLine()) != null){
                    int lenA= firstPartText.length();
                    int lenB= secondPartText.length();
                    int lenC= stringbuffer.length();
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
                textResult = stringText;
                System.out.println("textResult9: -First Part-"+firstPartText+" -Second Part- "+secondPartText +" -FUND");

            }catch(MalformedURLException e){
                e.printStackTrace();
                textResult =e.toString();
            }catch(IOException e){
                e.printStackTrace();
                textResult =e.toString();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid){
            textMsg.setText("DoubleVarText: "+firstPartText);
            textPrompt.setText("Finished! dhe gjateia"+textResult.length()+" gjatesia 1Part: "+ firstPartText.length()+" + 2Part:"+secondPartText.length());
            super.onPostExecute(aVoid);
        }
    }*/

    private void checkConnectivity(View v) {

        AlertDialog.Builder alert= new AlertDialog.Builder(this);
        alert.setTitle("Check Connectivity");
        alert.setMessage("Connection is fine");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Good job! :)", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Too bad! cuz it's awesome :)", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();

        try {
            Socket sock = new Socket();
            InetSocketAddress addr = new InetSocketAddress("www.google.com", 12345);
            sock.connect(addr, 300);

        }
        catch(Exception e){


        } finally {
            try{//sock.close();
                 }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public String isConnected(String url, int port){
        String answer="Nice!";
        /*Socket sock = new Socket();
        System.out.println("FIllimi i InetSocket! :)");
        InetSocketAddress addr = new InetSocketAddress(url, port);*/


        try {

            //FIllimi i HTMLSOURCECODE
            /*Socket s = new Socket(InetAddress.getByName("stackoverflow.com"), 80);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            System.out.println("FIllimi i HTMLSOURCECODE! :)");
            pw.println("GET / HTTP/1.1");
            pw.println("Host: stackoverflow.com");
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String t;
            while((t = br.readLine()) != null) System.out.println(t);
            br.close();*/
            //Fundi i HTMLSOURCECODE


            //System.out.println("FIllimi i socket! :)");
            //sock.connect(addr, 3000);
            //System.out.println("Fundi i socket! :)");
            //answer="Your device is connected! :)";
            System.out.println("Bani! :)");
        }
        catch(Exception e){
            System.out.println("Nuk bani! :)");
            System.out.println("Test Case: Failed1");
            System.out.println("Failing Reason: "+ e.toString());
            answer="No internet connection! :)";
        } finally {
            try{ //sock.close();
                 }
            catch(Exception e){
            }
        }
        return answer;
    }

    public String getJGConfirmCode(String jgsource, int postlen){
        String jgcode="",ini="";
        int start=-1, len=-1, end=-1, prestart=-1, prelen=-1;

        ini="confirm="; len=ini.length(); start = jgsource.indexOf(ini);
        prestart=start+len; prelen=postlen;

        jgcode = jgsource.substring(prestart, prelen);

        return jgcode;
    }

    public void showAlertDialog(View v) throws IOException {
        String conSuccess=isConnected("www.google.com",80);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Check Conectivity");
        alert.setMessage("You wana check connection?");

        alert.setPositiveButton("conSuccess", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "conSuccess", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "You didn't wanted to test your connectivity! :)", Toast.LENGTH_SHORT).show();
            }
        });

        alert.create().show();

    }


    public void browser1(View view){
        Intent BrowserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
        startActivity(BrowserIntent);
    }

    public class MyTask extends AsyncTask<Void, Void, Void>{
        private String gurlN="https://drive.google.com/uc?export=download&id=11wtw6iY4rmeoAZzhsrodhFAop8CV-kEY";
        /*MyTask(String gurl){
            gurlN=gurl;
        }*/


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
        }
        /*
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //N.s(MainActivity.this, "All Items were added succesfully");
            super.onPostExecute(aVoid);
        }*/
    }
}
