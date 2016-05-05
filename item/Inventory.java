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
	
//	public void add(ItemPoint p) {
//		
//	}
	
	public void swap(ItemPoint p, int index) {
		AbstractItem temp;
		
		if(p.containsExistingItem()) {
			if(items[index].getItemID() ==  p.getContents().getItemID() && items[index].isStackable()) {
				items[index].uses += p.getContents().uses;
				p.setContents(null);
			} else {
				temp = p.getContents();
				p.setContents(items[index]);
				items[index] = temp;
			}
		} else {
			temp = p.getContents();
			p.setContents(items[index]);
			items[index] = temp;
		}
	}
	
	public boolean has(short ItemID) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getItemID() == ItemID) {
				return true;
			}
		}
		
		return false;
	}
	
	public int get(short ItemID) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getItemID() == ItemID) {
				return i;
			}
		}
		
		return -1;
	}
	
	
}
