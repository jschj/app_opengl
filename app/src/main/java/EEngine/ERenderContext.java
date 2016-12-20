package EEngine;



public class ERenderContext
{
    public int iShaderProgramIndex;
    public EGeometryDynamic geometry;
    public EShaderManager shaderManager;
    public EView view;



    public ERenderContext(int shaderProgramIndex, EGeometryDynamic geo, EShaderManager manager, EView v)
    {
        iShaderProgramIndex = shaderProgramIndex;
        geometry = geo;
        shaderManager = manager;
        view = v;
    }

    public boolean isValidContext()
    {
        return (geometry != null && shaderManager != null && view != null);
    }

    public void setShaderProgramIndex(int index)
    {
        iShaderProgramIndex = index;
    }

    public void setGeometry(EGeometryDynamic geo)
    {
        geometry = geo;
    }

    public void setShaderManager(EShaderManager manager)
    {
        shaderManager = manager;
    }

    public void setView(EView v)
    {
        view = v;
    }
}
