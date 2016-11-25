package gameShared;



public class Vec3
{
    public float x, y, z;

    public Vec3()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vec3(float _x, float _y, float _z)
    {
        x = _x;
        y = _y;
        z = _z;
    }

    public float getLength()
    {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize()
    {
        float l = getLength();

        x /= l;
        y /= l;
        z /= l;
    }

    public float getDot(Vec3 v)
    {
        return x * v.x + y * v.y + z * v.z;
    }

    public float getAngle(Vec3 v)
    {
        return (float)Math.acos(getDot(v) / (getLength() * v.getLength()));
    }

    public Vec3 getCross(Vec3 v)
    {
        Vec3 result = new Vec3();

        result.x = y * v.z - z * v.y;
        result.y = z * v.x - x * v.z;
        result.z = x * v.y - y * v.x;

        return result;
    }

    @Override
    public String toString()
    {
        return Float.toString(x) + ";" + Float.toString(y) + ";" + Float.toString(z);
    }
}
