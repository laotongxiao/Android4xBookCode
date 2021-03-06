package introduction.android.bitmapDrawDemo;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class BitmapDrawDemoActivity extends Activity {
	private  static final int WIDTH=320 ;
    private  static final int HEIGHT=480 ;
    private static final int STRIDE = 64;   // must be >= WIDTH

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyBitmapView(this));
		int SCR_WIDTH=getWindowManager().getDefaultDisplay().getWidth();
		int SCR_HEIGHT=getWindowManager().getDefaultDisplay().getHeight();
	}
	
    private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,int quality) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			src.compress(format, quality, os);

			byte[] array = os.toByteArray();
			return BitmapFactory.decodeByteArray(array, 0, array.length);
		}
    
	private class MyBitmapView extends View {
		private Bitmap myBitmap;
		private float[] mPts;
	     private static final float SIZE = 300;
	     private static final int SEGS = 32;
	     private static final int X = 0;
	     private static final int Y = 1;
	     
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			canvas.drawBitmap(myBitmap, 0, 0,null);
		}

		public MyBitmapView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			buildPoints();
			myBitmap=Bitmap.createBitmap(WIDTH,HEIGHT,Bitmap.Config.ARGB_8888);
			Canvas canvas=new Canvas(myBitmap);
			//使用Canvas绘图		
			//画布移动到（10,10）位置
	        canvas.translate(10,10);
	        //画布使用白色填充
	        canvas.drawColor(Color.WHITE);
	        //创建红色画笔，使用单像素宽度，绘制直线
	        Paint paint = new Paint();
	        paint.setColor(Color.RED);
	        paint.setStrokeWidth(0);
	        canvas.drawLines(mPts, paint);
	        //创建蓝色画笔，宽度为3，绘制相关点
	        paint.setColor(Color.BLUE);
	        paint.setStrokeWidth(3);
	        canvas.drawPoints(mPts, paint);
	        //创建Path，并沿着path显示文字信息
	        RectF rect=new RectF(10,300,290,370);
	        Path path=new Path();
	        path.addArc(rect, -180, 180);        
	        paint.setTextSize(18);
	        paint.setColor(Color.BLUE);  
	        canvas.drawTextOnPath("在Bitmap中使用Canvas对象绘图实例",path, 0, 0, paint);   
	        myBitmap=codec(myBitmap, Bitmap.CompressFormat.JPEG, 80);
		}

		private void buildPoints() {
		     //生成一系列点
	         final int ptCount = (SEGS + 1) * 2;
	         mPts = new float[ptCount * 2];

	         float value = 0;
	         final float delta = SIZE / SEGS;
	         for (int i = 0; i <= SEGS; i++) {
	             mPts[i*4 + X] = SIZE - value;
	             mPts[i*4 + Y] = 0;
	             mPts[i*4 + X + 2] = 0;
	             mPts[i*4 + Y + 2] = value;
	             value += delta;
	         }
	     }		
	}
}