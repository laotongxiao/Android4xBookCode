package introduction.android.openglDemo;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class OpenGLDemoActivity extends Activity {
    private GLSurfaceView myGLSurfaceView;
    /** Called when the activity is first created. */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGLSurfaceView = new GLSurfaceView(this);
        myGLSurfaceView.setRenderer(new CubeRenderer());
        setContentView(myGLSurfaceView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        myGLSurfaceView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        myGLSurfaceView.onPause();
    }
}
