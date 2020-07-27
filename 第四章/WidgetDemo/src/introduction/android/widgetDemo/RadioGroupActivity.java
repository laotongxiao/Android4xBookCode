package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioGroupActivity extends Activity {
	    private TextView textview;  
	    private RadioGroup radiogroup;  
	    private RadioButton radio1,radio2,radio3;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.radiogroup);
	        textview=(TextView)findViewById(R.id.radiohello);  
	        radiogroup=(RadioGroup)findViewById(R.id.radiogroup1);  
	        radio1=(RadioButton)findViewById(R.id.radiobutton1);  
	        radio2=(RadioButton)findViewById(R.id.radiobutton2);  
	        radio3=(RadioButton)findViewById(R.id.radiobutton3);  
	        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
		    
		    @Override
		    public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
		    	String text="我最喜欢的运动是";
			if(checkedId==radio1.getId()){
				text+=radio1.getText().toString();
			    textview.setText(text);
			}else if(checkedId==radio2.getId()){
				text+=radio2.getText().toString();
			    textview.setText(text);
			}else if(checkedId==radio3.getId()){
				text+=radio3.getText().toString();
			    textview.setText(text);
			}
		    }
		});     
	    }
}
