package introduction.android.surfaceViewDrawDemo;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceDrawDemoActivity extends Activity {
    private SurfaceView mySurfaceView;
    private float[] mPts;
    private static final float SIZE = 300;
    private static final int SEGS = 32;
    private static final int X = 0;
    private static final int Y = 1;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildPoints();
        mySurfaceView=(SurfaceView)findViewById(R.id.surfaceView1);
        SurfaceHolder surfaceHolder = mySurfaceView. getHolder() ;   
        surfaceHolder.addCallback(new SurfaceHolder.Callback(){
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				Canvas   canvas  = holder.lockCanvas() ;          //获得canvas对象  
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
		        RectF rect=new RectF(10,250,290,480);
		        Path path=new Path();
		        path.addArc(rect, -180, 180);        
		        paint.setTextSize(18);
		        paint.setColor(Color.BLUE);  
		        canvas.drawTextOnPath("在SurfaceView中使用Canvas对象绘制静态图实例",path, 0, 0, paint);  
		        holder.unlockCanvasAndPost(canvas) ;            //释放canvas对象并显示绘制内容
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}});
        
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