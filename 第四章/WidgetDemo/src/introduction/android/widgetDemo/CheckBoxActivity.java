package introduction.android.widgetDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CheckBoxActivity extends Activity {
	private TextView textView;
	private CheckBox bookCheckBox;
	private CheckBox songCheckBox;
	private CheckBox footbaCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.checkbox);
		textView = (TextView) findViewById(R.id.text);
		footbaCheckBox = (CheckBox) findViewById(R.id.CheckBox1);
		songCheckBox = (CheckBox) findViewById(R.id.CheckBox2);
		bookCheckBox = (CheckBox) findViewById(R.id.CheckBox3);

		footbaCheckBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						String football = footbaCheckBox.getText().toString();
						if (footbaCheckBox.isChecked()) {
							textView.append(football);
						} else {
							if (textView.getText().toString()
									.contains(football)) {
								textView.setText(textView.getText().toString()
										.replace(football, ""));
							}
						}
					}
				});
		songCheckBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						String song = songCheckBox.getText().toString();
						if (songCheckBox.isChecked()) {
							textView.append(song);
						} else {
							if (textView.getText().toString().contains(song)) {
								textView.setText(textView.getText().toString()
										.replace(song, ""));
							}
						}
					}
				});

		bookCheckBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						String book = bookCheckBox.getText().toString();
						if (bookCheckBox.isChecked()) {
							textView.append(book);
						} else {
							if (textView.getText().toString().contains(book)) {
								textView.setText(textView.getText().toString()
										.replace(book, ""));
							}
						}
					}
				});
	}

}
