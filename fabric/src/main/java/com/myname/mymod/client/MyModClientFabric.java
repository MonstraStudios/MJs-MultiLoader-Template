package com.myname.mymod.client;

import com.myname.mymod.MyModConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

@Environment(EnvType.CLIENT)
public class MyModClientFabric implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        // ClientCommon register
        MyModClientCommon.init();


        // Register Client Commands
        // using dif root literal to not cause interference with common commands !
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(
                    ClientCommandManager.literal(MyModConstants.MOD_ID+"-openconfig")
                            .executes(context -> {
                                MyModClientCommon.openConfig();
                                return 1;
                            })
            )
        );

    }

}