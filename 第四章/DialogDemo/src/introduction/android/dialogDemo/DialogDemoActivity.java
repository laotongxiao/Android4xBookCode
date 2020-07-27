package introduction.android.dialogDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DialogDemoActivity extends Activity {
    public static final int ALERT_DLG=1;
    public static final int PROGRESS_DLG=ALERT_DLG+1;
	private Button btn;
	private Button progressbtn;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(ALERT_DLG);
			}
        	
        });
        progressbtn=(Button)findViewById(R.id.button2);
        progressbtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(PROGRESS_DLG);
			}
        	
        });
    }

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog=null;
		final ProgressDialog progressDialog;
		switch(id){
		case ALERT_DLG:
			AlertDialog.Builder builder=new AlertDialog.Builder(DialogDemoActivity.this);
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setTitle("AlertDialog");
			builder.setMessage("这是一个AlertDialog");
			builder.setPositiveButton("Positive",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.i("DialogDemo","OK按键被点击！");
				}
				
			});
			builder.setNegativeButton("Negative",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.i("DialogDemo","Cancel按键被点击！");
				}
				
			});
			builder.setNeutralButton("Neutral",new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Log.i("DialogDemo","Neutral按键被点击！");
				}
				
			});			
			dialog=builder.create();
			break;
		case PROGRESS_DLG:
			progressDialog=new ProgressDialog(this);
			//设置水平进度条
			progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
			//设置进度条最大值为100
			progressDialog.setMax(100);
			//设置进度条当前值为0
			progressDialog.setProgress(0);
			dialog=progressDialog;
			new Thread(new Runnable(){
				int count=0;
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(progressDialog.getProgress()<100){
						count+=3;
						progressDialog.setProgress(count);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}).start();
			break;
		default:
			break;
		}
		return dialog;
	}
}