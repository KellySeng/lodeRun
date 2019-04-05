package loderunner.services;

public interface ItemService extends CellContent{

	/*
	 * Observators
	 */
	
	public int getId();
	public ItemType getNature();
	public int getHgt();
	public int getCol();
}
