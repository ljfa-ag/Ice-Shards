package de.ljfa.iceshards.items;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.iceshards.Reference;

public class ItemIceShards extends Item {
    public static String name = "ice_shards";
    
    @SideOnly(Side.CLIENT)
    private IIcon texture, texture_opaque;
    
    ItemIceShards() {
        setCreativeTab(CreativeTabs.tabMaterials);
        setUnlocalizedName(Reference.MODID + ":" + name);
        setTextureName(Reference.MODID + ":" + name);
        GameRegistry.registerItem(this, name);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        texture = iconRegister.registerIcon(Reference.MODID + ":ice_shards");
        texture_opaque = iconRegister.registerIcon(Reference.MODID + ":ice_shards_opaque");
    }
    
    @Override
    public IIcon getIconFromDamage(int damage) {
        return Minecraft.isFancyGraphicsEnabled() ? texture : texture_opaque;
    }
}
