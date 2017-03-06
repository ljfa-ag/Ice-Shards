package de.ljfa.iceshards;

import de.ljfa.iceshards.items.ModItems;
import ljfa.glassshards.glass.ModGlassHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class IceHandler extends ModGlassHandler {
    
    public static final IceHandler instance = new IceHandler(false, false);
    public static final IceHandler clearingInstance = new IceHandler(true, false);
    public static final IceHandler packedInstance = new IceHandler(false, true);
    public static final IceHandler clearingPackedInstance = new IceHandler(true, true);
    
    private final boolean removeDrops;
    private final boolean packed;
    
    protected IceHandler(boolean removeDrops, boolean packed) {
        this.removeDrops = removeDrops;
        this.packed = packed;
    }
    
    @Override
    public void addShardsDrop(HarvestDropsEvent event) {
        float chance = getChanceFromFortune(event.getFortuneLevel(), packed);
        if(event.getWorld().rand.nextFloat() < chance) {
            event.getDrops().add(new ItemStack(ModItems.ice_shards));
        }
    }
    
    @Override
    public boolean shouldRemoveDrop(IBlockState state) {
        return removeDrops;
    }
    
    public static float getChanceFromFortune(int fortune, boolean packed) {
        float baseCh = packed ? Config.packedIceShardsChance : Config.iceShardsChance;
        float fortuneCh = packed ? Config.packedIceShardsFortuneChance : Config.iceShardsFortuneChance;
        return Math.min(baseCh + fortune*fortuneCh, 1.0f);
    }
}
