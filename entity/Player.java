/**
 * 
 */
package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
		
		grounded = true;
		
		hp_gro = 50;
		str_gro = 50;
		def_gro = 50;
		mag_gro = 50;
		res_gro = 50;
		skl_gro = 50;
		spd_gro = 50;
		
		levelUp(level);
		
	}
	
	public void onTurn() {

	}

	@Override
	public void onAttacked(AttackEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
