package EEngine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;



public class ESurfaceView extends GLSurfaceView
{
    private final ERenderer renderer;
    private float fPreviousX, fPreviousY;



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

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        float x, y;

        x = e.getX();
        y = e.getY();

        if (e.getAction() == MotionEvent.ACTION_MOVE)
        {
            float dx, dy, d;
            float[] viewInfo = new float[9];

            dx = x - fPreviousX;
            dy = y - fPreviousY;

            d = (dx + dy) / 8;

            renderer.getRenderInstance().view.getViewInfo(viewInfo);

            EMath.rotate3y(viewInfo, dx / 100);
            EMath.rotate3z(viewInfo, dy / 100);

            renderer.getRenderInstance().view.setViewTarget(viewInfo[0], viewInfo[1], viewInfo[2],
                    viewInfo[6], viewInfo[7], viewInfo[8]);
        }

        fPreviousX = x;
        fPreviousY = y;

        return true;
    }
}