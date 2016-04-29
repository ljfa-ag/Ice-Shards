package de.ljfa.iceshards.items;

import de.ljfa.iceshards.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemIceShards extends Item {
    public static final String name = "ice_shards";
    
    public ItemIceShards() {
        setCreativeTab(CreativeTabs.tabMaterials);
        setUnlocalizedName(Reference.MODID + ":" + name);
        setRegistryName(name);
        GameRegistry.register(this);
        
        if(FMLCommonHandler.instance().getSide().isClient())
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
    }
}
