package eva.rclickfix.mixin.client;


import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static net.minecraft.world.InteractionHand.OFF_HAND;

@Mixin(Minecraft.class)
public class ClickiReMixin {

    @Inject(method = "startUseItem", at = @At(value = "RETURN", ordinal = 1),
            slice = @Slice(from = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;itemUsed(Lnet/minecraft/world/InteractionHand;)V",
                    ordinal = 0),
                    to = @At(value = "INVOKE",
                            target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 1)))
    public void clickityClick(CallbackInfo ci, @Local InteractionHand interactionHand) {
        if (interactionHand == OFF_HAND) {
            return;
        }
        Minecraft tMc = (Minecraft) (Object) this;
        assert tMc.player != null;
        ItemStack itemStack = tMc.player.getItemInHand(OFF_HAND);
        assert tMc.level != null;
        if (!itemStack.isItemEnabled(tMc.level.enabledFeatures())) {
            return;
        }
        if (tMc.hitResult != null) {
            BlockHitResult blockHitResult = (BlockHitResult) tMc.hitResult;
            int i = itemStack.getCount();
            assert tMc.gameMode != null;
            InteractionResult interactionResult2 = tMc.gameMode.useItemOn(tMc.player, OFF_HAND, blockHitResult);
            if (interactionResult2.consumesAction()) {
                if (interactionResult2.shouldSwing()) {
                    tMc.player.swing(OFF_HAND);
                    if (!itemStack.isEmpty() && (itemStack.getCount() != i || tMc.gameMode.hasInfiniteItems())) {
                        tMc.gameRenderer.itemInHandRenderer.itemUsed(OFF_HAND);
                    }
                }
            }
        }
    }
}