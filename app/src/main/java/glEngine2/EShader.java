package glEngine2;

import android.opengl.GLES20;



public class EShader
{
    private String shaderCode;
    private int iShaderType;
    private int iShader;



    public EShader()
    {
        shaderCode = null;
        iShaderType = 0;
        iShader = 0;
    }

    public EShader(String code, int type)
    {
        shaderCode = code;
        iShaderType = type;
    }

    public int getShader()
    {
        return iShader;
    }

    public int buildShader()
    {
        iShader = GLES20.glCreateShader(iShaderType);

        GLES20.glShaderSource(iShader, shaderCode);
        GLES20.glCompileShader(iShader);

        return iShader;
    }
}