package introduction.android.AudioRecord;

import java.io.File;
import java.io.IOException;

import introduction.android.AudioRecord.R;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AudioRecordDemo extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private ImageButton st,stop;
	private TextView sttext;	
	private MediaRecorder mRecorder;
	 private File recordPath;
	 private File recordFile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        st = (ImageButton)findViewById(R.id.st);
        stop = (ImageButton)findViewById(R.id.stop);
        sttext =(TextView)findViewById(R.id.sttext);
        st.setOnClickListener(this);
        stop.setOnClickListener(this);         
    }
    public void start() {
        if (checkSDCard()) {
                recordPath = Environment.getExternalStorageDirectory();
                File path = new File(recordPath.getPath() + File.separator
                                + "audioRecords");
                if(!path.mkdirs()){
                	Log.d("audioRecorder", "����Ŀ¼ʧ��");
                }      
                recordPath=path;
        } else {
                Toast.makeText(AudioRecordDemo.this, "SDcardδ����", Toast.LENGTH_LONG).show();              
                return ;
        }
       
        try {
        	recordFile = File.createTempFile(String.valueOf("myrecord_"), ".amr", recordPath);
        } catch (IOException e) {
                Log.d("audioRecorder", "�ļ�����ʧ��");
        }     
        
        mRecorder = new MediaRecorder();
        // ������˷�
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // ����ļ���ʽ
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        // ��Ƶ�ļ�����
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        // ����ļ�·��
        mRecorder.setOutputFile(recordFile.getAbsolutePath());     
      
        // ��ʼ¼��
        try {
                mRecorder.prepare();
                mRecorder.start();
        } catch (IllegalStateException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
}

    public void stop() {
    	try{    	
	        if(mRecorder != null) {
	                mRecorder.stop();               
	                mRecorder.release();
	                mRecorder = null;
	        }
        }
    	catch(IllegalStateException e){
    		
    	}
}
    
	private boolean checkSDCard() {
		// TODO Auto-generated method stub
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) 
	    { 
	      return true; 
	    } 
	    
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == st){
			AudioRecordDemo.this.start();
			sttext.setText("����¼��������������");
		}
		
		if(v == stop){
			sttext.setText("ֹͣ¼��������������");
			AudioRecordDemo.this.stop();			
		}		
	}   
}