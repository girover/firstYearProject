USE master;

IF DB_ID('ferrari') IS NOT NULL
  DROP DATABASE ferrari; 

-- INV (invariant): databasen ferrari findes ikke

CREATE DATABASE ferrari;

GO

USE ferrari;

-- City Table

CREATE TABLE [city] (
	zipCode nvarchar(6) PRIMARY KEY,
	city nvarchar(50) NOT NULL,
	CONSTRAINT City_Unique_ZipCode_City UNIQUE (zipCode, city)
);

INSERT INTO city (zipCode, city)
VALUES 
    ('1000', 'København'),
    ('8000', 'Århus'),
    ('5000', 'Odense'),
    ('9000', 'Ålborg'),
    ('6700', 'Esbjerg'),
    ('8900', 'Randers'),
    ('6000', 'Kolding'),
    ('8700', 'Horsens'),
    ('7100', 'Vejle'),
    ('4000', 'Roskilde'),
    ('7400', 'Herning'),
    ('3000', 'Helsingør'),
    ('8600', 'Silkeborg'),
    ('4700', 'Næstved'),
    ('7000', 'Fredericia'),
    ('8800', 'Viborg'),
    ('4600', 'Køge'),
    ('7500', 'Holstebro'),
    ('2630', 'Tåstrup'),
    ('4200', 'Slagelse'),
    ('3400', 'Hillerød'),
    ('6400', 'Sønderberg'),
    ('5700', 'Svenborg'),
    ('9800', 'Hjøeeing'),
    ('2970', 'Hørsholm'),
    ('7430', 'Ikast'),
    ('7330', 'Brande'),
    ('9900', 'Frederikshavn'),
    ('8300', 'Odder'),
    ('9990', 'Skagen'),
    ('3700', 'Rønne'),
    ('6950', 'Rønkøbing'),
    ('6900', 'Skjern');

-- Employee Table

CREATE TABLE [employee] (
  id int PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  zipCode nvarchar(6) NOT NULL,
  hireDate datetime NOT NULL,
  department nvarchar(50) NOT NULL CHECK (department IN ('administration', 'sales', 'finance', 'service', 'marketing', 'parts', 'IT')),
  [role] nvarchar(50) NOT NULL CHECK ([role] IN ('manager', 'seller'))
);

INSERT INTO [employee] ([firstName], [lastName], [email], [phone], [address], [zipCode], [hireDate], [department], [role])
VALUES 
	('Majed Hussein', 'Farhan', 'majed@gmail.com', '12345678', 'Soenderparken 17 1. th', '7430', '2023-01-01', 'IT', 'manager'),
    ('John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St', '1000', '2023-01-01', 'administration', 'seller'),
    ('Jane', 'Smith', 'janesmith@example.com', '9876543210', '456 Elm St', '8000', '2023-02-01', 'sales', 'seller'),
    ('Michael', 'Johnson', 'michaeljohnson@example.com', '5551234567', '789 Oak St', '5000', '2023-03-01', 'finance', 'seller'),
    ('Sarah', 'Williams', 'sarahwilliams@example.com', '1112223333', '321 Pine St', '9000', '2023-04-01', 'service', 'seller'),
    ('Emily', 'Brown', 'emilybrown@example.com', '9998887777', '654 Cedar St', '6700', '2023-05-01', 'marketing', 'seller'),
    ('Daniel', 'Taylor', 'danieltaylor@example.com', '4445556666', '987 Maple St', '8900', '2023-06-01', 'parts', 'seller'),
    ('Olivia', 'Anderson', 'oliviaanderson@example.com', '7778889999', '789 Oak St', '6000', '2023-07-01', 'IT', 'seller'),
    ('James', 'Martinez', 'jamesmartinez@example.com', '2223334444', '123 Elm St', '8700', '2023-08-01', 'administration', 'seller'),
    ('Sophia', 'Garcia', 'sophiagarcia@example.com', '6667778888', '456 Oak St', '7100', '2023-09-01', 'sales', 'seller'),
    ('Benjamin', 'Brown', 'benjaminbrown@example.com', '3334445555', '789 Maple St', '4000', '2023-10-01', 'finance', 'seller');


-- SellerApprovalLimit

