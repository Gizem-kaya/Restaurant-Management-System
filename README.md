


# The Restaurant Management System in JAVA



  **The restaurant management software employs Tables, Orders, Items, People (as workers) and performs some operations depending on the provided commands. It basically relies on two types of workers to operate: i) employers who only create Tables and ii) waiter who
handles all the stuffs regarding the orders. There are limited number of tables that can only be created by employer and every table has limited number of orders in which limited number of items are stored.**


   * The workers (employers and waiter) handle all the operations throughout the run. Every worker has name, salary, authorization which states permission to create a new table or to take any order. Table creation can only be performed by employer whereas orders can only be operated by waiter. Therefore, every employer should have also information carrying the number of tables that (s)he has created so far. Waiter, however, should carry information stating the number of orders operation by him/her. There are maximum *MAX EMPLOYER* employers and *MAX WAITER* waiters (=5 for both in this study) allowed. Employer should not be allowed to create more than *ALLOWED MAX TABLES* (=2 in this project) tables. In addition, waiter can deal with no more than *MAX TABLE SERVICES* (=3 in this project) tables at any time.
   

  * The number of tables created by employers should be limited to *MAX TABLES* (=5 in this assignment). Every table should have an ID that uniquely identifies itself, capacity that states maximum number of customers it can take. In addition, it has information stating whether it is currently in service or not. The creator as well as the waiter information should also be stored within the table. As stated previously, every table possesses order information and maximum *MAX ORDERS* (=5 in this assignment) orders should be provided. An order simply records every items that are ordered by customer and there should not be more than *MAX ITEMS* (=10 in this assignment) within a single order! Finally, every item should have name, cost, and amount information which states its name, cost, and the stock amount, respectively.


# Initialization

Software should not take any parameter as argument but rather perform first some setup
operations considering a provided input file, named setup.dat. This file always contains
three types of commands which are add item (to add a new item into the restaurant’s stock),
add employer (to add a person as employer), and add waiter (to add a person as waiter).
Below, detailed description of each command is provided with its syntax:


* _add item:_ It takes item name, cost for a single item, and number of items that is
added into the stock as arguments.

```add item [Name];[Cost];[Amount]```


* _add employer:_ It takes employer’s name as well as salary.

```add employer [Name];[Salary]```


* _add waiter:_ It is similar to addition of a new employer and takes two inputs corresponding waiter’s name and salary.

```add waiter [Name];[Salary]```

After it completes initial setup progress, it take another input file, named commands.dat,
comprising of a set of commands each of which yields different production. In the following;
each command, with its syntax, is described in detail.



# Commands


* _create table [EMPLOYER NAME];[CAPACITY]:_ It creates a new table. It should
be initial command so that the remaining commands become meaningful. It takes only
two parameters; one for name that represents employer as creator, and other for the
capacity stating maximum number of customer it can take for service.

**Input:**

```create_table ahmet;4```

**Output:**

```PROGRESSING COMMAND: create_table```\
```A new table has succesfully been added```

In the case i) where non-existing employer attempts to create a new table, ii) where
more than allowed maximum number of tables (MAX TABLES) is being created, or iii)
where given employer attempts to create more than maximum number of tables allowed to create (ALLOWED MAX TABLES), an appropriate message should be displayed
as below.

**Input:**

```create_table elif;2```

**Output:**

```PROGRESSING COMMAND: create_table```\
```There is no employer named elif```

**Output:**

```PROGRESSING COMMAND: create_table```\
```Not allowed to exceed max. number of tables, MAX_TABLES```

**Output:**

```PROGRESSING COMMAND: create_table```\
```elif has already created ALLOWED_MAX_TABLES tables!```


