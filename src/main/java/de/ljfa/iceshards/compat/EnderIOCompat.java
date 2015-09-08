package de.ljfa.iceshards.compat;

import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import cpw.mods.fml.common.event.FMLInterModComms;

public class EnderIOCompat {

    public static void addSAGMillRecipes() {
        try {
            FMLInterModComms.sendMessage("EnderIO", "recipe:sagmill",
                "<recipeGroup name=\"GlassShards\">"
                + Resources.toString(Resources.getResource("assets/ice_shards/EnderIORecipes/SagRecipes.xml"), Charsets.UTF_8)
                + "</recipeGroup>");
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
