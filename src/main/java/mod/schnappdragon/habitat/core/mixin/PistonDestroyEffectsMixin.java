package mod.schnappdragon.habitat.core.mixin;

import mod.schnappdragon.habitat.common.block.IHasPistonDestroyEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.PistonBlockStructureHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Map;

@Mixin(PistonBlock.class)
public class PistonDestroyEffectsMixin {
    @Inject(
            method = "doMove(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Direction;Z)Z",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void habitat_pistonDestroyEffectsMixin(World worldIn, BlockPos pos, Direction directionIn, boolean extending, CallbackInfoReturnable<Boolean> cir, BlockPos blockpos, PistonBlockStructureHelper pistonblockstructurehelper, Map<BlockPos, BlockState> map, List<BlockPos> list, List<BlockState> list1, List<BlockPos> list2, BlockState[] ablockstate, Direction direction, int j, int k, BlockPos blockpos2, BlockState blockstate1) {
        if (blockstate1.getBlock() instanceof IHasPistonDestroyEffect)
            ((IHasPistonDestroyEffect) blockstate1.getBlock()).onPistonDestroy(worldIn, blockpos2, blockstate1);
    }
}