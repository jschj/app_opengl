package EEngine;



public class ERenderInstance
{
    public EGeometry geometry;
    public EView view;
    public EShaderManager shaderManager;



    public ERenderInstance(EGeometry geo, EView v, EShaderManager manager)
    {
        geometry = geo;
        view = v;
        shaderManager = manager;
    }
}
