package gameCore;



/*
    Efficient stack like class to manage the blocks in the scene/world.
*/
public class BlockList
{
    public static void copyBlockArray(Block[] src, Block[] dst, int count)
    {
        for (; count >= 0; count--)
        {
            dst[count] = src[count];
        }
    }

    private Block[] blocks;
    private int iBlockCount;

    public BlockList()
    {
        blocks = null;
        iBlockCount = 0;
    }

    public int add(Block block)
    {
        Block[] newBlocks = new Block[iBlockCount + 1];

        if (blocks != null)
        {
            copyBlockArray(blocks, newBlocks, iBlockCount);
        }

        newBlocks[iBlockCount] = block;

        blocks = newBlocks;
        iBlockCount++;

        return iBlockCount - 1;
    }

    public void remove(int blockId)
    {
        if (blockId < 0 || blockId >= iBlockCount) return;

        for (int i = blockId; i < iBlockCount; i++)
        {
            blocks[i] = blocks[i + 1];
        }

        iBlockCount--;
    }

    public void clone(BlockList dest)
    {
        dest = new BlockList();

        dest.iBlockCount = iBlockCount;
        dest.blocks = new Block[iBlockCount];

        copyBlockArray(blocks, dest.blocks, iBlockCount);
    }
}
