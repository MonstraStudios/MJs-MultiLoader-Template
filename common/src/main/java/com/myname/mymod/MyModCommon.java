package com.myname.mymod;

import com.myname.mymod.config.ConfigCommon;
import com.myname.mymod.platform.Services;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class MyModCommon {

    public static void init() {

        // Log
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME + "'s Common initializing !");
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME+" is setting up !");

        // Logging using Platform Helper
        MyModConstants.MOD_LOGGER.info("Running on: {} platform !", Services.PLATFORM.getPlatformName());
        if (Services.PLATFORM.isModLoaded("mymod")) { MyModConstants.MOD_LOGGER.info("MyMod is loaded !"); }
        if (Services.PLATFORM.isDevEnv()) { MyModConstants.MOD_LOGGER.info("Running in: dev env !"); }

        // Register Config
        ConfigCommon.CONFIG.register("common");

    }


    // Example Commands to Open & Print Config
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal(MyModConstants.MOD_ID)

                        // reloadconfig
                        .then(Commands.literal("reloadconfig")
                                .executes(context -> {
                                    reloadConfig(context.getSource());
                                    return 1;
                                })
                        )

                        // printconfig
                        .then(Commands.literal("printconfig")
                                .executes(context -> {
                                    printConfig(context.getSource());
                                    return 1;
                                })
                        )

        );

    }


    // Funcs for Commands
    private static void reloadConfig(CommandSourceStack source) {
        ConfigCommon.CONFIG.reload();
        if (ConfigCommon.CONFIG.isHadErrorLoading()) {
            source.sendSuccess(() -> Component.literal(MyModConstants.MOD_ID + ": could not reload Config!"), false);
        } else {
            source.sendSuccess(() -> Component.literal(MyModConstants.MOD_ID + ": reloaded  Config!"), false);
        }
    }

    public static void printConfig(CommandSourceStack source) {
        try {
            var cfg = ConfigCommon.get();
            source.sendSuccess(() -> Component.literal(MyModConstants.MOD_ID+": Config Values: "+cfg.newInt+", "+cfg.newBool +", "+ cfg.newString), false);
        } catch (Exception e) {
            MyModConstants.MOD_LOGGER.error("Config: Error printing config values {}", String.valueOf(e));
        }
    }

}
