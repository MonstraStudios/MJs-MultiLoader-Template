package com.myname.mymod.config;

import com.myname.mymod.MyModConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ConfigCommonScreen extends Screen {

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 4;

    public ConfigCommonScreen() {
        super(Component.literal(MyModConstants.MOD_ID +"config-screen"));
    }

    @Override
    protected void init() {
        super.init();

        int startY = 40;
        int centerX = this.width / 2 - BUTTON_WIDTH / 2;


        // newInt button - 1 to 3
        addRenderableWidget(
                Button.builder(Component.literal("newInt: " + ConfigCommon.get().newInt), button -> {
                    ConfigCommon.get().newInt += 1;
                    if (ConfigCommon.get().newInt > 3 ) {
                        ConfigCommon.get().newInt = 1;
                    }
                    ConfigCommon.CONFIG.save();
                    button.setMessage(Component.literal("newInt: " + ConfigCommon.get().newInt));
                }).bounds(centerX, startY, BUTTON_WIDTH, BUTTON_HEIGHT).build()
        );


        // newBool button - true / false
        addRenderableWidget(
                Button.builder(Component.literal("newBool: " + ConfigCommon.get().newBool), button -> {
                    ConfigCommon.get().newBool = !ConfigCommon.get().newBool;
                    ConfigCommon.CONFIG.save();
                    button.setMessage(Component.literal("newBool: " + ConfigCommon.get().newBool));
                }).bounds(centerX, startY + BUTTON_HEIGHT + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT).build()
        );

        // newString button - steve / alex
        addRenderableWidget(
                Button.builder(Component.literal("newString: " + ConfigCommon.get().newString), button -> {
                    if (ConfigCommon.get().newString.equals("steve")) {
                        ConfigCommon.get().newString = "alex";
                    }
                    else {
                        ConfigCommon.get().newString = "steve";
                    }
                    ConfigCommon.CONFIG.save();
                    button.setMessage(Component.literal("newString: " + ConfigCommon.get().newString));
                }).bounds(centerX, startY + (BUTTON_HEIGHT + BUTTON_SPACING) * 2, BUTTON_WIDTH, BUTTON_HEIGHT).build()
        );
    }


    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);

        // Heading
        guiGraphics.drawCenteredString(this.font,
                MyModConstants.MOD_NAME + " Config",
                this.width / 2,
                20,
                0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
