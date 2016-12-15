package glEngine2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.*;



public class ERenderer implements GLSurfaceView.Renderer
{
    private ERenderInstance renderInstance;
    private EShaderManager shaderManager;



    public ERenderer()
    {

    }

    public void setRenderInstance(ERenderInstance instance)
    {
        renderInstance = instance;

        shaderManager = renderInstance.shaderManager;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        GLES20.glClearColor(0, 0, 0.5f, 1);
        GLES20.glEnable(GLES20.GL_DEPTH_FUNC);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        if (shaderManager != null)
        {
            shaderManager.buildShaderPrograms();
        }
    }

    @Override
    public void onDrawFrame(GL10 unused)
    {
        ByteBuffer byteBuffer;
        EFace face;
        float[] posBuf = new float[9];
        int shaderProg;
        int positionHandle;
        int colorHandle;
        int mvpMatrixHandle;

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        if (renderInstance == null) return;

        byteBuffer = ByteBuffer.allocateDirect(9 * 4);
        byteBuffer.order(ByteOrder.nativeOrder());

        final FloatBuffer vertexBuffer = byteBuffer.asFloatBuffer();

        for (int i = 0; i < renderInstance.geometry.iFaceIndex; i++)
        {
            //setting up vertices
            face = renderInstance.geometry.faceBuffer[i];

            renderInstance.geometry.getFloat3(posBuf, face.iWorldA, 0);
            renderInstance.geometry.getFloat3(posBuf, face.iWorldB, 3);
            renderInstance.geometry.getFloat3(posBuf, face.iWorldC, 6);

            vertexBuffer.put(posBuf);
            vertexBuffer.position(0);

            //setting up shader
            shaderProg =  shaderManager.getShaderProgram(face.iShaderProgramIndex).getProgram();

            GLES20.glUseProgram(shaderProg);

            //providing all information for the shaders
            positionHandle = GLES20.glGetAttribLocation(shaderProg, "vPosition");
            GLES20.glEnableVertexAttribArray(positionHandle);

            GLES20.glVertexAttribPointer(positionHandle,
                    3,
                    GLES20.GL_FLOAT,
                    false,
                    3 * 4,
                    vertexBuffer);

            colorHandle = GLES20.glGetUniformLocation(shaderProg, "vColor");
            GLES20.glUniform4fv(colorHandle, 1, face.fSolidColor, 0);

            mvpMatrixHandle = GLES20.glGetUniformLocation(shaderProg, "uMVPMatrix");

            GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, renderInstance.view.getMVPMatrix(), 0);

            //finally drawing
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 9 / 3);

            GLES20.glDisableVertexAttribArray(positionHandle);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int w, int h)
    {
        if (renderInstance == null) return;

        renderInstance.view.setResolution(w, h);
    }
}