CREATE TABLE [sellerApprovalLimit]
(
	[id] int PRIMARY KEY IDENTITY(1000,1),
	[employeeID] int FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE,
	[maxApprovalLimit] int NOT NULL
);

INSERT INTO [sellerApprovalLimit] ([employeeID], [maxApprovalLimit]) VALUES
(1000, 500000),
(1001, 200000),
(1002, 250000),
(1003, 175000),
(1004, 300000),
(1005, 225000),
(1006, 100000),
(1007, 150000),
(1008, 175000),
(1009, 350000),
(1010, 400000);

-- User Table

CREATE TABLE [user] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  userName nvarchar(50) NOT NULL UNIQUE,
  [password] nvarchar(100) NOT NULL,
  employeeId int NOT NULL FOREIGN KEY REFERENCES employee(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO [user] ([userName], [password], [employeeId]) VALUES 
('majed', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1000),
('rasmv', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1001),
('rasmk', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1002),
('shahana', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1003),
('user4', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1004),
('user5', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1005),
('user6', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1006),
('user7', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1007),
('user8', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1008),
('user9', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1009),
('user10', 'jXE0n+zjmq+634jKdquyEh4QwhjSZwGVDd1cIksVdHc=MZ2fhgzsUS7mjfaUnVwyPQ==', 1010);

-- Log Table

CREATE TABLE [log] (
  id INT PRIMARY KEY IDENTITY(1,1),
  logType nvarchar(50) NOT NULL,
  [logMessage] text NOT NULL,
  userID INT DEFAULT 0,
  createdAt datetime NOT NULL DEFAULT GETDATE()
);

INSERT INTO [log] (logType, logMessage) VALUES
('information', 'Logged in the system'),
('error', 'Error'),
('warning', 'failed login attempt to the system'),
('debugging', 'RuntimeException...............');

-- Customer Table

CREATE TABLE [customer] (
  id INT PRIMARY KEY IDENTITY(1000,1),
  firstName nvarchar(50) NOT NULL,
  lastName nvarchar(50) NOT NULL,
  email nvarchar(100) NOT NULL UNIQUE,
  phone nvarchar(20) NOT NULL,
  [address] nvarchar(255) NOT NULL,
  CPRHash nvarchar(100) NOT NULL,
  zipCode nvarchar(6) NOT NULL FOREIGN KEY REFERENCES city(zipCode) ON DELETE NO ACTION ON UPDATE CASCADE
);

INSERT INTO [customer] (firstName, lastName, email, phone, [address], CPRHash, zipCode)
VALUES
    ('Mads', 'Jensen', 'mads.jensen@example.com', '12345678', 'Søndergade 1', 'WXOo+IED8m5JU+EFMbzwZ/KENylV+gsUnaDdiDKEGn0=x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', '1000'),
    ('Lise', 'Nielsen', 'lise.nielsen@example.com', '87654321', 'Hovedvej 2', 'deU6QWtawzCm4hRLoMa4D9yQ6OKr8i1vnd8fRzS2xMo=UjqhjsuJLFH72+KMV+EKkkGeCnPokx5Xi5i6/8z5nN0=', '8000'),
    ('Søren', 'Hansen', 'soren.hansen@example.com', '76543210', 'Gadekær 3', 'B7VuxQEF3YbE9W7hMpoSBZoXnOdcp4kcDfU+sETrn5Y=IXHaFjPoglxOrkIAO5xcA3KQCwQPgaL2KLTrmaN7lTM=', '5000'),
    ('Line', 'Pedersen', 'line.pedersen@example.com', '34567890', 'Skovvej 4', 'jBpQXipAf9C5Q4kVlRAM4nnNB5Oq4Yr6dHgUJMlbSYE=nKLEqoGkCsAupVcuC2xgPxmMclpSMk7xSzeWPgcHWfc=', '9000'),
    ('Henrik', 'Møller', 'henrik.moller@example.com', '09874321', 'Østergade 5', 'nK1W2mNYzu0MD5gu79prcEPvuI1MAPcOud44aoouFhc=pDn5rIWZj7cNkIP03NKetLzHNhS3oaxREMy4zhqCXPw=', '6700'),
    ('Camilla', 'Rasmussen', 'camilla.rasmussen@example.com', '98743210', 'Nørregade 6', 'BhKaqdq5LSc/AYH+qqCYBlZX5+GGtZqPRQ7EhgNqOPo=UcQP9GVaodkYDJeW20Tk0dkaLRfQnOyYHfj4ltAea6Q=', '6000'),
    ('Martin', 'Christensen', 'martin.christensen@example.com', '14567890', 'Vestergade 7', 'DpvSUMoEWA06NEeQMANzAVZmBcrXJQlYw85OgKIH2C4=hdgHUbn8WckzVuDwXmtJ/Z+PFGPv2VuYyzSPeDAyPvA=', '8700'),
    ('Maria', 'Andersen', 'maria.andersen@example.com', '09876541', 'Kirkestræde 8', '9ZIfjg1CaUMoVFS9KA2H1csiE2163yvDI9MJujEdOv8=bh8YpDDJbLxf9bph5AS2Zn/k4vRDFTOaok6NIImM+9s=', '7100'),
    ('Thomas', 'Larsen', 'thomas.larsen@example.com', '98765430', 'Torvet 9', '20j/285pwKXlSBBX9vxt96HZVrxED1cEpykFHYJdLPU=BrizMgVeg5wMRbi9KzfOu3kjCUkfWLdjd8oPy1kgBak=', '4000'),
    ('Louise', 'Mortensen', 'louise.mortensen@example.com', '14567890', 'Gågade 10', 'DRUzFB7JhbFTUV3LBu5FJSaJ/siRk8/MOMTnnqTcWZA=lSNPAntdpaCZKL3l/EMqih5SVVnKsIuDDovf90qduTo=', '7400');


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
  VIN nvarchar(20) NOT NULL,
  sold tinyint NOT NULL DEFAULT 0 CHECK(sold IN(0, 1)),
  price decimal(10,2) NOT NULL,
  [description] text
);

