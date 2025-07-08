package net.gecko.prettypigeon.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class PigeonAnimations {

    public static final Animation ANIM_PIGEON_IDLE = Animation.Builder.create(1.25f)
            .addBoneAnimation("head",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(152.66f, -44.86f, -89.45f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.625f, AnimationHelper.createRotationalVector(146.12f, -42.22f, -83.59f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(159.92f, -46.77f, -96.08f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.875f, AnimationHelper.createRotationalVector(146.12f, -42.22f, -83.59f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("left",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, -67.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.875f, AnimationHelper.createRotationalVector(0f, 0f, -67.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC))).build();

    public static final Animation ANIM_PIGEON_SIT = Animation.Builder.create(2f)
            .addBoneAnimation("body",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -2f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("wings",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -2f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("feet",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0.6f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("head",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, -2f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation ANIM_PIGEON_WALK = Animation.Builder.create(1f).looping()
            .addBoneAnimation("head",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, -15.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 15.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("rightfoot",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(25f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(-25f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("leftfoot",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(-25f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(25f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC))).build();

    public static final Animation ANIM_PIGEON_FEZ = Animation.Builder.create(2f)
            .addBoneAnimation("fez",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation ANIM_PIGEON_GLASSES = Animation.Builder.create(0.1f)
            .addBoneAnimation("glasses",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation ANIM_PIGEON_HEADPHONES= Animation.Builder.create(0.1f)
            .addBoneAnimation("headphones",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation ANIM_PIGEON_CROWN= Animation.Builder.create(0.1f)
            .addBoneAnimation("crown",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation ANIM_PIGEON_RAM= Animation.Builder.create(0.1f)
            .addBoneAnimation("ram",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}
