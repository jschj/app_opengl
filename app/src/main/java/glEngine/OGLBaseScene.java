package glEngine;



public class OGLBaseScene
{
    public static final int I_INVALID_INDEX = 0xFFFFFFFF;

    private OGLBaseTriangle[] baseTriangles;
    private int iTriangleIndex;

    public  OGLBaseScene(int maxTriangles)
    {
        baseTriangles = new OGLBaseTriangle[maxTriangles];
        iTriangleIndex = 0;
    }

    public void resetScene()
    {
        iTriangleIndex = 0;
    }

    public int addTriangle(OGLBaseTriangle triangle)
    {
        if (iTriangleIndex == baseTriangles.length) return I_INVALID_INDEX;

        baseTriangles[iTriangleIndex] = triangle;
        iTriangleIndex++;

        return iTriangleIndex - 1;
    }

    public int getTriangleCount()
    {
        return iTriangleIndex + 1;
    }

    public void render()
    {
        
    }
}
