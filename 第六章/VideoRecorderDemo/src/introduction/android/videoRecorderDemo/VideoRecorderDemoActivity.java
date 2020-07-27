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
		//给摄像头解锁
		camera.unlock();
		//MediaRecorder获取到摄像头的访问权
		videoRecorder.setCamera(camera);
		//设置视频录制过程中所录制的音频来自手机的麦克风
		videoRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		//设置视频源为摄像头
		videoRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		//设置视频录制的输出文件格式为3gp文件
		videoRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); 
		//设置音频编码方式为AAC
		videoRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		// 设置录制的视频编码方式为H.264  
		videoRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);  
		// 设置视频录制的分辨率，必须放在设置编码和格式的后面，否则报错  
		videoRecorder.setVideoSize(176, 144);  
		// 设置录制的视频帧率，必须放在设置编码和格式的后面，否则报错  
		videoRecorder.setVideoFrameRate(20);
		//	videoRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_LOW));
		if(!checkSDCard()){
			Log.e("videoRecorder","未找到SD卡！");
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
		// 判断SD存储卡是否存在
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}