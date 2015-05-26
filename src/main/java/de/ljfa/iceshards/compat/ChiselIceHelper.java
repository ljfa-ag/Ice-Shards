package de.ljfa.iceshards.compat;

import ljfa.glassshards.glass.GlassRegistry;

import com.cricketcraft.chisel.block.BlockCarvableIceStairs;
import com.cricketcraft.chisel.block.BlockCarvablePackedIceStairs;
import com.cricketcraft.chisel.init.ChiselBlocks;

import de.ljfa.iceshards.IceHandler;
import de.ljfa.iceshards.IceShards;


public class ChiselIceHelper {

    public static void init() {
        GlassRegistry.addHandler(ChiselBlocks.ice_pillar, IceHandler.instance);
        GlassRegistry.addHandler(ChiselBlocks.packedice, IceHandler.packedInstance);
        if(ljfa.glassshards.Config.chiselFixPaneDrops)
            GlassRegistry.addHandler(ChiselBlocks.packedice_pillar, IceHandler.clearingPackedInstance);
        
        for(BlockCarvableIceStairs block: ChiselBlocks.iceStairs)
            GlassRegistry.addHandler(block, IceHandler.instance);
        
        for(BlockCarvablePackedIceStairs block: ChiselBlocks.packediceStairs)
            GlassRegistry.addHandler(block, IceHandler.packedInstance);
        
        IceShards.logger.info("Successfully loaded Chisel compatibility.");
    }
}
