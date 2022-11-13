# AP-Group-Project

# Requirements

Your application should be built using the **Client/Server** architecture. Your Database s**hould be located on the server machine** where the client will make requests to the server over the network. **Clients cannot communicate with the database.** The server will process the corresponding request from the client. Your application should be developed with a **GUI** that will aid the cashier in the check-out process. The server thus needs to be threaded to accommodate multiple client cashiers that will connect to it to interact with the database.

You are not limited to the database fields given below for the database tables. Feel free to add other fields or develop your own database schema.

## Instructions

1. **System Name**: Jan’s Wholesale & Retail Management System (Jans W&RMS)
2. Please stick to **programming conventions** such as proper indentation. Include
comments (one to **briefly describe the function** and any other special/important
lines).
3. **Classes** begin with **capital letters** (ex: Person), **methods** begin with **camelCase** (ex: getName( ))
4. **Variables** should have **meaningful names**. Refrain from using ‘x’ or any other single letter variables. Variables also use camelCase (ex: regStatus)
5. **Logging** should be on the **server side**
    - INFO - General information (E.g.- keep track of users logged in)
    - WARN - Warning for operations
    - ERROR - Errors (Great with exceptions)
    - FATAL - Errors (Requires program termination)
    - Rolling File Appender*
    - Database Appender*
6. Use appropriate exception handling
7. Develop your group solution with the use of a GitHub Repository or its equivalent
to host the project’s source code files, to which each member of the group is
expected to contribute. Proof of members’ contribution will be verified via the
project’s history logs.
8. Your lab tutor is to receive an invitation from each group, to collaborate on their
project, within seven (7) days of this document being distributed.

## Deliverables

- [ ]  Group Report
- [x]  Application User Manuals
- [x]  Server-side Application
- [ ]  Client-side Application

## Assessment

- [ ]  Application Document
    - [x]  User Manuals
    - [x]  UML Classes
    - [x]  Database ER Diagrams
    - [ ]  Comments, Programming Conventions and documentation
- [ ]  Functioning Software Solution
    - [ ]  Graphical User Interface
    - [x]  Client/Server Networking Model
    - [x]  Database Connectivity
    - [ ]  Exception Handling & Logging, User & data input validation
    - [x]  Classes
    - [x]  Interfaces
    - [x]  Inheritance and Polymorphism
    - [ ]  Customer Membership/Record Management (C.R.U.D.)
        - [x]  Member discount correctly applied
    - [ ]  Staff data/record management(C.R.U.D.)
    - [ ]  Inventory management
        - [x]  Management dashboard
        - [ ]  CRUD
        - [x]  Real-time inventory update
    - [x]  Checkout
        - [x]  Invoice generation (printable)
        - [x]  Real-time inventory update
    - [ ]  Sales report
        - [ ]  Pull product data for specific period (Printable)
    - [ ]  45 minutes Grading Presentation – all group members must be present and able to fully present the project. Absence from the grading interview means no grade.

## Customer Table

A database that will store contact details of their customers.

Customers with membership will be given a **ten percent (10%) discount** subtracted from the total before tax (15%) is added.

**Customer Data:**

1. Customer ID – string
2. **First Name – string**
3. **Last Name - string**
4. DOB – date
5. Address – string
6. Telephone – string
7. Email – string
8. Date of Membership – date
9. Date of Membership Expiry - date

### D**epartments**

The 3 departments in the business are:

- Management
- Inventory
- Accounting and Sales – Cashiers also fall within this department

**Department Data**

1. Department Code – string
2. Name - string

## **Staff Database**

3 types of employees

- Manager
- Supervisor
- Line worker

**Staff data:**

1. **ID Number**
2. **First Name**
3. **Last Name**
4. **Phone Number**
5. **Employee Type**
6. **Address – string**
7. Department code - foreign key

## Stock and Inventory

The management wishes to use the system to view the stock of particular products in the
warehouse to determine when to replenish the supply from their suppliers. The system should also aid in the stock-taking process so that the inventory operators may update product information, delete product information, insert new products and their information.

**Inventory Data:**

1. Product Code – string
2. Name – string
3. Short Description – string
4. Long Description – string
5. Items in Stock – integer
6. Unit price - float

## Check Out

The cashiers will use this module to create an invoice and accept payment from the customer.

**Invoice Data**:

1. Invoice Num – integer
2. Billing Date – date
3. Item – string
4. Quantity of item – int
5. Cashier – string
6. Customer – string

<aside>
⚠️ NB. Pay close attention to, many to many relationships and normalization

</aside>

## Sales Report

The accounting personnel should be able to pull the data on a particular product between a particular period. This should aid them in analyzing the customer demand for the product to determine if it should be restocked, or the rate at which that product should be restocked.
