package com.myname.mymod.mixin;

import com.myname.mymod.MyModConstants;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MyModMixin {

    // Example mixin
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        MyModConstants.MOD_LOGGER.info(MyModConstants.MOD_NAME+"'s Mixin initialized !");
    }

}