package totemic_commons.pokefenn.block.totem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import totemic_commons.pokefenn.ModItems;
import totemic_commons.pokefenn.Totemic;
import totemic_commons.pokefenn.api.music.MusicEnum;
import totemic_commons.pokefenn.block.BlockTileTotemic;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.tileentity.totem.TileTotemBase;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Pokefenn
 * Date: 29/01/14
 * Time: 20:20
 */
public class BlockTotemBase extends BlockTileTotemic
{

    private Random rand = new Random();

    public BlockTotemBase()
    {
        super(Material.wood);
        setBlockName(Strings.TOTEM_BASE_NAME);
        setCreativeTab(Totemic.tabsTotem);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        world.setBlockMetadataWithNotify(x, y, z, itemStack.getItemDamage(), 2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        for(int i = 0; i < 5; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        TileTotemBase tileTotemBase = (TileTotemBase) world.getTileEntity(x, y, z);

        if(tileTotemBase != null)
            if(!world.isRemote)
            {
                if(player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.totemicStaff && tileTotemBase.isCeremony)
                {
                    tileTotemBase.resetSelector();
                    tileTotemBase.isCeremony = false;
                }
            }
    }

    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileTotemBase tileTotemBase = (TileTotemBase) world.getTileEntity(x, y, z);

        ItemStack heldItem = player.inventory.getCurrentItem();


        if(tileTotemBase != null && !world.isRemote)
        {
            if(!tileTotemBase.isCeremony && player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.totemicStaff)
            {
                TileTotemBase tileEntity = (TileTotemBase) world.getTileEntity(x, y, z);
            }

            if(tileTotemBase.isCeremony && player.getHeldItem() != null && player.getHeldItem().getItem() == ModItems.totemicStaff)
            {
                if(tileTotemBase.isDoingStartup)
                    player.addChatComponentMessage(new ChatComponentText("The Totem Base is doing startup"));
                if(tileTotemBase.isDoingEffect)
                    player.addChatComponentMessage(new ChatComponentText("The Totem Base is doing it's effect"));

                if(!tileTotemBase.isDoingEffect && !player.isSneaking())
                {
                    //if(tileTotemBase.isMusicSelecting)
                    {
                        if(tileTotemBase.isMusicSelecting && tileTotemBase.musicSelector[0] == 0 && tileTotemBase.musicSelector[1] == 0 && tileTotemBase.musicSelector[2] == 0 && tileTotemBase.musicSelector[3] == 0 && !tileTotemBase.isDoingEffect && !tileTotemBase.isDoingStartup)
                        {
                            player.addChatComponentMessage(new ChatComponentText("No Music for selector."));
                            return true;
                        }

                        for(int i = 0; i < 4; i++)
                        {
                            if(tileTotemBase.isMusicSelecting)
                            {
                                if(tileTotemBase.musicSelector[i] == 0)
                                    player.addChatComponentMessage(new ChatComponentText("No Music for selection on " + (i + 1)));
                                else if(tileTotemBase.musicSelector[i] != 0)
                                    player.addChatComponentMessage(new ChatComponentText("Musical Selection " + (i + 1) + " is " + MusicEnum.values()[tileTotemBase.musicSelector[i] - 1].getMusicName()));
                            }
                        }
                    }

                }
            }

        }


        return !(heldItem != null && heldItem.getItem() == ModItems.totemicStaff);

    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TileTotemBase();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
}
