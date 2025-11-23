package com.myname.mymod.platform;

import net.fabricmc.loader.api.FabricLoader;

public class PlatformHelperFabric implements PlatformHelperInterface {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnv() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

}
