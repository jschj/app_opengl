package glWrapper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import gameShared.*;



public class OGLRenderer implements GLSurfaceView.Renderer
{
    public static final int INVALID_ID = -1;

    private Camera camera;
    private Vec3 v3GlobalRotation;
    private float fAngle;

    public OGLScene oglScene;

    private final float[] faMVPMatrix = new float[16];
    private final float[] faProjectionMatrix = new float[16];
    private final float[] faViewMatrix = new float[16];
    private final float[] faRotationMatrix = new float[16];



    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        oglScene = new OGLScene();

        camera = new Camera();
        camera.v3ViewPosition = new Vec3(0, 10, -10);

        v3GlobalRotation = new Vec3(0, 0, 0);
        fAngle = 0;


        //ordered
        oglScene.addTriangle(new OGLTriangle(
                new Vec3(0.0f,  0.5f, 0.0f),
                new Vec3(-0.5f, -0.5f, 0.0f),
                new Vec3(0.5f, -0.5f, 0.0f),
                FloatColor.White));


        oglScene.addTriangle(new OGLTriangle(
                new Vec3(0.0f,  0.5f, 0.0f),
                new Vec3(0.0f, -0.5f, -0.5f),
                new Vec3(0.0f, -0.5f, 0.5f),
                FloatColor.Red));

    }

    @Override
    public void onDrawFrame(GL10 unused)
    {
        float[] scratch = new float[16];

        fAngle += 0.5f;

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(faViewMatrix, 0, camera.v3ViewPosition.x, camera.v3ViewPosition.y, camera.v3ViewPosition.z, 0, 0, 0, 0, 1, 0);
        Matrix.multiplyMM(faMVPMatrix, 0, faProjectionMatrix, 0, faViewMatrix, 0);

        //Matrix.setRotateM(faRotationMatrix, 0, 0, 0, 0, 1.0f);
        Matrix.setRotateM(faRotationMatrix, 0, fAngle, 0.0f, 1.0f, 0.0f);

        Matrix.multiplyMM(scratch, 0, faMVPMatrix, 0, faRotationMatrix, 0);

        for (int i = 0; i < oglScene.getTriangleCount(); i++)
        {
            oglScene.triangles[i].draw(scratch);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int w, int h)
    {
        float ratio;

        GLES20.glViewport(0, 0, w, h);

        ratio = (float)w / h;

        Matrix.frustumM(faProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 100000);
    }

    public static void checkGlError(String glOp)
    {
        int error;

        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR)
        {
            Log.e("OGLRenderer: ", glOp + ": glError " + error);
            throw new RuntimeException(glOp + ": glError " + error);
        }
    }
}
