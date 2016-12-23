package EEngine;



public class EGeometryDynamic
{
    private int iFloatExpansionSize;
    private int iFaceExpansionSize;

    private float[] fFloatBuffer;
    private int iFloatBufferLength;
    private int iFloatBufferIndex;

    private EFace[] faceBuffer;
    private int iFaceBufferLength;
    private int iFaceBufferIndex;

    private boolean bGeometryChangedFlag;



    public EGeometryDynamic(int floatExpSize, int faceExpSize)
    {
        if (isInvalidExpansionValue(floatExpSize)) floatExpSize = 4096;
        if (isInvalidExpansionValue(faceExpSize)) faceExpSize = 4096;

        iFloatExpansionSize = floatExpSize;
        iFaceExpansionSize = faceExpSize;
        iFloatBufferLength = 0;
        iFaceBufferLength = 0;

        bGeometryChangedFlag = true;

        expandFloatBuffer();
        expandFaceBuffer();

        resetBuffers();
    }

    public int getFloatCount()
    {
        return iFloatBufferIndex;
    }

    public int getFaceCount()
    {
        return iFaceBufferIndex;
    }

    public int getFloatBufferLength()
    {
        return iFloatBufferLength;
    }

    public int getFaceBufferLength()
    {
        return iFaceBufferLength;
    }

    private boolean isInvalidExpansionValue(int val)
    {
        return (val < 0 || val > 4096);
    }

    private boolean isValidFloatIndex(int i)
    {
        return (i >= 0 && i < iFloatBufferLength);
    }

    private boolean isValidFaceIndex(int i)
    {
        return (i >= 0 && i < iFaceBufferLength);
    }

    public void setGeometryChangedFlag(boolean state)
    {
        bGeometryChangedFlag = state;
    }

    public boolean getGeometryChangedFlag()
    {
        return bGeometryChangedFlag;
    }

    private void expandFloatBuffer()
    {
        float[] newFloats = new float[iFloatBufferLength + iFloatExpansionSize];

        for (int i = 0; i < iFloatBufferLength; i++)
        {
            newFloats[i] = fFloatBuffer[i];
        }

        iFloatBufferLength += iFloatExpansionSize;
        fFloatBuffer = newFloats;
    }

    private void expandFaceBuffer()
    {
        EFace[] newFaces = new EFace[iFaceBufferLength + iFaceExpansionSize];

        for (int i = 0; i < iFaceBufferLength; i++)
        {
            newFaces[i] = faceBuffer[i];
        }

        iFaceBufferLength += iFaceExpansionSize;
        faceBuffer = newFaces;
    }


    public void resetBuffers()
    {
        iFloatBufferIndex = 0;
        iFaceBufferIndex = 0;

        setGeometryChangedFlag(true);
    }

    public int addFloats(float[] f, int offset, int count)
    {
        while (iFloatBufferIndex + count > iFloatBufferLength)
        {
            expandFloatBuffer();
        }

        for (int i = 0; i < count; i++)
        {
            fFloatBuffer[i + iFloatBufferIndex] = f[i + offset];
        }

        iFloatBufferIndex += count;

        setGeometryChangedFlag(true);

        return iFloatBufferIndex - count;
    }

    public void getFloats(int index, float[] buf, int offset, int count)
    {
        //if (!isValidFloatIndex(index) || !isValidFaceIndex(index + count - 1)) return;

        for (int i = 0; i < count; i++)
        {
            buf[offset + i] = fFloatBuffer[index + i];
        }
    }

    public int addFace(EFace face)
    {
        if (iFaceBufferIndex + 1 > iFaceBufferLength)
        {
            expandFaceBuffer();
        }

        faceBuffer[iFaceBufferIndex] = face;
        iFaceBufferIndex++;

        setGeometryChangedFlag(true);

        return iFaceBufferIndex - 1;
    }

    public int addFace(float[] worldA, float[] worldB, float[] worldC, float[] colorA, float[] colorB, float[] colorC)
    {
        EFace face = new EFace(
                addFloats(worldA, 0, 3),
                addFloats(worldB, 0, 3),
                addFloats(worldC, 0, 3),
                addFloats(colorA, 0, 3),
                addFloats(colorB, 0, 3),
                addFloats(colorC, 0, 3)
        );

        setGeometryChangedFlag(true);

        return addFace(face);
    }

    public EFace getFace(int index)
    {
        if (!isValidFaceIndex(index)) return null;

        return faceBuffer[index];
    }
}
