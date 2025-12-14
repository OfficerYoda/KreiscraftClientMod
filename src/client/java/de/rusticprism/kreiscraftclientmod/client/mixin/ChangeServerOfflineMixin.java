package de.rusticprism.kreiscraftclientmod.client.mixin;

import de.rusticprism.kreiscraftclientmod.client.KreiscraftclientmodClient;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.network.DisconnectionInfo;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.awt.*;

@Mixin(DisconnectedScreen.class)
public class ChangeServerOfflineMixin {

    @ModifyArgs(method = "<init>(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DisconnectedScreen;<init>(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/text/Text;Lnet/minecraft/network/DisconnectionInfo;)V"))
    private static void connect(Args args) {
        if(((DisconnectionInfo)args.get(2)).reason().getString().toLowerCase().contains("getsockopt") && KreiscraftclientmodClient.address.getAddress().contains("kreiscraft")) {
            args.set(1, Text.literal("Kreiscraft ist gerade Offline").withColor(Color.red.getRGB()));
            args.set(2,new DisconnectionInfo(Text.literal("\n Falls der Server nicht in wenigen Minuten \n wieder online ist, dann bitte").withColor(Color.darkGray.getRGB()).append(Text.literal(" OfficerYoda ").withColor(Color.blue.getRGB())).append(Text.literal("informieren \n \n -Die Admins").withColor(Color.darkGray.getRGB()))));
        }
    }
}
