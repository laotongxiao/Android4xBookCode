package introduction.android.openglDemo;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class CubeRenderer implements GLSurfaceView.Renderer {    
    private MyCube myCube;
    private float roate;    
    public CubeRenderer() {       
        myCube = new MyCube();
    }

    public void onDrawFrame(GL10 gl) {  
    	//填充屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //设置模型视景矩阵为当前操作矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //将坐标原点移动到屏幕中心
        gl.glLoadIdentity();
        //移动坐标系
        gl.glTranslatef(0, 0, -3.0f);
        //在Y轴方向旋转坐标系
        gl.glRotatef(roate,        0, 1, 0);
        //在X轴方向旋转坐标系
        gl.glRotatef(roate*0.25f,  1, 0, 0);
        //开启顶点坐标
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //开启颜色
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        myCube.draw(gl);        
        roate += 1.0f;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
         gl.glViewport(0, 0, width, height);
         float ratio = (float) width / height;
         gl.glMatrixMode(GL10.GL_PROJECTION);
         gl.glLoadIdentity();
         gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {               
         gl.glEnable(GL10.GL_CULL_FACE);        
         gl.glClearColor(0.5F,0.5F,0.5F,1.0F);
    }

}
