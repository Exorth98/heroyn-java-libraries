package net.heroyn.mobarena.mobs.entities;

import net.heroyn.mobarena.mobs.MaMobInterface;
import net.minecraft.server.v1_12_R1.EntityEvoker;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_12_R1.World;

public class MaEvoker extends EntityEvoker implements MaMobInterface{

	public MaEvoker(World arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPathFinders() {
		this.targetSelector.a(10, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));	
		
	}

	@Override
	public void equip() {
		// TODO Auto-generated method stub
		
	}

}
