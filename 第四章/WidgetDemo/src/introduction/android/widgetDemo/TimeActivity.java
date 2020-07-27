package introduction.android.widgetDemo;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeActivity extends Activity {
	private TextView textview;
	private TimePicker timepicker;
	private DatePicker datepicker;
	/* �������ڼ�ʱ����� */
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);
		/* ��ȡ��ǰ���ڼ�ʱ�� */
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR);
		minute = calendar.get(Calendar.MINUTE);
		datepicker = (DatePicker) findViewById(R.id.datepicker);
		timepicker = (TimePicker) findViewById(R.id.timepicker);
		/* ����TextView������ʾ��ʼ����ʱ�� */
		textview = (TextView) findViewById(R.id.timeview);
		textview.setText(new StringBuilder().append(year).append("/")
				.append(format(month + 1)).append("/").append(format(day))
				.append("��").append(format(hour)).append(":")
				.append(format(minute)));
		/* ����OnDateChangedListener() */
		datepicker.init(year, month, day,
				new DatePicker.OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						TimeActivity.this.year = year;
						month = monthOfYear;
						day = dayOfMonth;
						textview.setText(new StringBuilder().append(year)
								.append("/").append(format(month + 1))
								.append("/").append(format(day)).append("��")
								.append(format(hour)).append(":")
								.append(format(minute)));
					}
				});
		timepicker
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						hour = hourOfDay;
						TimeActivity.this.minute = minute;
						textview.setText(new StringBuilder().append(year)
								.append("/").append(format(month + 1))
								.append("/").append(format(day)).append("��")
								.append(format(hour)).append(":")
								.append(format(minute)));
					}
				});

	}

	private String format(int time) {
		String str = "" + time;
		if (str.length() == 1)
			str = "0" + str;
		return str;
	}
}
