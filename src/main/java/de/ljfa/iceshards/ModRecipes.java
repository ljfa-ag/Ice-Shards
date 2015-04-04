package de.ljfa.iceshards;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ljfa.iceshards.items.ModItems;

public class ModRecipes {

    public static void init() {
        addOredict();
        addCrafting();
    }
    
    private static void addOredict() {
        OreDictionary.registerOre("shardsIce", ModItems.ice_shards);
    }
    
    private static void addCrafting() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.ice, 4), "SS", "SS", 'S', "shardsIce"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.packed_ice, 4),
                " S ", "S#S", " S ",'S', "shardsIce", '#', Blocks.snow));
    }
}
