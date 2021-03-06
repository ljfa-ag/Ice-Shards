package de.ljfa.iceshards.items;

import java.util.Collections;

import de.ljfa.iceshards.IceShards;
import de.ljfa.iceshards.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ItemFrozenPickaxe extends ItemTool {
    
    public ItemFrozenPickaxe() {
        super(1.5f, -2.8f, IceShards.instance.toolMatPackedIce, Collections.emptySet());
        setRegistryName("frozen_pickaxe");
        setUnlocalizedName(Reference.MODID + ":frozen_pickaxe");
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if(isMaterialIce(state.getMaterial()))
            return efficiencyOnProperMaterial;
        else
            return super.getStrVsBlock(stack, state);
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
        if(player.capabilities.isCreativeMode)
            return super.onBlockStartBreak(stack, pos, player);
        World world = player.world;
        IBlockState state = world.getBlockState(pos);
        if(isMaterialIce(state.getMaterial())) {
            emulateBlockHarvest(stack, world, pos, state, player);
            return true;
        } else
            return super.onBlockStartBreak(stack, pos, player);
    }
    
    private boolean isMaterialIce(Material mat) {
        return mat == Material.ICE || mat == Material.PACKED_ICE;
    }
    
    private void emulateBlockHarvest(ItemStack stack, World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        world.playEvent(player, 2001, pos, Block.getStateId(state));
        stack.onBlockDestroyed(world, state, pos, player);
        if(stack.isEmpty())
            ForgeEventFactory.onPlayerDestroyItem(player, stack, EnumHand.MAIN_HAND);
        if(state.getBlock().removedByPlayer(state, world, pos, player, false)) {
            int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
            if(fortune == 0)
                fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0 ? 2 : 0;
            state.getBlock().dropBlockAsItem(world, pos, state, fortune);
        }
    }

}
