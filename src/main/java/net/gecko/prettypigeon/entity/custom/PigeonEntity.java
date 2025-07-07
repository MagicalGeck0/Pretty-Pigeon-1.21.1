package net.gecko.prettypigeon.entity.custom;

import net.gecko.prettypigeon.entity.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static java.lang.Math.random;

public class PigeonEntity extends TameableEntity implements Flutterer {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState fezAnimationState = new AnimationState();
    public final AnimationState glassesAnimationState = new AnimationState();
    public final AnimationState headphonesAnimationState = new AnimationState();
    public final AnimationState crownAnimationState = new AnimationState();


    private int idleAnimationTimeout = 0;

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT = DataTracker.registerData(PigeonEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> DATA_ID_TYPE_HAT = DataTracker.registerData(PigeonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PigeonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this,1.150));
        this.goalSelector.add(2, new TemptGoal(this,1.250, Ingredient.fromTag(ItemTags.PARROT_FOOD), true));

        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, (double)1.0F, 5.0F, 1.0F));

        this.goalSelector.add(3,new FollowParentGoal(this, 1.10));

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, (double)6.0F).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.3F).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, (double)3.0F);
    }

    private void SetupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = (int) (random() * 201);
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }
    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.SetupAnimationStates();
        } if (this.isInSittingPose()) {
            this.sittingAnimationState.start(0);
        } else {
            this.sittingAnimationState.stop();
        } if (this.getCustomName() != null && this.getCustomName().getString().equals("Chloe")) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.crownAnimationState.stop();
        } else if (this.getTypeHat() == 0) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 1) {
            this.fezAnimationState.stop();
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 2) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.stop();
            this.headphonesAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 3) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.stop();
            this.crownAnimationState.start(0);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.PARROT_FOOD);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        PigeonEntity baby = ModEntities.PIGEON.create(world);
        PigeonVariant variant = Util.getRandom(PigeonVariant.values(), this.random);
        baby.setVariant(variant);
        return baby;
    }
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.isTamed() && itemStack.isIn(ItemTags.PARROT_FOOD)) {
            itemStack.decrementUnlessCreative(1, player);
            if (!this.isSilent()) {
                this.getWorld().playSound((PlayerEntity) null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            if (!this.getWorld().isClient) {
                if (this.random.nextInt(10) == 0) {
                    this.setOwner(player);
                    this.getWorld().sendEntityStatus(this, (byte) 7);
                } else {
                    this.getWorld().sendEntityStatus(this, (byte) 6);
                }
            }

            return ActionResult.success(this.getWorld().isClient);
        } else if (this.getOwner() == player && itemStack.isOf(Items.RED_WOOL)) {
            itemStack.decrementUnlessCreative(1, player);
            setHat(PigeonHat.FEZ);
            return ActionResult.success(this.getWorld().isClient);

        } else if (this.getOwner() == player && itemStack.isOf(Items.REDSTONE)) {
            itemStack.decrementUnlessCreative(1, player);
            setHat(PigeonHat.GLASSES);
            return ActionResult.success(this.getWorld().isClient);

        }else if (this.getOwner() == player && itemStack.isOf(Items.NOTE_BLOCK)) {
            itemStack.decrementUnlessCreative(1, player);
            setHat(PigeonHat.HEADPHONES);
            return ActionResult.success(this.getWorld().isClient);

        } else if (!itemStack.isIn(ItemTags.PARROT_POISONOUS_FOOD)) {
            if (!this.isInAir() && this.isTamed() && this.isOwner(player)) {
                if (!this.getWorld().isClient) {
                    this.setSitting(!this.isSitting());
                }

                return ActionResult.success(this.getWorld().isClient);
            } else {
                return super.interactMob(player, hand);
            }
        } else {
            itemStack.decrementUnlessCreative(1, player);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 900));
            if (player.isCreative() || !this.isInvulnerable()) {
                this.damage(this.getDamageSources().playerAttack(player), Float.MAX_VALUE);
            }

            return ActionResult.success(this.getWorld().isClient);
        }
    }

    public boolean isInAir() {
        return !this.isOnGround();
    }

    protected boolean canTeleportOntoLeaves() {
        return true;
    }


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
        builder.add(DATA_ID_TYPE_HAT, 0);
    }

    /*VARIANT*/
    public PigeonVariant getVariant() {
        return PigeonVariant.byid(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private  void setVariant(PigeonVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    /*HAT*/
    public PigeonHat getHat() {
        return PigeonHat.byid(this.getTypeHat() & 255);
    }

    private int getTypeHat() {
        return this.dataTracker.get(DATA_ID_TYPE_HAT);
    }

    private void setHat(PigeonHat hat) {
        this.dataTracker.set(DATA_ID_TYPE_HAT, hat.getId() & 255);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("variant", this.getTypeVariant());
        nbt.putInt("hat", this.getTypeHat());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("variant"));
        this.dataTracker.set(DATA_ID_TYPE_HAT, nbt.getInt("hat"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

        setHat(PigeonHat.DEFAULT);
        PigeonVariant variant = Util.getRandom(PigeonVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
