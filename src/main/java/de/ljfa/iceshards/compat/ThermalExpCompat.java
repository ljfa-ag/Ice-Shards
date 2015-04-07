package de.ljfa.iceshards.compat;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.apache.logging.log4j.Level;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cpw.mods.fml.common.FMLLog;
import de.ljfa.iceshards.Reference;
import de.ljfa.iceshards.items.ModItems;

public class ThermalExpCompat {

    public static void addRecipes() {
        ThermalExpansionHelper.addPulverizerRecipe(2800, new ItemStack(Blocks.ice), new ItemStack(ModItems.ice_shards));
        ThermalExpansionHelper.addPulverizerRecipe(2800, new ItemStack(Blocks.packed_ice), new ItemStack(ModItems.ice_shards));
        ThermalExpansionHelper.addPulverizerRecipe(2000, new ItemStack(ModItems.ice_shards), new ItemStack(Items.snowball, 2));
        
        ThermalExpansionHelper.addCrucibleRecipe(1400, new ItemStack(ModItems.ice_shards), new FluidStack(FluidRegistry.WATER, 1000));
        
        FMLLog.log(Reference.MODNAME, Level.INFO, "Successfully added Thermal Expansion recipes");
    }
}
