
public class Orders {
	
	public int MAX_ITEMS = 10;				// an order has items that is maximum MAX_ITEMS
	private int totalItemCount;				// the order's total item count
	public Items[] items;					// the order's Items objects array
	
	public int getTotalItemCount() { return totalItemCount;}					// get the order's item counts
	public void setTotalItemCount(int count) {this.totalItemCount = count;}		// set the order's item counts
	
	private void incrementItemCount() {this.totalItemCount = this.totalItemCount + 1;}	//increment the order's item counts by 1
	
	
	
	public Items[] createItemsList() {			// creates the order's item array

		this.items = new Items[MAX_ITEMS];

		return this.items;
		
	}
	
	public Items[] addItem(String itemsName, double cost, int stockCount) {				// creates an Items object and set the attributes of the created object

		this.items[this.totalItemCount] = new Items();
		this.items[this.totalItemCount].itemName = itemsName;
		this.items[this.totalItemCount].itemCost = cost;
		this.items[this.totalItemCount].stockCount = stockCount;

		this.incrementItemCount();														// increments the order's item count by 1
		return items;
		
	}
}
