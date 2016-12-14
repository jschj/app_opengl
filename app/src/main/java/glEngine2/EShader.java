package glEngine2;



public class EShader
{
    public String shaderCode;
    public int iShaderType;
    public int iShader;



    public EShader(String code, int type)
    {
        shaderCode = code;
        iShaderType = type;
        iShader = 0;
    }
}
