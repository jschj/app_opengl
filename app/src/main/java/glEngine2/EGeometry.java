package glEngine2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;



public class EGeometry
{
    public int iPosIndex;
    public int iShaderIndex;
    public int iFaceIndex;

    public float[] fPosBuffer;
    public EFace[] faceBuffer;

    public EGeometry(int maxFloatCount, int maxFaceCount)
    {
        fPosBuffer = new float[maxFloatCount];
        faceBuffer = new EFace[maxFaceCount];

        reset();
    }

    public void reset()
    {
        iPosIndex = 0;
        iShaderIndex = 0;
        iFaceIndex = 0;
    }

    public int addFloat3(float[] f)
    {
        int i;

        for (i = 0; i < 3; i++)
        {
            fPosBuffer[iPosIndex + i] = f[i];
            iPosIndex++;
        }

        return iPosIndex - i;
    }

    public void getFloat3(float[] f, int index, int offset)
    {
        f[offset + 0] = fPosBuffer[index];
        f[offset + 1] = fPosBuffer[index + 1];
        f[offset + 2] = fPosBuffer[index + 2];
    }

    public int addFace(EFace face)
    {
        faceBuffer[iFaceIndex] = new EFace(face);
        iFaceIndex++;

        return iFaceIndex - 1;
    }

    public int addFace(float[] worldA, float[] worldB, float[] worldC, int shaderProgram, float[] color)
    {
        faceBuffer[iFaceIndex] = new EFace();

        faceBuffer[iFaceIndex].iWorldA = addFloat3(worldA);
        faceBuffer[iFaceIndex].iWorldB = addFloat3(worldB);
        faceBuffer[iFaceIndex].iWorldC = addFloat3(worldC);

        faceBuffer[iFaceIndex].iShaderProgramIndex = shaderProgram;
        faceBuffer[iFaceIndex].fSolidColor[0] = color[0];
        faceBuffer[iFaceIndex].fSolidColor[1] = color[1];
        faceBuffer[iFaceIndex].fSolidColor[2] = color[2];
        faceBuffer[iFaceIndex].fSolidColor[3] = color[3];

        iFaceIndex++;

        return iFaceIndex - 1;
    }
}
