package com.myname.mymod.platform;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class PlatformHelperForge implements PlatformHelperInterface{

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }

    @Override
    public String getPlatformName() {
        return "Forge";
    }

}
