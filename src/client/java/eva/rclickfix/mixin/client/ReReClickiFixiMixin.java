package eva.rclickfix.mixin.client;
//SLATED FOR DELETION
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class ReReClickiFixiMixin {

    @ModifyExpressionValue(
            method = "startUseItem",
            at = @At(value = "CONSTANT",
            args = "classValue=net/minecraft/world/InteractionResult$Fail")
    )
    public boolean clickyClicky(boolean original) {

        return original && no();

    }

    private static boolean no() {return false;}

}
