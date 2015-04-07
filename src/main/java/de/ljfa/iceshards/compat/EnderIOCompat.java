package de.ljfa.iceshards.compat;

import cpw.mods.fml.common.event.FMLInterModComms;

public class EnderIOCompat {

    public static void addSAGMillRecipes() {
        //SAG Mill: ice and paced ice -> ice shards
        FMLInterModComms.sendMessage("EnderIO", "recipe:sagmill",
            "<recipeGroup name=\"GlassShards\">" +
              "<recipe name=\"Ice\" energyCost=\"1000\">" +
                "<input>" +
                  "<itemStack modID=\"minecraft\" itemName=\"ice\" />" +
                "</input>" +
                "<output>" +
                  "<itemStack modID=\"ice_shards\" itemName=\"ice_shards\" />" +
                "</output>" +
              "</recipe>" +
              "<recipe name=\"Packed Ice\" energyCost=\"1000\">" +
                "<input>" +
                  "<itemStack modID=\"minecraft\" itemName=\"packed_ice\" />" +
                "</input>" +
                "<output>" +
                  "<itemStack modID=\"ice_shards\" itemName=\"ice_shards\" />" +
                "</output>" +
              "</recipe>" +
            "</recipeGroup>");
    }
}
