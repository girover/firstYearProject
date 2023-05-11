USE master;

IF DB_ID('ferrari') IS NOT NULL
  DROP DATABASE ferrari; 

-- INV (invariant): databasen ferrari findes ikke

CREATE DATABASE ferrari;

GO

USE ferrari;

-- Employee Table

CREATE TABLE [employee] (
  id int PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  zipCode nvarchar(6) NOT NULL,
  city nvarchar(50) NOT NULL,
  hireDate datetime NOT NULL,
  department nvarchar(50) NOT NULL CHECK (department IN ('administration', 'sales', 'finance', 'service', 'marketing', 'parts', 'IT')),
  [role] nvarchar(50) NOT NULL CHECK ([role] IN ('admin', 'seller'))
);

INSERT INTO [employee] ([firstName], lastName, email, phone, [address], [zipCode], [city], [hireDate], [department], [role]) VALUES
('Majed', 'Farhan', 'girover.mhf@gmail.com', '12345678', 'Soenderparken 17, 1. th', '7430', 'Ikast', GETDATE(),'IT', 'admin'),
('Employee1', 'last name 1', 'employee1@gmail.com', '87654321', 'gade 16, 1. th', '8000', 'Herning', GETDATE(),'finance', 'seller');

-- SellerApprovalLimit

CREATE TABLE [sellerApprovalLimit]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[employeeID] int FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[maxApprovalLimit] decimal(9,2) NOT NULL
);

INSERT INTO [sellerApprovalLimit] ([employeeID], [maxApprovalLimit]) VALUES
(1000, 500000),
(1000, 400000);


-- User Table

CREATE TABLE [user] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  userName nvarchar(50) NOT NULL UNIQUE,
  [password] nvarchar(50) NOT NULL,
  employeeId int NOT NULL FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE
  --CONSTRAINT FK_User_Employee_EmployeeID FOREIGN KEY (employeeId)
  --      REFERENCES employee (id)
  --      ON DELETE CASCADE
  --      ON UPDATE CASCADE
);

INSERT INTO [user] ([userName], [password], [employeeId]) VALUES 
('majed', '1234', 1000),
('employee1', '1234', 1001);

-- Log Table

CREATE TABLE [log] (
  id INT PRIMARY KEY IDENTITY(1,1),
  logType nvarchar(50) NOT NULL,
  [logMessage] text NOT NULL,
  userID INT DEFAULT 0,
  createdAt datetime NOT NULL DEFAULT GETDATE(),
 -- CONSTRAINT FK_Log_User_userID FOREIGN KEY (userID)
 --       REFERENCES [user] (id)
 --       ON DELETE SET DEFAULT
 --       ON UPDATE CASCADE
);

INSERT INTO [log] (logType, logMessage) VALUES
('information', 'Logged in the system'),
('error', 'Error'),
('warning', 'failed login attempt to the system'),
('debugging', 'RunTimeException');

-- Customer Table

CREATE TABLE [customer] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  CPRHash nvarchar(64) NOT NULL,
  zipCode nvarchar(6) NOT NULL,
  city nvarchar(50) NOT NULL,
  --creditWorthiness nvarchar(1) NOT NULL CHECK (creditWorthiness IN ('A', 'B', 'C', 'D'))
);

INSERT INTO [customer] ([firstName], lastName, email, phone, [address], CPRHash, [zipCode], [city]) VALUES
('Customer1', 'lastName1', 'customer1@gmail.com', '12345678', 'Soenderparken 17, 1. th', 'kjfjshfdwrwer544564646asda', '7430', 'Ikast'),--, 'A'),
('Customer2', 'lastName2', 'customer2@gmail.com', '87654321', 'gade 16, 1. th', 'kjfjs45454wer544564646asda', '8000', 'Herning'),--, 'B'),
('Customer3', 'lastName3', 'customer3@gmail.com', '12345678', 'Soenderparken 17, 1. th', 'kjf454545wrwer544564646asda', '7430', 'Ikast');--, 'A');


-- Car Table
CREATE TABLE car (
  id INT PRIMARY KEY IDENTITY(1000,1),
  brand nvarchar(100) NOT NULL,
  model nvarchar(100) NOT NULL,
  [year] int NOT NULL,
  color nvarchar(50) NOT NULL,
  [mileage] int NOT NULL,
  transmission nvarchar(20) NOT NULL CHECK ([transmission] IN ('manual', 'automatic')),
  fuelType nvarchar(50) NOT NULL CHECK (fuelType IN ('benzin', 'diesel', 'biodiesel', 'hybrid', 'el')),
  engineSize decimal(2,1) NOT NULL,
  kmPerLiter int,
  horsePower int NOT NULL,
  seats int NOT NULL,
  doors int NOT NULL,
  VIN nvarchar(20),
  sold tinyint NOT NULL DEFAULT 0 CHECK(sold IN(0, 1)),
  price decimal(9,2) NOT NULL,
  [description] text
);

