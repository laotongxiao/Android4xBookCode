package introduction.android.widgetDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class GalleryActivity extends Activity {
	private Gallery gallery;
	private ImageSwitcher imageSwitcher;
	private int[] resids = new int[] { 
			R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		/* ����Gallery��ImageSwitcher */
		gallery = (Gallery) findViewById(R.id.gallery);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		/* ������������ͼ�����ݵ�ImageAdapter���� */
		ImageAdapter imageAdapter = new ImageAdapter(this);
		/* ����Gallery�����Adapter���� */
		gallery.setAdapter(imageAdapter);
		/* ���Gallery������ */
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// ��ѡȡGrallery�ϵ�ͼƬʱ����ImageSwitcher�������ʾ��ͼ��
				imageSwitcher.setImageResource(resids[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		/* ����ImageSwitcher����Ĺ������� */
		imageSwitcher.setFactory(new ViewFactory() {
			/* ImageSwitcher���������������һ��View����ȥ��ʾͼƬ */
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView imageView = new ImageView(GalleryActivity.this);
				/* setScaleType�������õ�ͼƬ��С��������С��ƥ��ʱ�ļ���ģʽ */
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return imageView;
			}

		});
		/* ����ImageSwitcher�����ʾͼ��Ķ���Ч�� */
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
	}

	public class ImageAdapter extends BaseAdapter {
		/* ����Context */
		private Context mContext;

		/* ����ImageAdapter */
		public ImageAdapter(Context context) {
			mContext = context;
		}

		@Override
		/* ��ȡͼƬ�ĸ��� */
		public int getCount() {
			// TODO Auto-generated method stub
			return resids.length;
		}

		/* ��ȡͼƬ�ڿ��е�λ�� */
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/* ��ȡͼƬID */
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/* ���ؾ���λ�õ�ImageView���� */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageview = new ImageView(mContext);
			/* ��ImageView������Դ */
			imageview.setImageResource(resids[position]);
			/* ���� ͼƬ���ִ�СΪ100*100 */
			imageview.setLayoutParams(new Gallery.LayoutParams(100, 100));
			/* ������ʾ�������� */
			imageview.setScaleType(ImageView.ScaleType.FIT_XY);
			return imageview;
		}
	}
}
