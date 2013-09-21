package com.universalbay.mobbosses.mob;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.universalbay.mobbosses.MobBosses;

public class ArmoredMobSwarm extends MobSwarm {

	public ArmoredMobSwarm(EntityType type, int size, MobBosses mplugin) {
		super(type, size, mplugin);
		this.giveArmor();
	}
	
	public void giveArmor() {
		for (SwarmEntity entity : this.entities) {
			Random random = new Random();
			boolean isBoss = entity.isBoss();
			if (random.nextInt(3) == 1 || isBoss) {
				ItemStack is = new ItemStack(Material.IRON_HELMET, 1);
				int armorType = this.randomArmorType(isBoss);
				if (armorType == 1) {
					is.setType(Material.LEATHER_HELMET);
				}
				else if (armorType == 2) {
					is.setType(Material.IRON_HELMET);
				}
				else if (armorType == 3) {
					is.setType(Material.GOLD_HELMET);
				}
				else {
					is.setType(Material.DIAMOND_HELMET);
				}
				entity.getEntity().getEquipment().setHelmet(is);
			}
			if (random.nextInt(3) == 1 || isBoss) {
				ItemStack is = new ItemStack(Material.IRON_CHESTPLATE, 1);
				int armorType = this.randomArmorType(isBoss);
				if (armorType == 1) {
					is.setType(Material.LEATHER_CHESTPLATE);
				}
				else if (armorType == 2) {
					is.setType(Material.IRON_CHESTPLATE);
				}
				else if (armorType == 3) {
					is.setType(Material.GOLD_CHESTPLATE);
				}
				else {
					is.setType(Material.DIAMOND_CHESTPLATE);
				}
				entity.getEntity().getEquipment().setChestplate(is);
			}
			if (random.nextInt(3) == 1 || isBoss) {
				ItemStack is = new ItemStack(Material.IRON_LEGGINGS, 1);
				int armorType = this.randomArmorType(isBoss);
				if (armorType == 1) {
					is.setType(Material.LEATHER_LEGGINGS);
				}
				else if (armorType == 2) {
					is.setType(Material.IRON_LEGGINGS);
				}
				else if (armorType == 3) {
					is.setType(Material.GOLD_LEGGINGS);
				}
				else {
					is.setType(Material.DIAMOND_LEGGINGS);
				}
				entity.getEntity().getEquipment().setLeggings(is);
			}
			if (random.nextInt(3) == 1 || isBoss) {
				ItemStack is = new ItemStack(Material.IRON_BOOTS, 1);
				int armorType = this.randomArmorType(isBoss);
				if (armorType == 1) {
					is.setType(Material.LEATHER_BOOTS);
				}
				else if (armorType == 2) {
					is.setType(Material.IRON_BOOTS);
				}
				else if (armorType == 3) {
					is.setType(Material.GOLD_BOOTS);
				}
				else {
					is.setType(Material.DIAMOND_BOOTS);
				}
				entity.getEntity().getEquipment().setBoots(is);
			}
		}
	}
	
	public int randomArmorType(boolean isBoss) {
		Random random = new Random();
		if (isBoss == false) {
			switch (random.nextInt(10)) {
				default: return 1;
				case 0 : return 1;
				case 1 : return 1;
				case 2 : return 2;
				case 3 : return 2;
				case 4 : return 2;
				case 5 : return 2;
				case 6 : return 3;
				case 7 : return 3;
				case 8 : return 4;
				case 9 : return 4;
			}
		}
		else {
			switch (random.nextInt(4)) {
				default: return 4;
				case 0 : return 1;
				case 1 : return 2;
				case 2 : return 3;
				case 3 : return 4;
			}
		}
	}

}
