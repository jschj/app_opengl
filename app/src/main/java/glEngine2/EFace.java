package glEngine2;



public class EFace
{
    public int iWorldA, iWorldB, iWorldC;
    //public int iColorA, iColorB, iColorC;
    //public int iTextureA, iTextureB, iTextureC;
    //public int iNormalA, iNormalB, iNormalC;
    public int iShaderProgramIndex;
    //public int iTextureIndex;
    //public int iFaceNormal;
    public float[] fSolidColor;
    public float[] fModelMatrix;



    public EFace()
    {
        iWorldA = -1;
        iWorldB = -1;
        iWorldC = -1;
        iShaderProgramIndex = -1;
        fSolidColor = new float[4];
    }

    public EFace(EFace face)
    {
        iWorldA = face.iWorldA;
        iWorldB = face.iWorldB;
        iWorldC = face.iWorldC;
        iShaderProgramIndex = face.iShaderProgramIndex;
        fSolidColor = new float[4];
        fSolidColor[0] = face.fSolidColor[0];
        fSolidColor[1] = face.fSolidColor[1];
        fSolidColor[2] = face.fSolidColor[2];
        fSolidColor[3] = face.fSolidColor[3];
    }

    public EFace(int worldA, int worldB, int worldC, int programIndex, float[] color)
    {
        iWorldA = worldA;
        iWorldB = worldB;
        iWorldC = worldC;
        iShaderProgramIndex = programIndex;
        fSolidColor[0] = color[0];
        fSolidColor[1] = color[1];
        fSolidColor[2] = color[2];
        fSolidColor[3] = color[3];
    }
}
