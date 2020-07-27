package introduction.android.playvideo;


import introduction.android.playvideo.R;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideo extends Activity {
	private VideoView videoView;
	private MediaController mc ;
	private String path ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        videoView = (VideoView) this.findViewById(R.id.videoView);
        //path ="file:///sdcard/Movies/1.3gp";
        //videoView.setVideoURI(Uri.parse(path));
        path="/sdcard/Movies/movie.mp4";        
        mc = new MediaController(this);
        videoView.setMediaController(mc);        
        videoView.setVideoPath(path);
        videoView.setOnCompletionListener(new OnCompletionListener(){
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				finish();
			}        	
        });
        videoView.requestFocus();
        videoView.start();        
	}
}
