package de.ljfa.iceshards.items;

import ljfa.glassshards.items.IModeledItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.iceshards.Reference;

public class ItemIceShards extends Item implements IModeledItem {
    public static String name = "ice_shards";
    
    ItemIceShards() {
        setCreativeTab(CreativeTabs.tabMaterials);
        setUnlocalizedName(Reference.MODID + ":" + name);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public void registerModels(ItemModelMesher mesher) {
        mesher.register(this, 0, new ModelResourceLocation(Reference.MODID + ":ice_shards", "inventory"));
    }
}
