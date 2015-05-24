package de.ljfa.iceshards.items;

import java.util.Collections;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ljfa.iceshards.IceShards;
import de.ljfa.iceshards.Reference;

public class ItemFrozenPickaxe extends ItemTool {
    public static final String name = "frozen_pickaxe";

    public ItemFrozenPickaxe() {
        super(0.0f, IceShards.toolMatPackedIce, Collections.EMPTY_SET);
        setUnlocalizedName(Reference.MODID + ":" + name);
        setTextureName(Reference.MODID + ":" + name);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        if(isMaterialIce(block.getMaterial()))
            return efficiencyOnProperMaterial;
        else
            return super.getDigSpeed(stack, block, meta);
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        if(player.capabilities.isCreativeMode || EnchantmentHelper.getSilkTouchModifier(player))
            return super.onBlockStartBreak(stack, x, y, z, player);
        World world = player.worldObj;
        Block block = world.getBlock(x, y, z);
        if(isMaterialIce(block.getMaterial())) {
            emulateBlockHarvest(stack, world, x, y, z, block, world.getBlockMetadata(x, y, z), player);
            return true;
        } else
            return super.onBlockStartBreak(stack, x, y, z, player);
    }
    
    private boolean isMaterialIce(Material mat) {
        return mat == Material.ice || mat == Material.packedIce;
    }
    
    private void emulateBlockHarvest(ItemStack stack, World world, int x, int y, int z, Block block, int meta, EntityPlayer player) {
        world.playAuxSFXAtEntity(player, 2001, x, y, z, Block.getIdFromBlock(block) | meta << 12);
        stack.func_150999_a(world, block, x, y, z, player);
        if(stack.stackSize == 0)
            player.destroyCurrentEquippedItem();
        if(block.removedByPlayer(world, player, x, y, z, false)) {
            block.dropBlockAsItem(world, x, y, z, meta, EnchantmentHelper.getFortuneModifier(player));
        }
    }
}
