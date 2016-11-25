package gameCore;

import gameShared.Vec3;


/*
    A block is aligned at the top, left, front corner of the block:
    The block extends in the +x, +y and +z direction.
*/
public class Block
{
    public static final float F_BLOCK_WIDTH = 100.0f,
                              F_BLOCK_HEIGHT = 100.0f,
                              F_BLOCK_DEPTH = 100.0f;
    public static final Vec3 BLOCK_SIZE = new Vec3(100.0f, 100.0f, 100.0f);

    public int iBlockId;
    public Vec3 v3Position;

    public Block(int id, float x, float y, float z)
    {
        iBlockId = id;
        v3Position = new Vec3(x, y, z);
    }
}
