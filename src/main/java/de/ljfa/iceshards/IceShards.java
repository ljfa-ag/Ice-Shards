package de.ljfa.iceshards;

import ljfa.glassshards.glass.GlassRegistry;
import ljfa.glassshards.glass.ModGlassHandler;
import ljfa.glassshards.handlers.HarvestDropsHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import de.ljfa.iceshards.items.ModItems;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards")
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    public static final ModGlassHandler iceHandler = new ModGlassHandler() {
        @Override
        public void addShardsDrop(HarvestDropsEvent event) {
            float chance = HarvestDropsHandler.getChanceFromFortune(event.fortuneLevel);
            if(event.world.rand.nextFloat() <= chance) {
                event.drops.add(new ItemStack(ModItems.ice_shards));
            }
        }
    };
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        ModItems.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GlassRegistry.addHandler(Blocks.ice, iceHandler);
        GlassRegistry.addHandler(Blocks.packed_ice, iceHandler);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
