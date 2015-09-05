package de.ljfa.iceshards.compat;

import de.ljfa.iceshards.IceHandler;
import de.ljfa.iceshards.IceShards;
import ljfa.glassshards.compat.ChiselGlassHelper;
import ljfa.glassshards.glass.GlassRegistry;
import ljfa.glassshards.util.ReflectionHelper;
import net.minecraft.block.Block;

public class ChiselIceHelper {

    public static void init() {
        Class<?> chiselBlocks = ChiselGlassHelper.getChiselBlocks();
        try {
            Block ice_pillar = ReflectionHelper.getStaticField(chiselBlocks, "ice_pillar");
            Block packedice = ReflectionHelper.getStaticField(chiselBlocks, "packedice");
            Block packedice_pillar = ReflectionHelper.getStaticField(chiselBlocks, "packedice_pillar");
            Block[] iceStairs = ReflectionHelper.getStaticField(chiselBlocks, "iceStairs");
            Block[] packediceStairs = ReflectionHelper.getStaticField(chiselBlocks, "packediceStairs");
            
            GlassRegistry.addHandler(ice_pillar, IceHandler.instance);
            GlassRegistry.addHandler(packedice, IceHandler.packedInstance);
            if(ljfa.glassshards.Config.chiselFixPaneDrops)
                GlassRegistry.addHandler(packedice_pillar, IceHandler.clearingPackedInstance);
            
            for(Block block: iceStairs)
                GlassRegistry.addHandler(block, IceHandler.clearingInstance);
            
            for(Block block: packediceStairs)
                GlassRegistry.addHandler(block, IceHandler.clearingPackedInstance);
            
            IceShards.logger.info("Successfully loaded Chisel compatibility.");
        }
        catch(Exception e) {
            throw new RuntimeException("Could not load Chisel compatibility.\n"
                + "The ChiselBlocks class was " + chiselBlocks.getName(), e);
        }
    }
}
