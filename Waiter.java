
public class Waiter extends People {

	public int orderCount;				// the order count that the waiter has
	private int tableCount;				// the order count that the waiter deals with
	public int orderCountForSalary;
	
	// getters and setters for the waiter object
	
	public int getOrderCount() {return orderCount;}
	public void setOrderCount(int newOrderCount) {this.orderCount = newOrderCount;}
	public void incrementOrderCount() {this.orderCount = this.orderCount + 1;}
	public int getTableCount() {return tableCount;}
	public void setTableCount(int newTableCount) { this.tableCount = newTableCount; }
	
	
	public void incrementTableCount() {this.tableCount = this.tableCount + 1;}			// increment the table count of the waiter
}
