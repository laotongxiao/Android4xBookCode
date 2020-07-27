package introduction.android.AudioServiceDemo;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyAudioService extends Service {

	private MediaPlayer mediaplayer;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mediaplayer != null)
		{
			mediaplayer.release();
			mediaplayer =null ;
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		String path="sdcard/music/white.mp3";
		mediaplayer=new MediaPlayer();
		try {			
			mediaplayer.setDataSource(path);
			mediaplayer.prepare();
			mediaplayer.start();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