INSERT INTO car (brand, model, [year], color, [mileage], transmission, fuelType, engineSize, kmPerLiter, horsePower, seats, doors, VIN, sold, price, [description])
VALUES 
('Ferrari', '488 GTB', 2022, 'Red', 0, 'Automatic', 'benzin', 3.9, 12, 660, 2, 2, 'HFHFHFHDJSK454DDDD', 0, 700000.00, 'New Ferrari 488 GTB for sale.'),
('Ferrari', '488 GTB', 2019, 'Red', 5000, 'automatic', 'benzin', 3.9, 13, 661, 2, 2, 'HFSSSSFHDJSK454DDDD', 0, 800000.00, 'Beautiful car in excellent condition.'),
('Ferrari', '812 Superfast', 2022, 'Red', 5000, 'automatic', 'benzin', 6.5, 14, 789, 2, 2, 'AAAAFHFHDJSK454DDDD', 0, 900000.00, 'Brand new car'),
('Ferrari', 'SF90 Stradale', 2021, 'Yellow', 8000, 'automatic', 'hybrid', 4.0, 10, 986, 2, 2, 'NBBBBFHFHDJSK454DDDD', 0, 700000.00, 'Like-new car'),
('Ferrari', 'Roma', 2021, 'White', 3000, 'automatic', 'benzin', 3.9, 20, 612, 2, 2, 'XXXXCCFHDJSK454DDDD', 0, 650000.00, 'Used car in excellent condition'),
('Ferrari', 'Portofino', 2020, 'Black', 12000, 'automatic', 'benzin', 3.9, 15, 592, 2, 2, 'VVVVVVHFHDJSK454DDDD', 0, 700000, 'Pre-owned car with low mileage'),
('Ferrari', '488 Pista', 2019, 'Blue', 15000, 'manual', 'benzin', 3.9, 16, 711, 2, 2, 'MMMMMMMHDJSK454DDDD', 0, 850000, 'Rare supercar'),
('Ferrari', 'LaFerrari', 2016, 'Red', 20000, 'automatic', 'hybrid', 6.3, 17, 950, 2, 2, 'DDDDDDDJSK454DDDD', 0, 9000000, 'Limited edition hypercar'),

