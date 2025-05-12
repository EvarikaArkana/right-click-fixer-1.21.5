package eva.rclickfix.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.minecraft.world.InteractionHand.OFF_HAND;

@Mixin(Minecraft.class)
@Debug(export = true)
public abstract class ReClickifyMixin {
    @Redirect(method = "handleKeybinds",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;startUseItem()V"))
    private void itemUseReplace(Minecraft instance) {
        Minecraft thisMinecraft = (Minecraft) (Object) this;
        if (!thisMinecraft.gameMode.isDestroying()) {
            thisMinecraft.rightClickDelay = 4;
            if (!thisMinecraft.player.isHandsBusy()) {
                if (thisMinecraft.hitResult == null) {
                    LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
                }

                for (InteractionHand interactionHand : InteractionHand.values()) {
                    ItemStack itemStack = thisMinecraft.player.getItemInHand(interactionHand);
                    if (!itemStack.isItemEnabled(thisMinecraft.level.enabledFeatures())) {
                        return;
                    }

                    if (thisMinecraft.hitResult != null) {
                        switch (thisMinecraft.hitResult.getType()) {
                            case ENTITY:
                            EntityHitResult entityHitResult = (EntityHitResult) thisMinecraft.hitResult;
                            Entity entity = entityHitResult.getEntity();
                            if (!thisMinecraft.level.getWorldBorder().isWithinBounds(entity.blockPosition())) {
                                return;
                            }
                            InteractionResult interactionResult = thisMinecraft.gameMode.interactAt(thisMinecraft.player, entity, entityHitResult, interactionHand);
                            if (!interactionResult.consumesAction()) {
                                interactionResult = thisMinecraft.gameMode.interact(thisMinecraft.player, entity, interactionHand);
                            }

                            if (interactionResult instanceof InteractionResult.Success) {
                                InteractionResult.Success success = (InteractionResult.Success) interactionResult;
                                if (success.swingSource() == InteractionResult.SwingSource.CLIENT) {
                                    thisMinecraft.player.swing(interactionHand);
                                    return;
                                }
                                if (interactionHand == OFF_HAND) {return;}
                            }
                            break;
                            case BLOCK:
                            BlockHitResult blockHitResult = (BlockHitResult) thisMinecraft.hitResult;
                            int i = itemStack.getCount();
                            InteractionResult interactionResult2 = thisMinecraft.gameMode.useItemOn(thisMinecraft.player, interactionHand, blockHitResult);
                            if (interactionResult2 instanceof InteractionResult.Success) {
                                InteractionResult.Success success2 = (InteractionResult.Success) interactionResult2;
                                if (success2.swingSource() == InteractionResult.SwingSource.CLIENT) {
                                    thisMinecraft.player.swing(interactionHand);
                                    if (!itemStack.isEmpty() && (itemStack.getCount() != i || thisMinecraft.player.hasInfiniteMaterials())) {
                                        thisMinecraft.gameRenderer.itemInHandRenderer.itemUsed(interactionHand);
                                        return;
                                    }
                                }
                                if (interactionHand == OFF_HAND) {
                                    return;
                                }
                            }
                            if (interactionHand == OFF_HAND && interactionResult2 instanceof InteractionResult.Fail) {
                                return;
                            }
                        }
                    }

                    if (!itemStack.isEmpty()) {
                        InteractionResult interactionResult3 = thisMinecraft.gameMode.useItem(thisMinecraft.player, interactionHand);
                        if (interactionResult3 instanceof InteractionResult.Success) {
                            InteractionResult.Success success3 = (InteractionResult.Success) interactionResult3;
                            if (success3.swingSource() == InteractionResult.SwingSource.CLIENT) {
                                thisMinecraft.player.swing(interactionHand);
                            }

                            thisMinecraft.gameRenderer.itemInHandRenderer.itemUsed(interactionHand);
                            return;
                        }
                    }
                }
            }
        }
    }
}