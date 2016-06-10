package de.ljfa.iceshards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ljfa.iceshards.items.ModItems;
import ljfa.glassshards.glass.GlassRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards@[1.6.2,),[${version}]", guiFactory = Reference.GUI_FACTORY_CLASS,
    acceptedMinecraftVersions = "[1.9.4,1.10)", updateJSON = Reference.UPDATE_JSON)
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    public static final Logger logger = LogManager.getLogger(Reference.MODNAME);
    
    public static ToolMaterial toolMatPackedIce;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        toolMatPackedIce = EnumHelper.addToolMaterial("PACKED_ICE", 1, Config.pickDurability, 3.0f, 0.5f, 14).setRepairItem(new ItemStack(Blocks.PACKED_ICE));
        ModItems.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registerAllIce();
        initCompatModules();
    }
    
    private void registerAllIce() {
        int counter = 0;
        for(Block block: Block.REGISTRY) {
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
    
    private void initCompatModules() {
    }
}
