package de.ljfa.iceshards.items;

public class ModItems {
    public static ItemIceShards ice_shards;
    public static ItemFrozenPickaxe frozen_pickaxe;
    
    public static void preInit() {
        ice_shards = new ItemIceShards();
        frozen_pickaxe = new ItemFrozenPickaxe();
    }
}
