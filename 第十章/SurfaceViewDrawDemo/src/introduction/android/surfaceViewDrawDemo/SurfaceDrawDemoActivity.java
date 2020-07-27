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
				Canvas   canvas  = holder.lockCanvas() ;          //���canvas����  
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
		        RectF rect=new RectF(10,250,290,480);
		        Path path=new Path();
		        path.addArc(rect, -180, 180);        
		        paint.setTextSize(18);
		        paint.setColor(Color.BLUE);  
		        canvas.drawTextOnPath("��SurfaceView��ʹ��Canvas������ƾ�̬ͼʵ��",path, 0, 0, paint);  
		        holder.unlockCanvasAndPost(canvas) ;            //�ͷ�canvas������ʾ��������
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}});
        
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