package myproject2018.org.urlrequesttestapplications;

import android.annotation.SuppressLint;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

abstract class jHostDriveId {

    private String jhosturl = null;
    private String jsource = null;
    private String jlinkgen = null;
    private String unique = null;
    private char lastchar;
    private int postlen = -1;


    public jHostDriveId() {
    }

    public jHostDriveId(String jmegaurl, String unique, int postlen) {
        this.jhosturl = jmegaurl;
        this.unique = unique;
        this.postlen = postlen;
    }

    public jHostDriveId(String jmegaurl, String unique, char lastchar) {
        this.jhosturl = jmegaurl;
        this.unique = unique;
        this.lastchar = lastchar;
    }

    public String execute() {
        if (!(postlen == -1)) {
            this.jlinkgen = this.jLinkGen(this.jMegaSource(this.jhosturl), this.unique, this.postlen);
        } else if (!(lastchar == '\u0000')) {
            this.jlinkgen = this.jLinkGen(this.jMegaSource(this.jhosturl), this.unique, this.lastchar);
        } else {
            this.jlinkgen = this.jLinkGen(this.jMegaSource(this.jhosturl), this.unique);
        }

        return this.jlinkgen;
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
        while (!isJHostSourceUpOrDown(0)) {
            i++;
        }

        return jsource;
    }

    public String jLinkGen(String jsource, String uniqueF) {

        String jlinkgen = "", unique = "";
        int start = -1, len = -1, end = -1, prestart = -1, prelen = -1;

        unique = uniqueF;
        len = unique.length();
        start = jsource.indexOf(unique);
        prestart = start + len;
        end = jsource.length() - 1;

        jlinkgen = jlinkgen + jsource.substring(prestart, end);

        jlinkgen = jlinkgen.substring(1, jlinkgen.indexOf(jlinkgen.charAt(0)));

        return jlinkgen;
    }

    public String jLinkGen(String jsource, String uniqueF, int postlen) {

        String jlinkgen = "", unique = uniqueF;

        int start = -1, len = -1, end = -1, prestart = -1, prelen = -1;

        len = unique.length();
        start = jsource.indexOf(unique);
        prestart = start + len;
        end = prestart + postlen;

        jlinkgen = jlinkgen + jsource.substring(prestart, end);

        return jlinkgen;
    }

    public String jLinkGen(String jsource, String uniqueF, char lastchar) {

        String jlinkgen = "", unique = "";
        int start = -1, len = -1, end = -1, prestart = -1, prelen = -1;

        unique = uniqueF;
        len = unique.length();
        start = jsource.indexOf(unique);
        prestart = start + len;
        end = jsource.length() - 1;

        jlinkgen = jlinkgen + jsource.substring(prestart + 1, end);

        jlinkgen = jlinkgen.substring(0, jlinkgen.indexOf(lastchar));

        return jlinkgen;
    }

    private boolean isJHostSourceUpOrDown(int ini) {
        boolean jgsup = false;
        if (ini == 0) {
            if (!(this.jsource == null)) {
                jgsup = true;
            } else {
                jgsup = false;
            }
        } else if (ini == 1) {
            if (!(this.jlinkgen == null)) {
                jgsup = true;
            } else {
                jgsup = false;
            }
        }
        return jgsup;
    }
}
