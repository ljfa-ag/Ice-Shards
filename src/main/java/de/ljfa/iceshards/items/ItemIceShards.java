package de.ljfa.iceshards.items;

import de.ljfa.iceshards.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemIceShards extends Item {
    
    public ItemIceShards() {
        setRegistryName("ice_shards");
        setCreativeTab(CreativeTabs.MATERIALS);
        setUnlocalizedName(Reference.MODID + ":ice_shards");
    }
}
