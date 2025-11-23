package com.myname.mymod.config;

public class ConfigCommon {

    // Make new Config
    public static ConfigAPI<ConfigCommon> CONFIG = new ConfigAPI<>(ConfigCommon.class);


    // Config Vars non-static
    public int newInt = 1;
    public boolean newBool = true;
    public String newString = "steve";


    // Optional Helper method
    public static ConfigCommon get() {
        return CONFIG.get();
    }

}