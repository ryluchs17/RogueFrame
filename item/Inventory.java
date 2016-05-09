/**
 * 
 */
package item;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * @author Ryan Luchs
 *
 */
public class Inventory {
	
//	private AbstractEntity owner;
	
	private AbstractItem[] items;
	
	private AbstractItem select;
	
	public Inventory() {
		items = new AbstractItem[5];
	}
	
//	public Inventory(AbstractEntity owner) {
//		items = new AbstractItem[5];
//		
//		this.owner = owner;
//	}
//	
//	public void setOwner(AbstractEntity e) {
//		owner = e;
//	}
	
	public void set(int index, AbstractItem i) {
		if(index >= 0 && index <= 4) {
			items[index] = i;
		}
	}
	
	public void select(int index) {
		if(index >= 0 && index <= 4) {
			select = items[index];
		}
	}
	
	public AbstractItem selected() {
		return select;
	}
	
	public void swap( int index, AbstractTile t) {
		if(index >= 0 && index <= 4) {
			AbstractItem temp;
			
			if(t.hasItem() && items[index].getName().equals(t.getItem().getName()) && items[index].isStackable()) {
				items[index].uses += t.getItem().uses;
				t.setItem(null);
			} else {
				temp = t.getItem();
				t.setItem(items[index]);
				items[index] = temp;
			}
		}
	}
	
	public void swap(int index1, int index2) {
		if((index1 >= 0 && index1 <= 4) && (index2 >= 0 && index2 <= 4)) {
			AbstractItem temp;
			
			if(items[index1] != null && items[index2] != null && items[index1].getName().equals(items[index2].getName()) && items[index1].isStackable()) {
				items[index1].uses += items[index2].uses;
				items[index2] = null;
			} else {
				temp = items[index1];
				items[index1] = items[index2];
				items[index2] = items[index1];
			}
		}
	}
	
	public boolean has(String name) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
//	public int get(short ItemID) {
//		for(int i = 0; i < items.length; i++) {
//			if(items[i] != null && items[i].getItemID() == ItemID) {
//				return i;
//			}
//		}
//		
//		return -1;
//	}
	
}
