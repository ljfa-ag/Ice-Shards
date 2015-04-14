package de.ljfa.iceshards;

import ljfa.glassshards.glass.ModGlassHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import de.ljfa.iceshards.items.ModItems;

public class IceHandler extends ModGlassHandler {
    
    public static final IceHandler instance = new IceHandler(false, false);
    public static final IceHandler clearingInstance = new IceHandler(true, false);
    public static final IceHandler packedInstance = new IceHandler(false, false);
    public static final IceHandler clearingPackedInstance = new IceHandler(true, false);
    
    private final boolean removeDrops;
    private final boolean packed;
    
    protected IceHandler(boolean removeDrops, boolean packed) {
        this.removeDrops = removeDrops;
        this.packed = packed;
    }
    
    @Override
    public void addShardsDrop(HarvestDropsEvent event) {
        float chance = getChanceFromFortune(event.fortuneLevel, packed);
        if(event.world.rand.nextFloat() <= chance) {
            event.drops.add(new ItemStack(ModItems.ice_shards));
        }
    }
    
    @Override
    public boolean shouldRemoveDrop(Block block, int meta) {
        return removeDrops;
    }
    
    public static float getChanceFromFortune(int fortune, boolean packed) {
        float baseCh = packed ? Config.packedIceShardsChance : Config.iceShardsChance;
        float fortuneCh = packed ? Config.packedIceShardsFortuneChance : Config.iceShardsFortuneChance;
        return Math.min(baseCh + fortune*fortuneCh, 1.0f);
    }
}
