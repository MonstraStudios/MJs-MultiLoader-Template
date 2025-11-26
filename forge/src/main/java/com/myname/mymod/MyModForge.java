package com.myname.mymod;

import com.myname.mymod.client.MyModClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(MyModConstants.MOD_ID)
public class MyModForge {

    public MyModForge(FMLJavaModLoadingContext context) {

        // Log
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME+"'s Forge initializing !");

        // Common register
        MyModCommon.init();

        // Register Setup
        context.getModEventBus().addListener(this::onSetup);
        MinecraftForge.EVENT_BUS.register(this);

        // Register Client
        if (FMLEnvironment.dist == Dist.CLIENT) { MyModClientForge.MyModClientForgeInit(context); }

    }

    // Common Setup
    private void onSetup(final FMLCommonSetupEvent event) {}


    // Register Commands
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        MyModCommon.registerCommands(event.getDispatcher());
    }

}
