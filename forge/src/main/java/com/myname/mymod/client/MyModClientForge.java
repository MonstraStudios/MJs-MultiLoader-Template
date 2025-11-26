package com.myname.mymod.client;

import com.myname.mymod.MyModConstants;
import net.minecraft.commands.Commands;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MyModClientForge {

    public static void MyModClientForgeInit(FMLJavaModLoadingContext context) {

        // Register Events
        context.getModEventBus().addListener(MyModClientForge::onInitializeClient);

        MinecraftForge.EVENT_BUS.addListener(MyModClientForge::onRegisterClientCommands);

    }


    public static void onInitializeClient(FMLClientSetupEvent event) {

        // ClientCommon register
        MyModClientCommon.init();

    }


    // Register Client Commands
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {

        event.getDispatcher().register(
                Commands.literal(MyModConstants.MOD_ID)
                        .then(Commands.literal("openconfig")
                                .executes(context -> {
                                    MyModClientCommon.openConfig();
                                    return 1;
                                })
                        )
        );

    }

}
