package totemic_commons.pokefenn.item.equipment.music;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import totemic_commons.pokefenn.api.music.IMusic;
import totemic_commons.pokefenn.api.music.MusicEnum;
import totemic_commons.pokefenn.item.ItemTotemic;
import totemic_commons.pokefenn.lib.Strings;
import totemic_commons.pokefenn.util.EntityUtil;
import totemic_commons.pokefenn.util.TotemUtil;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class ItemFlute extends ItemTotemic implements IMusic
{
    public int time;

    public ItemFlute()
    {
        super(Strings.FLUTE_NAME);
        setMaxStackSize(1);
        time = 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {
            time++;
            if(time >= 9 && !player.isSneaking())
            {
                time = 0;
                TotemUtil.playMusicFromItem(world, player, this.musicEnum(itemStack, world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player), (int) player.posX, (int) player.posY, (int) player.posZ, this.getRange(world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player), this.getMaximumMusic(world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player), this.getMusicOutput(world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player));
                particlesAllAround(world, player.posX, player.posY, player.posZ, false);
                return itemStack;
            }
            if(time >= 9 && player.isSneaking())
            {
                time = 0;
                TotemUtil.playMusicFromItemForCeremonySelector(itemStack, player, (int) player.posX, (int) player.posY, (int) player.posZ, musicEnum(itemStack, world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player), this.getRange(world, (int) player.posX, (int) player.posY, (int) player.posZ, true, player));
                particlesAllAround(world, player.posX, player.posY, player.posZ, true);
            }
            if(itemStack.getItemDamage() == 1)
                if(EntityUtil.getEntitiesInRange(world, player.posX, player.posY, player.posZ, 2, 2) != null)
                {
                    for(Entity entity : EntityUtil.getEntitiesInRange(world, player.posX, player.posY, player.posZ, 2, 2))
                    {
                        if(entity instanceof EntityAnimal || entity instanceof EntityVillager)
                        {
                            if(entity instanceof EntityAnimal)
                                ((EntityAnimal) entity).targetTasks.addTask(5, new EntityAITempt((EntityCreature) entity, 1, this, false));
                            if(entity instanceof EntityVillager)
                                ((EntityVillager) entity).targetTasks.addTask(5, new EntityAITempt((EntityCreature) entity, 0.5, this, false));
                        }

                    }
                }
        }
        return itemStack;
    }

    @Override
    public MusicEnum musicEnum(ItemStack itemStack, World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return MusicEnum.FLUTE;
    }

    @Override
    public int getMaximumMusic(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 90;
    }

    @Override
    public int getMusicOutput(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 8;
    }

    @Override
    public int getRange(World world, int x, int y, int z, boolean isFromPlayer, EntityPlayer player)
    {
        return 7;
    }

    public void particlesAllAround(World world, double x, double y, double z, boolean firework)
    {
        MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("note", (double) x + 0.5D, (double) y + 1.2D, (double) z + 0.5D, 2, 0.5D, 0.0D, 0.5D, 0.0D);
        MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("note", (double) x, (double) y + 1.2D, (double) z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("note", (double) x + 0.5D, (double) y + 1.2D, (double) z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("note", (double) x, (double) y + 1.2D, (double) z + 0.5D, 2, 0.0D, 0.0D, 0.0D, 0.0D);

        if(firework)
        {
            MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("fireworksSpark", x + 0.5D, y + 1.2D, z + 0.5D, 2, 0.5D, 0.0D, 0.5D, 0.0D);
            MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("fireworksSpark", x, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
            MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("fireworksSpark", x + 0.5D, y + 1.2D, z, 2, 0.0D, 0.0D, 0.0D, 0.0D);
            MinecraftServer.getServer().worldServerForDimension(world.provider.dimensionId).func_147487_a("fireworksSpark", x, y + 1.2D, z + 0.5D, 2, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }


}
