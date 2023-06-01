USE master;

IF DB_ID('ferrari_testing') IS NOT NULL
  DROP DATABASE ferrari_testing; 

-- INV (invariant): databasen ferrari findes ikke

CREATE DATABASE ferrari_testing;

GO

USE ferrari_testing;

-- City Table

CREATE TABLE [city] (
	zipCode int PRIMARY KEY,
	city nvarchar(50) NOT NULL,
	CONSTRAINT City_Unique_ZipCode_City UNIQUE (zipCode, city)
);

-- Employee Table

CREATE TABLE [employee] (
  id int PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  zipCode int NOT NULL,
  hireDate datetime NOT NULL,
  department nvarchar(50) NOT NULL CHECK (department IN ('administration', 'sales', 'finance', 'service', 'marketing', 'parts', 'IT')),
  [role] nvarchar(50) NOT NULL CHECK ([role] IN ('manager', 'seller'))
);

-- SellerApprovalLimit

CREATE TABLE [sellerApprovalLimit]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[employeeID] int FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[maxApprovalLimit] int NOT NULL
);

-- User Table

CREATE TABLE [user] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  userName nvarchar(50) NOT NULL UNIQUE,
  [password] nvarchar(100) NOT NULL,
  employeeId int NOT NULL FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE
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
  zipCode int NOT NULL FOREIGN KEY REFERENCES city(zipCode) ON DELETE NO ACTION ON UPDATE CASCADE
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
  engineSize decimal(4,2) NOT NULL,
  kmPerLiter int,
  horsePower int NOT NULL,
  seats int NOT NULL,
  doors int NOT NULL,
  VIN nvarchar(20) NOT NULL UNIQUE,
  sold tinyint NOT NULL DEFAULT 0 CHECK(sold IN(0, 1)),
  price decimal(10,2) NOT NULL,
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
	[customerID] int FOREIGN KEY REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[sellerID] int FOREIGN KEY REFERENCES employee(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[carID] int FOREIGN KEY REFERENCES car(id) ON DELETE SET NULL ON UPDATE CASCADE,
	[applicationDate] datetime DEFAULT GETDATE(),
	[loanAmount] int NOT NULL,
	[payment] int NOT NULL,
	[months] int NOT NULL,
	[interestRate] float NOT NULL,
	[monthlyPayment] decimal(10,2),
	[status] nvarchar(100) NOT NULL CHECK ([status] IN ('processing','approved', 'rejected')),
	[note] text
);