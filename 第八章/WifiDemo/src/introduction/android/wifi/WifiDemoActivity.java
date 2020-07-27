package introduction.android.wifi;

import java.util.List;

import sie.android.wifi.R;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class WifiDemoActivity extends Activity {
	private Button open_bt, close_bt, check_bt, search_bt;
	private TextView textView;
	private WifiManager wifiManager;
	private WifiInfo wifiInfo;
	private ScrollView scrollView;
	private List WifiConfiguration;
	private ScanResult scanResult;
	private List<ScanResult> WifiList;
	private StringBuffer stringBuffer = new StringBuffer();

	/** Called when the activity is first created. */
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		scrollView = (ScrollView) findViewById(R.id.mScrollView);
		open_bt = (Button) findViewById(R.id.open_bt);
		close_bt = (Button) findViewById(R.id.close_bt);
		check_bt = (Button) findViewById(R.id.check_bt);
		search_bt = (Button) findViewById(R.id.search_bt);
		textView = (TextView) findViewById(R.id.text);

		open_bt.setOnClickListener(new open_btListener());
		close_bt.setOnClickListener(new close_btListener());
		check_bt.setOnClickListener(new check_btListener());
		search_bt.setOnClickListener(new search_btListener());
	}

	class search_btListener implements OnClickListener {

		
		public void onClick(View v) {
			// TODO Auto-generated method stub

			wifiManager.startScan();
			WifiList = wifiManager.getScanResults();
			wifiInfo = wifiManager.getConnectionInfo();

			if (stringBuffer != null) {
				stringBuffer = new StringBuffer();
			}

			stringBuffer=stringBuffer.append("Wifi名").append("     ").append("Wifi地址").append("         ").append("Wifi频率").append("     ").append("Wifi信号").append("\n");
			if (WifiList != null) {
				for (int i = 0; i < WifiList.size(); i++) {
					scanResult = WifiList.get(i);
					stringBuffer = stringBuffer.append(scanResult.SSID).append("  ")
							.append(scanResult.BSSID).append("  ")
							.append(scanResult.frequency).append(" ")
							.append(scanResult.level).append("\n");

					textView.setText(stringBuffer.toString());
				}
				stringBuffer = stringBuffer.append("-----------------------------------------------").append("\n");
				textView.setText(stringBuffer.toString());
				stringBuffer = stringBuffer.append("当前Wifi——BSSID").append(":    ").append(wifiInfo.getBSSID()).append("\n")
						.append("当前Wifi——HiddenSSID").append(":    ").append(wifiInfo.getHiddenSSID()).append("\n")
						.append("当前Wifi——IpAddress").append(":    ").append(wifiInfo.getIpAddress()).append("\n")
						.append("当前Wifi——LinkSpeed").append(":    ").append(wifiInfo.getLinkSpeed()).append("\n")
						.append("当前Wifi——MacAddress").append(":    ").append(wifiInfo.getMacAddress()).append("\n")
						.append("当前Wifi——Network ID").append(":    ").append(wifiInfo.getNetworkId()).append("\n")
						.append("当前Wifi——RSSI").append(":    ").append(wifiInfo.getRssi()).append("\n")
						.append("当前Wifi——SSID").append(":    ").append(wifiInfo.getSSID()).append("\n")
						.append("-----------------------------------------------").append("\n")
						.append("全部打印出关于本机Wifi信息").append(":    ").append(wifiInfo.toString());
						
				
				textView.setText(stringBuffer.toString());
			}
			//stringBuffer = stringBuffer.append("-----------------------------------------------").append("\n");
			//textView.setText()
		}
	}

	class check_btListener implements OnClickListener {

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager) WifiDemoActivity.this
					.getSystemService(Context.WIFI_SERVICE);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(WifiDemoActivity.this,
					"当前网卡状态为：" + change(), Toast.LENGTH_SHORT)
					.show();
		}

	}

	class close_btListener implements OnClickListener {

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager) WifiDemoActivity.this
					.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(WifiDemoActivity.this,
					"当前网卡状态为：" + change(), Toast.LENGTH_SHORT)
					.show();
		}

	}

	class open_btListener implements OnClickListener {

		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager) WifiDemoActivity.this
					.getSystemService(Context.WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			System.out.println("wifi state --->" + wifiManager.getWifiState());
			Toast.makeText(WifiDemoActivity.this,
					"当前网卡状态为：" + change(), Toast.LENGTH_SHORT)
					.show();
		}

	}
	
	public String change(){
		String temp=null;
		switch (wifiManager.getWifiState()) {
		case 0:
				temp="Wifi正在关闭ING";
				break;
		case 1:		
				temp="Wifi已经关闭";
				break;
		case 2:
				temp="Wifi正在打开ING";
				break;
		case 3:
				temp="Wifi已经打开";
				break;
		default:
            break;
		}
		return temp;
	}

}