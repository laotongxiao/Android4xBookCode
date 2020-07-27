package introduction.android.MyViewCanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.view.View;

public class MyView extends View {
	 private float[] mPts;
     private static final float SIZE = 300;
     private static final int SEGS = 32;
     private static final int X = 0;
     private static final int Y = 1;

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		buildPoints();		
	}		
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
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
        RectF rect=new RectF(10,300,290,430);
        Path path=new Path();
        path.addArc(rect, -180, 180);        
        paint.setTextSize(18);
        paint.setColor(Color.BLUE);  
        canvas.drawTextOnPath("在自定义View中使用Canvas对象绘图实例",path, 0, 0, paint);  
        //canvas.drawText("在自定义View中使用Canvas对象绘图实例", 40, 360, paint);
       
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
