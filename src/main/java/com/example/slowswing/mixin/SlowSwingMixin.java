package com.example.slowswing.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Stretches the length of YOUR OWN hand-swing animation on the client only.
 * Attack cooldown, mining speed and the swing packet are unaffected.
 */
@Mixin(LivingEntity.class)
public abstract class SlowSwingMixin {

    // 1.0 = vanilla. Higher = slower looking swing.
    private static final float SWING_SLOWDOWN = 2.5f;

    @Inject(method = "getHandSwingDuration", at = @At("RETURN"), cancellable = true)
    private void slowswing$stretch(CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof ClientPlayerEntity) {
            cir.setReturnValue(Math.max(1, Math.round(cir.getReturnValue() * SWING_SLOWDOWN)));
        }
    }
}
