package glWrapper;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;



public class OGLSurfaceView extends GLSurfaceView
{
    private final OGLRenderer oglRenderer;

    public OGLSurfaceView(Context context)
    {
        super(context);

        setEGLContextClientVersion(2);

        oglRenderer = new OGLRenderer();

        setRenderer(oglRenderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        requestRender();

        return true;
    }

    public OGLRenderer getRenderer()
    {
        return oglRenderer;
    }
}
