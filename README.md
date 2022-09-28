# AP-Group-Project

You have been contacted to develop the system for Jan’s Wholesale and Retail. After
meeting with the management and the other employees the requirements reveal the
following modules:
Customer Database
The management wants to have a good rapport with their customers and thus would like
to maintain strong communication. They wish to maintain a database that will store
contact details of their customers. The management allows the general public to shop in
the store but customers with membership will be given a ten percent (10%) discount
subtracted from the total before tax (15%) is added.
Store the following data on the customers:
• Customer ID – string
• Name – string

Page 2 of 6

• DOB – date
• Address – string
• Telephone – string
• Email – string
• Date of Membership – date
• Date of Membership Expiry - date
Staff Database
Management also wants to maintain data on their staff for their own bookkeeping
protocols. There are 3 types of employees, Manager, Supervisor and Line
worker.
The 3 departments in the business are:
• Management
• Inventory
• Accounting and Sales – Cashiers also fall within this department.
Store the following data on the departments:
• Department Code – string
• Name - string
Stock and Inventory
Keeping track of the inventory is very important to the running of the business. The
management wishes to use the system to view the stock of particular products in the
warehouse to determine when to replenish the supply from their suppliers. The system
should also aid in the stock-taking process so that the inventory operators may update
product information, delete product information, insert new products and their
information.
Store the following data on the products:
• Product Code – string
• Name – string

Page 3 of 6

• Short Description – string
• Long Description – string
• Items in Stock – integer
• Unit price - float
Check Out
The cashiers will be using the check-out module to assist customers in making payment.
The cashiers will use this module to create an invoice and accept payment from the
customer. Upon finalizing payment, the items in the inventory should be updated.
Store the following information on invoices:
• Invoice Num – integer
• Billing Date – date
• Item – string
• Quantity of item – int
• Cashier – string
• Customer – string
NB. Pay close attention to, many to many relationships and normalization
Sales Reports
The accounting personnel need to print reports on various aspects of the system. They
should be able to pull the data on a particular product between a particular period. This
should aid them in analyzing the customer demand for the product to determine if it
should be restocked, or the rate at which that product should be restocked.

Requirements
Design a JAVA application that will meet the requirements discussed in the meeting. Your
application should be built using the Client/Server architecture. Your Database
should be located on the server machine where the client will make requests to the

Page 4 of 6
server over the network. Clients cannot communicate with the database. The
server will process the corresponding request from the client. Your application should be
developed with a graphical user interface that will aid the cashier in the check-out
process.
The management has decided to buy the adjacent store to the supermarket to expand. The
expansion requires that the management add more cashiers to check out customers. The
server thus needs to be threaded to accommodate multiple client cashiers that will
connect to it to interact with the database.
Instructions:
1. System Name: Jan’s Wholesale & Retail Management System (Jans W&RMS)
2. Please stick to programming conventions such as proper indentation. Include
comments (one to briefly describe the function and any other special/important
lines).
3. Classes begin with capital letters (ex: Person), methods begin with common
letters followed by initial caps (ex: getName( ))
4. Variables should have meaningful names. Refrain from using ‘x’ or any other
single letter variables. Variables also begin with common letters followed by
initial caps (ex: regStatus)
5. Logging should be on the server side
6. Use appropriate exception handling
7. Develop your group solution with the use of a GitHub Repository or its equivalent
to host the project’s source code files, to which each member of the group is
expected to contribute. Proof of members’ contribution will be verified via the
project’s history logs.
8. Your lab tutor is to receive an invitation from each group, to collaborate on their
project, within seven (7) days of this document being distributed.
