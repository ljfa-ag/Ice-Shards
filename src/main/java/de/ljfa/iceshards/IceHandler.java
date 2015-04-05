package de.ljfa.iceshards;

import ljfa.glassshards.glass.ModGlassHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import de.ljfa.iceshards.items.ModItems;

public class IceHandler extends ModGlassHandler {
    
    public static final IceHandler instance = new IceHandler(false);
    public static final IceHandler clearingInstance = new IceHandler(true);
    
    protected IceHandler(boolean removeDrops) {
        this.removeDrops = removeDrops;
    }
    
    private final boolean removeDrops;
    
    @Override
    public void addShardsDrop(HarvestDropsEvent event) {
        float chance = getChanceFromFortune(event.fortuneLevel);
        if(event.world.rand.nextFloat() <= chance) {
            event.drops.add(new ItemStack(ModItems.ice_shards));
        }
    }
    
    @Override
    public boolean shouldRemoveDrop(Block block, int meta) {
        return removeDrops;
    }
    
    public static float getChanceFromFortune(int fortune) {
        return Math.min(Config.iceShardsChance + fortune*Config.iceShardsFortuneChance, 1.0f);
    }
}
