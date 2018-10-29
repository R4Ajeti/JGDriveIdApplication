package myproject2018.org.urlrequesttestapplications;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.VideoView;

import static android.content.ContentValues.TAG;

public class VideoPlayer extends VideoView {


    public VideoPlayer(Context context) {
        super(context);
        init();
    }

    public void init() {
        setAlpha(0); // hides view
        setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            setAlpha(1); // shows view
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }
}