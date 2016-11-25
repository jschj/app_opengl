package glWrapper;



public class OGLScene
{
    OGLTriangle[] triangles;
    int iTriangleIndex;



    public OGLScene()
    {
        triangles = new OGLTriangle[1];
        iTriangleIndex = 0;
    }

    public int addTriangle(OGLTriangle triangle)
    {
        OGLTriangle[] tmp;

        if (iTriangleIndex >= triangles.length)
        {
            tmp = new OGLTriangle[iTriangleIndex + 1];
            tmp[iTriangleIndex] = triangle;

            for (int i = 0; i < iTriangleIndex; i++)
            {
                tmp[i] = triangles[i];
            }

            triangles = tmp;

            iTriangleIndex++;

            return iTriangleIndex - 1;
        }

        triangles[iTriangleIndex] = triangle;

        iTriangleIndex++;

        return iTriangleIndex - 1;
    }

    public void removeTriangle(int index)
    {
        if (index < 0 || index >= iTriangleIndex) return;

        for (; index < iTriangleIndex; index++)
        {
            triangles[index] = triangles[index + 1];
        }
    }

    public int getTriangleCount()
    {
        return iTriangleIndex;
    }
}
