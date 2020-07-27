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
    	//�����Ļ
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //����ģ���Ӿ�����Ϊ��ǰ��������
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //������ԭ���ƶ�����Ļ����
        gl.glLoadIdentity();
        //�ƶ�����ϵ
        gl.glTranslatef(0, 0, -3.0f);
        //��Y�᷽����ת����ϵ
        gl.glRotatef(roate,        0, 1, 0);
        //��X�᷽����ת����ϵ
        gl.glRotatef(roate*0.25f,  1, 0, 0);
        //������������
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //������ɫ
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
