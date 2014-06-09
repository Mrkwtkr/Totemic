package totemic_commons.pokefenn.compat;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Created by Pokefenn.
 * Licensed under MIT (If this is one of my Mods)
 */
public class Compatibility
{

    public static void sendIMCMessages()
    {
        if(Loader.isModLoaded("Waila"))
            FMLInterModComms.sendMessage("Waila", "register", "totemic_commons.pokefenn.compat.waila.TotemicWailaMain.initiateWaila");
    }
}
