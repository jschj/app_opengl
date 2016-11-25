package glWrapper;

import android.opengl.GLES20;



public class OGLShaderManager
{
    public static int loadVertexShader(String shaderCode)
    {
        int shader;

        shader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static int loadFragmentShader(String shaderCode)
    {
        int shader;

        shader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
