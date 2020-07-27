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
		
		//ʹ��Canvas��ͼ		
		//�����ƶ�����10,10��λ��
        canvas.translate(10,10);
        //����ʹ�ð�ɫ���
        canvas.drawColor(Color.WHITE);
        //������ɫ���ʣ�ʹ�õ����ؿ�ȣ�����ֱ��
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(0);
        canvas.drawLines(mPts, paint);
        //������ɫ���ʣ����Ϊ3��������ص�
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawPoints(mPts, paint);
        //����Path��������path��ʾ������Ϣ
        RectF rect=new RectF(10,300,290,430);
        Path path=new Path();
        path.addArc(rect, -180, 180);        
        paint.setTextSize(18);
        paint.setColor(Color.BLUE);  
        canvas.drawTextOnPath("���Զ���View��ʹ��Canvas�����ͼʵ��",path, 0, 0, paint);  
        //canvas.drawText("���Զ���View��ʹ��Canvas�����ͼʵ��", 40, 360, paint);
       
	}
	
	 private void buildPoints() {
		 //����һϵ�е�
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
