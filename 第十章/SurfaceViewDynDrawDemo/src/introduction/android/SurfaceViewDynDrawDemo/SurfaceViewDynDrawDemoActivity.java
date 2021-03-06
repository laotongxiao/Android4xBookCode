package introduction.android.SurfaceViewDynDrawDemo;

import introduction.android.SurfaceViewDynDrawDemo.SurfaceViewDynDrawDemoActivity.DrawingThread;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewDynDrawDemoActivity extends Activity {
    private SurfaceView mySurfaceView;
    private DrawingThread mDrawingThread;
    SurfaceHolder surfaceHolder;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mySurfaceView=(SurfaceView)findViewById(R.id.surfaceView1);
        surfaceHolder = mySurfaceView. getHolder() ;   
        surfaceHolder.addCallback(new SurfaceHolder.Callback(){			

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				mDrawingThread = new DrawingThread();
				mDrawingThread.mSurface=surfaceHolder;
		        mDrawingThread.start();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				mDrawingThread.mQuit = true;
			}
        	
        });
    }
    
    static final class MovingPoint {
        float x, y, dx, dy;
        
        void init(int width, int height, float minStep) {
            x = (float)((width-1)*Math.random());
            y = (float)((height-1)*Math.random());
            dx = (float)(Math.random()*minStep*2) + 1;
            dy = (float)(Math.random()*minStep*2) + 1;
        }
        
        float adjDelta(float cur, float minStep, float maxStep) {
            cur += (Math.random()*minStep) - (minStep/2);
            if (cur < 0 && cur > -minStep) cur = -minStep;
            if (cur >= 0 && cur < minStep) cur = minStep;
            if (cur > maxStep) cur = maxStep;
            if (cur < -maxStep) cur = -maxStep;
            return cur;
        }
        
        void step(int width, int height, float minStep, float maxStep) {
            x += dx;
            if (x <= 0 || x >= (width-1)) {
                if (x <= 0) x = 0;
                else if (x >= (width-1)) x = width-1;
                dx = adjDelta(-dx, minStep, maxStep);
            }
            y += dy;
            if (y <= 0 || y >= (height-1)) {
                if (y <= 0) y = 0;
                else if (y >= (height-1)) y = height-1;
                dy = adjDelta(-dy, minStep, maxStep);
            }
        }
    }
    
    class DrawingThread extends Thread {
        // These are protected by the Thread's lock.
        SurfaceHolder mSurface;
        boolean mRunning;
        boolean mActive;
        boolean mQuit;
        
        // Internal state.
        int mLineWidth;
        float mMinStep;
        float mMaxStep;
        
        boolean mInitialized=false;
        final MovingPoint mPoint1 = new MovingPoint();
        final MovingPoint mPoint2 = new MovingPoint();
        
        static final int NUM_OLD = 100;
        int mNumOld = 0;
        final float[] mOld = new float[NUM_OLD*4];
        final int[] mOldColor = new int[NUM_OLD];
        int mBrightLine = 0;
        
        // X is red, Y is blue.
        final MovingPoint mColor = new MovingPoint();
        
        final Paint mBackground = new Paint();
        final Paint mForeground = new Paint();
        
        int makeGreen(int index) {
            int dist = Math.abs(mBrightLine-index);
            if (dist > 10) return 0;
            return (255-(dist*(255/10))) << 8;
        }
        
        @Override
        public void run() {
            mLineWidth = (int)(getResources().getDisplayMetrics().density * 1.5);
            if (mLineWidth < 1) mLineWidth = 1;
            mMinStep = mLineWidth * 2;
            mMaxStep = mMinStep * 3;
            
            mBackground.setColor(0xff000000);
            mForeground.setColor(0xff00ffff);
            mForeground.setAntiAlias(false);
            mForeground.setStrokeWidth(mLineWidth);
            
            while (true) {
                
            	if (mQuit) {
                    return;
                }
            	// Lock the canvas for drawing.
                    Canvas canvas = mSurface.lockCanvas();
                    if (canvas == null) {
                        Log.i("WindowSurface", "Failure locking canvas");
                        continue;
                    }
                    
                    // Update graphics.
                    if (!mInitialized) {
                        mInitialized = true;
                        mPoint1.init(canvas.getWidth(), canvas.getHeight(), mMinStep);
                        mPoint2.init(canvas.getWidth(), canvas.getHeight(), mMinStep);
                        mColor.init(127, 127, 1);
                    } else {
                        mPoint1.step(canvas.getWidth(), canvas.getHeight(),
                                mMinStep, mMaxStep);
                        mPoint2.step(canvas.getWidth(), canvas.getHeight(),
                                mMinStep, mMaxStep);
                        mColor.step(127, 127, 1, 3);
                    }
                    mBrightLine+=2;
                    if (mBrightLine > (NUM_OLD*2)) {
                        mBrightLine = -2;
                    }
                    
                    // Clear background.
                    canvas.drawColor(mBackground.getColor());
                    
                    // Draw old lines.
                    for (int i=mNumOld-1; i>=0; i--) {
                        mForeground.setColor(mOldColor[i] | makeGreen(i));
                        mForeground.setAlpha(((NUM_OLD-i) * 255) / NUM_OLD);
                        int p = i*4;
                        canvas.drawLine(mOld[p], mOld[p+1], mOld[p+2], mOld[p+3], mForeground);
                    }
                    
                    // Draw new line.
                    int red = (int)mColor.x + 128;
                    if (red > 255) red = 255;
                    int blue = (int)mColor.y + 128;
                    if (blue > 255) blue = 255;
                    int color = 0xff000000 | (red<<16) | blue;
                    mForeground.setColor(color | makeGreen(-2));
                    canvas.drawLine(mPoint1.x, mPoint1.y, mPoint2.x, mPoint2.y, mForeground);
                    
                    // Add in the new line.
                    if (mNumOld > 1) {
                        System.arraycopy(mOld, 0, mOld, 4, (mNumOld-1)*4);
                        System.arraycopy(mOldColor, 0, mOldColor, 1, mNumOld-1);
                    }
                    if (mNumOld < NUM_OLD) mNumOld++;
                    mOld[0] = mPoint1.x;
                    mOld[1] = mPoint1.y;
                    mOld[2] = mPoint2.x;
                    mOld[3] = mPoint2.y;
                    mOldColor[0] = color;
                    
                    // All done!
                    mSurface.unlockCanvasAndPost(canvas);
                
            }
        }
    }
}