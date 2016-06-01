/**
 * 
 */
package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import item.*;
import tile.TileMap;

/**
 * @author SJHSStudent
 *
 */
public class Player extends AbstractEntity {
	
	private static final String PLAYER_CHAR = "@";
	
	private static final String PLAYER_NAME = "You";
	
	private static final String PLAYER_DESCRIPTION = "How Meta...";
	
	public Player(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = PLAYER_CHAR;
		this.color = Color.RED;
		
		this.name = PLAYER_NAME;
		this.description = PLAYER_DESCRIPTION;
		
		this.ignore = true;
		
		faction = FACTION_PLAYER_OR_ALLY;
		
		grounded = true;
		
		inventory = new Inventory();
		inventory.set(0, new Sword());
		inventory.set(1, new Hex());
		inventory.set(2, new OrbOfFlight());
		inventory.equip();
		
		hp_cap = 16;
		str_cap = 6;
		def_cap = 6;
		mag_cap = 6;
		res_cap = 6;
		skl_cap = 4;
		spd_cap = 4;
		
		hp_gro = 20;
		str_gro = 50;
		def_gro = 50;
		mag_gro = 50;
		res_gro = 50;
		skl_gro = 50;
		spd_gro = 50;
		
		levelUp(level);
		
	}
	
	public void onTurn() {
		this.regen();
	}

	@Override
	public void onAttacked(AbstractEntity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
