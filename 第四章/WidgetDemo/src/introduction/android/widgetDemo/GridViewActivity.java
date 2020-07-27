package introduction.android.widgetDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		GridView gridview = (GridView) findViewById(R.id.gridView1);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Log.i("gridview", "���ǵ�" + position + "��ͼ��");
			}
		});
	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		/* ��ȡ��ǰͼƬ���� */
		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		/* ������Ҫposition�����GridView�еĶ��� */
		@Override
		public Object getItem(int position) {
			return position;
		}

		/* �����GridView�ж����ID */
		@Override
		public long getItemId(int id) {
			return id;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				/* ʵ����ImageView���� */
				imageView = new ImageView(mContext);
				/* ����ImageView���󲼾� ������View��height��width */
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				/* ���ñ߽���� */
				imageView.setAdjustViewBounds(false);
				/* ������ͳһ����ͼƬ������ͼƬ�ĳߴ������ */
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				/* ���ü�� */
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}
	}

	// references to our images
	private Integer[] mThumbIds = { R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5, R.drawable.sample_6,
			R.drawable.sample_7, R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
			R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1, R.drawable.sample_2,
			R.drawable.sample_3, R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7 };

}
