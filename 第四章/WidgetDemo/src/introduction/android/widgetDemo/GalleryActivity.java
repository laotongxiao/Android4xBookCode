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
		/* 加载Gallery和ImageSwitcher */
		gallery = (Gallery) findViewById(R.id.gallery);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		/* 创建用于描述图像数据的ImageAdapter对象 */
		ImageAdapter imageAdapter = new ImageAdapter(this);
		/* 设置Gallery组件的Adapter对象 */
		gallery.setAdapter(imageAdapter);
		/* 添加Gallery监听器 */
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 当选取Grallery上的图片时，在ImageSwitcher组件中显示该图像
				imageSwitcher.setImageResource(resids[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		/* 设置ImageSwitcher组件的工厂对象 */
		imageSwitcher.setFactory(new ViewFactory() {
			/* ImageSwitcher用这个方法来创建一个View对象去显示图片 */
			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView imageView = new ImageView(GalleryActivity.this);
				/* setScaleType可以设置当图片大小和容器大小不匹配时的剪辑模式 */
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return imageView;
			}

		});
		/* 设置ImageSwitcher组件显示图像的动画效果 */
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
	}

	public class ImageAdapter extends BaseAdapter {
		/* 定义Context */
		private Context mContext;

		/* 声明ImageAdapter */
		public ImageAdapter(Context context) {
			mContext = context;
		}

		@Override
		/* 获取图片的个数 */
		public int getCount() {
			// TODO Auto-generated method stub
			return resids.length;
		}

		/* 获取图片在库中的位置 */
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/* 获取图片ID */
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/* 返回具体位置的ImageView对象 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageview = new ImageView(mContext);
			/* 给ImageView设置资源 */
			imageview.setImageResource(resids[position]);
			/* 设置 图片布局大小为100*100 */
			imageview.setLayoutParams(new Gallery.LayoutParams(100, 100));
			/* 设置显示比例类型 */
			imageview.setScaleType(ImageView.ScaleType.FIT_XY);
			return imageview;
		}
	}
}