* _new order [WAITER NAME];[#CUSTOMER];[ITEM NAME]-[ORDER COUNT]:_ It states
the initial order and used when a new customer arrives at the restaurant. Therefore a
free and appropriate table should first be allocated with this command. It takes three
parameters. The first represents waiter’s name, the second indicates the number of
customer, the final parameter lists name-number pairs of items.


**Input:**

```new_order kemal;3;Pizza-2:Coke-1```

**Output:**

```PROGRESSING COMMAND: new_order```\
```Table (= ID 0) has been taken into service```\
```Item Pizza added into order```\
```Item Pizza added into order```\
```Item Coke added into order```

**Input:**

```new_order kemal;4;Pizza-8:Coke-1```

**Output:**

```PROGRESSING COMMAND: new_order```\
```Table (= ID 2) has been taken into service```\
```Item Pizza added into order```\
```Item Pizza added into order```\
```Sorry! No Pizza in the stock!```\
```Sorry! No Pizza in the stock!```\
```Sorry! No Pizza in the stock!```\
```Sorry! No Pizza in the stock!```\
```Sorry! No Pizza in the stock!```\
```Sorry! No Pizza in the stock!```\
```Item Coke added into order```

**Output:**

```PROGRESSING COMMAND: new_order```\
```Not allowed to service max. number of tables, MAX_TABLE_SERVICES```

**Output:**

```PROGRESSING COMMAND: new_order```\
```There is no appropriate table for this order!```

**Output:**

```PROGRESSING COMMAND: new_order```\
```There is no waiter named kemal```

**Input:**

```new_order kemal;1;Waffle-1:Tea-1```

**Output:**

```PROGRESSING COMMAND: new_order```\
```Table (= ID 3) has been taken into service```\
```Unknown item Waffle```\
```Item Tea added into order```

* _add order [WAITER NAME];[TABLE ID];[ITEM NAME]-[ORDER COUNT]:_ Similar to new order, it inserts another order into a given table which should already be in service. The same warnings in new order should also be displayed in this command except the one regarding MAX TABLE SERVICES as no new table is currently being taken into service. Special to this command, an appropriate message should be displayed as below in the case where a given table is not currently in service or where a waiter other than the one first operated to this table attempts to operate further orders!

**Input:**

```add_order kemal;0;Water-1:Coffee-1```

**Output:**

```PROGRESSING COMMAND: add_order```\
```Item Water added into order```\
```Item Coffee added into order```

**Output:**

```PROGRESSING COMMAND: add_order```\
```This table is either not in service now or kemal cannot be```\
```assigned this table!```

**Output:**

```PROGRESSING COMMAND: add_order```\
```Not allowed to exceed max number of orders!```


* _check out [WAITER NAME];[TABLE ID]:_ It indicates a case where customers taking service at table TABLE ID want to checkout. As expressed in previous command, only waiter who operated to table with TABLE ID is allowed to carry out this command,
otherwise appropriate message should be displayed.

**Input:**

```check_out kemal;1```

**Output:**

```PROGRESSING COMMAND: check_out```\
```Pizza: 3.000 (x 2) 6.000 $```\
```Coke: 1.500 (x 1) 1.500 $```\
```Water: 0.500 (x 1) 0.500 $```\
```Coffee: 0.750 (x 1) 0.750 $```\
```Donut: 1.250 (x 2) 2.500 $```\
```Tea: 0.200 (x 3) 0.600 $```\
```Total: 11.850 $```

**Output:**

```PROGRESSING COMMAND: check_out```\
```This table is either not in service now or kemal cannot be```\
```assigned this table!```

**Output:**

```There is no waiter named kemal```


* _stock status:_ It simply displays the stock amount of all items.

**Input:**

```stock_status```

**Output:**

```PROGRESSING COMMAND: stock_status```\\
```Pizza: 0```\
```Hamburger: 2```\
```Water: 3```\
```Coke: 4```\
```Coffee: 2```\
```Tea: 5```\
```Donut: 4```\
```Doner: 6```

It is seen from the output that stock status of all items (including ones with 0 stock)
should be displayed in insertion order.



* _get table status:_ It prints out all created tables with its current status (Free, or
Reserved) in an insertion order as below. Note that, waiter’s name should also be
displayed in the case Reserved.

**Input:**

```get_table_status```\
```PROGRESSING COMMAND: get_table_status```\
```Table 0: Free```\
```Table 1: Free```\
```Table 2: Reserved (kemal)```


* _get order status:_ It prints out information regarding table-order-item trio in an
insertion order as below. Note that, table should also be displayed even if it is not
currently in service.

**Input:**

```get_order_status```

**Output:**

```PROGRESSING COMMAND: get_order_status```\
```Table: 0```\
```3 order(s)```\
```3 item(s)```\
```2 item(s)```\
```5 item(s)```\
```Table: 1```\
```0 order(s)```\
```Table: 2```\
```1 order(s)```\
```3 item(s)```\


* _get employer salary:_ In order to improve motivation, employers (as well as waiter)
are awarded (as additional payment to their salary) every time they create a table. Total
award (A) for an employer e should be calculated as follows:

                            Ae = Te × Se × 0.1
                            
where Te and Se represent total number of tables created by employer e and the salary
for employer e, respectively. With this command, net salary (i.e., Se + Ae) should be displayed for each employer in
an insertion order.

**Input:**

```get_employer_salary```

**Output:*

```PROGRESSING COMMAND: get_employer_salary```\
```Salary for ahmet: 2750.0```\
```Salary for zeynep: 3000.0```\
```Salary for kamil: 3000.0```


* _get waiter salary:_ It is very similar to get employer salary but differs only in
calculation, which should now be as follows for a waiter w:

                            Aw = Ow × Sw × 0.05
                            
where Ow is total number of orders operated by waiter w. As in previous command,
net salary (i.e., Sw + Aw) should be displayed for each waiter in an insertion order.


**Input:**

```get_waiter_salary```

**Output:**

```PROGRESSING COMMAND: get_waiter_salary```\
```Salary for kemal: 1440.0```\
```Salary for ayse: 1500.0```\
```Salary for ziya: 1575.0```


