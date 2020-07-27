package introduction.android.receiveMessage;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReciver extends BroadcastReceiver {
	StringBuilder strb=new StringBuilder();
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		   Bundle bundle = arg1.getExtras();   
		   Object[] pdus = (Object[]) bundle.get("pdus");   
		   SmsMessage[] msgs = new SmsMessage[pdus.length];   
		   for (int i = 0; i < pdus.length; i++) {   
		     msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);   
		   }
		   for(SmsMessage msg : msgs){
			   strb.append("发信人：\n");
			   strb.append(msg.getDisplayOriginatingAddress());
			   strb.append("\n信息内容：\n");
			   strb.append(msg.getDisplayMessageBody());
		   }
		   Toast.makeText(arg0, strb.toString(), Toast.LENGTH_LONG).show();
	}

}
