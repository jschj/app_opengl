package gameShared;



public class RotationTransformation
{
    public Vec3 v3Center;
    public Vec3 v3Angles;

    public RotationTransformation()
    {
        v3Center = new Vec3();
        v3Angles = new Vec3();
    }

    public RotationTransformation(Vec3 center, Vec3 angles)
    {
        v3Center = center;
        v3Angles = angles;
    }
}
