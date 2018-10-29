package myproject2018.org.urlrequesttestapplications;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class N {
    public static void m(String message){
        Log.d("VIVZ", message);
    }
    public static void s(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
