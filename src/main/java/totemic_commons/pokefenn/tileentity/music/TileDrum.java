package totemic_commons.pokefenn.tileentity.music;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import totemic_commons.pokefenn.tileentity.TileTotemic;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class TileDrum extends TileTotemic
{
    public int currentTime;
    public boolean canPlay;

    public TileDrum()
    {
        currentTime = 0;
        canPlay = true;
    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        /** TODO
         * Have the drums play music when you jump ontop of them, the armour will increase the amount of music played.
         * Also a drum beater item so when you hit the drum it pounces extra music.
         */

        World world = worldObj;

        if(!world.isRemote)
        {
            if(!canPlay)
            {
                currentTime++;
            }

            if(currentTime > 20)
            {
                currentTime = 0;
                canPlay = true;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("currentTime", currentTime);
        nbtTagCompound.setBoolean("canPlay", canPlay);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        currentTime = nbtTagCompound.getInteger("currentTime");
        canPlay = nbtTagCompound.getBoolean("canPlay");
    }
}
