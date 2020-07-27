package introduction.android.fileDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import introduction.android.fileDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileDemo extends Activity {
    private EditText SaveText;
    private Button SaveButton,LoadButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SaveText=(EditText)findViewById(R.id.phone_text);
        SaveButton = (Button)findViewById(R.id.SaveButton);
        LoadButton = (Button)findViewById(R.id.LoadButton);
        SaveButton.setOnClickListener(new ButtonListener());
        LoadButton.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements OnClickListener{
	@Override
	public void onClick(View v) {
	    switch (v.getId()) {
	    /*保存数据*/
	    case R.id.SaveButton:
		String saveinfo = SaveText.getText().toString().trim();
		FileOutputStream fos;
		try {
		    fos = openFileOutput("text", MODE_APPEND);
		    fos.write(saveinfo.getBytes());
		    fos.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		Toast.makeText(FileDemo.this,"数据保存成功",Toast.LENGTH_LONG).show();
		break;
		/*读取数据*/
	    case R.id.LoadButton:
		String get="";
		try {
		    FileInputStream fis=openFileInput("text");
		    byte [] buffer=new byte[fis.available()];
		    fis.read(buffer);
		    get = new String(buffer);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		Toast.makeText(FileDemo.this,"保存的数据是："+get, Toast.LENGTH_LONG).show();
		break;
	    default:
		break;
	    }
	}	
    }
}