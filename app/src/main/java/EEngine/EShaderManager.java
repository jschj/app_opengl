package EEngine;



public class EShaderManager
{
    private EShaderProgram[] shaderPrograms;
    private int iShaderProgramIndex;



    public EShaderManager(int maxProgramCount, int maxShaderCount)
    {
        shaderPrograms = new EShaderProgram[maxProgramCount];

        for (int i = 0; i < shaderPrograms.length; i++)
        {
            shaderPrograms[i] = new EShaderProgram(maxShaderCount);
        }

        iShaderProgramIndex = 0;
    }

    public int addProgram()
    {
        iShaderProgramIndex++;

        return iShaderProgramIndex - 1;
    }

    public EShaderProgram getShaderProgram(int index)
    {
        return shaderPrograms[index];
    }

    public void buildShaderPrograms()
    {
        for (int i = 0; i < iShaderProgramIndex; i++)
        {
            shaderPrograms[i].buildProgram();
        }
    }
}
