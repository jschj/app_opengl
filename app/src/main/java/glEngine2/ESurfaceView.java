package glEngine2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;



public class ESurfaceView extends GLSurfaceView
{
    private final ERenderer renderer;

    public ESurfaceView(Context context)
    {
        super(context);

        setEGLContextClientVersion(2);

        renderer = new ERenderer();

        setRenderer(renderer);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void linkRenderInstance(ERenderInstance instance)
    {
        renderer.setRenderInstance(instance);
    }

    public void addShader(String code, int type)
    {
        renderer.addShader(code, type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        requestRender();

        return true;
    }
}
