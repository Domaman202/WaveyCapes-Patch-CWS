package ru.cws.waveycapes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.kosmx.playerAnim.api.PartKey;
import dev.kosmx.playerAnim.api.TransformType;
import dev.kosmx.playerAnim.core.impl.AnimationProcessor;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.tr7zw.waveycapes.support.AnimationSupport;
import dev.tr7zw.waveycapes.support.PlayerAnimatorSupport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerAnimatorSupport.class)
public abstract class PlayerAnimatorSupportMixin implements AnimationSupport {
//    /**
//     * @author DomamaN202
//     * @reason Z
//     */
//    @Overwrite
//    public Vector3 applyAnimationChanges(AbstractClientPlayerEntity entity, float delta, Vector3 cur) {
//        if (entity instanceof IAnimatedPlayer player) {
//            if (player.playerAnimator_getAnimation().isActive()) {
//                CapeHolder capeHolder = (CapeHolder)entity;
//                AnimationProcessor anim = player.playerAnimator_getAnimation();
//                anim.setTickDelta(delta);
//                Vec3f rot = anim.get3DTransform(PartKey.BODY, TransformType.ROTATION, Vec3f.ZERO);
//                Vec3f pos = anim.get3DTransform(PartKey.BODY, TransformType.POSITION, Vec3f.ZERO);
//                Vec3f headPos = anim.get3DTransform(PartKey.HEAD, TransformType.POSITION, Vec3f.ZERO).scale(0.0625F);
//                Matrix4f relativeTranslation = new Matrix4f();
//                relativeTranslation.identity();
//                relativeTranslation.scale(-1.0F);
//                float bodyOffset = 0.8F;
//                relativeTranslation.translate(0.0F, -0.8F, 0.0F);
//                relativeTranslation.translate(pos.getX(), pos.getY(), pos.getZ());
//                relativeTranslation.rotate(Axis.ZP.rotation(rot.getZ()));
//                relativeTranslation.rotate(Axis.YP.rotation(rot.getY()));
//                relativeTranslation.rotate(Axis.XP.rotation(rot.getX()));
//                relativeTranslation.translate(-(Float)headPos.getX(), -(Float)headPos.getY(), headPos.getZ());
//                relativeTranslation.translate(0.0F, 0.8F, 0.0F);
//                float realYaw = MathHelper.lerpAngleDegrees(delta, entity.lastBodyYaw, entity.bodyYaw);
//                Matrix4f matrix = new Matrix4f();
//                matrix.mul((new Matrix4f()).scale(-1.0F, 1.0F, 1.0F));
//                matrix.mul((new Matrix4f()).rotate(Axis.YP.rotationDegrees(realYaw)));
//                matrix.mul((new Matrix4f()).scale(1.0F, 1.0F, -1.0F));
//                matrix.mul(relativeTranslation);
//                Vector4f offset = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
//                offset.mul(matrix);
//                float scale = -16.0F;
//                Vector3 curOffset = new Vector3(offset.x() * scale, offset.y() * scale, offset.z() + scale);
//                Vector3 lastOffset = capeHolder.getLastPlayerAnimatorPosition();
//                capeHolder.setLastPlayerAnimatorPosition(curOffset.clone());
//                curOffset.subtract(lastOffset);
//                cur.add(curOffset);
//            }
//        }
//
//        return cur;
//    }

    @Redirect(method = "applyAnimationChanges", at = @At(value = "INVOKE", target = "Ldev/kosmx/playerAnim/core/impl/AnimationProcessor;get3DTransform(Ljava/lang/String;Ldev/kosmx/playerAnim/api/TransformType;Ldev/kosmx/playerAnim/core/util/Vec3f;)Ldev/kosmx/playerAnim/core/util/Vec3f;"))
    public Vec3f applyAnimationChanges(AnimationProcessor instance, String s, TransformType transformType, Vec3f vec3f, @Local AnimationProcessor anim) {
        return switch (s) {
            case "body" -> anim.get3DTransform(PartKey.BODY, TransformType.ROTATION, Vec3f.ZERO);
            case "head" -> anim.get3DTransform(PartKey.HEAD, TransformType.ROTATION, vec3f);
            default -> throw new IllegalArgumentException("Unknown part key: " + s);
        };
    }
}
