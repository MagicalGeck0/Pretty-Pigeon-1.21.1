package net.gecko.prettypigeon.entity.client;

import com.google.common.collect.Maps;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.gecko.prettypigeon.entity.custom.PigeonHat;
import net.gecko.prettypigeon.entity.custom.PigeonVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PigeonRenderer extends MobEntityRenderer<PigeonEntity, PigeonModel<PigeonEntity>> {

    private static final Map<PigeonVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PigeonVariant.class), map -> {
                map.put(PigeonVariant.COMMON,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/pigeon.png"));
                map.put(PigeonVariant.CARNEAU,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/carneau.png"));
                map.put(PigeonVariant.SADDLEBACK,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/saddleback.png"));
                map.put(PigeonVariant.VERNANS,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/vernans.png"));
                map.put(PigeonVariant.DRAGON,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/dragon.png"));
                map.put(PigeonVariant.WARPED,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/warped.png"));
                map.put(PigeonVariant.CRIMSON,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/crimson.png"));
                map.put(PigeonVariant.AETHER,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/aether.png"));
                map.put(PigeonVariant.BUMBLE,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/bumble.png"));
                map.put(PigeonVariant.RIBBIT,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/ribbit.png"));
            });

    public PigeonRenderer(EntityRendererFactory.Context context) {
        super(context, new PigeonModel<>(context.getPart(PigeonModel.PIGEON)), 0.25f);

        this.addFeature(new PigeonHatFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(PigeonEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
