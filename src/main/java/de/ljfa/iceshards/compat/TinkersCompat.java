package de.ljfa.iceshards.compat;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tconstruct.library.crafting.Smeltery;
import de.ljfa.iceshards.items.ModItems;

public class TinkersCompat {

    public static void addSmelteryRecipe() {
        Smeltery.addMelting(new ItemStack(ModItems.ice_shards), Blocks.ice, 0, 75, new FluidStack(FluidRegistry.WATER, 1000));
    }
}
