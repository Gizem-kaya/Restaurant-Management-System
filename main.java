import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class RevisedAssignment2 {

	public static final int MAX_TABLES = 5; // maximum number of tables to be created
	public static final int MAX_EMPLOYER = 5; // maximum number of employers to be added
	public static final int MAX_WAITER = 5; // maximum number of waiters to be added
	public static final int ALLOWED_MAX_TABLES = 2; // maximum number of tables to be created by an employer
	public static final int MAX_TABLE_SERVICES = 3; // maximum number of tables to be dealed by a waiter

	public static int currentItemCount = 0; // current item count that the restaurant has, initially zero
	public static int currenTableCount = 0; // current table count that the restaurant has, initially zero
	public static int currentEmployerCount = 0; // current employer count that the restaurant has, initially zero
	public static int currentWaiterCount = 0; // current waiter count that the restaurant has, initially zero

	public static void main(String[] args) {

		String command; // for holding the command strings
		String arguments; // for holding the commands' parameters
		Items[] items = new Items[1000]; // a list that holds Items class'es objects in
		Tables[] tables = new Tables[MAX_TABLES]; // a list that holds Tables class'es objects in
		Employer[] employers = new Employer[MAX_EMPLOYER]; // a list that holds Employer class'es objects in
		Waiter[] waiters = new Waiter[MAX_WAITER]; // a list that holds Waiter class'es objects in

		/* Initialization Part of The Program */

		/* reading setup.dat */

		Scanner sc = null;
		try {
			sc = new Scanner(new File("setup.dat")); // for reading input file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc.hasNextLine()) { // reading line by line

			Scanner forSetup = new Scanner(sc.nextLine());

			command = forSetup.next(); // command is changing for each line

			arguments = forSetup.next(); // command arguments

			////////////////// ADD_ITEM \\\\\\\\\\\\\\\\\\\\
			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			if (command.equals("add_item")) {

				Items newItem = new Items(); // create an object from Items class

				String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																				// split the arguments from ";"

				newItem.setItemName(splittedCommandArguments[0]); // first word is new item's name
				newItem.setItemCost(Double.parseDouble(splittedCommandArguments[1])); // second word is new item's cost
				newItem.setStockCount(Integer.parseInt(splittedCommandArguments[2])); // Third word is new item's amount

				items[currentItemCount] = newItem; // put the created object into the items array

				currentItemCount++; // Increment the total number of the restaurant's items

			}

			////////////////// ADD_EMPLOYER \\\\\\\\\\\\\\\\\\\\
			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("add_employer")) { // if command is add_employer

				if (currentEmployerCount < MAX_EMPLOYER) { // if total count of the employers are less than MAX_EMPLOYER
															// number

					Employer newEmployer = new Employer(); // create an object from Employer class

					String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																					// split the arguments from ";"
					newEmployer.setName(splittedCommandArguments[0]); // first word is new employer's name
					newEmployer.setSalary(Double.parseDouble(splittedCommandArguments[1])); // second word is new
																							// employer's salary
					newEmployer.setTableCount(0); // third word is new employer's total table count
					employers[currentEmployerCount] = newEmployer; // put the created object into the employers array

					currentEmployerCount++; // increment the total number of the restaurant's employers
				}

			}

			////////////////// ADD_WAITER \\\\\\\\\\\\\\\\\\\\
			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("add_waiter")) { // if command is add_waiter

				if (currentWaiterCount < MAX_WAITER) { // if total count of the waiters are less than MAX_WAITER number

					Waiter newWaiter = new Waiter(); // create an object from Waiter class

					String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																					// split the arguments from ";"
					newWaiter.setName(splittedCommandArguments[0]); // first word is new waiter's name
					newWaiter.setSalary(Double.parseDouble(splittedCommandArguments[1])); // second word is waiter's
																							// salary
					newWaiter.setOrderCount(0); // third word is waiter's total order count
					newWaiter.setTableCount(0);
					waiters[currentWaiterCount] = newWaiter;
					newWaiter.orderCountForSalary = 0; // put the created object into the waiters array

					currentWaiterCount++; // increment the total number of the restaurant's waiters
				}

			}
			forSetup.close();

		}
		sc.close();

		/* Commands Part of The Program */

		/* reading commands.dat */

		Scanner sc2 = null;
		try {
			sc2 = new Scanner(new File("commands.dat")); // for reading commands input file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (sc2.hasNextLine()) { // reading line by line

			Scanner forCommands = new Scanner(sc2.nextLine());

			command = forCommands.next(); // command is changing for each line

			System.out.println("***********************************\nPROGRESSING COMMAND: " + command);

			////////////////// CREATE_TABLE \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			if (command.equals("create_table")) { // if command is create_table

				arguments = forCommands.next();
				String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																				// split the arguments from ";"
				int employerFound = 0; // checks the searching employer is found or not
				int employerNumber = -1; // holds the searching employer's index in employers array

				for (int i = 0; i < currentEmployerCount; i++) { // searching in employers array to find the employer
																	// that is given

					if (splittedCommandArguments[0].equals(employers[i].getName())) { // if we find the employer
						employerFound = 1;
						employerNumber = i;
						break;
					}

				}

				if (currenTableCount < MAX_TABLES && employerFound == 1 && employerNumber > -1
						&& employers[employerNumber].getTableCount() < ALLOWED_MAX_TABLES) { // if all rules are
																								// followed to create a
																								// table

					Tables newTable = new Tables(); // create an object from Tables class

					newTable.setID(currenTableCount); // set the object's ID
					newTable.setInService(0); // set the object's service information as zero. Because the table is
												// currently opened
					newTable.setCapacity(Integer.parseInt(splittedCommandArguments[1])); // set the object's capacity as
																							// second word of the
																							// splitted arguments
					newTable.setCreator(splittedCommandArguments[0]); // set the object's creator as first word of the
																		// splitted arguments
					newTable.setTotalOrderCount(0); // set the object's total order count as zero. Because the table is
													// currently opened
					tables[currenTableCount] = newTable; // put the created table object into the tables array
					employers[employerNumber].incrementTableCount(); // increment the creator's table count
					currenTableCount++; // increment the total table count

					System.out.println("A new table has successfully been added");
				} else if (employerFound == 0) { // if the creator is not in employers of the restaurant
					System.out.println("There is no employer named " + splittedCommandArguments[0]);

				} else if (currenTableCount >= MAX_TABLES) { // if the table count is bigger than MAX_TABLES

					System.out.println("Not allowed to exceed max. number of tables, MAX_TABLES");
				} else if (employers[employerNumber].getTableCount() >= ALLOWED_MAX_TABLES) { // if the number of tables
																								// creator owns is
																								// greater than
																								// ALLOWED_MAX_TABLES

					System.out.println(splittedCommandArguments[0] + " has already created ALLOWED_MAX_TABLES tables!");

				}

			}

			////////////////// NEW_ORDER \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("new_order")) { // if command is new_order

				arguments = forCommands.next();
				String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																				// split the arguments from ";"
				int tableID = -1; // checks the searching table is found or not if its found then holds the given
									// tables ID
				int waiterNumber = -1; // checks the searching waiter is found or not if its found then holds the given
										// waiter's index in the waiters array

				for (int i = 0; i < currenTableCount; i++) { // finding the tableID

					if (tables[i].getInService() == 0
							&& tables[i].getCapacity() >= Integer.parseInt(splittedCommandArguments[1])) { // if we find
																											// the table

						tableID = i;
						break;
					}
				}

				for (int i = 0; i < currentWaiterCount; i++) { // searching in waiters array to find the waiter that is
																// given

					if (splittedCommandArguments[0].equals(waiters[i].getName())) { // if we find the waiter
						waiterNumber = i;
						break;
					}

				}

				if (tableID == -1) { // if the table is not in tables array
					System.out.println("There is no appropriate table for this order!");
				} else if (waiterNumber == -1) { // if the waiter is not in waiters array
					System.out.println("There is no waiter named " + splittedCommandArguments[0]);

				} else if (waiters[waiterNumber].getOrderCount() >= MAX_TABLE_SERVICES) { // if the waiter's order count
																							// is greater than
																							// MAX_ORDERS

					System.out.println("Not allowed to service max. number of tables, MAX_TABLE_SERVICES");
				}

				else { // if all rules are followed to adding a new order for the first time

					String[] splittedItemArguments = splittedCommandArguments[2].split(":", -2); // splittedItemArguments
																									// elements are
																									// items in new
																									// order

					System.out.println("Table (= ID " + tableID + ") has been taken into service");

					tables[tableID].setWaiter(waiters[waiterNumber].getName()); // set table's waiter
					tables[tableID].setInService(1); // set table's service information as 1. This represents the table
														// is busy
					tables[tableID].createOrderList(); // call createOrderList from Tables class. This method is
														// explained in Tables class
					tables[tableID].orders[0].setTotalItemCount(0); // set table's first order's item count as zero

					for (String anItem : splittedItemArguments) { // loop for the words in command's arguments

						String[] splittedItems = anItem.split("-", -2); // splittedItems' first element is item, second
																		// element is amount of the item

						int itemFound = 0; // checks given item is in items array or not

						for (int i = 0; i < currentItemCount; i++) { // loop in items array

							if (splittedItems[0].equals(items[i].getItemName())) { // if given item is in items array

								for (int j = 0; j < Integer.parseInt(splittedItems[1]); j++) { // loop in item's amount

									if (1 <= items[i].getStockCount()) { // if the item has a sufficient number in stock
										if (tables[tableID].orders[0]
												.getTotalItemCount() == tables[tableID].orders[0].MAX_ITEMS)
											System.out.println(
													"Not allowed to exceed max no. of max item in a single order!");
										else {
											tables[tableID].addfirstOrder(items[i].getItemName(),
													items[i].getItemCost(), items[i].getStockCount()); // call
																										// addFirstOrder
																										// method in
																										// Tables class
											System.out.println("Item " + items[i].getItemName() + " added into order");
											items[i].decrementStockCount(); // decrement the stock count of the ordered
																			// item in items array
										}
									} else if (items[i].getStockCount() == 0) { // if the item's amount is zero (in
																				// items array)
										System.out.println("Sorry! No " + items[i].getItemName() + " in the stock!");

									}

								}
								itemFound = 1;
							}

						}

						if (itemFound == 0) {
							System.out.println("Unknown item " + splittedItems[0]);
						} // if given item is not in items array

					} // loop for items array ends

					waiters[waiterNumber].orderCountForSalary++;
					waiters[waiterNumber].incrementOrderCount(); // increments the table's waiter's order count
					waiters[waiterNumber].incrementTableCount(); // increments the waiter's table count
					tables[tableID].incrementOrderCount(); // increments the table's order count
				}
			}

			////////////////// ADD_ORDER \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("add_order")) {

				arguments = forCommands.next();
				String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																				// split the arguments from ";"
				int waiterNumber = -1; // checks the searching table is found or not if its found then holds the given
										// tables ID
				int tableID = -1; // checks the searching waiter is found or not if its found then holds the given
									// waiter's index in the waiters array

				for (int i = 0; i < currentWaiterCount; i++) { // searching in waiters object array to find the waiter
																// that is given

					if (splittedCommandArguments[0].equals(waiters[i].getName())) { // if we find the waiter
						waiterNumber = i;
						break;
					}

				}

				for (int i = 0; i < currenTableCount; i++) { // finding the tableID

					if (tables[i].getID() == Integer.parseInt(splittedCommandArguments[1])) { // if we find the table

						tableID = i;
					}
				}

				if (waiterNumber == -1) { // if the waiter is not in waiters array
					System.out.println("There is no waiter named " + splittedCommandArguments[0]);

				} else if (tableID == -1) { // if the table is not in tables array
					System.out.println("There is no appropriate table for this order!");
				}

				else if (tables[tableID].getWaiter().equals(splittedCommandArguments[0]) == false
						|| tables[tableID].getInService() == 0) { // if given waiter is not the table's waiter or the
																	// table is not busy

					System.out.println("This table is either not in service now or " + splittedCommandArguments[0]
							+ " cannot be assigned this table!");
				} else if (tables[tableID].getTotalOrderCount() >= tables[tableID].getMAX_ORDERS()) { // if the table's
																										// order count
																										// is greater
																										// than or equal
																										// MAX_ORDER

					System.out.println("Not allowed to exceed max number of orders!");

				} else { // if all rules are followed to adding an order

					String[] splittedItemArguments = splittedCommandArguments[2].split(":", -2); // splittedItemArguments
																									// elements are
																									// items in add
																									// order

					tables[tableID].addOrderList(); // call addOrderList method from Tables class

					for (String anItem : splittedItemArguments) {

						String[] splittedItems = anItem.split("-", -2); // splittedItems first element is item, second
																		// element is amount of the item

						int itemFound = 0; // checks given item is in items array or not

						for (int i = 0; i < currentItemCount; i++) { // loop in items array

							if (splittedItems[0].equals(items[i].getItemName())) { // if given item is in items array

								for (int j = 0; j < Integer.parseInt(splittedItems[1]); j++) { // loop in item's amount

									if (1 <= items[i].getStockCount()) { // if the item has a sufficient number in stock
										if (tables[tableID].orders[tables[tableID].totalOrderCount]
												.getTotalItemCount() == tables[tableID].orders[tables[tableID].totalOrderCount].MAX_ITEMS)
											System.out.println(
													"Not allowed to exceed max no. of max item in a single order!");
										else {
											tables[tableID].addfirstOrder(items[i].getItemName(),
													items[i].getItemCost(), items[i].getStockCount()); // call
																										// addFirstOrder
																										// method in
																										// Tables class
											System.out.println("Item " + items[i].getItemName() + " added into order");
											items[i].decrementStockCount(); // decrement the stock count of the ordered
																			// item in items array
										}
									} else if (items[i].getStockCount() == 0) { // if the item's amount is zero (in
																				// items array)
										System.out.println("Sorry! No " + items[i].getItemName() + " in the stock!");
									}
								}
								itemFound = 1;
							}
						}

						if (itemFound == 0) {
							System.out.println("Unknown item " + splittedItems[0]);
						} // if given item is not in items array

					}
					waiters[waiterNumber].incrementOrderCount(); // increments the table's waiter's order count
					tables[tableID].incrementOrderCount(); // increments the table's order count
					waiters[waiterNumber].orderCountForSalary++;
				}
			}

			////////////////// CHECK_OUT \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("check_out")) {

				arguments = forCommands.next();
				String[] splittedCommandArguments = arguments.split(";", -2); // for getting arguments word by word,
																				// split the arguments from ";"
				int waiterNumber = -1;
				int tableID = -1;

				for (int i = 0; i < currentWaiterCount; i++) { // Searching in waiters object array to find the waiter
																// that is given

					if (splittedCommandArguments[0].equals(waiters[i].getName())) { // if we find the waiter
						waiterNumber = i;
						break;
					}

				}

				for (int i = 0; i < currenTableCount; i++) {

					if (tables[i].getInService() == 1
							&& Integer.parseInt(splittedCommandArguments[1]) == tables[i].getID()) { // if we find the
																										// table

						tableID = i;
					}
				}

				if (waiterNumber == -1) { // if given waiter is not in waiters array

					System.out.println("There is no waiter named " + splittedCommandArguments[0]);

				} else if (tableID == -1 || tables[tableID].getInService() == 0
						|| tables[tableID].getWaiter().equals(splittedCommandArguments[0]) == false) { // if the table
																										// is not found
																										// in tables
																										// array or
																										// given table
																										// is not in
																										// service or
																										// given waiter
																										// is not the
																										// table's
																										// waiter

					System.out.println("This table is either not in service now or " + waiters[waiterNumber].getName()
							+ " cannot be assigned this table!");

				} else { // if all rules are followed to adding an order

					String array[] = new String[1000]; // temporary array that is holds the unique items and the
														// repeating count in the orders.
					int foundIndex = -1; // if coming item is in array then the variable holds the item's index into the
											// array[] else the variable holds -1
					int counter = 0; // for putting the item's name and repeat count into the array[]

					for (int i = 0; i < tables[tableID].totalOrderCount; i++) { // loop for a table's orders
						for (int j = 0; j < tables[tableID].orders[i].getTotalItemCount(); j++) { // loop for the
																									// table's orders'
																									// items
							foundIndex = -1; // for each item the variable is initially -1
							for (int k = 0; k < array.length; k = k + 2) { // loop for the array's length/2. Because the
																			// array holds 2 value for each item. First
																			// index for item's name, second is for
																			// item's repeat count
								if (tables[tableID].orders[i].items[j].itemName.equals(array[k])) { // if the item is in
																									// array[]
									foundIndex = k;
									array[k + 1] = (Integer.parseInt(array[k + 1]) + 1) + "";

								}

							}
							if (foundIndex == -1) { // if the item is not in array[]
								array[counter] = tables[tableID].orders[i].items[j].itemName; // put the item's name
																								// into the array[]
								array[counter + 1] = "1"; // put the item's repeat count into the array[]
								counter = counter + 2;

							}

						}

					}

					double total = 0;
					NumberFormat formatter = new DecimalFormat("#0.000"); // for formatting the output

					for (int l = 0; array[l] != null; l = l + 2) { // for printing the array[] elements

						for (int i = 0; i < currentItemCount; i++) { // for finding the array[] elements in items[]

							if (array[l].equals(items[i].getItemName())) { // find the array[] elements in items[]

								total = total + items[i].getItemCost() * Double.parseDouble(array[l + 1]);
								System.out.println(array[l] + ":\t" + formatter.format(items[i].getItemCost()) + " (x "
										+ array[l + 1] + ") "
										+ formatter.format(items[i].getItemCost() * Double.parseDouble(array[l + 1]))
										+ " $");
							}
						}

					}
					System.out.println("Total:\t" + formatter.format(total) + " $");
					tables[tableID].setInService(0);
					waiters[waiterNumber].orderCount--;
					tables[tableID].totalOrderCount = 0;
				}
			}

			////////////////// STOCK_STATUS \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("stock_status")) {

				for (int i = 0; i < currentItemCount; i++) { // print all of the items array's elements

					System.out.println(items[i].getItemName() + ":\t" + items[i].getStockCount());
				}
			}

			////////////////// GET_TABLE_STATUS \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("get_table_status")) {

				for (int i = 0; i < currenTableCount; i++) { // loop for tables array

					System.out.print("Table " + tables[i].getID() + ": ");
					if (tables[i].getInService() == 0) { // if the table is not in service

						System.out.print("Free\n");
					} else { // if the table is in service
						System.out.print("Reserved (" + tables[i].getWaiter() + ")\n");

					}
				}
			}

			////////////////// GET_ORDER_STATUS \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("get_order_status")) {

				for (int i = 0; i < currenTableCount; i++) { // loop for tables array

					System.out.print("Table: " + tables[i].getID() + "\n");
					if (tables[i].getInService() == 0) { // if the table is not in service
						System.out.print("\t0 order(s)\n");
					} else { // if the table is in service

						System.out.print("\t" + tables[i].getTotalOrderCount() + " order(s)\n");
						for (int j = 0; tables[i].totalOrderCount > j; j++) { // loop for tables array's elements's
																				// order elements

							System.out.print("\t\t" + tables[i].orders[j].getTotalItemCount() + " item(s)\n");

						}
					}
				}
			}

			////////////////// GET_EMPLOYER_SALARY \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("get_employer_salary")) {

				for (int i = 0; i < currentEmployerCount; i++) { // loop for employers array

					int tableCount = 0;
					for (int j = 0; j < currenTableCount; j++) { // loop for tables array

						if (tables[j].getCreator().equals(employers[i].getName())) { // if given employer is a table's
																						// creator

							tableCount++; // increment the employer's tableCount
						}
					}
					System.out.println("Salary for " + employers[i].getName() + ": "
							+ (tableCount * employers[i].getSalary() * 0.1 + employers[i].getSalary()));

				}
			}

			////////////////// GET_WAITER_SALARY \\\\\\\\\\\\\\\\\\\\

			////////////////// COMMAND \\\\\\\\\\\\\\\\\\\\

			else if (command.equals("get_waiter_salary")) {

				for (int i = 0; i < currentWaiterCount; i++) { // loop for waiters array

					System.out.println("Salary for " + waiters[i].getName() + ": "
							+ (waiters[i].orderCountForSalary * waiters[i].getSalary() * 0.05
									+ waiters[i].getSalary()));

				}

			}

			forCommands.close();
		}

		sc2.close();
	}

}
