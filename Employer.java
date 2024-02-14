
public class Employer extends People {

	private int tableCount;				// the table count that this employer created 

	public int getTableCount() {return tableCount;}										// get the tableCount	
	public void setTableCount(int newTableCount) {this.tableCount = newTableCount;}		// set the tableCount
	
	public void incrementTableCount() {this.tableCount = this.tableCount + 1;}			// increment the employer's tableCount by 1
	
}
