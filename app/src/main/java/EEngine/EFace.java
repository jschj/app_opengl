package EEngine;



public class EFace
{
    public int iWorldA, iWorldB, iWorldC;
    public int iColorA, iColorB, iColorC;
    public int iShaderProgramIndex;



    public EFace()
    {
        iWorldA = -1;
        iWorldB = -1;
        iWorldC = -1;
        iShaderProgramIndex = -1;
    }

    public EFace(EFace face)
    {
        iWorldA = face.iWorldA;
        iWorldB = face.iWorldB;
        iWorldC = face.iWorldC;
        iShaderProgramIndex = face.iShaderProgramIndex;
    }

    public EFace(int worldA, int worldB, int worldC,
                 int colorA, int colorB, int colorC,
                 int modelMatrixIndex, int programIndex)
    {
        iWorldA = worldA;
        iWorldB = worldB;
        iWorldC = worldC;
        iColorA = colorA;
        iColorB = colorB;
        iColorC = colorC;
        iShaderProgramIndex = programIndex;
    }
}
