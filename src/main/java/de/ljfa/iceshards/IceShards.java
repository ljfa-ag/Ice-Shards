package de.ljfa.iceshards;

import ljfa.glassshards.GlassShards;
import ljfa.glassshards.glass.GlassRegistry;
import ljfa.glassshards.render.TransparentItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import de.ljfa.iceshards.compat.ChiselIceHelper;
import de.ljfa.iceshards.compat.EnderIOCompat;
import de.ljfa.iceshards.compat.ThaumcraftCompat;
import de.ljfa.iceshards.compat.ThermalExpCompat;
import de.ljfa.iceshards.compat.TinkersCompat;
import de.ljfa.iceshards.items.ModItems;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards@[1.3.2,),[${version}]", guiFactory = Reference.GUI_FACTORY_CLASS)
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    public static ToolMaterial toolMatPackedIce;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadConfig(event.getSuggestedConfigurationFile());
        toolMatPackedIce = EnumHelper.addToolMaterial("PACKED_ICE", 1, 131, 3.0f, 0.5f, 10).setRepairItem(new ItemStack(Blocks.packed_ice));
        ModItems.preInit();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        
        if(event.getSide() == Side.CLIENT && ljfa.glassshards.Config.renderTransparent) {
            MinecraftForgeClient.registerItemRenderer(ModItems.ice_shards, new TransparentItemRenderer());
        }
        
        if(ljfa.glassshards.Config.tePulverizer && Loader.isModLoaded("ThermalExpansion"))
            ThermalExpCompat.addRecipes();
        if(ljfa.glassshards.Config.eioSagMill && Loader.isModLoaded("EnderIO"))
            EnderIOCompat.addSAGMillRecipes();
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registerAllIce();
        initCompatModules();
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
    
    private void initCompatModules() {
        if(ljfa.glassshards.Config.chiselEnable && GlassShards.instance.isChiselCorrectVersion)
            ChiselIceHelper.init();
        if(ljfa.glassshards.Config.tinkersMeltShards && Loader.isModLoaded("TConstruct"))
            TinkersCompat.addSmelteryRecipe();
        if(ljfa.glassshards.Config.thaumAspects && Loader.isModLoaded("Thaumcraft"))
            ThaumcraftCompat.addAspects();
    }
}
