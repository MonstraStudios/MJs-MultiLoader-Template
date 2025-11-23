package com.myname.mymod.config;

import com.myname.mymod.MyModConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ConfigCommonScreen extends Screen {

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 4;

    public ConfigCommonScreen() {
        super(new TextComponent(MyModConstants.MOD_ID +"config-screen"));
    }

    @Override
    protected void init() {
        super.init();

        int startY = 40;
        int centerX = this.width / 2 - BUTTON_WIDTH / 2;

        // newInt button - 1 to 3
        addRenderableWidget(new Button(
                centerX,
                startY,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new TextComponent("newInt: " + ConfigCommon.get().newInt),
                button -> {
                    ConfigCommon.get().newInt += 1;
                    if (ConfigCommon.get().newInt > 3) {
                        ConfigCommon.get().newInt = 1;
                    }
                    ConfigCommon.CONFIG.save();
                    button.setMessage(new TextComponent("newInt: " + ConfigCommon.get().newInt));
                }
        ));

        // newBool button - true / false
        addRenderableWidget(new Button(
                centerX,
                startY + BUTTON_HEIGHT + BUTTON_SPACING,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new TextComponent("newBool: " + ConfigCommon.get().newBool),
                button -> {
                    ConfigCommon.get().newBool = !ConfigCommon.get().newBool;
                    ConfigCommon.CONFIG.save();
                    button.setMessage(new TextComponent("newBool: " + ConfigCommon.get().newBool));
                }
        ));

        // newString button - steve / alex
        addRenderableWidget(new Button(
                centerX,
                startY + (BUTTON_HEIGHT + BUTTON_SPACING) * 2,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new TextComponent("newString: " + ConfigCommon.get().newString),
                button -> {
                    if (ConfigCommon.get().newString.equals("steve")) {
                        ConfigCommon.get().newString = "alex";
                    }
                    else {
                        ConfigCommon.get().newString = "steve";
                    }
                    ConfigCommon.CONFIG.save();
                    button.setMessage(new TextComponent("newString: " + ConfigCommon.get().newString));
                }
        ));

    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);

        // Heading
        drawCenteredString(poseStack, this.font,
                MyModConstants.MOD_NAME + " Config",
                this.width / 2,
                20,
                0xFFFFFF);

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
