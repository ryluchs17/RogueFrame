/**
 * 
 */
package item;

import tile.AbstractTile;

/**
 * @author Ryan Luchs
 *
 */
public class Inventory {
	
	private boolean equipt;
	
	private AbstractItem[] items;
	
	public Inventory() {
		items = new AbstractItem[5];
	}
	
	public Inventory(AbstractItem[] items) {
		this.items = items;
	}
	
	public void set(int index, AbstractItem i) {
		if(index >= 0 && index <= 4) {
			items[index] = i;
		}
	}
	
	public AbstractItem get(int index) {
		if(index >= 0 && index <= 4) {
			return items[index];
		} else {
			return null;
		}
	}
	
	public void remove(int index) {
		if(index >= 0 && index <= 4) {
			items[index] = null;
		}
	}
	
	public boolean exists(int index) {
		if(index >= 0 && index <= 4) {
			return items[index] != null;
		} else {
			return false;
		}
	}
	
	public void equip() {
		if(items[0] != null) equipt = !equipt;
	}
	
	public boolean hasEquipt() {
		return equipt;
	}
	
	public void swap(int index, AbstractTile t) {
		if(index >= 0 && index <= 4 && !(index == 0 && equipt)) {
			AbstractItem temp;
			
			if(t.hasItem() && items[index] != null && items[index].getName().equals(t.getItem().getName()) && items[index].isStackable()) {
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
		if((index1 >= 0 && index1 <= 4) && (index2 >= 0 && index2 <= 4) && !((index1 == 0 || index2 == 0) && equipt)) {
			AbstractItem temp;
			
			if(items[index1] != null && items[index2] != null && items[index1].getName().equals(items[index2].getName()) && items[index1].isStackable() && items[index1] != items[index2]) {
				items[index1].uses += items[index2].uses;
				items[index2] = null;
			} else {
				temp = items[index1];
				items[index1] = items[index2];
				items[index2] = temp;
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
	
	public void clean() {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].isStackable() && items[i].uses == 0) {
				items[i] = null;
			}
		}
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
