package de.ljfa.iceshards.items;

import de.ljfa.iceshards.Config;

public class ModItems {
    public static ItemIceShards ice_shards;
    public static ItemFrozenPickaxe frozen_pickaxe;
    
    public static void preInit() {
        ice_shards = new ItemIceShards();
        if(Config.enableFrozenPick)
            frozen_pickaxe = new ItemFrozenPickaxe();
    }
}
