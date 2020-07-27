package introduction.android.videoRecorderDemo;

import java.io.IOException;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoRecorderDemoActivity extends Activity {
	private Button opbtn;
	private Button playbtn;
	private Button clobtn;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera camera;
	private MediaRecorder videoRecorder;
	private String myVideofilepath = "/sdcard/myVideo.3gp";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        opbtn = (Button) this.findViewById(R.id.button1);
		playbtn = (Button) this.findViewById(R.id.button2);
		clobtn = (Button) this.findViewById(R.id.button3);
		videoRecorder=new MediaRecorder();
		surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView1);
		surfaceHolder = surfaceView.getHolder();
//		surfaceHolder.addCallback(new SurfaceHolder.Callback() {
//
//			@Override
//			public void surfaceDestroyed(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//				Log.i("videoRecorder", "surface destroyed.");
//				surfaceHolder=null;
//				stopRecording();
//				releaseCamera();
//			}
//
//			@Override
//			public void surfaceCreated(SurfaceHolder holder) {
//				// TODO Auto-generated method stub
//				Log.i("videoRecorder", "surface created.");
//				surfaceHolder=holder;
//			}
//
//			@Override
//			public void surfaceChanged(SurfaceHolder holder, int format,
//					int width, int height) {
//				// TODO Auto-generated method stub
//				Log.i("videoRecorder", "surface changed.");
//				surfaceHolder=holder;
//			}
//		});
		opbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openCamera();
			}

		});
		playbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				benginRecording();
			}

		});
		clobtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopRecording();
			}

		});
    }
    
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopRecording();
		releaseCamera();
	}

	protected void stopRecording() {
		// TODO Auto-generated method stub
    	Log.i("videoRecorder","stopRecording....");
    	if(videoRecorder!=null){
    	videoRecorder.stop();
    	videoRecorder.reset();
    	videoRecorder.release();
    	videoRecorder=null;
    	camera.lock();	
    	}
    }
    private void releaseCamera(){
        if (camera != null){
        	camera.stopPreview();
        	camera.release();        // release the camera for other applications
        	camera = null;
        }
    }
	protected void benginRecording() {
		// TODO Auto-generated method stub
		Log.i("videoRecorder","beginRecording.");
		//������ͷ����
		camera.unlock();
		//MediaRecorder��ȡ������ͷ�ķ���Ȩ
		videoRecorder.setCamera(camera);
		//������Ƶ¼�ƹ�������¼�Ƶ���Ƶ�����ֻ�����˷�
		videoRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		//������ƵԴΪ����ͷ
		videoRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		//������Ƶ¼�Ƶ�����ļ���ʽΪ3gp�ļ�
		videoRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); 
		//������Ƶ���뷽ʽΪAAC
		videoRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		// ����¼�Ƶ���Ƶ���뷽ʽΪH.264  
		videoRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);  
		// ������Ƶ¼�Ƶķֱ��ʣ�����������ñ���͸�ʽ�ĺ��棬���򱨴�  
		videoRecorder.setVideoSize(176, 144);  
		// ����¼�Ƶ���Ƶ֡�ʣ�����������ñ���͸�ʽ�ĺ��棬���򱨴�  
		videoRecorder.setVideoFrameRate(20);
		//	videoRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_LOW));
		if(!checkSDCard()){
			Log.e("videoRecorder","δ�ҵ�SD����");
			return;
		}			
		videoRecorder.setOutputFile(myVideofilepath);
		videoRecorder.setPreviewDisplay(surfaceHolder.getSurface());
		try {
			videoRecorder.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		videoRecorder.start();
	}
	private void openCamera() {
		// TODO Auto-generated method stub
		Log.i("videoRecorder","openCamera.");
		try {
			camera = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
			Log.e("camera", "open camera error!");
			e.printStackTrace();
			return;
		}		
		try {
			camera.setPreviewDisplay(surfaceHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("camera", "preview failed.");
			e.printStackTrace();
		}
		camera.startPreview();

	}
	private boolean checkSDCard() {
		// �ж�SD�洢���Ƿ����
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}