package gameShared;



public class FloatColor
{
    public static FloatColor White = new FloatColor(1, 1, 1, 1);
    public static FloatColor Black = new FloatColor(1, 0, 0, 0);
    public static FloatColor Red = new FloatColor(1, 1, 0, 0);
    public static FloatColor Green = new FloatColor(1, 0, 1, 0);
    public static FloatColor Blue = new FloatColor(1, 0, 0, 1);

    private float fA, fR, fG, fB;

    private float limit(float f)
    {
        if (f < 0.0f) return 0.0f;
        if (f > 1.0f) return 1.0f;
        return f;
    }

    public FloatColor()
    {
        fA = 0;
        fR = 0;
        fG = 0;
        fB = 0;
    }

    public FloatColor(float a, float r, float g, float b)
    {
        fA = limit(a);
        fR = limit(r);
        fG = limit(g);
        fB = limit(b);
    }

    public FloatColor(byte a, byte r, byte g, byte b)
    {
        fA = limit((float)a / 255.0f);
        fR = limit((float)r / 255.0f);
        fG = limit((float)g / 255.0f);
        fB = limit((float)b / 255.0f);
    }

    public float getA()
    {
        return fA;
    }

    public float getR()
    {
        return fR;
    }

    public float getG()
    {
        return fG;
    }

    public float getB()
    {
        return fB;
    }

    //adjusted OpenGL
    public void copyToFloatArray(float[] buffer)
    {
        buffer[0] = fR;
        buffer[1] = fG;
        buffer[2] = fB;
        buffer[3] = fA;
    }
}
