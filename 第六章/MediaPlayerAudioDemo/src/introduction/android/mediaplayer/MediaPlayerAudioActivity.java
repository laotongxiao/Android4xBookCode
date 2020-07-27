package introduction.android.mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaPlayerAudioActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private Button button01,button02,button03;
	private String  PLAY = "paly";
	private int Local = 1;
	private int Stream = 2 ;
	private  int Resources = 3 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button01  = (Button)findViewById(R.id.button01);
        button02  = (Button)findViewById(R.id.button02);
        button03  = (Button)findViewById(R.id.button03);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
        button03.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MediaPlayerAudioActivity.this,PlayAudio.class);
		if(v == button01){
			intent.putExtra(PLAY,  Local);
		}
		if(v == button02){
			intent.putExtra(PLAY,  Stream);
		}		
		if(v == button03){
			intent.putExtra(PLAY,  Resources);
		}		
		MediaPlayerAudioActivity .this.startActivity(intent);
		finish();
	}
}