package eva.rclickfix.mixin.client;


import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
@Debug(export = true)
public class ClickiReMixin {

    @Redirect(method = "startUseItem", order = 9999,
            at = @At(value = "CONSTANT",
                    args = "classValue=net/minecraft/world/InteractionResult$Fail"))
    public boolean noCancel(Object targetObj, Class<?> classValue) {
        return false;
    }


//    @Inject(method = "startUseItem",
//            at = @At(value = "CONSTANT", args = "classValue=net/minecraft/world/InteractionResult$Fail",
//                    shift = At.Shift.AFTER),
//            locals = Local)
//    private void no(CallbackInfo ci){
//        value.var10000
//        return ;
//    }

//    @Definition(id = "cost", field = "Lnet/minecraft/world/inventory/AnvilMenu;cost:Lnet/minecraft/world/inventory/DataSlot;")
//    @Definition(id = "get", method = "Lnet/minecraft/world/inventory/DataSlot;get()I")
//    @Expression("this.cost.get() >= 40")
//    @ModifyExpressionValue(method = "startUseItem", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 1))
//    private boolean doS(boolean original) {
//        return InteractionResult.Success;
//    }

//    @Inject(method = "startUseItem", at = @At(value = "CONSTANT",
//            args = "classValue=net/minecraft/world/InteractionResult$Fail", shift = At.Shift.AFTER),
//            slice = @Slice(from = @At(value = "INVOKE",
//                    target = "Lnet/minecraft/client/player/LocalPlayer;hasInfiniteMaterials()Z"),
//                    to = @At(value = "INVOKE",
//                            target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 1)))
//    public void clickityClick(CallbackInfo ci, @Local InteractionHand interactionHand) {
//        if (interactionHand == InteractionHand.OFF_HAND) {return;}
//        Minecraft tMc = (Minecraft) (Object) this;
//        assert tMc.player != null;
//        ItemStack stack = tMc.player.getItemInHand(InteractionHand.OFF_HAND);
//        assert tMc.level != null;
//        if (!stack.isItemEnabled(tMc.level.enabledFeatures())) {
//            return;
//        }
//        if (tMc.hitResult != null) {
//            BlockHitResult bRes = (BlockHitResult) tMc.hitResult;
//            int i = stack.getCount();
//            assert tMc.gameMode != null;
//            InteractionResult iRes = tMc.gameMode.useItemOn(tMc.player, InteractionHand.OFF_HAND, bRes);
//            if (iRes instanceof InteractionResult.Success suc) {
//                if (suc.swingSource() == InteractionResult.SwingSource.CLIENT) {
//                    tMc.player.swing(InteractionHand.OFF_HAND);
//                    if (!stack.isEmpty() && (stack.getCount() != i || tMc.player.hasInfiniteMaterials())) {
//                        tMc.gameRenderer.itemInHandRenderer.itemUsed(InteractionHand.OFF_HAND);
//                    }
//                }
//            }
//        }
//    }
}