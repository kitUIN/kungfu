package io.github.kituin.kungfu.capabilities.qi;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import java.util.UUID;

public class QiCapability implements IQiCapability {
    private static final Logger LOGGER = LogManager.getLogger();
    private float current;
    private float maximum;
    private float recuperation;
    private float sprintSpeed;
    private static final UUID SPRINTING_SPEED_BOOST_ID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private float healthCureSpeed;
    private float mineSpeed;

    public QiCapability(float current,float maximum,float recuperation,float sprintSpeed,float healthCureSpeed,float mineSpeed) {
        this.current = current;
        this.maximum = maximum;
        this.recuperation = recuperation;
        this.sprintSpeed = sprintSpeed;
        this.healthCureSpeed = healthCureSpeed;
        this.mineSpeed = mineSpeed;
    }
    // Current Qi
    @Override
    public void setCurrent(float current) {
        this.current = Math.min(Math.max(current, 0),this.maximum);
    }
    @Override
    public float getCurrent() {
        return current;
    }
    // Max Qi
    @Override
    public void setMaximum(float maximum) {
        this.maximum = Math.max(Math.max(maximum, 0),this.current);
    }
    @Override
    public float getMaximum() {
        return maximum;
    }
    // Qi recuperate itself
    @Override
    public void setRecuperation(float recuperation) {
        this.recuperation = Math.max(recuperation, 0);
    }
    @Override
    public void recuperation() {
        this.current = Math.min(this.current+this.recuperation,this.maximum);
    }
    // Sprint Speed Up
    @Override
    public void setSpeedUp(float sprintSpeed) {
        this.sprintSpeed = Math.max(sprintSpeed, 0);

    }
    public void setSPRINTING_SPEED_BOOST(float sprintSpeed, PlayerEntity playerIn){
        ModifiableAttributeInstance modifiableattributeinstance = playerIn.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeModifier SPRINTING_SPEED_BOOST =  new AttributeModifier(SPRINTING_SPEED_BOOST_ID, "Sprinting speed boost", (double)sprintSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL);
        assert modifiableattributeinstance != null;
        modifiableattributeinstance.removeModifier(SPRINTING_SPEED_BOOST);
        modifiableattributeinstance.applyNonPersistentModifier(SPRINTING_SPEED_BOOST);
    }
    @Override
    public void speedUp(PlayerEntity playerIn){
        if (this.sprintSpeed > 0.3F && playerIn.isSprinting() && this.current > 30) {
            this.current = Math.max(this.current - 1,0);
            this.setSPRINTING_SPEED_BOOST(this.sprintSpeed,playerIn);
        }else{
            this.setSPRINTING_SPEED_BOOST(0.3F,playerIn);
        }
    }
    // Health Cure
    @Override
    public void setHealthCureSpeed(float healthCureSpeed) {
        this.healthCureSpeed = Math.max(healthCureSpeed, 0);
    }

    @Override
    public void healthCure(PlayerEntity playerIn) {
        float health = playerIn.getHealth();
        if(this.healthCureSpeed>0 && health < playerIn.getMaxHealth() && this.current > 0){
            playerIn.setHealth(health +this.healthCureSpeed);
            this.current = Math.max(this.current - 1,0);
        }
    }

    @Override
    public void setMineSpeed(float mineSpeed) {
        this.mineSpeed = Math.max(mineSpeed, 0);

    }

    @Override
    public void mineSpeedUp(BreakSpeed event) {
        if(this.mineSpeed > 1.0F && this.current > 0){
            this.current = Math.max(this.current - 0.05F,0);
            event.setNewSpeed(this.mineSpeed);
        }else {
            event.setNewSpeed(1.0F);
        }
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putFloat("current", this.current);
        compoundNBT.putFloat("maximum", this.maximum);
        compoundNBT.putFloat("recuperation", this.recuperation);
        compoundNBT.putFloat("sprintSpeed", this.sprintSpeed);
        compoundNBT.putFloat("healthCureSpeed", this.healthCureSpeed);
        compoundNBT.putFloat("mineSpeed", this.mineSpeed);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.current = nbt.getFloat("current");
        this.maximum = nbt.getFloat("maximum");
        this.recuperation = nbt.getFloat("recuperation");
        this.sprintSpeed = nbt.getFloat("sprintSpeed");
        this.healthCureSpeed = nbt.getFloat("healthCureSpeed");
        this.mineSpeed = nbt.getFloat("mineSpeed");
    }
}
