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
	
	private AbstractItem[] items;
	
	/**
	 * Creates an empty Invetory of size 5
	 */
	public Inventory() {
		items = new AbstractItem[5];
	}
	
	/**
	 * Creates an Inventory from a given array of AbstractItems
	 * @param items
	 */
	public Inventory(AbstractItem[] items) {
		this.items = items;
		this.equip();
	}
	
	/**
	 * Creates an Inventory of size 1 from the given AbstractItem
	 * @param item
	 */
	public Inventory(AbstractItem item) {
		this.items = new AbstractItem[]{item};
		this.equip();
	}
	
	/**
	 * Sets the slot of the given index to the given AbstractItem
	 * @param index The index
	 * @param i The AbstractItem
	 */
	public void set(int index, AbstractItem i) {
		if(index >= 0 && index < items.length) {
			items[index] = i;
		}
	}
	
	/**
	 * Returns the AbstractItel at the given index
	 * returns null if fails
	 * @param index The index
	 * @return The AbstractItem at the index
	 */
	public AbstractItem get(int index) {
		if(index >= 0 && index < items.length) {
			return items[index];
		} else {
			return null;
		}
	}
	
	/**
	 * Checks to see whether this Inventory contains a given AbstractItem and returns the index
	 * reutrns -1 if fails
	 * @param i The AbstractItem
	 * @return The index of the AbstractItem
	 */
	public int getIndex(AbstractItem i) {
		for(int x = 0; x < items.length; x++) {
			if(items[x] == i) {
				return x;
			}
		}
		return -1;
	}
	
	/**
	 * Removes the AbstractItem at the given index
	 * @param index The index
	 */
	public void remove(int index) {
		if(index >= 0 && index < items.length) {
			items[index] = null;
		}
	}
	
	/**
	 * Returns whether a given index exists
	 * @param index The index
	 * @return true if the index exists
	 */
	public boolean exists(int index) {
		if(index >= 0 && index < items.length) {
			return items[index] != null;
		} else {
			return false;
		}
	}
	
	/**
	 * Locks/Unlocks the item in slot 0
	 */
	public void equip() {
		if(items[0] != null && !items[0].isCursed()) items[0].locked = !items[0].locked;
	}
	
	/**
	 * Returns whether the item in slot 0 is locked
	 * @return true if locked
	 */
	public boolean hasEquipt() {
		return items[0] != null ? items[0].locked : false;
	}
	
	/**
	 * Swaps the item at the given index with the item of the given AbtractTile
	 * fails if the index is locked
	 * @param index The index
	 * @param t The AbstractTile
	 */
	public void swap(int index, AbstractTile t) {
		if(index >= 0 && index < items.length && (items[index] != null ? !items[index].isLocked() : true)) {
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
	
	/**
	 * Swaps the items at the given indices
	 * fails if either is locked
	 * @param index1 The first index
	 * @param index2 The second index
	 */
	public void swap(int index1, int index2) {
		if((index1 >= 0 && index1 < items.length) && (items[index1] != null ? !items[index1].isLocked() : true) && (items[index2] != null ? !items[index2].isLocked() : true)) {
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
	
	/**
	 * Returns whether any item in this Inventory has a name matching the given
	 * @param name The name to check for
	 * @return true if there is a match
	 */
	public boolean has(String name) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns whether an item exists at the given index
	 * @param index The index
	 * @return true if the item at the index exists
	 */
	public boolean has(int index) {
		return index > 0 && index < items.length && items[index] != null;
	}
	
	/**
	 * Removes all stackable items with uses of 0 or less
	 */
	public void clean() {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].isStackable() && items[i].uses == 0) {
				items[i] = null;
			}
		}
	}
	
	/**
	 * Returns a String representation of this Inventory
	 */
	public String toString() {
		StringBuilder build = new StringBuilder();
		
		for(int i = 0; i < items.length; i++) {
			build.append(items[i] == null ? " nothing \n" : " " + items[i].uses + " " + items[i].name + " ");
		}
		
		return build.toString();
	}
	
}
