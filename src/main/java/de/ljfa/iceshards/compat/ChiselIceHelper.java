package de.ljfa.iceshards.compat;

import org.apache.logging.log4j.Level;

import ljfa.glassshards.glass.GlassRegistry;

import com.cricketcraft.chisel.init.ChiselBlocks;

import cpw.mods.fml.common.FMLLog;
import de.ljfa.iceshards.IceHandler;
import de.ljfa.iceshards.Reference;


public class ChiselIceHelper {

    public static void init() {
        GlassRegistry.addHandler(ChiselBlocks.ice_pillar, IceHandler.instance);
        GlassRegistry.addHandler(ChiselBlocks.packedice, IceHandler.packedInstance);
        if(ljfa.glassshards.Config.chiselFixPaneDrops)
            GlassRegistry.addHandler(ChiselBlocks.packedice_pillar, IceHandler.clearingPackedInstance);
        
        FMLLog.log(Reference.MODNAME, Level.INFO, "Successfully loaded Chisel compatibility.");
    }
}
