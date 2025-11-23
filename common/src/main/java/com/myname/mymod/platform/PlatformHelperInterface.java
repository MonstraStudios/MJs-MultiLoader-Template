package com.myname.mymod.platform;

public interface PlatformHelperInterface {

    // Mod
    boolean isModLoaded(String modId);
    boolean isDevEnv();
    String getPlatformName();

}
