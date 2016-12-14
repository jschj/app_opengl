package glEngine2;

import android.opengl.GLES20;
import android.opengl.Matrix;



public class EView
{
    private int iWidth;
    private int iHeight;
    private final float[] fViewInfo;
    private final float[] fProjectionMatrix;
    private final float[] fViewMatrix;
    private final float[] fVPMatrix;



    public EView()
    {
        fViewInfo = new float[6];
        fProjectionMatrix = new float[16];
        fViewMatrix = new float[16];
        fVPMatrix = new float[16];
    }

    private void buildVPMatrix()
    {
        Matrix.multiplyMM(fVPMatrix, 0, fProjectionMatrix, 0, fViewMatrix, 0);
    }

    public void setViewTarget(float cx, float cy, float cz,
                              float tx, float ty, float tz)
    {
        Matrix.setLookAtM(fViewMatrix, 0,
                cx, cy, cz,
                tx, ty, tz,
                0, 1, 0);

        buildVPMatrix();
    }

    public void setResolution(int w, int h)
    {
        float ratio;

        iWidth = w;
        iHeight = h;

        GLES20.glViewport(0, 0, iWidth, iHeight);

        ratio = (float)iWidth / (float)iHeight;

        Matrix.frustumM(fProjectionMatrix, 0,                               //matrix, offset
                -ratio, ratio,                                              //left, right
                -1, 1,                                                      //top, bottom
                3, 100000);                                                 //near, far

        buildVPMatrix();
    }

    public float[] getMVPMatrix()
    {
        return fVPMatrix;
    }
}
