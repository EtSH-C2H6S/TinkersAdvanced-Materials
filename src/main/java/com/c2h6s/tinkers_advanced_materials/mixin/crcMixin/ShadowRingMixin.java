package com.c2h6s.tinkers_advanced_materials.mixin.crcMixin;

import com.LunaGlaze.rainbowcompound.Projects.Items.Props.ShadowRing;
import com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(value = ShadowRing.class,remap = false)
public class ShadowRingMixin {
    @Inject(method = "RainbowKit",at = @At("RETURN"),cancellable = true)
    public void addTconKit(SlotContext slotContext, CallbackInfoReturnable<Integer> cir){
        var living = slotContext.entity();
        living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(cap->
                cir.setReturnValue(cir.getReturnValueI()+cap.get(TiAcMeModifierProvider.DataKeys.KEY_RAINBOW_KIT,
                        0)));
    }
}
