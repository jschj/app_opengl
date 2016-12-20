package EEngine;



public class ERenderInstance
{
    public EGeometry geometry;
    public EView view;
    public EShaderManager shaderManager;
    public int iShaderProgramIndex;



    public ERenderInstance(EGeometry geo, EView v, EShaderManager manager, int shaderProgram)
    {
        geometry = geo;
        view = v;
        shaderManager = manager;
        iShaderProgramIndex = shaderProgram;
    }
}
