package net.gecko.prettypigeon.entity.client;

import net.gecko.prettypigeon.PrettyPigeon;
import net.gecko.prettypigeon.entity.custom.PigeonEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PigeonModel<T extends PigeonEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer PIGEON = new EntityModelLayer(Identifier.of(PrettyPigeon.MOD_ID, "pigeon"), "main");

    private final ModelPart base;
    private final ModelPart head;

    public PigeonModel(ModelPart root) {
        this.base = root.getChild("base");
        this.head = this.base.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData base = modelPartData.addChild("base", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, 0.5F));

        ModelPartData body = base.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -2.0F, 0.5F));

        ModelPartData base_r1 = body.addChild("base_r1", ModelPartBuilder.create().uv(14, 0).cuboid(-1.0F, -7.0F, -3.0F, 2.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData bodyup_r1 = body.addChild("bodyup_r1", ModelPartBuilder.create().uv(19, 4).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -2.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData wings = base.addChild("wings", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, -2.0F, -1.5F));

        ModelPartData tail_r1 = wings.addChild("tail_r1", ModelPartBuilder.create().uv(7, 10).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 1.0F, 4.0F, 0.3927F, 0.0F, 0.0F));

        ModelPartData left = wings.addChild("left", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(0.0F, -1.0F, -2.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F)).mirrored(false).uv(0, -2).cuboid(0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -1.0F, 1.0F));

        ModelPartData right = wings.addChild("right", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -2.0F, 1.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(0, -2).cuboid(-2.0F, -3.0F, 4.0F, 0.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 2.0F, 1.0F));

        ModelPartData feet = base.addChild("feet", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -0.5F));

        ModelPartData rightfoot = feet.addChild("rightfoot", ModelPartBuilder.create().uv(-3, 3).cuboid(-1.0F, 4.0F, -1.5F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F))
                .uv(8, 0).cuboid(0.0F, 2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -2.0F, 0.5F));

        ModelPartData leftfoot = feet.addChild("leftfoot", ModelPartBuilder.create().uv(-3, 3).mirrored().cuboid(-2.0F, 4.0F, -1.5F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(8, 0).cuboid(-1.0F, 2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -2.0F, 0.5F));

        ModelPartData head = base.addChild("head", ModelPartBuilder.create().uv(0, 13).cuboid(-1.0F, -3.0F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(21, 0).cuboid(-1.5F, -2.75F, -1.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(21, 0).cuboid(0.5F, -2.75F, -1.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -2.5F));

        ModelPartData beak_r1 = head.addChild("beak_r1", ModelPartBuilder.create().uv(12, 0).cuboid(-1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -2.75F, 0.0F, 0.7854F, 0.0F));

        ModelPartData hat = head.addChild("hat", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 2.0F));

        ModelPartData fez = hat.addChild("fez", ModelPartBuilder.create().uv(15, 8).cuboid(1.75F, -9.25F, -2.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData fezbase_r1 = fez.addChild("fezbase_r1", ModelPartBuilder.create().uv(15, 9).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, -7.75F, -1.75F, 0.0F, 0.0F, 0.3054F));

        ModelPartData glasses = hat.addChild("glasses", ModelPartBuilder.create().uv(11, 15).cuboid(-1.0F, -8.0F, -4.25F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData lenceL_r1 = glasses.addChild("lenceL_r1", ModelPartBuilder.create().uv(10, 15).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.5F, -6.5F, -3.25F, 0.0F, -1.1781F, 0.0F));

        ModelPartData lenceR_r1 = glasses.addChild("lenceR_r1", ModelPartBuilder.create().uv(10, 15).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -6.5F, -3.25F, 0.0F, 1.1781F, 0.0F));

        ModelPartData headphones = hat.addChild("headphones", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(-2.0F, -8.5F, -2.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
                .uv(24, 0).cuboid(1.0F, -8.5F, -2.5F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 2).cuboid(-1.0F, -9.0F, -2.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData crown = hat.addChild("crown", ModelPartBuilder.create().uv(15, 13).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(16, 13).cuboid(-0.5F, -2.0F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(15, 13).cuboid(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(16, 13).cuboid(-0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(15, 11).cuboid(1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(15, 11).cuboid(-1.0F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, -7.25F, -2.0F, -0.5956F, -0.037F, 0.6485F));

        ModelPartData ram = hat.addChild("ram", ModelPartBuilder.create().uv(27, 5).cuboid(1.0F, -8.5F, -2.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 5).mirrored().cuboid(-2.0F, -8.5F, -2.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(27, 5).cuboid(1.25F, -8.25F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(27, 5).mirrored().cuboid(-2.25F, -8.25F, -1.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(26, 4).cuboid(1.5F, -7.25F, -2.75F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(26, 4).mirrored().cuboid(-2.5F, -7.25F, -2.75F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));


        ModelPartData rightT_r1 = crown.addChild("rightT_r1", ModelPartBuilder.create().uv(16, 13).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData leftT_r1 = crown.addChild("leftT_r1", ModelPartBuilder.create().uv(16, 13).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        return TexturedModelData.of(modelData, 32, 32);
    }
    @Override
    public void setAngles(PigeonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        /*HEAD*/
        netHeadYaw = MathHelper.clamp(netHeadYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch,-25.0F,45.0F);

        this.head.yaw = netHeadYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;

        PlayerEntity player = entity.getWorld().getClosestPlayer(entity, 4.0);
        if (player != null) {
            Vec3d toPlayer = player.getPos().subtract(entity.getPos());
            toPlayer = new Vec3d(toPlayer.x, 0, toPlayer.z).normalize();

            Vec3d lookVec = entity.getRotationVec(1.0F).normalize();
            lookVec = new Vec3d(lookVec.x,0,lookVec.z);
            double dot = lookVec.dotProduct(toPlayer);
            if (dot > 0.90) {
                this.head.roll = (float) Math.toRadians(20);
                this.head.yaw = (float) Math.toRadians(-30);
            }
        }
        /*ANIMATION*/
        this.animateMovement(PigeonAnimations.ANIM_PIGEON_WALK, limbSwing, limbSwingAmount, 2F, 2.5f);

        this.updateAnimation(entity.idleAnimationState, PigeonAnimations.ANIM_PIGEON_IDLE, ageInTicks, 1f);
        this.updateAnimation(entity.sittingAnimationState, PigeonAnimations.ANIM_PIGEON_SIT, ageInTicks, 1f);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        base.render(matrices, vertexConsumer, light, overlay, color);
    }
    @Override
    public ModelPart getPart() {
        return base;
    }

}

