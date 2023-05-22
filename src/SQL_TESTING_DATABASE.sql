USE master;

IF DB_ID('ferrari_testing') IS NOT NULL
  DROP DATABASE ferrari_testing; 

-- INV (invariant): databasen ferrari findes ikke

CREATE DATABASE ferrari_testing;

GO

USE ferrari_testing;

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

-- SellerApprovalLimit

CREATE TABLE [sellerApprovalLimit]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[employeeID] int,-- FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[maxApprovalLimit] decimal(9,2) NOT NULL,
	CONSTRAINT FK_SellerApprovalLimit_EmployeeID_Employee_ID FOREIGN KEY (employeeID)
		REFERENCES employee(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

-- User Table

CREATE TABLE [user] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  userName nvarchar(50) NOT NULL UNIQUE,
  [password] nvarchar(50) NOT NULL,
  employeeID int NOT NULL,-- FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE
  CONSTRAINT FK_User_EmployeeID_Employee_ID FOREIGN KEY (employeeID)
        REFERENCES employee(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Log Table

CREATE TABLE [log] (
  id INT PRIMARY KEY IDENTITY(1,1),
  logType nvarchar(50) NOT NULL,
  [logMessage] text NOT NULL,
  userID INT DEFAULT 0,
  createdAt datetime NOT NULL DEFAULT GETDATE()
);

-- Customer Table

CREATE TABLE [customer] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  CPRHash nvarchar(100) NOT NULL,
  zipCode nvarchar(6) NOT NULL,
  city nvarchar(50) NOT NULL,
  --creditWorthiness nvarchar(1) NOT NULL CHECK (creditWorthiness IN ('A', 'B', 'C', 'D'))
);

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
  engineSize decimal(3,2) NOT NULL,
  kmPerLiter int,
  horsePower int NOT NULL,
  seats int NOT NULL,
  doors int NOT NULL,
  VIN nvarchar(20) NOT NULL,
  sold tinyint NOT NULL DEFAULT 0 CHECK(sold IN(0, 1)),
  price decimal(11,2) NOT NULL,
  [description] text
);

-- CarImage Table

Create TABLE [carImage](
	[id] int PRIMARY KEY IDENTITY(1,1),
	[carID] int NOT NULL FOREIGN KEY REFERENCES car(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[image] nvarchar(255) NOT NULL
);

-- LoanApplication Table

CREATE TABLE [loanApplication]
(
	id int PRIMARY KEY IDENTITY(1000, 1),
	[customerID] int, -- FOREIGN KEY REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[sellerID] int, -- FOREIGN KEY REFERENCES employee(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[carID] int, -- FOREIGN KEY REFERENCES car(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[applicationDate] datetime DEFAULT GETDATE(),
	[loanAmount] int NOT NULL,
	[payment] int NOT NULL,
	[months] int NOT NULL,
	[interestRate] float NOT NULL,
	[monthlyPayment] decimal(12,2),
	[status] nvarchar(100) NOT NULL CHECK ([status] IN ('processing','approved', 'rejected')),
	[note] text,
	CONSTRAINT FK_LoanApplication_CustomerID_Customer_ID FOREIGN KEY (customerID)
        REFERENCES customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FK_LoanApplication_SellerID_Employee_ID FOREIGN KEY (sellerID)
        REFERENCES employee(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
	CONSTRAINT FK_LoanApplication_CarID_Car_ID FOREIGN KEY (carID)
        REFERENCES car(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- ApplicationAnswer Table

CREATE TABLE [applicationAnswer]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[applicationID] int,-- FOREIGN KEY REFERENCES loanApplication(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[employeeID] int DEFAULT NULL, -- FOREIGN KEY REFERENCES [employee]([id]) ON DELETE SET NULL ON UPDATE CASCADE,
	[answerDate] datetime DEFAULT GETDATE(),
	[accepted] nvarchar(3) NOT NULL DEFAULT 'no' CHECK(accepted IN ('yes', 'no')),
	[note] text,
	CONSTRAINT FK_ApplicationAnswer_EmployeeID_Employee_ID FOREIGN KEY (employeeID)
        REFERENCES employee(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);