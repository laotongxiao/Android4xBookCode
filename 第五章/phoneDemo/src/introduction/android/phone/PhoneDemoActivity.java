package introduction.android.phone;

import android.app.Activity;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PhoneDemoActivity extends Activity {
    /** Called when the activity is first created. */
	private Button button;
	private EditText edittext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new buttonListener());
            }
    class buttonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			edittext = (EditText) findViewById(R.id.edittext);

            String number = edittext.getText().toString();


            Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));

            startActivity(intent);
			
		  }
       }
   }





    