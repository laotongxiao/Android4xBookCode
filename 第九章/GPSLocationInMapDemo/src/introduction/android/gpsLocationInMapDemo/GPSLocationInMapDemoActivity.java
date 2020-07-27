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
	// 定义Location对象
	protected Location location;
	// 定义MapView对象
	private MapView map;
	// 定义MyLocationOverlay对象，在地图上标注当前位置
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
		// 定义LocationManager对象
		LocationManager locationManager;
		String seviceName = Context.LOCATION_SERVICE;
		// 获取LocationManager对象
		locationManager = (LocationManager) getSystemService(seviceName);
		// //获取Criteria对象
		// Criteria criteria = new Criteria();
		// //设置需要更精细的定位精度要求
		// criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// //设置定位不需要高度信息
		// criteria.setAltitudeRequired(false);
		// //设置不需要提供轴承信息
		// criteria.setBearingRequired(false);
		// //设置允许是收费
		// criteria.setCostAllowed(true);
		// //设置低耗能
		// criteria.setPowerRequirement(Criteria.POWER_LOW);
		// //根据设置的条件获取合适的provider
		// String provider = locationManager.getBestProvider(criteria,true);
		// //得到Location对象
		// Location location = locationManager.getLastKnownLocation(provider);
		// //调用updateWithNewLocation方法
		// updateWithNewLocation(location);
		// 设置使用的Provider，设置位置变化监听器。每隔2s时间、间隔超10米触发位置变化事件
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				2000, 10, locationListener);
		// 得到MapView对象
		map = (MapView) findViewById(R.id.myMapView);
		mapController = map.getController();
		mapController.setZoom(13);
		// 设置map支持缩放工具条
		map.setBuiltInZoomControls(true);
		map.setSatellite(true);
		

	}

	// 得到LocationListener对象
	private final LocationListener locationListener = new LocationListener() {

		// 当Provider处于不能使用时触发
		public void onProviderDisabled(String provider) {

		}

		// 当状态发生改变时触发
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		// 当位置发生变化时触发
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			// 得到当前位置的纬度
			latitude = location.getLatitude();
			// 得到当前位置的经度
			longitude = location.getLongitude();
			geopoint = new GeoPoint(new Double(latitude * 1E6).intValue(),
					new Double(longitude * 1E6).intValue());

			mapController.setCenter(geopoint);
			// 得到当前位置的MyLocationOverlay对象
			myLocation = new MyLocationOverlay(
					GPSLocationInMapDemoActivity.this, map);
			myLocation.enableMyLocation();
			// 将当前位置添加到地图上
			map.getOverlays().add(myLocation);
			// 设置地图为卫星模式
			myLocationText.setText("当前位置纬度：" + latitude + "\n当前位置经度："
					+ longitude);

		}

		// 当Provider处于可用时触发
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}
	};	
}