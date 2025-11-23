package com.myname.mymod.client;

import com.myname.mymod.MyModConstants;
import com.myname.mymod.config.ConfigCommonScreen;
import net.minecraft.client.Minecraft;

public class MyModClientCommon {

    public static void init() {
        // Log
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME+"'s CommonClient initialized !");
    }


    private static final Minecraft mc = Minecraft.getInstance();

    // Func for opening Config Screen
    public static void openConfig() {
        if (mc != null && mc.player != null) {
            try {
                mc.tell(() -> Minecraft.getInstance().setScreen(new ConfigCommonScreen()));
            }
            catch (Exception e) {
                MyModConstants.MOD_LOGGER.error("Error opening config: {}", String.valueOf(e));
            }
        }
    }

}
