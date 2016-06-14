package de.ljfa.iceshards;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
    public static Configuration conf;

    public static final String CAT_GENERAL = "general";
    
    public static float iceShardsChance;
    public static float iceShardsFortuneChance;
    public static float packedIceShardsChance;
    public static float packedIceShardsFortuneChance;
    public static boolean enableFrozenPick;
    public static int pickDurability;
    
    public static void loadConfig(File file) {
        if(conf == null)
            conf = new Configuration(file);
        
        conf.load();
        loadValues();
        
        MinecraftForge.EVENT_BUS.register(new ChangeHandler());
    }
    
    public static void loadValues() {
        conf.getCategory(CAT_GENERAL).setComment("General options");
        
        iceShardsChance = (float)conf.get(CAT_GENERAL, "iceShardsChance", 0.6, "Base chance that a block of ice drops shards", 0.0, 1.0).getDouble();
        iceShardsFortuneChance = (float)conf.get(CAT_GENERAL, "iceShardsFortuneChance", 0.08, "Chance per fortune level that a block of ice drops shards", 0.0, 1.0).getDouble();
        packedIceShardsChance = (float)conf.get(CAT_GENERAL, "packedIceShardsChance", 0.7, "Base chance that a block of packed ice drops shards", 0.0, 1.0).getDouble();
        packedIceShardsFortuneChance = (float)conf.get(CAT_GENERAL, "packedIceShardsFortuneChance", 0.07, "Chance per fortune level that a block of packed ice drops shards", 0.0, 1.0).getDouble();
        enableFrozenPick = conf.get(CAT_GENERAL, "enableFrozenPickaxe", true, "Enables the Frozen Pickaxe").setRequiresMcRestart(true).getBoolean();
        pickDurability = conf.get(CAT_GENERAL, "frozenPickaxeDurability", 131, "Durability of the Frozen Pickaxe", 1, 1561).setRequiresMcRestart(true).getInt();
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
