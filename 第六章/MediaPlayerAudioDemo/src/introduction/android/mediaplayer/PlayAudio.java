package introduction.android.mediaplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PlayAudio extends Activity{
	private TextView textview ;
	private String  PLAY = "paly";
	private MediaPlayer midiaplayer;
	private String path ;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        textview = (TextView)findViewById(R.id.textview);
       Bundle extras = getIntent().getExtras();
       playAudio(extras.getInt(PLAY));  
	}
	private void playAudio(int play) {
		// TODO Auto-generated method stub
		try{
			switch(play){
			case 1:
				path ="sdcard/music/white.mp3";
				if(path == ""){
				    Toast.makeText(PlayAudio.this, "请放入音频文件", Toast.LENGTH_LONG);
				              }
				midiaplayer  = new  MediaPlayer(); 
				midiaplayer.setDataSource(path);
				midiaplayer.prepare(); 
				midiaplayer.start();
				textview.setText("正在播放文件中的音乐");
				break;
			  case 2:
				path = "http://www.musiconline.com/classic/007.mp3";
				  if(path == "")  {
					Toast.makeText(PlayAudio.this, "未找到您要播放的歌曲", Toast.LENGTH_LONG).show();
				                  }
					midiaplayer = new  MediaPlayer();
					midiaplayer.setDataSource(path);
					midiaplayer.prepare(); 
					midiaplayer.start();
					textview.setText("正在播放网络中的音乐");
					break ;
			  case 3:
					midiaplayer = MediaPlayer.create(this, R.raw.black);
					midiaplayer.start();
	                textview.setText("正在播放本地资源中的音乐");
					break;
			             }
		   }
			catch(Exception e){
			
			   System.out.println("出现异常");
		                       }
	
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (midiaplayer != null)
		{
			midiaplayer.release();
			midiaplayer =null ;
		}
	}
}
