package de.ljfa.iceshards.items;

import de.ljfa.iceshards.Reference;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIceShards extends Item {
    public static final String name = "ice_shards";
    
    public ItemIceShards() {
        setCreativeTab(CreativeTabs.tabMaterials);
        setUnlocalizedName(Reference.MODID + ":" + name);
        GameRegistry.registerItem(this, name);
        
        if(FMLCommonHandler.instance().getSide().isClient())
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
    }
}