('Ferrari', 'F8 Tributo', 2020, 'Red', 5000, 'automatic', 'benzin', 3.9, 12, 710, 2, 2, 'AASDA4A3ASD47DAS', 0, 950000.00, 'Powerful sports car'),
('Ferrari', '812 Superfast', 2021, 'Yellow', 3000, 'automatic', 'benzin', 6.5, 8, 800, 2, 2, 'CV4F7ERSADSW3R78', 0, 850000.00, 'Exhilarating performance'),
('Ferrari', 'SF90 Stradale', 2022, 'Black', 1000, 'automatic', 'hybrid', 4.0, 20, 1000, 2, 2, 'SDFWE854WER5FSD', 0, 750000.00, 'Cutting-edge hybrid technology'),
('Ferrari', 'Portofino', 2019, 'White', 8000, 'automatic', 'benzin', 3.9, 10, 600, 2, 2, 'VCSDF45S3DF4WR5DF', 0, 600000.00, 'Elegant and versatile'),
('Ferrari', 'Roma', 2021, 'Blue', 4000, 'automatic', 'benzin', 3.9, 11, 620, 2, 2, 'FSDFSDFSDF4WERSD', 0, 780000.00, 'Sophisticated grand tourer'),
('Ferrari', '488 GTB', 2018, 'Red', 12000, 'automatic', 'benzin', 3.9, 9, 670, 2, 2, 'GFDGDRTER54ERTERT', 0, 920000.00, 'Iconic mid-engine supercar'),
('Ferrari', '488 Spider', 2020, 'Yellow', 6000, 'automatic', 'benzin', 3.9, 9, 670, 2, 2, 'UOIUIO1UO2T4YUR2E', 0, 640000.00, 'Open-top driving experience'),
('Ferrari', '458 Italia', 2016, 'Red', 15000, 'automatic', 'benzin', 4.5, 7, 570, 2, 2, 'ZXCSDWE54W82QWE1', 0, 880000.00, 'Legendary V8 engine'),
('Ferrari', 'California T', 2017, 'Black', 10000, 'automatic', 'benzin', 3.9, 10, 560, 2, 2, 'RTTWW1H1D9WER453', 0, 690000.00, 'Stylish and versatile convertible'),
('Ferrari', 'LaFerrari', 2015, 'Red', 2000, 'automatic', 'hybrid', 6.3, 12, 963, 2, 2, 'R96W1ER64321WER231', 0, 1500000.00, 'Limited edition hybrid hypercar');;


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
	[monthlyPayment] decimal(10,2),
	[status] nvarchar(100) NOT NULL CHECK ([status] IN ('processing','approved', 'rejected')),
	[note] text
);

INSERT INTO [loanApplication] (customerID, sellerID, carID, applicationDate, loanAmount, payment, [months], interestRate, monthlyPayment, [status], [note]) VALUES 
(1000, 1000, 1000, '2023-01-11', 500000, 200000, 120, 5.5, 7000, 'processing', 'notes asdasdasdasd'),
(1001, 1001, 1001, '2023-02-04', 650000, 150000, 120, 5.9, 8000, 'processing', 'notes aljlioejkds'),
(1002, 1002, 1002, '2023-02-07', 750000, 150000, 120, 5.8, 8000, 'processing', 'notes aljlioejkds'),
(1003, 1003, 1003, '2023-03-08', 550000, 150000, 120, 5.6, 8000, 'processing', 'notes aljlioejkds'),
(1004, 1000, 1004, '2023-03-15', 450000, 150000, 120, 6.1, 8000, 'processing', 'notes aljlioejkds'),
(1005, 1001, 1005, '2023-04-20', 550000, 150000, 120, 6.2, 8000, 'processing', 'notes aljlioejkds'),
(1006, 1002, 1006, '2023-04-30', 700000, 150000, 120, 6.3, 8000, 'processing', 'notes aljlioejkds'),
(1007, 1003, 1007, '2023-05-05', 750000, 150000, 120, 6.4, 8000, 'processing', 'notes aljlioejkds'),
(1008, 1004, 1008, '2023-05-15', 800000, 150000, 120, 7.0, 8000, 'processing', 'notes aljlioejkds'),
(1009, 1005, 1009, '2023-05-24', 700000, 150000, 120, 7.1, 8000, 'processing', 'notes aljlioejkds');

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- CRUD SECTION
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


-- Get all cars that have no loan application

SELECT * FROM [car]
WHERE [id] NOT IN (SELECT [carID] FROM [loanApplication] WHERE [status] = 'processing' or [status] = 'approved');

-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++