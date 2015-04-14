package de.ljfa.iceshards;

import ljfa.glassshards.GlassShards;
import ljfa.glassshards.glass.GlassRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Level;

import de.ljfa.iceshards.compat.ChiselIceHelper;
import de.ljfa.iceshards.compat.EnderIOCompat;
import de.ljfa.iceshards.compat.ThaumcraftCompat;
import de.ljfa.iceshards.compat.ThermalExpCompat;
import de.ljfa.iceshards.compat.TinkersCompat;
import de.ljfa.iceshards.items.ModItems;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards", guiFactory = Reference.GUI_FACTORY_CLASS)
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        ModItems.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        
        if(event.getSide() == Side.CLIENT && ljfa.glassshards.Config.renderTransparent) {
            MinecraftForgeClient.registerItemRenderer(ModItems.ice_shards, new TransparentItemRenderer());
        }
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registerAllIce();
        //initCompatModules();
    }
    
    private void registerAllIce() {
        int counter = 0;
        for(Object obj: GameData.getBlockRegistry()) {
            if(!(obj instanceof Block))
                continue;
            Block block = (Block)obj;
            if(block instanceof BlockIce) {
                GlassRegistry.addHandler(block, IceHandler.instance);
                counter++;
            } else if(block instanceof BlockPackedIce) {
                GlassRegistry.addHandler(block, IceHandler.packedInstance);
                counter++;
            }
        }
        FMLLog.log(Reference.MODNAME, Level.INFO, "Added %d ice blocks to the GlassRegistry", counter);
    }
    
    /*private void initCompatModules() {

    }*/
}
