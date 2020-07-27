package introduction.android.notificationDemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationDemoActivity extends Activity {
    private Button toastBtn;
	private Button notifyBtn;
	private static final int NOTIFICATION_ID = 1;
	NotificationManager mNotificationManager;
	Notification notification;
	Context context;
	private Button cancelBtn;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        toastBtn=(Button)this.findViewById(R.id.button1);
        toastBtn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(NotificationDemoActivity.this,
					     "这是一个位于中间位置的Toast", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				//Toast.makeText(NotificationDemoActivity.this, "这是一个Toast演示！", Toast.LENGTH_LONG).show();
			}
		});
        notifyBtn=(Button)this.findViewById(R.id.button2);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context = getApplicationContext();
				String ns = Context.NOTIFICATION_SERVICE;
				mNotificationManager = (NotificationManager) getSystemService(ns);				
				int icon = R.drawable.icon01;
				CharSequence tickerText = "这是一个Notification！";
				long when = System.currentTimeMillis();

				Notification.Builder builder=new Notification.Builder(context);
				builder.setSmallIcon(icon);
				builder.setTicker(tickerText);
				builder.setWhen(when);
				notification=builder.getNotification();
				
				//Notification notification = new Notification(icon, tickerText, 10000);
				
				CharSequence contentTitle = "My notification";
				CharSequence contentText = "点击这个notification，可以跳转到NoteActivity.";
				Intent notificationIntent = new Intent(context, NoteActivity.class);
				PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

				notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
				notification.defaults=notification.DEFAULT_SOUND;
				mNotificationManager.notify(NOTIFICATION_ID, notification);
			}
		});
        cancelBtn=(Button)this.findViewById(R.id.button3);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mNotificationManager.cancel(NOTIFICATION_ID);
			}
		});
    }
}