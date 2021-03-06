package totemic_commons.pokefenn.totem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import totemic_commons.pokefenn.api.recipe.TotemRegistry;
import totemic_commons.pokefenn.api.totem.ITotemEffect;
import totemic_commons.pokefenn.util.EntityUtil;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class TotemEffectPotion implements ITotemEffect
{
    public Potion effect;
    public int timeTill;
    public int defaultTime;
    public int multiplication;

    public TotemEffectPotion(Potion potion, int timeTill, int defaultTime, int multiplication)
    {
        this.effect = potion;
        this.timeTill = timeTill;
        this.defaultTime = defaultTime;
        this.multiplication = multiplication;
    }

    @Override
    public void effect(TileEntity totem, int socketAmount, TotemRegistry totemRegistry, int horizontal, int vertical, int melodyAmount, int totemWoodBonus, int repetitionBonus)
    {
        if(totem.getWorldObj().getWorldTime() % timeTill == 0)
        {
            if(EntityUtil.getEntitiesInRange(totem.getWorldObj(), totem.xCoord, totem.yCoord, totem.zCoord, horizontal, vertical) != null)
            {
                for(Entity entity : EntityUtil.getEntitiesInRange(totem.getWorldObj(), totem.xCoord, totem.yCoord, totem.zCoord, horizontal, vertical))
                {
                    if(entity instanceof EntityPlayer)
                    {
                        //TotemUtil.addPotionEffects((EntityPlayer) entity, defaultTime, multiplication, effect, 0, totemWoodBonus, repetitionBonus);
                    }
                }

            }
        }
    }
}
