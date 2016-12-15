package glEngine2;

import android.opengl.GLES20;



public class EShaderProgram
{
    private EShader[] shaders;
    private int iShaderIndex;
    private int iShaderProgram;



    public EShaderProgram()
    {
        shaders = null;
        iShaderIndex = 0;
        iShaderProgram = 0;
    }

    public EShaderProgram(int maxShaderCount)
    {
        shaders = new EShader[maxShaderCount];
        iShaderIndex = 0;
        iShaderProgram = 0;
    }

    public int addShader(String code, int type)
    {
        shaders[iShaderIndex] = new EShader(code, type);
        iShaderIndex++;

        return iShaderIndex - 1;
    }

    public int buildProgram()
    {
        for (int i = 0; i < iShaderIndex; i++)
        {
            shaders[i].buildShader();
        }

        iShaderProgram = GLES20.glCreateProgram();

        for (int i = 0; i < iShaderIndex; i++)
        {
            GLES20.glAttachShader(iShaderProgram, shaders[i].getShader());
        }

        GLES20.glLinkProgram(iShaderProgram);

        return iShaderProgram;
    }

    public int getProgram()
    {
        return iShaderProgram;
    }
}


/*
public void buildShaders()
    {
        for (int i = 0; i < iShaderIndex; i++)
        {
            shaders[i].iShader = GLES20.glCreateShader(shaders[i].iShaderType);

            GLES20.glShaderSource(shaders[i].iShader, shaders[i].shaderCode);
            GLES20.glCompileShader(shaders[i].iShader);
        }

        iShaderProgram = GLES20.glCreateProgram();

        for (int i = 0; i < iShaderIndex; i++)
        {
            GLES20.glAttachShader(iShaderProgram, shaders[i].iShader);
        }

        GLES20.glLinkProgram(iShaderProgram);
    }
 */