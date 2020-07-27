package introduction.android.gpsLocation;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GPSLocationActivity extends Activity {
    /** Called when the activity is first created. */
	private Button btn_listen;
	private TextView tv_01,tv_02;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn_listen=(Button) findViewById(R.id.btn_listen);
        tv_01=(TextView) findViewById(R.id.tv_01);
        tv_02=(TextView) findViewById(R.id.tv_02);
        
        btn_listen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LocationManager locationManager=(LocationManager) GPSLocationActivity.this.getSystemService(Context.LOCATION_SERVICE);
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,new MyLocationListener());
			}
		});        
    }
    
    class MyLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			tv_01.setText("您当前位置的经度为："+location.getLongitude());
			tv_02.setText("您当前位置的纬度为："+location.getLatitude());
		}

		@Override
		public void onProviderDisabled(String provider) {
			//当provider被用户关闭时调用
			Log.i("GpsLocation","provider被关闭！");
		}

		@Override
		public void onProviderEnabled(String provider) {
			//当provider被用户开启后调用
			Log.i("GpsLocation","provider被开启！");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			//当provider的状态在OUT_OF_SERVICE、TEMPORARILY_UNAVAILABLE和AVAILABLE之间发生变化时调用
			Log.i("GpsLocation","provider状态发生改变！");
		}    	
    }
}