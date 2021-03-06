package totemic_commons.pokefenn.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import totemic_commons.pokefenn.ModBlocks;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.lib.Resources;
import totemic_commons.pokefenn.lib.Strings;

import java.util.List;
import java.util.Random;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class BlockCedarStripped extends BlockLog
{
    public BlockCedarStripped()
    {
        super();
        setBlockName(Strings.RED_CEDAR_STRIPPED_NAME);
        setHardness(1F);
        setCreativeTab(Totemic.tabsTotem);
        setTickRandomly(true);
    }


    @SideOnly(Side.CLIENT)
    private IIcon topAndBot;
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    @Override
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if(!world.isRemote)
        {
            if(random.nextInt(20 * 60 * 4) == 1)
            {
                if(world.getBlock(x, y - 1, z).getMaterial() == Material.ground || world.getBlock(x, y - 1, z).getMaterial() == Material.grass)
                {
                    world.setBlock(x, y, z, ModBlocks.cedarLog);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        sideIcon = register.registerIcon(Resources.TEXTURE_LOCATION + ":" + "cedarLogStrippedSide");
        topAndBot = register.registerIcon(Resources.TEXTURE_LOCATION + ":" + "cedarLogStrippedTop");

    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        int logDirection = meta & 12;
        return logDirection == 0 && (side == 1 || side == 0) ? topAndBot : (logDirection == 4 && (side == 5 || side == 4) ? topAndBot : (logDirection == 8 && (side == 2 || side == 3) ? topAndBot : sideIcon));

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item blockId, CreativeTabs creativeTab, List subTypes)
    {
        subTypes.add(new ItemStack(blockId, 1, 0));
    }

}
