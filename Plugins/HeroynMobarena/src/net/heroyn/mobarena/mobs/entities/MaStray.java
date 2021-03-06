package net.heroyn.mobarena.mobs.entities;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.heroyn.mobarena.mobs.MaMobInterface;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntitySkeletonStray;
import net.minecraft.server.v1_12_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_12_R1.World;

public class MaStray extends EntitySkeletonStray implements MaMobInterface{

	public MaStray(World arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setPathFinders() {
		this.targetSelector.a(10, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));	
		
	}

	@Override
	public void equip() {
		
		ItemStack bow = new ItemStack(Material.BOW,1);
		ItemMeta meta = bow.getItemMeta();
		meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
		bow.setItemMeta(meta);
		
		LivingEntity ent = (LivingEntity)(this.getBukkitEntity());
		ent.getEquipment().setItemInMainHand(bow);
		
	}

}
