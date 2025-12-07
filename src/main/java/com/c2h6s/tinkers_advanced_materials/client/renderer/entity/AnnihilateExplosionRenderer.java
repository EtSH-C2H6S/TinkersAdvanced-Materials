package com.c2h6s.tinkers_advanced_materials.client.renderer.entity;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.util.RenderUtil;
import com.c2h6s.tinkers_advanced_materials.content.entity.AnnihilateExplosionEntity;
import com.c2h6s.tinkers_advanced_tools.content.entity.PlasmaExplosionProjectile;
import com.c2h6s.tinkers_advanced_tools.content.entity.PlasmaSlashEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class AnnihilateExplosionRenderer extends EntityRenderer<AnnihilateExplosionEntity> {
    public AnnihilateExplosionRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }
    @Override
    public void render(AnnihilateExplosionEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.tickCount>=1) {
            pPoseStack.pushPose();
            pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
            pPoseStack.scale(pEntity.getScale(), pEntity.getScale(), pEntity.getScale());
            PoseStack.Pose pose = pPoseStack.last();
            Matrix4f poseMatrix = pose.pose();
            Matrix3f normalMatrix = pose.normal();
            VertexConsumer consumer = pBuffer.getBuffer(RenderUtil.brightProjectileRenderType(getTextureLocation(pEntity,pPartialTick)));
            consumer.vertex(poseMatrix, -1, -1, 0).color(0xffffffff).uv(0, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 0, 0).endVertex();
            consumer.vertex(poseMatrix, 1, -1, 0).color(0xffffffff).uv(0, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 0, 1).endVertex();
            consumer.vertex(poseMatrix, 1, 1, 0).color(0xffffffff).uv(1, 1).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 1).endVertex();
            consumer.vertex(poseMatrix, -1, 1, 0).color(0xffffffff).uv(1, 0).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(LightTexture.FULL_BRIGHT).normal(normalMatrix, 0, 1, 0).endVertex();
            pPoseStack.popPose();
        }
    }
    @Override
    public ResourceLocation getTextureLocation(AnnihilateExplosionEntity pEntity) {
        return getTextureLocation(pEntity,0);
    }

    public ResourceLocation getTextureLocation(AnnihilateExplosionEntity pEntity,float partialTick) {
        int frame = Math.round( Mth.clamp((pEntity.tickCount-1+partialTick)*1.5f,1,12));
        return TinkersAdvanced.getLocation("textures/entity/annihilate_explosion/annihilate_explosion_"+frame+".png");
    }
}
