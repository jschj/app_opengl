package glWrapper;

import gameShared.*;



public class OGLBlock
{
    private OGLTriangle triangleA0, triangleA1,
            triangleB0, triangleB1,
            triangleC0, triangleC1,
            triangleD0, triangleD1,
            triangleE0, triangleE1,
            triangleF0, triangleF1;
    private int[] iaTriangleIds;
    private OGLRenderer renderTarget;

    public Vec3 v3BlockCenter;
    public FloatColor fcSolidColor;



    public  OGLBlock(Vec3 center, float width, float height, float depth, FloatColor color)
    {
        iaTriangleIds = new int[12];


    }

    public void register(OGLRenderer renderer)
    {

    }

    public void render()
    {

    }
}
