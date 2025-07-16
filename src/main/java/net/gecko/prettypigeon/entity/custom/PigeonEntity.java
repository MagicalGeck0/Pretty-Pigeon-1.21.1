package net.gecko.prettypigeon.entity.custom;

import net.gecko.prettypigeon.PrettyPigeonCompat;
import net.gecko.prettypigeon.entity.ModEntities;
import net.gecko.prettypigeon.item.ModItems;
import net.gecko.prettypigeon.sound.ModSounds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Arrays;

import static java.lang.Math.random;

public class PigeonEntity extends TameableEntity implements Flutterer {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState fezAnimationState = new AnimationState();
    public final AnimationState glassesAnimationState = new AnimationState();
    public final AnimationState headphonesAnimationState = new AnimationState();
    public final AnimationState crownAnimationState = new AnimationState();
    public final AnimationState ramAnimationState = new AnimationState();


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
        this.goalSelector.add(2, new TemptGoal(this,1.250, Ingredient.ofItems(ModItems.WORM), true));

        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, (double)1.0F, 5.0F, 1.0F));

        this.goalSelector.add(3,new FollowParentGoal(this, 1.10));

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }
    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, (double)6.0F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, (double)0.3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, (double)0.5F)
                .add(EntityAttributes.GENERIC_GRAVITY, (double)0.01f)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH,(double)0.5f);
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
        } if (this.isInAir()) {
            this.makePuff(1f,1f,1f,1);
        } if (this.getCustomName() != null && this.getCustomName().getString().equals("Chloe")) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.ramAnimationState.start(0);
            this.crownAnimationState.stop();
        } else if (this.getTypeHat() == 0) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.ramAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 1) {
            this.fezAnimationState.stop();
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.ramAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 2) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.stop();
            this.headphonesAnimationState.start(0);
            this.ramAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 3) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.stop();
            this.ramAnimationState.start(0);
            this.crownAnimationState.start(0);
        } else if (this.getTypeHat() == 4) {
            this.fezAnimationState.start(0);
            this.glassesAnimationState.start(0);
            this.headphonesAnimationState.start(0);
            this.ramAnimationState.stop();
            this.crownAnimationState.start(0);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.WORM);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        PigeonEntity baby = ModEntities.PIGEON.create(world);
        PigeonVariant variant = Util.getRandom(Arrays.stream(PigeonVariant.values()).filter(v -> v.getId() <= 3).toArray(PigeonVariant[]::new), this.random);
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
        ItemStack itemOff = player.getOffHandStack();
        if (!this.isTamed() && itemStack.isOf(ModItems.WORM)) {
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
        }
        if (player.isSneaking()) {

            /*Pointer*/
            if (itemStack.isOf(ModItems.POINTER)){
                if (itemStack.get(DataComponentTypes.CUSTOM_DATA) == null) {
                    NbtCompound data = new NbtCompound();
                    data.putInt("variant", this.getTypeVariant());
                    NbtComponent component = NbtComponent.of(data);
                    itemStack.set(DataComponentTypes.CUSTOM_DATA, component);
                    return ActionResult.success(this.getWorld().isClient);
                } else if (this.getOwner() == player){
                    int variant = itemStack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().getInt("variant");
                    this.setVariant(PigeonVariant.byid(variant));
                    itemStack.set(DataComponentTypes.CUSTOM_DATA, null);
                    makePuff(1f,1f,1f,100);
                    this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL);
                    itemStack.damage(1,player,EquipmentSlot.MAINHAND);
                    return ActionResult.SUCCESS;
                }else {
                    return ActionResult.PASS;
                }
            }

            /*bumblezone compat*/
            if (PrettyPigeonCompat.isLoaded("the_bumblezone")) {
                Item beeBread = PrettyPigeonCompat.loadItem("the_bumblezone","bee_bread");
                if (this.getOwner() == player && !this.getVariant().equals(PigeonVariant.BUMBLE) && ((itemStack.isOf(beeBread) && itemOff.isOf(ModItems.RAD_BLEND)) || (itemStack.isOf(ModItems.RAD_BLEND) && itemOff.isOf(beeBread)))) {
                    itemStack.decrementUnlessCreative(1, player);
                    itemOff.decrementUnlessCreative(1, player);
                    this.setVariant(PigeonVariant.BUMBLE);
                    this.makePuff(0.65f, 0.5f, 0.2f, 50);

                    return ActionResult.success(this.getWorld().isClient);
                }

            }/*ribbits compat*/
            if (PrettyPigeonCompat.isLoaded("ribbits")){
                Item toadstool = PrettyPigeonCompat.loadItem("ribbits","toadstool");
                if (this.getOwner() == player && !this.getVariant().equals(PigeonVariant.RIBBIT) && ((itemStack.isOf(toadstool) && itemOff.isOf(ModItems.RAD_BLEND)) || (itemStack.isOf(ModItems.RAD_BLEND) && itemOff.isOf(toadstool)))) {
                    itemStack.decrementUnlessCreative(1, player);
                    itemOff.decrementUnlessCreative(1, player);
                    this.setVariant(PigeonVariant.RIBBIT);
                    this.makePuff(0.6f,0.7f,0.3f,50);

                    return ActionResult.success(this.getWorld().isClient);
                }

            } if (this.getOwner() == player && !this.getVariant().equals(PigeonVariant.DRAGON) && ((itemStack.isOf(Items.DRAGON_BREATH) && itemOff.isOf(ModItems.RAD_BLEND)) || (itemStack.isOf(ModItems.RAD_BLEND) && itemOff.isOf(Items.DRAGON_BREATH)))) {
                itemStack.decrementUnlessCreative(1, player);
                itemOff.decrementUnlessCreative(1, player);
                this.setVariant(PigeonVariant.DRAGON);
                this.makePuff(0.6f,0.3f,0.5f,50);

                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getVariant().equals(PigeonVariant.WARPED) && ((itemStack.isOf(Items.WARPED_FUNGUS) && itemOff.isOf(ModItems.RAD_BLEND)) || (itemStack.isOf(ModItems.RAD_BLEND) && itemOff.isOf(Items.WARPED_FUNGUS)))) {
                itemStack.decrementUnlessCreative(1, player);
                itemOff.decrementUnlessCreative(1, player);
                this.setVariant(PigeonVariant.WARPED);
                this.makePuff(0.2f,0.4f,0.4f,50);

                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getVariant().equals(PigeonVariant.CRIMSON) && ((itemStack.isOf(Items.CRIMSON_FUNGUS) && itemOff.isOf(ModItems.RAD_BLEND)) || (itemStack.isOf(ModItems.RAD_BLEND) && itemOff.isOf(Items.CRIMSON_FUNGUS)))) {
                itemStack.decrementUnlessCreative(1, player);
                itemOff.decrementUnlessCreative(1, player);
                this.setVariant(PigeonVariant.CRIMSON);
                this.makePuff(0.4f,0.2f,0.2f,50);

                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getHat().equals(PigeonHat.FEZ) && itemStack.isOf(Items.RED_WOOL)) {
                itemStack.decrementUnlessCreative(1, player);
                setHat(PigeonHat.FEZ);
                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getHat().equals(PigeonHat.GLASSES) && itemStack.isOf(Items.REDSTONE)) {
                itemStack.decrementUnlessCreative(1, player);
                setHat(PigeonHat.GLASSES);
                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getHat().equals(PigeonHat.HEADPHONES) && itemStack.isOf(Items.NOTE_BLOCK)) {
                itemStack.decrementUnlessCreative(1, player);
                setHat(PigeonHat.HEADPHONES);
                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && !this.getHat().equals(PigeonHat.RAM) && itemStack.isOf(Items.GOAT_HORN)) {
                itemStack.decrementUnlessCreative(1, player);
                setHat(PigeonHat.RAM);
                return ActionResult.success(this.getWorld().isClient);

            } else if (this.getOwner() == player && itemStack.isOf(ModItems.RAD_STAR) && !this.isInvulnerable()) {
                itemStack.decrementUnlessCreative(1, player);
                this.setInvulnerable(true);

                this.makePuff(0f,1f,0f,50);
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.NEUTRAL);
                return ActionResult.success(this.getWorld().isClient);

            } else if (!this.isInAir() && this.isTamed() && this.isOwner(player)) {
                this.setSitting(!this.isSitting());


                return ActionResult.success(this.getWorld().isClient);
            }
        }

        return super.interactMob(player, hand);

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
    public void makePuff(float r,float g, float b,int quantity) {
        DustParticleEffect colorSmoke = new DustParticleEffect(new Vector3f(r, g, b), 1.0f);
        for (int i = 0; i < quantity; i++) {
            this.getWorld().addParticle(colorSmoke,
                    this.getX() + (random.nextDouble() - 0.5),
                    this.getY() + (random.nextDouble() - 0.5),
                    this.getZ() + (random.nextDouble() - 0.5),
                    (random.nextDouble() - 0.5),
                    (random.nextDouble() - 0.5),
                    (random.nextDouble() - 0.5));
        }
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
        PigeonVariant variant = Util.getRandom(Arrays.stream(PigeonVariant.values()).filter(v -> v.getId() <= 3).toArray(PigeonVariant[]::new), this.random);
        setVariant(variant);

        return super.initialize(world, difficulty, spawnReason, entityData);

    }

    @Nullable
    @Override
    protected  SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_PIGEON_AMBIENT;
    }
}
