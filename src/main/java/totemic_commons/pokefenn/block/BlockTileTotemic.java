package totemic_commons.pokefenn.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.tileentity.TileTotemic;

/**
 * Created with IntelliJ IDEA.
 * User: Pokefenn
 * Date: 28/01/14
 * Time: 12:33
 */
public abstract class BlockTileTotemic extends BlockContainer
{
    public BlockTileTotemic(Material material)
    {
        super(material);
        setHardness(2);
        setCreativeTab(Totemic.tabsTotem);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if(world.getTileEntity(x, y, z) instanceof TileTotemic)
        {
            int direction = 0;
            int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

            if(facing == 0)
            {
                direction = ForgeDirection.NORTH.ordinal();
            } else if(facing == 1)
            {
                direction = ForgeDirection.EAST.ordinal();
            } else if(facing == 2)
            {
                direction = ForgeDirection.SOUTH.ordinal();
            } else if(facing == 3)
            {
                direction = ForgeDirection.WEST.ordinal();
            }

            if(itemStack.hasDisplayName())
            {
                ((TileTotemic) world.getTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
            }

            ((TileTotemic) world.getTileEntity(x, y, z)).setOrientation(direction);

            world.markBlockForUpdate(x, y, z);
        }
    }

}
