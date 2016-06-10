package de.ljfa.iceshards;

import de.ljfa.iceshards.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

    public static void init() {
        addOredict();
        addCrafting();
    }
    
    private static void addOredict() {
        OreDictionary.registerOre("shardsIce", ModItems.ice_shards);
    }
    
    private static void addCrafting() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.ICE, 4), "SS", "SS", 'S', "shardsIce"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.PACKED_ICE, 4),
                " S ", "S#S", " S ",'S', "shardsIce", '#', Blocks.SNOW));
        if(ModItems.frozen_pickaxe != null)
            GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.frozen_pickaxe, "PPP", " | ", " | ",
                    'P', Blocks.PACKED_ICE, '|', "stickWood"));
    }
}
