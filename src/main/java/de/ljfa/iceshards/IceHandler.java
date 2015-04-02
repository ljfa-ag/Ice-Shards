package de.ljfa.iceshards;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import de.ljfa.iceshards.items.ModItems;
import ljfa.glassshards.glass.ModGlassHandler;
import ljfa.glassshards.handlers.HarvestDropsHandler;

public class IceHandler extends ModGlassHandler {
    public static final IceHandler instance = new IceHandler(true);
    public static final IceHandler clearingInstance = new IceHandler(false);
    
    protected IceHandler(boolean removeDrops) {
        this.removeDrops = removeDrops;
    }
    
    private final boolean removeDrops;
    
    @Override
    public void addShardsDrop(HarvestDropsEvent event) {
        float chance = HarvestDropsHandler.getChanceFromFortune(event.fortuneLevel);
        if(event.world.rand.nextFloat() <= chance) {
            event.drops.add(new ItemStack(ModItems.ice_shards));
        }
    }
    
    @Override
    public boolean shouldRemoveDrop(Block block, int meta) {
        return removeDrops;
    }
}
