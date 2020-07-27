package introduction.android.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CameraDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private ImageView imageview;
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageview = (ImageView)findViewById(R.id.imageview);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 try{
        	Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        	startActivityForResult(i, 1);
        }
        catch (Exception e){
        
        }
			}
        	
        });
       
    }

    protected void onActivityResult(int requestcode, int resultCode,Intent data){
    	try{
    		if (requestcode != 1){
    			return;
    		}
    		super.onActivityResult(requestcode, resultCode, data);
    		Bundle  extras = data.getExtras();
    		Bitmap bitmap = (Bitmap)extras.get("data");
    		imageview.setImageBitmap(bitmap);
    	}
    	catch(Exception e){
    		
    	}
    }
    
}