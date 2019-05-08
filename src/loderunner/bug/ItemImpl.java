package loderunner.bug;

import loderunner.services.ItemService;
import loderunner.services.ItemType;

public class ItemImpl implements ItemService{
	
	private int id;
	private ItemType nature;
	private int hgt;
	private int col;

	public ItemImpl(int id, ItemType nature, int col, int hgt) {
		this.id = id;
		this.nature = nature;
		this.hgt = hgt;
		this.col = col;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public ItemType getNature() {
		return nature;
	}

	@Override
	public int getHgt() {
		return hgt;
	}

	@Override
	public int getCol() {
		return col;
	}

}