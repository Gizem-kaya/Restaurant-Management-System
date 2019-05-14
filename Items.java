
public class Items {
	
	public String itemName;	
	public double itemCost;
	public int stockCount;

	public String getItemName() { return itemName; }					// get the Items object's name
	public void setItemName(String name) {this.itemName = name; }		// set the Items object's name
	public double getItemCost() {return itemCost;}						// get the Items object's cost
	public void setItemCost(double cost) {this.itemCost = cost;}		// set the Items object's cost
	public int getStockCount() {return stockCount; }					// get the Items object's stock count
	public void setStockCount(int stock) { this.stockCount = stock;}	// set the Items object's stock count
	
	public void decrementStockCount() {this.stockCount = this.stockCount -1;}     // decrement the Items object's stock count by 1
	
	
}
