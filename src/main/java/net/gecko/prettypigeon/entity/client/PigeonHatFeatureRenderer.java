package net.gecko.prettypigeon.entity.client;

import com.google.common.collect.Maps;
import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.gecko.prettypigeon.entity.custom.PigeonHat;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class PigeonHatFeatureRenderer extends FeatureRenderer<PigeonEntity, PigeonModel<PigeonEntity>> {

    private static final Map<PigeonHat, Identifier> LOCATION_BY_HAT =
            Util.make(Maps.newEnumMap(PigeonHat.class), map -> {
                map.put(PigeonHat.DEFAULT,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/default.png"));
                map.put(PigeonHat.FEZ,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/fez.png"));
                map.put(PigeonHat.GLASSES,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/glasses.png"));
                map.put(PigeonHat.HEADPHONES,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/headphones.png"));
                map.put(PigeonHat.RAM,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/ram.png"));
                map.put(PigeonHat.CROWN,
                        Identifier.of(PrettyPigeon.MOD_ID, "textures/entity/pigeon/hat/crown.png"));
            });

    private final PigeonModel<PigeonEntity> model;

    public PigeonHatFeatureRenderer(FeatureRendererContext<PigeonEntity, PigeonModel<PigeonEntity>> context, EntityModelLoader loader) {

        super(context);
        this.model = new PigeonModel<>(loader.getModelPart(PigeonModel.PIGEON));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PigeonEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getHat() == PigeonHat.DEFAULT) return;

        Identifier texture = PigeonHatFeatureRenderer.LOCATION_BY_HAT.get(entity.getHat());

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture));

        this.getContextModel().copyStateTo(this.model);
        this.model.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.model.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }
}
