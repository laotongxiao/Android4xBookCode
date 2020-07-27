package introduction.android.myCameraDemo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyCameraDemoActivity extends Activity {
	private Button opbtn;
	private Button playbtn;
	private Button clobtn;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera camera;
	private int previewWidth = 320;
	private int previewHeight = 240;
	private String filepath = "/sdcard/mypicture.jpg";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		opbtn = (Button) this.findViewById(R.id.button1);
		playbtn = (Button) this.findViewById(R.id.button2);
		clobtn = (Button) this.findViewById(R.id.button3);
		surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView1);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				Log.i("camera", "surface destroyed.");
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				Log.i("camera", "surface created.");
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				Log.i("camera", "surface changed.");
			}
		});

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
				takePicture();
			}

		});
		clobtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				closeCamera();
			}

		});

	}

	protected void closeCamera() {
		// TODO Auto-generated method stub
		camera.stopPreview();
		camera.release();
		camera = null;
	}

	protected void takePicture() {
		// TODO Auto-generated method stub
		if (checkSDCard()) {
			camera.takePicture(null, null, jpeg);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			camera.startPreview();
		} else {
			Log.e("camera", "SD CARD not exist.");
			return;
		}
	}

	private void openCamera() {
		// TODO Auto-generated method stub

		try {
			camera = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
			Log.e("camera", "open camera error!");
			e.printStackTrace();
			return;
		}
		Parameters params = camera.getParameters();
		params.setPreviewSize(previewWidth, previewHeight);
		params.setPictureFormat(PixelFormat.JPEG);
		params.setPictureSize(previewWidth, previewHeight);
		camera.setParameters(params);
		try {
			camera.setPreviewDisplay(surfaceHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("camera", "preview failed.");
			e.printStackTrace();
		}
		camera.startPreview();

	}

	private PictureCallback jpeg = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			File pictureFile = new File(filepath);
			if (pictureFile == null) {
				Log.d("camera",
						"Error creating media file, check storage permissions");
				return;
			}
			try {
				// ½«ÅÄÉãµÄÕÕÆ¬Ð´ÈëSD¿¨ÖÐ
				FileOutputStream fos = new FileOutputStream(pictureFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
				bos.flush();
				bos.close();
				fos.close();
				Log.i("camera", "jpg file saved.");
			} catch (FileNotFoundException e) {
				Log.d("camera", "File not found: " + e.getMessage());
			} catch (IOException e) {
				Log.d("camera", "Error accessing file: " + e.getMessage());
			}
		}

	};

	private boolean checkSDCard() {
		// ÅÐ¶ÏSD´æ´¢¿¨ÊÇ·ñ´æÔÚ
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}