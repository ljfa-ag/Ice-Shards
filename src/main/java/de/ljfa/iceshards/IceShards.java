package de.ljfa.iceshards;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ljfa.iceshards.items.ItemFrozenPickaxe;
import de.ljfa.iceshards.items.ItemIceShards;
import de.ljfa.iceshards.items.ModItems;
import ljfa.glassshards.glass.GlassRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION,
    dependencies = "required-after:glass_shards@[1.6.3,),[${version}]",
    acceptedMinecraftVersions = "[1.11.2,)", updateJSON = Reference.UPDATE_JSON)
public class IceShards {
    @Mod.Instance(Reference.MODID)
    public static IceShards instance;
    
    public static final Logger logger = LogManager.getLogger(Reference.MODNAME);
    
    public ToolMaterial toolMatPackedIce;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        toolMatPackedIce = EnumHelper.addToolMaterial("PACKED_ICE", 1, Config.pickDurability, 3.0f, 0.5f, 14).setRepairItem(new ItemStack(Blocks.PACKED_ICE));
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registerAllIce();
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
    
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemIceShards());
        if(Config.enableFrozenPick)
            event.getRegistry().register(new ItemFrozenPickaxe());
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ModItems.ice_shards, 0, new ModelResourceLocation(Reference.MODID + ":ice_shards", "inventory"));
        if(ModItems.frozen_pickaxe != null)
            ModelLoader.setCustomModelResourceLocation(ModItems.frozen_pickaxe, 0, new ModelResourceLocation(Reference.MODID + ":frozen_pickaxe", "inventory"));
    }
}
