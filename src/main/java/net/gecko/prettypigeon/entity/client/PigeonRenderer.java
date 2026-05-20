package net.gecko.prettypigeon.entity.client;

import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PigeonRenderer extends MobEntityRenderer<PigeonEntity, PigeonModel<PigeonEntity>> {

    public PigeonRenderer(EntityRendererFactory.Context context) {
        super(context, new PigeonModel<>(context.getPart(PigeonModel.PIGEON)), 0.25f);

        this.addFeature(new PigeonHatFeatureRenderer(this, context.getModelLoader()));
        this.addFeature(new PigeonCoreFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(PigeonEntity entity) {
        return Identifier.of(PrettyPigeon.MOD_ID, String.format("textures/entity/pigeon/%s.png",entity.getVariant().toString().toLowerCase()));
    }

    @Override
    public void render(PigeonEntity livingEntity, float f, float g, MatrixStack matrixstack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixstack.scale(0.5f,0.5f,0.5f);
        } else {
            matrixstack.scale(1f,1f,1f);
        }
        super.render(livingEntity, f, g, matrixstack, vertexConsumerProvider, i);
    }
}
