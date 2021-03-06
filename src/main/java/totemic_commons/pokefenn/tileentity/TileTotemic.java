package totemic_commons.pokefenn.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import totemic_commons.pokefenn.lib.Strings;

public class TileTotemic extends TileEntity
{

    protected ForgeDirection orientation;
    protected byte state;
    protected String customName;

    public TileTotemic()
    {
        state = 0;
        customName = "";
        orientation = ForgeDirection.SOUTH;
    }


    public void setOrientation(ForgeDirection orientation)
    {
        this.orientation = orientation;
    }

    public void setOrientation(int orientation)
    {
        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    public short getState()
    {
        return state;
    }

    public void setState(byte state)
    {

        this.state = state;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public String getCustomName()
    {

        return customName;
    }

    public void setCustomName(String customName)
    {

        this.customName = customName;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if(nbtTagCompound.hasKey(Strings.NBT_TE_DIRECTION_KEY))
        {
            orientation = ForgeDirection.getOrientation(nbtTagCompound.getByte(Strings.NBT_TE_DIRECTION_KEY));
        }

        if(nbtTagCompound.hasKey(Strings.NBT_TE_STATE_KEY))
        {
            state = nbtTagCompound.getByte(Strings.NBT_TE_STATE_KEY);
        }

        if(nbtTagCompound.hasKey(Strings.NBT_TE_CUSTOM_NAME))
        {
            customName = nbtTagCompound.getString(Strings.NBT_TE_CUSTOM_NAME);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Strings.NBT_TE_DIRECTION_KEY, (byte) orientation.ordinal());
        nbtTagCompound.setByte(Strings.NBT_TE_STATE_KEY, state);

        if(this.hasCustomName())
        {
            nbtTagCompound.setString(Strings.NBT_TE_CUSTOM_NAME, customName);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("TileTotemic Data - xCoord: %d, yCoord: %d, zCoord: %d, customName: '%s', orientation: %s, state: %d\n", xCoord, yCoord, zCoord, customName, state));

        return stringBuilder.toString();
    }


}
