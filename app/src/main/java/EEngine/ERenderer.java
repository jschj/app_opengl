package EEngine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.*;



public class ERenderer implements GLSurfaceView.Renderer
{
    private final FloatBuffer vBuffer;
    private final int[] iVBOBuffers;

    private ERenderInstance renderInstance;
    private EShaderManager shaderManager;



    public ERenderer()
    {
        vBuffer = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder()).asFloatBuffer();
        iVBOBuffers = new int[16];
    }

    public void setRenderInstance(ERenderInstance instance)
    {
        renderInstance = instance;

        shaderManager = renderInstance.shaderManager;
    }

    public ERenderInstance getRenderInstance()
    {
        return renderInstance;
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        GLES20.glClearColor(0, 0, 0, 1);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);

        GLES20.glGenBuffers(1, iVBOBuffers, 0);

        if (shaderManager != null)
        {
            shaderManager.buildShaderPrograms();
        }
    }

    @Override
    public void onDrawFrame(GL10 unused)
    {
        EFace face;
        float[] posBuf = new float[(3 + 3) * 3];
        int shaderProg;
        int positionHandle;
        int colorHandle;
        int mvpMatrixHandle;

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        if (renderInstance == null) return;

        for (int i = 0; i < renderInstance.geometry.iFaceIndex; i++)
        {
            //setting up vertices
            face = renderInstance.geometry.faceBuffer[i];

            renderInstance.geometry.getFloat3(posBuf, face.iWorldA, 0);
            renderInstance.geometry.getFloat3(posBuf, face.iColorA, 3);
            renderInstance.geometry.getFloat3(posBuf, face.iWorldB, 6);
            renderInstance.geometry.getFloat3(posBuf, face.iColorB, 9);
            renderInstance.geometry.getFloat3(posBuf, face.iWorldC, 12);
            renderInstance.geometry.getFloat3(posBuf, face.iColorC, 15);

            vBuffer.put(posBuf);
        }

        face = renderInstance.geometry.faceBuffer[0];

        //copy vertex data into a buffer
        //GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, iVBOBuffers[0]);
        //GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, renderInstance.geometry.iFaceIndex * (3 + 3) * 4, vBuffer, GLES20.GL_STATIC_DRAW);

        //use shader

        shaderProg = shaderManager.getShaderProgram(face.iShaderProgramIndex).getProgram();
        GLES20.glUseProgram(shaderManager.getShaderProgram(face.iShaderProgramIndex).getProgram());

        //set vertex attributes and shader vars

        //matrix
        mvpMatrixHandle = GLES20.glGetUniformLocation(shaderProg, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, renderInstance.view.getMVPMatrix(), 0);

        vBuffer.position(0);

        //position
        positionHandle = GLES20.glGetAttribLocation(shaderProg, "v4Position");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, (3 + 3) * 4, vBuffer);

        vBuffer.position(3);

        //color
        colorHandle = GLES20.glGetAttribLocation(shaderProg, "v3Color");
        GLES20.glEnableVertexAttribArray(colorHandle);
        GLES20.glVertexAttribPointer(1, 3, GLES20.GL_FLOAT, false, (3 + 3) * 4, vBuffer);

        vBuffer.position(0);

        //draw
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, renderInstance.geometry.iFaceIndex * 3);
        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(colorHandle);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int w, int h)
    {
        if (renderInstance == null) return;

        renderInstance.view.setResolution(w, h);
    }
}
