package introduction.android.bitmapDemo;

import com.sie.bitmapdemo.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class BitmapDemoActivity extends Activity
{
    ImageView myImageView;
    Bitmap myBmp, newBmp;
    int bmpWidth, bmpHeight;
    SeekBar seekbarRotate;
    float rotAngle;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myImageView = (ImageView) findViewById(R.id.imageview);
        // 由Resource载入图片
        myBmp = BitmapFactory.decodeResource(getResources(), R.drawable.im01);
        bmpWidth = myBmp.getWidth();
        bmpHeight = myBmp.getHeight();
        // 实例化matrix
        Matrix matrix = new Matrix();
        //设定Matrix属性 x，y缩放比例为1.5
        matrix.postScale(1.5F, 1.5F);
        //顺时针旋转45度
        matrix.postRotate(45.0F);
        newBmp = Bitmap.createBitmap(myBmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
        seekbarRotate =(SeekBar) findViewById(R.id.seekBarId);
        seekbarRotate.setOnSeekBarChangeListener(onRotate);
    }
    private SeekBar.OnSeekBarChangeListener onRotate=new SeekBar.OnSeekBarChangeListener() {
        
        public void onStopTrackingTouch(SeekBar seekBar)
        {
            // TODO Auto-generated method stub
            
        }
        
        public void onStartTrackingTouch(SeekBar seekBar)
        {
            // TODO Auto-generated method stub
            
        }
        
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser)
        {
            // TODO Auto-generated method stub
            Matrix m = new Matrix();          
            m.postRotate((float)progress*3.6F);
            newBmp=Bitmap.createBitmap(myBmp, 0, 0, bmpWidth, bmpHeight, m, true);
            myImageView.setImageBitmap(newBmp);
        }
    };
}
