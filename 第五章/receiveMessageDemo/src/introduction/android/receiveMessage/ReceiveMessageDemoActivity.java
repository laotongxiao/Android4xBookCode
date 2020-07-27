package introduction.android.receiveMessage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ReceiveMessageDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText text=(EditText) this.findViewById(R.id.editText1);
        text.setText("waiting...");
    }
}