package gameShared;


public class Camera
{
    public float fFovX;
    public float fFovY;
    public Vec3 v3ViewPosition;
    public Vec3 v3ViewAngles;

    public Camera()
    {
        fFovX = 1.570796326794897f;     //90° in rad
        fFovY = 1.570796326794897f;     //90° in rad
        v3ViewPosition = new Vec3();
        v3ViewAngles = new Vec3();
    }

    public Camera(float fovx, float fovy, Vec3 pos, Vec3 ang)
    {
        fFovX = fovx;
        fFovY = fovy;
        v3ViewPosition = pos;
        v3ViewAngles = ang;
    }
}
