package net.gecko.prettypigeon;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.entity.client.PigeonModel;
import net.gecko.prettypigeon.entity.client.PigeonRenderer;

public class PrettyPigeonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(PigeonModel.PIGEON, PigeonModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PIGEON, PigeonRenderer::new);
    }
}
