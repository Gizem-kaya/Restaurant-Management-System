public class Tables {
	
	private final int MAX_ORDERS = 5; 
	
	private int ID; 				// the table's ID
	private int capacity;			// the table's capacity
	private int inService;			// the table's in service value
	public int  totalOrderCount;	// the table's order count
	public String creator;			// the table's creator
	public String waiter;			// the table's waiter
	public Orders[] orders;			// the table's order list
	
	// getters and setters for the objects attributes
	
	public int getID() {return ID;}
	public void setID(int newID) {this.ID = newID;}
	public int getCapacity() {return capacity;}
	public void setCapacity(int newCapacity) {this.capacity = newCapacity;}
	public int getInService() {return inService;}
	public void setInService(int newInService) {this.inService = newInService;}
	public int getTotalOrderCount() {return totalOrderCount;}
	public void setTotalOrderCount(int newTotalOrderCount) {this.totalOrderCount = newTotalOrderCount;}
	public String getCreator() {return creator;}
	public void setCreator(String newCreator) {this.creator = newCreator;}	
	public String getWaiter() {return waiter;}
	public void setWaiter(String newWaiter) {this.waiter = newWaiter;}	
	
	
	public void incrementOrderCount() { this.totalOrderCount = this.totalOrderCount + 1;}		// increments the table's order count
	public int getMAX_ORDERS() {return MAX_ORDERS;}												// gets the MAX_ORDERS for a table
	
	
	public Orders createOrderList() {				

		this.orders =  new Orders[MAX_ORDERS];							// creates an order array 

			
		this.orders[this.totalOrderCount] = new Orders();				// creates an order and put the order object into the orders array
		this.orders[this.totalOrderCount].setTotalItemCount(0);			// set the initial item count for the order
		this.orders[this.totalOrderCount].createItemsList();			// creates an items list for the order
		return this.orders[this.totalOrderCount];						
		
	}
	
	public void addfirstOrder(String itemsName, double cost, int stockCount) {		

		this.orders[this.totalOrderCount].addItem(itemsName, cost, stockCount);			// calls the addItem method from Orders class for current order object

		
	}
	
	public void addOrderList() {						
		
		this.orders[this.totalOrderCount] = new Orders();				// creates an order object
		this.orders[this.totalOrderCount].setTotalItemCount(0);			// set the initial item count for the order
		this.orders[this.totalOrderCount].createItemsList();			// creates an items list for the order
	}
	
	public void addOrder(String itemsName, double cost, int stockCount) {
	
		this.orders[this.totalOrderCount].addItem(itemsName, cost, stockCount);		// calls the addItem method from Orders class for current order object (the same method with addfirstOrder but this is more readable) 
	
		
	}
	
}
