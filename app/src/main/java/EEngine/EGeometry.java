package EEngine;



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
        }

        iPosIndex += i;

        return iPosIndex - i;
    }

    public void getFloat3(float[] f, int index, int offset)
    {
        f[offset + 0] = fPosBuffer[index + 0];
        f[offset + 1] = fPosBuffer[index + 1];
        f[offset + 2] = fPosBuffer[index + 2];
    }

    public int addFloats(float[] f, int count, int offset)
    {
        for (int i = 0; i < count; i++)
        {
            fPosBuffer[iPosIndex + i] = f[i + offset];
        }

        iPosIndex += count;

        return iPosIndex - count;
    }

    public void getFloats(int index, float[] f, int count, int offset)
    {
        for (int i = 0; i < count; i++)
        {
            f[offset + i] = fPosBuffer[index + i];
        }
    }

    public int addFace(EFace face)
    {
        faceBuffer[iFaceIndex] = new EFace(face);
        iFaceIndex++;

        return iFaceIndex - 1;
    }

    public int addFace(float[] worldA, float[] worldB, float[] worldC, float[] colorA, float[] colorB, float[] colorC, int shaderProgram)
    {
        faceBuffer[iFaceIndex] = new EFace();

        faceBuffer[iFaceIndex].iWorldA = addFloat3(worldA);
        faceBuffer[iFaceIndex].iWorldB = addFloat3(worldB);
        faceBuffer[iFaceIndex].iWorldC = addFloat3(worldC);

        faceBuffer[iFaceIndex].iColorA = addFloat3(colorA);
        faceBuffer[iFaceIndex].iColorB = addFloat3(colorB);
        faceBuffer[iFaceIndex].iColorC = addFloat3(colorC);

        faceBuffer[iFaceIndex].iShaderProgramIndex = shaderProgram;

        iFaceIndex++;

        return iFaceIndex - 1;
    }
}
