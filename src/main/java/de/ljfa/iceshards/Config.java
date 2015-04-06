package de.ljfa.iceshards;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Config {
    public static Configuration conf;

    public static final String CAT_GENERAL = "general";
    
    public static float iceShardsChance;
    public static float iceShardsFortuneChance;
    
    public static void loadConfig(File file) {
        if(conf == null)
            conf = new Configuration(file);
        
        conf.load();
        loadValues();
        
        FMLCommonHandler.instance().bus().register(new ChangeHandler());
    }
    
    public static void loadValues() {
        conf.getCategory(CAT_GENERAL).setComment("General options");
        
        iceShardsChance = (float)conf.get(CAT_GENERAL, "iceShardsChance", 0.6, "Base chance that a block of ice drops shards", 0.0, 1.0).getDouble();
        iceShardsFortuneChance = (float)conf.get(CAT_GENERAL, "iceShardsFortuneChance", 0.08, "Chance per fortune level that a block of ice drops shards", 0.0, 1.0).getDouble();
        //----------------
        if(conf.hasChanged())
            conf.save();
    }
    
    /** Reloads the config values upon change */
    public static class ChangeHandler {
        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.modID.equals(Reference.MODID))
                loadValues();
        }
    }
}
