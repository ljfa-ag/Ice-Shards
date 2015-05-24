package de.ljfa.iceshards.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import de.ljfa.iceshards.Config;

public class ModItems {
    public static ItemIceShards ice_shards;
    public static ItemFrozenPickaxe frozen_pickaxe;
    
    public static void preInit() {
        ice_shards = new ItemIceShards();
        if(Config.enableFrozenPick)
            frozen_pickaxe = new ItemFrozenPickaxe();
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        ice_shards.registerModels(mesher);
        if(frozen_pickaxe != null)
            frozen_pickaxe.registerModels(mesher);
    }
}
