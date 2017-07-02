package de.ljfa.iceshards;

import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;

@net.minecraftforge.common.config.Config(modid = Reference.MODID)
public class Config {
    @Comment("Base chance that a block of ice drops shards")
    @RangeDouble(min = 0.0, max = 1.0)
    public static double iceShardsChance = 0.6;
    
    @Comment("Chance per fortune level that a block of ice drops shards")
    @RangeDouble(min = 0.0, max = 1.0)
    public static double iceShardsFortuneChance = 0.08;
    
    @Comment("Base chance that a block of packed ice drops shards")
    @RangeDouble(min = 0.0, max = 1.0)
    public static double packedIceShardsChance = 0.7;
    
    @Comment("Chance per fortune level that a block of packed ice drops shards")
    @RangeDouble(min = 0.0, max = 1.0)
    public static double packedIceShardsFortuneChance = 0.07;
    
    @Comment("Enables the Frozen Pickaxe")
    @RequiresMcRestart
    public static boolean enableFrozenPickaxe = true;
    
    @Comment("Durability of the Frozen Pickaxe")
    @RangeInt(min = 1, max = 1561)
    @RequiresMcRestart
    public static int frozenPickaxeDurability = 131;
}
