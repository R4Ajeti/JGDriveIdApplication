package myproject2018.org.urlrequesttestapplications;

import android.annotation.SuppressLint;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JMegaDriveId {
    private String jmegaurl = null;
    private String jsource = null;
    private String jlinkgen = null;

    public JMegaDriveId() {
    }
    public JMegaDriveId(String jmegaurl){
        this.jmegaurl=jmegaurl;
    }

    public String execute(){
        String jlinkgen=this.jLinkGen(this.jMegaSource(this.jmegaurl));

        return jlinkgen;
    }

    @SuppressLint("StaticFieldLeak")
    public String jMegaSource(String megaurl) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(megaurl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) jsource = response.body().string();
            }
        });

        int i = 0;
        while (!isJMegaSourceUpOrDown(this, 0)) {
            i++;
        }

        return jsource;
    }

    public String jLinkGen(String jsource) {

        String jlinkgen = "", ini = "";
        int start = -1, len = -1, end = -1, prestart = -1, prelen = -1;

        ini = "source data-fluid-hd src=";
        len = ini.length();
        start = jsource.indexOf(ini);
        prestart = start + len;
        end = jsource.length() - 1;

        jlinkgen = jlinkgen + jsource.substring(prestart + 1, end);

        jlinkgen = jlinkgen.substring(0, jlinkgen.indexOf("'"));

        return jlinkgen;
    }

    public boolean isJMegaSourceUpOrDown(JMegaDriveId jMegaDriveIdObject, int ini) {
        boolean jgsup = false;
        if (ini == 0) {
            if (!(jMegaDriveIdObject.jsource == null)) {
                jgsup = true;
            } else {
                jgsup = false;
            }
        } else if (ini == 1) {
            if (!(jMegaDriveIdObject.jlinkgen == null)) {
                jgsup = true;
            } else {
                jgsup = false;
            }
        }
        return jgsup;
    }
}