package glWrapper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

import gameShared.Vec3;
import gameShared.FloatColor;



public class OGLTriangle
{
    //default vertex shader test code
    private final String strVertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String strFragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";



    private final FloatBuffer vertexBuffer;
    private int iShaderProgram;
    private int iPositionHandle;
    private int iColorHandle;
    private int iMVPMatrixHandle;
    private float[] fColor;



    public OGLTriangle(Vec3 a, Vec3 b, Vec3 c)
    {
        ByteBuffer bb;
        int vertexShader, fragmentShader;

        float[] triangleCoords =
                {
                        a.x, a.y, a.z,
                        b.x, b.y, b.z,
                        c.x, c.y, c.z
                };

        bb = ByteBuffer.allocateDirect(9 * 4);      //9 floats in triangleCoords * size of float in bytes
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        vertexShader = OGLShaderManager.loadVertexShader(strVertexShaderCode);
        fragmentShader = OGLShaderManager.loadFragmentShader(strFragmentShaderCode);

        iShaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iShaderProgram, vertexShader);
        GLES20.glAttachShader(iShaderProgram, fragmentShader);
        GLES20.glLinkProgram(iShaderProgram);

        fColor = new float[4];
        (new FloatColor()).copyToFloatArray(fColor);
    }

    public OGLTriangle(Vec3 a, Vec3 b, Vec3 c, FloatColor color)
    {
        ByteBuffer bb;
        int vertexShader, fragmentShader;
        float[] triangleCoords =
                {
                        a.x, a.y, a.z,
                        b.x, b.y, b.z,
                        c.x, c.y, c.z,
                };

        bb = ByteBuffer.allocateDirect(9 * 4);      //9 floats in triangleCoords * size of float in bytes
        bb.order(ByteOrder.nativeOrder());

        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        vertexShader = OGLShaderManager.loadVertexShader(strVertexShaderCode);
        fragmentShader = OGLShaderManager.loadFragmentShader(strFragmentShaderCode);

        iShaderProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iShaderProgram, vertexShader);
        GLES20.glAttachShader(iShaderProgram, fragmentShader);
        GLES20.glLinkProgram(iShaderProgram);

        fColor = new float[4];
        color.copyToFloatArray(fColor);
    }

    public void draw(float[] mvpMatrix)
    {
        GLES20.glUseProgram(iShaderProgram);

        iPositionHandle = GLES20.glGetAttribLocation(iShaderProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(iPositionHandle);

        GLES20.glVertexAttribPointer(iPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                3 * 4, vertexBuffer);

        iColorHandle = GLES20.glGetUniformLocation(iShaderProgram, "vColor");
        GLES20.glUniform4fv(iColorHandle, 1, fColor, 0);

        iMVPMatrixHandle = GLES20.glGetUniformLocation(iShaderProgram, "uMVPMatrix");
        OGLRenderer.checkGlError("glGetUniformLocation");

        GLES20.glUniformMatrix4fv(iMVPMatrixHandle, 1, false, mvpMatrix, 0);
        OGLRenderer.checkGlError("glUniformMatrix4fv");

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 9 / 3);

        GLES20.glDisableVertexAttribArray(iPositionHandle);
    }
}
