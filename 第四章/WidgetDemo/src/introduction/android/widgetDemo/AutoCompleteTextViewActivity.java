package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewActivity extends Activity {
	 private AutoCompleteTextView textView;
	    private static final String[] autotext = new String[] {"hello","hello World","hello Android"};
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.autocompletetextview);
	        textView = (AutoCompleteTextView )findViewById(R.id.autoCompleteTextView1);
	        /*new ArrayAdapterd对象将autotext字符串数组传入*/
	        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autotext); 
	        /*将ArrayAdapter添加到AutoCompleteTextView中*/
	        textView.setAdapter(adapter);
	    }
}
