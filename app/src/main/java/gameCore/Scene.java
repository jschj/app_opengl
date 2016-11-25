package gameCore;

import gameShared.Camera;


public class Scene
{
    private BlockList blockList;
    private Camera camera;



    public Scene()
    {
        blockList = new BlockList();
        camera = new Camera();
    }

    public int addBlock(Block block)
    {
        return blockList.add(block);
    }

    public void removeBlock(int blockId)
    {
        blockList.remove(blockId);
    }
}
