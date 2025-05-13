package eva.rclickfix.mixin.client;


import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
@Debug(export = true)
public abstract class ReClickiMixin {

    @Redirect(method = "startUseItem",
            at = @At(value = "CONSTANT",
                    args = "classValue=net/minecraft/world/InteractionResult$Fail"))
    public boolean clickyClicky(Object targetObj, Class<?> classValue) {

        return false;

    }
}
