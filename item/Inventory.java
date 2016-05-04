/**
 * 
 */
package item;

import entity.AbstractEntity;

/**
 * @author Ryan Luchs
 *
 */
public class Inventory {
	
	AbstractEntity owner;
	
	AbstractItem[] items;
	
	public Inventory(AbstractEntity owner) {
		items = new AbstractItem[5];
		
		this.owner = owner;
	}
	
	public Inventory(AbstractEntity owner, AbstractItem[] items) {
		this.items = items;
		this.owner = owner;
	}
	
	public void add(ItemPoint p) {
		
	}
	
	public void swap(ItemPoint p) {
		
	}
	
}
