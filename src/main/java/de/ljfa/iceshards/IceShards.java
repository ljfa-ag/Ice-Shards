package de.ljfa.iceshards;

import ljfa.glassshards.glass.GlassRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ljfa.iceshards.items.ModItems;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards@[1.3.2,),[${version}]", guiFactory = Reference.GUI_FACTORY_CLASS)
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    public static final Logger logger = LogManager.getLogger(Reference.MODNAME);
    
    public static ToolMaterial toolMatPackedIce;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        toolMatPackedIce = EnumHelper.addToolMaterial("PACKED_ICE", 1, Config.pickDurability, 3.0f, 0.5f, 10).setRepairItem(new ItemStack(Blocks.packed_ice));
        ModItems.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT)
            ModItems.registerModels();
        ModRecipes.init();
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
        logger.info("Added {} ice blocks to the GlassRegistry", counter);
    }
    
    /*private void initCompatModules() {

    }*/
}
