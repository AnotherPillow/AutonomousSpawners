package com.anotherpillow.autonomousspawners.mixin;

import com.anotherpillow.autonomousspawners.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin {

    @Inject(
            method="Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    public void isNearPlayer(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {


        cir.setReturnValue(Config.rangeNumber <= 0 || level.hasNearbyAlivePlayer(

                (double) pos.getX() + 0.5D,
                (double) pos.getY() + 0.5D,
                (double) pos.getZ() + 0.5D,
                (double) Config.rangeNumber));
    }
}