INSERT INTO car (brand, model, [year], color, [mileage], transmission, fuelType, engineSize, kmPerLiter, horsePower, seats, doors, VIN, sold, price, [description])
VALUES 
('Ferrari', '488 GTB', 2022, 'Red', 0, 'Automatic', 'benzin', 3.9, 12, 660, 2, 2, 'HFHFHFHDJSK454DDDD', 0, 400000.00, 'New Ferrari 488 GTB for sale.'),
('Ferrari', '488 GTB', 2019, 'Red', 5000, 'automatic', 'benzin', 3.9, 13, 661, 2, 2, 'HFSSSSFHDJSK454DDDD', 0, 300000.00, 'Beautiful car in excellent condition.'),
('Ferrari', '812 Superfast', 2022, 'Red', 5000, 'automatic', 'benzin', 6.5, 14, 789, 2, 2, 'AAAAFHFHDJSK454DDDD', 0, 350000.00, 'Brand new car'),
('Ferrari', 'SF90 Stradale', 2021, 'Yellow', 8000, 'automatic', 'hybrid', 4.0, 10, 986, 2, 2, 'NBBBBFHFHDJSK454DDDD', 0, 500000.00, 'Like-new car'),
('Ferrari', 'Roma', 2021, 'White', 3000, 'automatic', 'benzin', 3.9, 20, 612, 2, 2, 'XXXXCCFHDJSK454DDDD', 0, 250000.00, 'Used car in excellent condition'),
('Ferrari', 'Portofino', 2020, 'Black', 12000, 'automatic', 'benzin', 3.9, 15, 592, 2, 2, 'VVVVVVHFHDJSK454DDDD', 0, 200000, 'Pre-owned car with low mileage'),
('Ferrari', '488 Pista', 2019, 'Blue', 15000, 'manual', 'benzin', 3.9, 16, 711, 2, 2, 'MMMMMMMHDJSK454DDDD', 0, 450000, 'Rare supercar'),
('Ferrari', 'LaFerrari', 2016, 'Red', 20000, 'automatic', 'hybrid', 6.3, 17, 950, 2, 2, 'DDDDDDDJSK454DDDD', 0, 5000000, 'Limited edition hypercar');


-- CarImage Table

Create TABLE [carImage](
	[id] int PRIMARY KEY IDENTITY(1,1),
	[carID] int NOT NULL FOREIGN KEY REFERENCES car(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[image] nvarchar(255) NOT NULL
);

INSERT INTO [carImage] ([carId], [image]) VALUES
(1000, 'ferrari-488-gtb.jpeg'),
(1000, 'ferrari-488-gtb-2.jpeg'),
(1002, 'ferrari-812-Superfast-1.jpeg'),
(1002, 'ferrari-812-Superfast-2.jpeg'),
(1004, 'ferrari-roma-1.jpeg'),
(1004, 'ferrari-roma-2.jpeg');


-- LoanApplication Table

CREATE TABLE [loanApplication]
(
	id int PRIMARY KEY IDENTITY(1000, 1),
	[customerID] int FOREIGN KEY REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[sellerID] int FOREIGN KEY REFERENCES employee(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[carID] int FOREIGN KEY REFERENCES car(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[applicationDate] datetime DEFAULT GETDATE(),
	[loanAmount] int NOT NULL,
	[payment] int NOT NULL,
	[months] int NOT NULL,
	[interestRate] float NOT NULL,
	[monthlyPayment] decimal(6,2),
	[status] nvarchar(100) NOT NULL CHECK ([status] IN ('pending','processing','approved', 'rejected')),
	[note] text
);

--INSERT INTO [loanApplication] (customerID, sellerID, carID, applicationDate, loanAmount, payment, [months], interestRate, monthlyPayment, [status], [note]) VALUES 
--(1000, 1000, 1000, '2023-05-01', 500000, ...... )

-- ApplicationAnswer Table

CREATE TABLE [applicationAnswer]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[applicationID] int,-- FOREIGN KEY REFERENCES loanApplication(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[employeeID] int DEFAULT NULL FOREIGN KEY REFERENCES [employee]([id]) ON DELETE SET NULL ON UPDATE CASCADE,
	[answerDate] datetime DEFAULT GETDATE(),
	[accepted] nvarchar(3) NOT NULL DEFAULT 'no' CHECK(accepted IN ('yes', 'no')),
	[note] text
);

-- Payment Table

--CREATE TABLE [payment]
--(
--	[id] int PRIMARY KEY IDENTITY(1000,1),
--	[applicationID] int FOREIGN KEY REFERENCES loanApplication(id) ON DELETE CASCADE ON UPDATE CASCADE,
--	[employeeID] int int FOREIGN KEY REFERENCES employee(id) ON DELETE SET NULL ON UPDATE CASCADE,
--	[answerDate] datetime DEFAULT GETDATE(),
--	[accepted] nvarchar(3) NOT NULL DEFAULT 'no' CHECK(accepted IN ('yes', 'no')),
--	[note] text
--);


-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- CRUD SECTION
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

select * from [user];
select * from [employee];
select * from [log];