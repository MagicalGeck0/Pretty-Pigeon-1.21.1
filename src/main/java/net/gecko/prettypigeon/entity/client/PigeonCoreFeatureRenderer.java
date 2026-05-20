package net.gecko.prettypigeon.entity.client;

import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonCore;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PigeonCoreFeatureRenderer extends FeatureRenderer<PigeonEntity, PigeonModel<PigeonEntity>> {

    private final PigeonModel<PigeonEntity> model;

    public PigeonCoreFeatureRenderer(FeatureRendererContext<PigeonEntity, PigeonModel<PigeonEntity>> context, EntityModelLoader loader) {

        super(context);
        this.model = new PigeonModel<>(loader.getModelPart(PigeonModel.PIGEON));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PigeonEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getCore() == PigeonCore.NONE) return;

        Identifier texture = Identifier.of(PrettyPigeon.MOD_ID, String.format("textures/entity/pigeon/core/%s.png",entity.getCore().toString().toLowerCase()));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture));

        this.getContextModel().copyStateTo(this.model);
        this.model.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }
}
