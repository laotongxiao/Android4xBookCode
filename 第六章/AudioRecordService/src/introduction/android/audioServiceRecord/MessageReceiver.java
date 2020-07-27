package introduction.android.audioServiceRecord;

import java.io.File;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {
	private File recordPath;
	private File recordFile;
	private MediaRecorder mRecorder;
	private long startTime;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.proider.Telephony.SMS_RECEIVER"))
	    {
			recordBegin();
			new Thread(timing).start();	    
	    }
	}

	private Runnable timing=new Runnable(){
		private long currentTime=System.currentTimeMillis();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(currentTime<startTime+60*1000){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recordStop();
			}
		}		
	};	
	
	private void recordBegin() {
		// TODO Auto-generated method stub
		startTime=System.currentTimeMillis();
		recordPath = Environment.getExternalStorageDirectory();
	      File path = new File(recordPath.getPath() + File.separator
	                      + "audioRecords");                 
	      recordPath=path;  
	      try {
	      	recordFile = File.createTempFile(String.valueOf("myrecord_"), ".amr", recordPath);
	      } catch (IOException e) {
	              Log.d("audioRecorder", "文件创建失败");
	      }     
	      
	      mRecorder = new MediaRecorder();	      
	      mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);	      
	      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);	      
	      mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);	      
	      mRecorder.setOutputFile(recordFile.getAbsolutePath()); 
	      try {
	              mRecorder.prepare();
	              mRecorder.start();
	      } catch (IllegalStateException e) {
	              e.printStackTrace();
	      } catch (IOException e) {
	              e.printStackTrace();
	      }
	}
	protected void recordStop() {
		// TODO Auto-generated method stub
		mRecorder.stop();
		mRecorder.release();
		mRecorder=null;
	}
}
