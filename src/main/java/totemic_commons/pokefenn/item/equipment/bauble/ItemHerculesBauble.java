package totemic_commons.pokefenn.item.equipment.bauble;

import totemic_commons.pokefenn.item.ItemTotemic;
import totemic_commons.pokefenn.lib.Strings;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class ItemHerculesBauble extends ItemTotemic// implements IBauble, ITotemBauble
{

    public ItemHerculesBauble()
    {
        super(Strings.HERCULESE_BAUBLES_NAME);
        setMaxStackSize(1);
    }

    /*

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.AMULET;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
        IInventory baubleInventory = BaublesApi.getBaubles(player);
        if(baubleInventory.getStackInSlot(0) == null && !player.worldObj.isRemote && canEquip(par1ItemStack, player) && baubleInventory.isItemValidForSlot(0, par1ItemStack))
        {
            baubleInventory.setInventorySlotContents(0, new ItemStack(this, 1, 0));
            player.destroyCurrentEquippedItem();
            onEquipped(par1ItemStack, player);
        }

        return par1ItemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add("Bauble Slot: Amulet");
        list.add("A beetle to increase the length and strength of Totem Effects");
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
        if(!player.worldObj.isRemote && player.worldObj.getWorldTime() % 80L == 0)
        {
            EntityPlayer entityPlayer = (EntityPlayer) player;
            if(TotemUtil.getArmourAmounts(entityPlayer) == 0)
            {
                IInventory baubleInventory = BaublesApi.getBaubles(entityPlayer);
                if(baubleInventory.getStackInSlot(0) != null)
                {
                    EntityItem bauble = new EntityItem(player.worldObj, player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, new ItemStack(this, 1, 0));
                    entityPlayer.worldObj.spawnEntityInWorld(bauble);
                    baubleInventory.setInventorySlotContents(0, null);
                }
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {

    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {

    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
    {
        return TotemUtil.getArmourAmounts((EntityPlayer) player) > 0;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }

    @Override
    public int getTotemEfficiency(World world, ItemStack itemStack, EntityPlayer player)
    {
        return 2;
    }
    */
}
