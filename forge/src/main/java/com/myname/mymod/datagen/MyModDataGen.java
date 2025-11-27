package com.myname.mymod.datagen;

import com.myname.mymod.MyModConstants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = MyModConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MyModDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        // Register Providers
        dataGenerator.addProvider(event.includeServer(), new MyModRecipeProvider(dataGenerator.getPackOutput()));

    }

}