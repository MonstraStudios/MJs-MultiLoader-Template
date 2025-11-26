package com.myname.mymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class MyModFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        // Log
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME+"'s Fabric initializing !");

        // Common register
        MyModCommon.init();

        // Register Commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            MyModCommon.registerCommands(dispatcher);
        });

    }

}
