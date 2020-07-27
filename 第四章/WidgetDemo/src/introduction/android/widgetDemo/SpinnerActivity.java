package introduction.android.widgetDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity {
	 private List<String> list = new ArrayList<String>();
	    private TextView textview;
	    private Spinner spinnertext;
	    private ArrayAdapter<String> adapter;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.spiner);
	        //��һ�������������б�����
	        list.add("����");
	        list.add("���");
	        list.add("����");
	        list.add("�Ϻ�");
	        list.add("����");
	        textview = (TextView)findViewById(R.id.textView1);
	        spinnertext = (Spinner)findViewById(R.id.spinner1);
	        //�ڶ�����Ϊ�����б���һ��������
	        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
	        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
	        //�����������������б�����ʱ�Ĳ˵���ʽ��
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        //���Ĳ�������������ӵ������б���
	        spinnertext.setAdapter(adapter);
	        //���岽����Ӽ�������Ϊ�����б������¼�����Ӧ
	        spinnertext.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	                // TODO Auto-generated method stub
	                /* ����ѡspinnertext��ֵ����myTextView ��*/
	        	textview.setText("�����ԣ�"+ adapter.getItem(arg2));
	                /* ��spinnertext��ʾ*/
	                arg0.setVisibility(View.VISIBLE);
	            }
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub
	        	textview.setText("NONE");
	                arg0.setVisibility(View.VISIBLE);
	            }
	        });
	        //��spinnertext��ӵ�OnTouchListener ������ѡ����¼�����
	        spinnertext.setOnTouchListener(new Spinner.OnTouchListener(){
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			// ��mySpinner ����
                v.setVisibility(View.INVISIBLE);
                Log.i("spinner","Spinner Touch�¼�������!");
                return false;
		    }
	        });
	        //����ı��¼�����
	        spinnertext.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
	            public void onFocusChange(View v, boolean hasFocus) {
	            // TODO Auto-generated method stub
	                v.setVisibility(View.VISIBLE);
	                Log.i("spinner","Spinner FocusChange �¼���������");
	            }
	            });
	 
	 }
}
