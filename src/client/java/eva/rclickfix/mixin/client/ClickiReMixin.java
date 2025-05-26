package eva.rclickfix.mixin.client;


//import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
//import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.Slice;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import static net.minecraft.world.InteractionHand.OFF_HAND;

@Mixin(Minecraft.class)
public class ClickiReMixin {

    @Redirect(method = "startUseItem", at = @At(value = "CONSTANT",
            args = "classValue=net/minecraft/world/InteractionResult$Fail"))
    public boolean noCancel(Object instance, Class<?> type) {
        return false;
    }

//    @Inject(method = "startUseItem", at = @At("RETURN"),
//            slice = @Slice(from = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/client/player/LocalPlayer;hasInfiniteMaterials()Z"),
//                    to = @At(value = "INVOKE",
//                            target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 1)))
//    public void clickityClick(CallbackInfo ci, @Local InteractionHand interactionHand) {
//        if (interactionHand == OFF_HAND) {return;}
//        Minecraft tMc = (Minecraft) (Object) this;
//        assert tMc.player != null;
//        ItemStack stack = tMc.player.getItemInHand(OFF_HAND);
//        assert tMc.level != null;
//        if (!stack.isItemEnabled(tMc.level.enabledFeatures())) {
//            return;
//        }
//        if (tMc.hitResult != null) {
//            BlockHitResult bRes = (BlockHitResult) tMc.hitResult;
//            int i = stack.getCount();
//            assert tMc.gameMode != null;
//            InteractionResult iRes = tMc.gameMode.useItemOn(tMc.player, OFF_HAND, bRes);
//            if (iRes instanceof InteractionResult.Success suc) {
//                if (suc.swingSource() == InteractionResult.SwingSource.CLIENT) {
//                    tMc.player.swing(OFF_HAND);
//                    if (!stack.isEmpty() && (stack.getCount() != i || tMc.player.hasInfiniteMaterials())) {
//                        tMc.gameRenderer.itemInHandRenderer.itemUsed(OFF_HAND);
//                    }
//                }
//            }
//        }
//    }
}