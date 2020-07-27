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
			tv_01.setText("����ǰλ�õľ���Ϊ��"+location.getLongitude());
			tv_02.setText("����ǰλ�õ�γ��Ϊ��"+location.getLatitude());
		}

		@Override
		public void onProviderDisabled(String provider) {
			//��provider���û��ر�ʱ����
			Log.i("GpsLocation","provider���رգ�");
		}

		@Override
		public void onProviderEnabled(String provider) {
			//��provider���û����������
			Log.i("GpsLocation","provider��������");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			//��provider��״̬��OUT_OF_SERVICE��TEMPORARILY_UNAVAILABLE��AVAILABLE֮�䷢���仯ʱ����
			Log.i("GpsLocation","provider״̬�����ı䣡");
		}    	
    }
}