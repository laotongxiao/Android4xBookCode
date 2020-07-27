package introduction.android.audioServiceRecord;



import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AudioRecordService extends Service {

	private File recordPath;
	private File recordFile;
	private MediaRecorder mRecorder;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("aaaaa","start!");
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
     // 设置麦克风
     mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
     // 输出文件格式
     mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
     // 音频文件编码
     mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
     // 输出文件路径
     mRecorder.setOutputFile(recordFile.getAbsolutePath());     
   
     // 开始录音
     try {
             mRecorder.prepare();
             mRecorder.start();
     } catch (IllegalStateException e) {
             e.printStackTrace();
     } catch (IOException e) {
             e.printStackTrace();
     }
		return super.onStartCommand(intent, flags, startId);
	}

	
}
