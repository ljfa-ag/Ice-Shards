package de.ljfa.iceshards;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards")
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
