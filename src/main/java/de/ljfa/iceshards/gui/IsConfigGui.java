package de.ljfa.iceshards.gui;

import java.util.ArrayList;
import java.util.List;

import de.ljfa.iceshards.Config;
import de.ljfa.iceshards.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class IsConfigGui extends GuiConfig {
    public IsConfigGui(GuiScreen parent) {
        super(parent, getConfigElements(), Reference.MODID, false, false, "Ice Shards configuration");
    }
    
    /** Compiles a list of config elements
     * Borrowed from EnderIO's implementation
     */
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        for(String name: Config.conf.getCategoryNames())
            list.add(new ConfigElement(Config.conf.getCategory(name)));
        
        return list;
    }
}
