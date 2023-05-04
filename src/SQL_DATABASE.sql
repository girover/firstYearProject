USE master;

IF DB_ID('ferrari') IS NOT NULL
  DROP DATABASE ferrari; 

-- INV (invariant): databasen ferrari findes ikke

CREATE DATABASE ferrari;
GO

USE ferrari;

-- Employee Table

CREATE TABLE employee (
  id INT PRIMARY KEY IDENTITY(1000,1),
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone VARCHAR(20) NOT NULL,
  [address] VARCHAR(255) NOT NULL,
  zipCode VARCHAR(6) NOT NULL,
  city VARCHAR(50) NOT NULL,
  hireDate datetime NOT NULL,
  department VARCHAR(50) NOT NULL,
  [role] VARCHAR(50) NOT NULL CHECK ([role] IN ('admin', 'seller', 'finance'))
);

INSERT INTO [employee] ([firstName], lastName, email, phone, [address], [zipCode], [city], [hireDate], [department], [role]) VALUES
('Majed', 'Farhan', 'girover.mhf@gmail.com', '12345678', 'Soenderparken 17, 1. th', '7430', 'Ikast', GETDATE(),'IT', 'admin'),
('Employee1', 'last name 1', 'employee1@gmail.com', '87654321', 'gade 16, 1. th', '8000', 'Herning', GETDATE(),'finance', 'finance'),
('Employee2', 'last name 2', 'employee2@gmail.com', '87654321', 'gade 15, 1. th', '9000', 'Silkeborg', GETDATE(),'sale', 'seller');

-- User Table

CREATE TABLE [user] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  userName VARCHAR(50) NOT NULL UNIQUE,
  [password] VARCHAR(50) NOT NULL,
  employeeId INT NOT NULL,
  CONSTRAINT FK_User_Employee_EmployeeID FOREIGN KEY (employeeId)
        REFERENCES employee (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO [user] ([userName], [password], [employeeId]) VALUES 
('majed', '1234', 1000),
('employee1', '1234', 1001),
('employee2', '1234', 1002);

-- Log Table

CREATE TABLE [log] (
  id INT PRIMARY KEY IDENTITY(1,1),
  logType VARCHAR(50) NOT NULL,
  [logMessage] text NOT NULL,
  userID INT DEFAULT 0,
  createdAt datetime NOT NULL DEFAULT GETDATE(),
 -- CONSTRAINT FK_Log_User_userID FOREIGN KEY (userID)
 --       REFERENCES [user] (id)
 --       ON DELETE SET DEFAULT
 --       ON UPDATE CASCADE
);

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- CRUD SECTION
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

select * from [user];
select * from [employee];
select * from [log];