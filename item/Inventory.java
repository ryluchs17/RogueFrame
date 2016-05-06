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
	
	private AbstractEntity owner;
	
	private AbstractItem[] items;
	
	private AbstractItem select;
	
	public Inventory() {
		items = new AbstractItem[5];
	}
	
	public Inventory(AbstractEntity owner) {
		items = new AbstractItem[5];
		
		this.owner = owner;
	}
	
//	public void add(ItemPoint p) {
//		
//	}
	
	public void setOwner(AbstractEntity e) {
		owner = e;
	}
	
	public void select(int index) {
		if(index >= 0 && index <= 4) {
			select = items[index];
		}
	}
	
	public AbstractItem selected() {
		return select;
	}
	
	public void swap(ItemPoint p, int index) {
		if(index >= 0 && index <= 4) {
			AbstractItem temp;
			
			if(p.containsExistingItem() && items[index].getItemID() ==  p.getContents().getItemID() && items[index].isStackable()) {
				items[index].uses += p.getContents().uses;
				p.setContents(null);
			} else {
				temp = p.getContents();
				p.setContents(items[index]);
				items[index] = temp;
			}
		}
	}
	
	public void swap(int index1, int index2) {
		if((index1 >= 0 && index1 <= 4) && (index2 >= 0 && index2 <= 4)) {
			AbstractItem temp;
			
			if(items[index1] != null && items[index2] != null && items[index1].getItemID() == items[index2].getItemID() && items[index1].isStackable()) {
				items[index1].uses += items[index2].uses;
				items[index2] = null;
			} else {
				temp = items[index1];
				items[index1] = items[index2];
				items[index2] = items[index1];
			}
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
