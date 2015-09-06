package de.ljfa.iceshards.compat;

import de.ljfa.iceshards.IceHandler;
import de.ljfa.iceshards.IceShards;
import ljfa.glassshards.glass.GlassRegistry;

public class ChiselIceHelper {

    public static void init() {
        GlassRegistry.addHandler("chisel:ice_pillar", IceHandler.instance);
        GlassRegistry.addHandler("chisel:packedice", IceHandler.packedInstance);
        GlassRegistry.addHandler("chisel:packedice_pillar", IceHandler.clearingPackedInstance);
        
        for(int i = 0; i < 8; i++) {
            GlassRegistry.addHandler("chisel:ice_stairs." + i, IceHandler.clearingInstance);
            GlassRegistry.addHandler("chisel:packedice_stairs." + i, IceHandler.clearingPackedInstance);
        }
        IceShards.logger.info("Successfully loaded Chisel compatibility.");
    }
}
