package de.ljfa.iceshards.compat;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ThaumcraftCompat {

    public static void addAspects() {
        ThaumcraftApi.registerObjectTag("shardsIce", new AspectList().add(Aspect.COLD, 3).add(Aspect.ENTROPY, 1));
    }
}
