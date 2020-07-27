package introduction.android.gpsLocationInMapDemo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class GPSLocationInMapDemoActivity extends MapActivity {
	/** Called when the activity is first created. */
	// ����Location����
	protected Location location;
	// ����MapView����
	private MapView map;
	// ����MyLocationOverlay�����ڵ�ͼ�ϱ�ע��ǰλ��
	private MyLocationOverlay myLocation;
	private MapController mapController;
	private TextView myLocationText;
	private GeoPoint geopoint;
	private double latitude;
	private double longitude;

	/** Called when the activity is first created. */
	protected boolean isRouteDisplayed() {
		return false;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myLocationText = (TextView) findViewById(R.id.myLocationText);
		// ����LocationManager����
		LocationManager locationManager;
		String seviceName = Context.LOCATION_SERVICE;
		// ��ȡLocationManager����
		locationManager = (LocationManager) getSystemService(seviceName);
		// //��ȡCriteria����
		// Criteria criteria = new Criteria();
		// //������Ҫ����ϸ�Ķ�λ����Ҫ��
		// criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// //���ö�λ����Ҫ�߶���Ϣ
		// criteria.setAltitudeRequired(false);
		// //���ò���Ҫ�ṩ�����Ϣ
		// criteria.setBearingRequired(false);
		// //�����������շ�
		// criteria.setCostAllowed(true);
		// //���õͺ���
		// criteria.setPowerRequirement(Criteria.POWER_LOW);
		// //�������õ�������ȡ���ʵ�provider
		// String provider = locationManager.getBestProvider(criteria,true);
		// //�õ�Location����
		// Location location = locationManager.getLastKnownLocation(provider);
		// //����updateWithNewLocation����
		// updateWithNewLocation(location);
		// ����ʹ�õ�Provider������λ�ñ仯��������ÿ��2sʱ�䡢�����10�״���λ�ñ仯�¼�
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 10, locationListener);
		// �õ�MapView����
		map = (MapView) findViewById(R.id.myMapView);
		mapController = map.getController();
		mapController.setZoom(13);
		// ����map֧�����Ź�����
		map.setBuiltInZoomControls(true);
		map.setSatellite(true);
		

	}

	// �õ�LocationListener����
	private final LocationListener locationListener = new LocationListener() {

		// ��Provider���ڲ���ʹ��ʱ����
		public void onProviderDisabled(String provider) {

		}

		// ��״̬�����ı�ʱ����
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		// ��λ�÷����仯ʱ����
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// �õ���ǰλ�õ�γ��
			latitude = location.getLatitude();
			// �õ���ǰλ�õľ���
			longitude = location.getLongitude();
			geopoint = new GeoPoint(new Double(latitude * 1E6).intValue(),
					new Double(longitude * 1E6).intValue());

			mapController.setCenter(geopoint);
			// �õ���ǰλ�õ�MyLocationOverlay����
			myLocation = new MyLocationOverlay(
					GPSLocationInMapDemoActivity.this, map);
			myLocation.enableMyLocation();
			// ����ǰλ����ӵ���ͼ��
			map.getOverlays().add(myLocation);
			// ���õ�ͼΪ����ģʽ
			myLocationText.setText("��ǰλ��γ�ȣ�" + latitude + "\n��ǰλ�þ��ȣ�"
					+ longitude);

		}

		// ��Provider���ڿ���ʱ����
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}
	};	
}