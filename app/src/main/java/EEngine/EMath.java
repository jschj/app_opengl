package EEngine;



public class EMath
{
    public static float getVectorLength3(float[] f)
    {
        return (float)Math.sqrt(f[0] * f[0] + f[1] * f[1] + f[2] * f[2]);
    }

    public static void normalize3(float[] f)
    {
        float antiLen = 1 / getVectorLength3(f);

        f[0] *= antiLen;
        f[1] *= antiLen;
        f[2] *= antiLen;
    }

    public static void rotate3x(float[] f, float ang)
    {
        float y = f[1];
        float z = f[2];

        f[1] = (float)(y * Math.cos(ang) + z * Math.sin(ang));
        f[2] = (float)(z * Math.cos(ang) - y * Math.sin(ang));
    }

    public static void rotate3y(float[] f, float ang)
    {
        float x = f[0];
        float z = f[2];

        f[0] = (float)(x * Math.cos(ang) - z * Math.sin(ang));
        f[2] = (float)(z * Math.cos(ang) + x * Math.sin(ang));
    }

    public static void rotate3z(float[] f, float ang)
    {
        float x = f[0];
        float y = f[1];

        f[0] = (float)(x * Math.cos(ang) + y * Math.sin(ang));
        f[1] = (float)(y * Math.cos(ang) - x * Math.sin(ang));
    }
}
