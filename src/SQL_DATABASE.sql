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
('1000', 'K�benhavn K'),
('1500', 'K�benhavn V'),
('1800', 'Frederiksberg C'),
('2000', 'Frederiksberg'),
('2100', 'Copenhagen �'),
('2200', 'Copenhagen N'),
('2300', 'Copenhagen S'),
('2400', 'Copenhagen NV'),
('2450', 'Copenhagen SV'),
('2500', 'Valby'),
('2600', 'Glostrup'),
('2605', 'Br�ndby'),
('2610', 'R�dovre'),
('2620', 'Albertslund'),
('2625', 'Vallensb�k'),
('2630', 'Taastrup'),
('2635', 'Ish�j'),
('2640', 'Hedehusene'),
('2650', 'Hvidovre'),
('2660', 'Br�ndby Strand'),
('2665', 'Vallensb�k Strand'),
('2670', 'Greve Strand'),
('2680', 'Solr�d Strand'),
('2690', 'Karlslunde'),
('2700', 'Br�nsh�j'),
('2720', 'Vanl�se'),
('2730', 'Herlev'),
('2740', 'Skovlunde'),
('2750', 'Ballerup'),
('2760', 'M�l�v'),
('2765', 'Sm�rum'),
('2770', 'Kastrup'),
('2791', 'Drag�r'),
('2800', 'Kongens Lyngby'),
('2820', 'Gentofte'),
('2830', 'Virum'),
('2840', 'Holte'),
('2850', 'N�rum'),
('2860', 'S�borg'),
('2870', 'Dysseg�rd'),
('2880', 'Bagsv�rd'),
('2900', 'Hellerup'),
('2920', 'Charlottenlund'),
('2930', 'Klampenborg'),
('2942', 'Skodsborg'),
('2950', 'Vedb�k'),
('2960', 'Rungsted Kyst'),
('2970', 'H�rsholm'),
('2980', 'Kokkedal'),
('2990', 'Niv�'),
('3000', 'Helsing�r'),
('3050', 'Humleb�k'),
('3060', 'Esperg�rde'),
('3070', 'Snekkersten'),
('3080', 'Tik�b'),
('3100', 'Hornb�k'),
('3120', 'Dronningm�lle'),
('3140', '�lsg�rde'),
('3150', 'Helleb�k'),
('3200', 'Helsinge'),
('3210', 'Vejby'),
('3220', 'Tisvildeleje'),
('3230', 'Gr�sted'),
('3250', 'Gilleleje'),
('3300', 'Frederiksv�rk'),
('3310', '�lsted'),
('3320', 'Sk�vinge'),
('3330', 'G�rl�se'),
('3360', 'Liseleje'),
('3370', 'Melby, Denmark'),
('3390', 'Hundested'),
('3400', 'Hiller�d'),
('3450', 'Liller�d'),
('3460', 'Birker�d'),
('3480', 'Fredensborg'),
('3490', 'Kvistg�rd'),
('3500', 'V�rl�se'),
('3520', 'Farum'),
('3540', 'Lynge, Allerod'),
('3550', 'Slangerup'),
('3600', 'Frederikssund'),
('3630', 'J�gerspris'),
('3650', '�lstykke'),
('3660', 'Stenl�se, Denmark'),
('3670', 'Veks�'),
('4000', 'Roskilde'),
('4030', 'Tune, Denmark'),
('4040', 'Jyllinge'),
('4050', 'Skibby'),
('4060', 'Kirke S�by'),
('4070', 'Kirke Hyllinge'),
('4100', 'Ringsted'),
('4130', 'Viby Sj�lland'),
('4140', 'Borup, K�ge Municipality'),
('4160', 'Herlufmagle'),
('4171', 'Glums�'),
('4173', 'Fjenneslev'),
('4174', 'Jystrup'),
('4180', 'Sor�'),
('4190', 'Munke Bjergby'),
('4200', 'Slagelse'),
('4220', 'Kors�r'),
('4230', 'Sk�lsk�r'),
('4241', 'Vemmelev'),
('4242', 'Boeslunde'),
('4243', 'Rude'),
('4250', 'Fuglebjerg'),
('4261', 'Dalmose'),
('4262', 'Sandved'),
('4270', 'H�ng'),
('4281', 'G�rlev'),
('4291', 'Ruds Vedby'),
('4293', 'Dianalund'),
('4295', 'Stenlille'),
('4296', 'Nyrup'),
('4300', 'Holb�k'),
('4320', 'Lejre'),
('4330', 'Hvals�'),
('4340', 'T�ll�se'),
('4350', 'Ugerl�se'),
('4360', 'Kirke Eskilstrup'),
('4370', 'Store Merl�se'),
('4390', 'Vipper�d'),
('4400', 'Kalundborg'),
('4420', 'Regstrup'),
('4440', 'M�rk�v'),
('4450', 'Jyderup'),
('4460', 'Snertinge'),
('4470', 'Sveb�lle'),
('4480', 'Store Fuglede'),
('4490', 'Jerslev'),
('4500', 'Nyk�bing Sj�lland'),
('4520', 'Svinninge'),
('4532', 'Gislinge'),
('4534', 'H�rve'),
('4540', 'F�revejle'),
('4550', 'Asn�s'),
('4560', 'Vig, Denmark'),
('4571', 'Grevinge'),
('4572', 'N�rre Asmindrup'),
('4573', 'H�jby'),
('4581', 'R�rvig'),
('4583', 'Sj�llands Odde'),
('4591', 'F�llenslev'),
('4592', 'Sejer�'),
('4593', 'Eskebjerg'),
('4600', 'K�ge'),
('4621', 'Gadstrup'),
('4622', 'Havdrup'),
('4623', 'Lille Skensved'),
('4632', 'Bj�verskov'),
('4640', 'Faxe'),
('4652', 'H�rlev'),
('4653', 'Karise'),
('4654', 'Faxe Ladeplads'),
('4660', 'Store Heddinge'),
('4671', 'Str�by'),
('4672', 'Klippinge'),
('4673', 'R�dvig Stevns'),
('4681', 'Herf�lge'),
('4682', 'Tureby'),
('4683', 'R�nnede'),
('4684', 'Holmegaard'),
('4690', 'Haslev'),
('4700', 'N�stved'),
('4720', 'Pr�st�'),
('4733', 'Tappern�je'),
('4735', 'Mern'),
('4736', 'Karreb�ksminde'),
('4750', 'Lundby, Vordingborg'),
('4760', 'Vordingborg'),
('4771', 'Kalvehave'),
('4772', 'Langeb�k'),
('4773', 'Stensved'),
('4780', 'Stege, Denmark'),
('4791', 'Borre, Denmark'),
('4792', 'Askeby'),
('4793', 'Bog� By'),
('4800', 'Nyk�bing Falster'),
('4840', 'N�rre Alslev'),
('4850', 'Stubbek�bing'),
('4862', 'Guldborg'),
('4863', 'Eskilstrup'),
('4871', 'Horbelev'),
('4872', 'Idestrup'),
('4873', 'V�ggerl�se'),
('4874', 'Gedser'),
('4880', 'Nysted'),
('4891', 'Toreby'),
('4892', 'Kettinge'),
('4894', '�ster Ulslev'),
('4895', 'Errindlev'),
('4900', 'Nakskov'),
('4912', 'Harpelunde'),
('4913', 'Horslunde'),
('4920', 'S�llested'),
('4930', 'Maribo'),
('4941', 'Bandholm'),
('4943', 'Torrig'),
('4944', 'Fej�'),
('4951', 'N�rreballe'),
('4952', 'Stokkemarke'),
('4953', 'Vesterborg'),
('4960', 'Holeby'),
('4970', 'R�dby'),
('4983', 'Dannemare'),
('4990', 'Saksk�bing'),
('3700', 'R�nne'),
('3720', 'Aakirkeby'),
('3730', 'Nex�'),
('3740', 'Svaneke'),
('3751', '�stermarie'),
('3760', 'Gudhjem'),
('3770', 'Allinge'),
('3782', 'Klemensker'),
('3790', 'Hasle, Bornholm'),
('5000', 'Odense C'),
('5200', 'Odense V'),
('5210', 'Odense NV'),
('5220', 'Odense S�'),
('5230', 'Odense M'),
('5240', 'Odense N�'),
('5250', 'Odense SV'),
('5260', 'Odense S'),
('5270', 'Odense N'),
('5290', 'Marslev'),
('5300', 'Kerteminde'),
('5320', 'Agedrup'),
('5330', 'Munkebo'),
('5350', 'Rynkeby'),
('5370', 'Mesinge'),
('5380', 'Dalby'),
('5390', 'Martofte'),
('5400', 'Bogense'),
('5450', 'Otterup'),
('5462', 'Morud'),
('5463', 'Harndrup'),
('5464', 'Brenderup'),
('5466', 'Asperup'),
('5471', 'S�nders�'),
('5474', 'Veflinge'),
('5485', 'Skamby'),
('5491', 'Blommenslyst'),
('5492', 'Vissenbjerg'),
('5500', 'Middelfart'),
('5540', 'Ullerslev'),
('5550', 'Langeskov'),
('5560', 'Aarup'),
('5580', 'N�rre Aaby'),
('5591', 'Gelsted'),
('5600', 'Faaborg'),
('5620', 'Glamsbjerg'),
('5631', 'Ebberup'),
('5642', 'Millinge'),
('5683', 'Haarby'),
('5690', 'Tommerup'),
('5700', 'Svendborg'),
('5750', 'Ringe'),
('5771', 'Stenstrup'),
('5772', 'Kv�rndrup'),
('5792', '�rslev'),
('5800', 'Nyborg'),
('5853', '�rb�k'),
('5854', 'Gislev'),
('5856', 'Ryslinge'),
('5863', 'Ferritslev'),
('5871', 'Fr�rup'),
('5874', 'Hesselager'),
('5881', 'Sk�rup Fyn'),
('5882', 'Vejstrup'),
('5883', 'Oure'),
('5884', 'Gudme'),
('5892', 'Gudbjerg'),
('5900', 'Rudk�bing'),
('5935', 'Bagenkop'),
('5953', 'Tranek�r'),
('5960', 'Marstal'),
('5970', '�r�sk�bing'),
('5985', 'S�by'),
('6000', 'Kolding'),
('6040', 'Egtved'),
('6051', 'Almind'),
('6052', 'Viuf'),
('6064', 'Jordrup'),
('6070', 'Christiansfeld'),
('6091', 'Bjert'),
('6092', 'S�nder Stenderup'),
('6093', 'Sj�lund'),
('6094', 'Hejls'),
('6100', 'Haderslev'),
('6200', 'Aabenraa'),
('6230', 'R�dekro'),
('6240', 'L�gumkloster'),
('6261', 'Bredebro'),
('6270', 'T�nder'),
('6280', 'H�jer'),
('6300', 'Gr�sten'),
('6310', 'Broager'),
('6320', 'Egernsund'),
('6330', 'Padborg'),
('6340', 'Krus�'),
('6360', 'Tinglev'),
('6372', 'Bylderup-Bov'),
('6392', 'Bolderslev'),
('6400', 'S�nderborg'),
('6430', 'Nordborg'),
('6470', 'Sydals'),
('6500', 'Vojens'),
('6510', 'Gram'),
('6520', 'Toftlund'),
('6534', 'Agerskov'),
('6535', 'Branderup'),
('6541', 'Bevtoft'),
('6560', 'Sommersted'),
('6580', 'Vamdrup'),
('6600', 'Vejen'),
('6621', 'Gesten'),
('6622', 'B�kke'),
('6623', 'Vorbasse'),
('6630', 'R�dding'),
('6640', 'Lunderskov'),
('6650', 'Br�rup'),
('6660', 'Lintrup'),
('6670', 'Holsted'),
('6682', 'Hovborg'),
('6683', 'F�vling'),
('6690', 'G�rding'),
('6700', 'Esbjerg'),
('6705', 'Esbjerg �'),
('6710', 'Esbjerg V'),
('6715', 'Esbjerg N'),
('6720', 'Fan�'),
('6740', 'Bramming'),
('6752', 'Glejbjerg'),
('6753', 'Agerb�k'),
('6760', 'Ribe'),
('6771', 'Gredstedbro'),
('6780', 'Sk�rb�k'),
('6792', 'R�m�'),
('6800', 'Varde'),
('6818', '�rre'),
('6823', 'Ansager'),
('6830', 'N�rre Nebel'),
('6840', 'Oksb�l'),
('6851', 'Janderup'),
('6852', 'Billum'),
('6854', 'Henne'),
('6855', 'Outrup'),
('6857', 'Bl�vand'),
('6862', 'Tistrup'),
('6870', '�lgod'),
('6880', 'Tarm'),
('6893', 'Hemmet'),
('6900', 'Skjern'),
('6920', 'Videb�k'),
('6933', 'Kib�k'),
('6940', 'Lem St.'),
('6950', 'Ringk�bing'),
('6960', 'HvideSande'),
('6971', 'Spjald'),
('6973', '�rnh�j'),
('6980', 'Tim'),
('6990', 'Ulfborg'),
('7000', 'Fredericia'),
('7080', 'B�rkop'),
('7100', 'Vejle'),
('7120', 'Vejle �st'),
('7130', 'Juelsminde'),
('7140', 'Stouby'),
('7150', 'Barrit'),
('7160', 'T�rring'),
('7171', 'Uldum'),
('7173', 'Vonge'),
('7182', 'Bredsten'),
('7183', 'Randb�l'),
('7184', 'Vandel'),
('7190', 'Billund'),
('7200', 'Grindsted'),
('7250', 'Hejnsvig'),
('7260', 'S�nder Omme'),
('7270', 'Stakroge'),
('7280', 'S�nderFelding'),
('7300', 'Jelling'),
('7321', 'Gadbjerg'),
('7323', 'Give'),
('7330', 'Brande'),
('7361', 'Ejstrupholm'),
('7362', 'Hampen'),
('7400', 'Herning'),
('7430', 'Ikast'),
('7441', 'Bording'),
('7442', 'Engesvang'),
('7451', 'Sunds'),
('7470', 'Karup'),
('7480', 'Vildbjerg'),
('7490', 'Aulum'),
('7500', 'Holstebro'),
('7540', 'Haderup'),
('7550', 'S�rvad'),
('7560', 'Hjerm'),
('7570', 'Vemb'),
('7600', 'Struer'),
('7620', 'Lemvig'),
('7650', 'B�vlingbjerg'),
('7660', 'B�kmarksbro'),
('7673', 'Harbo�re'),
('7680', 'Thybor�n'),
('7700', 'Thisted'),
('7730', 'Hanstholm'),
('7741', 'Fr�strup'),
('7742', 'Vesl�s'),
('7752', 'Snedsted'),
('7755', 'Bedsted Thy'),
('7760', 'Hurup Thy'),
('7770', 'Vestervig'),
('7790', 'Thyholm'),
('7800', 'Skive'),
('7830', 'Vinderup'),
('7840', 'H�jslev'),
('7850', 'Stoholm'),
('7860', 'Sp�ttrup'),
('7870', 'Roslev'),
('7884', 'Fur'),
('7900', 'Nyk�bing Mors'),
('7950', 'Erslev'),
('7960', 'Karby'),
('7970', 'Redsted Mors'),
('7980', 'Vils'),
('7990', '�ster Assels'),
('8000', '�rhus C'),
('8200', '�rhus N'),
('8210', '�rhus V'),
('8220', 'Brabrand'),
('8230', '�byh�j'),
('8240', 'Risskov'),
('8250', 'Eg�'),
('8260', 'Viby'),
('8270', 'H�jbjerg'),
('8300', 'Odder'),
('8305', 'Sams�'),
('8310', 'Tranbjerg'),
('8320', 'M�rslet'),
('8330', 'Beder'),
('8340', 'Malling'),
('8350', 'Hundslund'),
('8355', 'Solbjerg'),
('8361', 'Hasselager'),
('8362', 'H�rning'),
('8370', 'Hadsten'),
('8380', 'Trige'),
('8381', 'Tilst'),
('8382', 'Hinnerup'),
('8400', 'Ebeltoft'),
('8410', 'R�nde'),
('8420', 'Knebel'),
('8444', 'Balle'),
('8450', 'Hammel'),
('8462', 'Harlev'),
('8464', 'Galten'),
('8471', 'Sabro'),
('8472', 'Sporup'),
('8500', 'Grenaa'),
('8520', 'Lystrup'),
('8530', 'Hjortsh�j'),
('8541', 'Sk�dstrup'),
('8543', 'Hornslet'),
('8544', 'M�rke'),
('8550', 'Ryomg�rd'),
('8560', 'Kolind'),
('8570', 'Trustrup'),
('8581', 'Nimtofte'),
('8585', 'Glesborg'),
('8586', '�rum Djurs'),
('8592', 'Anholt'),
('8600', 'Silkeborg'),
('8620', 'Kjellerup'),
('8632', 'Lemming'),
('8641', 'Sorring'),
('8643', 'Ans By'),
('8653', 'Them'),
('8654', 'Bryrup'),
('8660', 'Skanderborg'),
('8670', 'L�sby'),
('8680', 'Ry'),
('8700', 'Horsens'),
('8721', 'Daug�rd'),
('8722', 'Hedensted'),
('8723', 'L�sning'),
('8732', 'Hovedg�rd'),
('8740', 'Br�dstrup'),
('8751', 'Gedved'),
('8752', '�stbirk'),
('8762', 'Flemming'),
('8763', 'Rask M�lle'),
('8765', 'Klovborg'),
('8766', 'N�rre Snede'),
('8781', 'Stenderup'),
('8783', 'Hornsyld'),
('8800', 'Viborg'),
('8830', 'Tjele'),
('8831', 'L�gstrup'),
('8832', 'Skals'),
('8840', 'R�dk�rsbro'),
('8850', 'Bjerringbro'),
('8860', 'Ulstrup'),
('8870', 'Lang�'),
('8881', 'Thors�'),
('8882', 'F�rvang'),
('8883', 'Gjern'),
('8900', 'Randers C'),
('8920', 'Randers NV'),
('8930', 'Randers N�'),
('8940', 'Randers SV'),
('8960', 'Randers S�'),
('8950', '�rsted'),
('8961', 'Alling�bro'),
('8963', 'Auning'),
('8970', 'Havndal'),
('8981', 'Spentrup'),
('8983', 'Gjerlev'),
('8990', 'F�rup'),
('9000', 'Aalborg'),
('9200', 'Aalborg SV'),
('9210', 'Aalborg S�'),
('9220', 'Aalborg �st'),
('9230', 'Svenstrup'),
('9240', 'Nibe'),
('9260', 'Gistrup'),
('9270', 'Klarup'),
('9280', 'Storvorde'),
('9293', 'Kongerslev'),
('9300', 'S�by'),
('9310', 'Vodskov'),
('9320', 'Hjallerup'),
('9330', 'Dronninglund'),
('9340', 'Asaa'),
('9352', 'Dybvad'),
('9362', 'Gandrup'),
('9370', 'Hals'),
('9380', 'Vestbjerg'),
('9381', 'Sulsted'),
('9382', 'Tylstrup'),
('9400', 'N�rresundby'),
('9430', 'Vadum'),
('9440', 'Aabybro'),
('9460', 'Brovst'),
('9480', 'L�kken-Vr�'),
('9490', 'Pandrup'),
('9492', 'Blokhus'),
('9493', 'Saltum'),
('9500', 'Hobro'),
('9520', 'Sk�rping'),
('9530', 'St�vring'),
('9541', 'Suldrup'),
('9550', 'Mariager'),
('9560', 'Hadsund'),
('9574', 'B�lum'),
('9575', 'Terndrup'),
('9600', 'Aars'),
('9610', 'N�rager'),
('9620', 'Aalestrup'),
('9631', 'Gedsted'),
('9632', 'M�ldrup'),
('9640', 'Fars�'),
('9670', 'L�gst�r'),
('9681', 'Ranum'),
('9690', 'Fjerritslev'),
('9700', 'Br�nderslev'),
('9740', 'Jerslev Jylland'),
('9750', '�stervr�'),
('9760', 'Vr�'),
('9800', 'Hj�rring'),
('9830', 'T�rs'),
('9850', 'Hirtshals'),
('9870', 'Sindal'),
('9881', 'Bindslev'),
('9900', 'Frederikshavn'),
('9940', 'L�s�'),
('9970', 'Strandby'),
('9981', 'Jerup'),
('9982', '�lb�k'),
('9990', 'Skagen');

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
    ('Rasmus', 'V', 'rasmv@example.com', '1234567890', 'islandsgade 15 1. th', '1000', '2023-01-01', 'administration', 'seller'),
    ('Rasmus', 'K', 'rasmk@example.com', '9876543210', 'villavej 23 2. tv', '8000', '2023-02-01', 'sales', 'seller'),
    ('Shahana', 'T', 'shahana@example.com', '5551234567', 'herningsvej 25 3. th', '5000', '2023-03-01', 'finance', 'seller'),
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
    ('Mads', 'Jensen', 'mads.jensen@example.com', '12345678', 'S�ndergade 1', 'WXOo+IED8m5JU+EFMbzwZ/KENylV+gsUnaDdiDKEGn0=x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=', '1000'),
    ('Lise', 'Nielsen', 'lise.nielsen@example.com', '87654321', 'Hovedvej 2', 'deU6QWtawzCm4hRLoMa4D9yQ6OKr8i1vnd8fRzS2xMo=UjqhjsuJLFH72+KMV+EKkkGeCnPokx5Xi5i6/8z5nN0=', '8000'),
    ('S�ren', 'Hansen', 'soren.hansen@example.com', '76543210', 'Gadek�r 3', 'B7VuxQEF3YbE9W7hMpoSBZoXnOdcp4kcDfU+sETrn5Y=IXHaFjPoglxOrkIAO5xcA3KQCwQPgaL2KLTrmaN7lTM=', '5000'),
    ('Line', 'Pedersen', 'line.pedersen@example.com', '34567890', 'Skovvej 4', 'jBpQXipAf9C5Q4kVlRAM4nnNB5Oq4Yr6dHgUJMlbSYE=nKLEqoGkCsAupVcuC2xgPxmMclpSMk7xSzeWPgcHWfc=', '9000'),
    ('Henrik', 'M�ller', 'henrik.moller@example.com', '09874321', '�stergade 5', 'nK1W2mNYzu0MD5gu79prcEPvuI1MAPcOud44aoouFhc=pDn5rIWZj7cNkIP03NKetLzHNhS3oaxREMy4zhqCXPw=', '6700'),
    ('Camilla', 'Rasmussen', 'camilla.rasmussen@example.com', '98743210', 'N�rregade 6', 'BhKaqdq5LSc/AYH+qqCYBlZX5+GGtZqPRQ7EhgNqOPo=UcQP9GVaodkYDJeW20Tk0dkaLRfQnOyYHfj4ltAea6Q=', '6000'),
    ('Martin', 'Christensen', 'martin.christensen@example.com', '14567890', 'Vestergade 7', 'DpvSUMoEWA06NEeQMANzAVZmBcrXJQlYw85OgKIH2C4=hdgHUbn8WckzVuDwXmtJ/Z+PFGPv2VuYyzSPeDAyPvA=', '8700'),
    ('Maria', 'Andersen', 'maria.andersen@example.com', '09876541', 'Kirkestr�de 8', '9ZIfjg1CaUMoVFS9KA2H1csiE2163yvDI9MJujEdOv8=bh8YpDDJbLxf9bph5AS2Zn/k4vRDFTOaok6NIImM+9s=', '7100'),
    ('Thomas', 'Larsen', 'thomas.larsen@example.com', '98765430', 'Torvet 9', '20j/285pwKXlSBBX9vxt96HZVrxED1cEpykFHYJdLPU=BrizMgVeg5wMRbi9KzfOu3kjCUkfWLdjd8oPy1kgBak=', '4000'),
    ('Louise', 'Mortensen', 'louise.mortensen@example.com', '14567890', 'G�gade 10', 'DRUzFB7JhbFTUV3LBu5FJSaJ/siRk8/MOMTnnqTcWZA=lSNPAntdpaCZKL3l/EMqih5SVVnKsIuDDovf90qduTo=', '7400');


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