package faker;

import java.util.HashMap;

/**
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 *         
 * @see <a href = "https://en.youbianku.com/Denmark">Data are from</a>
 *
 */
public class City extends Provider {

	private static String[] zipCodes = { "1000", "1500", "1800", "2000", "2100", "2200", "2300",
			"2400", "2450", "2500", "2600", "2605", "2610", "2620", "2625", "2630", "2635", "2640", "2650", "2660",
			"2665", "2670", "2680", "2690", "2700", "2720", "2730", "2740", "2750", "2760", "2765", "2770", "2791",
			"2800", "2820", "2830", "2840", "2850", "2860", "2870", "2880", "2900", "2920", "2930", "2942", "2950",
			"2960", "2970", "2980", "2990", "3000", "3050", "3060", "3070", "3080", "3100", "3120", "3140", "3150",
			"3200", "3210", "3220", "3230", "3250", "3300", "3310", "3320", "3330", "3360", "3370", "3390", "3400",
			"3450", "3460", "3480", "3490", "3500", "3520", "3540", "3550", "3600", "3630", "3650", "3660", "3670",
			"3700", "3720", "3730", "3740", "3751", "3760", "3770", "3782", "3790", "4000", "4030", "4040", "4050",
			"4060", "4070", "4100", "4130", "4140", "4160", "4171", "4173", "4174", "4180", "4190", "4200", "4220",
			"4230", "4241", "4242", "4243", "4250", "4261", "4262", "4270", "4281", "4291", "4293", "4295", "4296",
			"4300", "4320", "4330", "4340", "4350", "4360", "4370", "4390", "4400", "4420", "4440", "4450", "4460",
			"4470", "4480", "4490", "4500", "4520", "4532", "4534", "4540", "4550", "4560", "4571", "4572", "4573",
			"4581", "4583", "4591", "4592", "4593", "4600", "4621", "4622", "4623", "4632", "4640", "4652", "4653",
			"4654", "4660", "4671", "4672", "4673", "4681", "4682", "4683", "4684", "4690", "4700", "4720", "4733",
			"4735", "4736", "4750", "4760", "4771", "4772", "4773", "4780", "4791", "4792", "4793", "4800", "4840",
			"4850", "4862", "4863", "4871", "4872", "4873", "4874", "4880", "4891", "4892", "4894", "4895", "4900",
			"4912", "4913", "4920", "4930", "4941", "4943", "4944", "4951", "4952", "4953", "4960", "4970", "4983",
			"4990", "5000", "5200", "5210", "5220", "5230", "5240", "5250", "5260", "5270", "5290", "5300", "5320",
			"5330", "5350", "5370", "5380", "5390", "5400", "5450", "5462", "5463", "5464", "5466", "5471", "5474",
			"5485", "5491", "5492", "5500", "5540", "5550", "5560", "5580", "5591", "5600", "5620", "5631", "5642",
			"5683", "5690", "5700", "5750", "5771", "5772", "5792", "5800", "5853", "5854", "5856", "5863", "5871",
			"5874", "5881", "5882", "5883", "5884", "5892", "5900", "5935", "5953", "5960", "5970", "5985", "6000",
			"6040", "6051", "6052", "6064", "6070", "6091", "6092", "6093", "6094", "6100", "6200", "6230", "6240",
			"6261", "6270", "6280", "6300", "6310", "6320", "6330", "6340", "6360", "6372", "6392", "6400", "6430",
			"6470", "6500", "6510", "6520", "6534", "6535", "6541", "6560", "6580", "6600", "6621", "6622", "6623",
			"6630", "6640", "6650", "6660", "6670", "6682", "6683", "6690", "6700", "6705", "6710", "6715", "6720",
			"6740", "6752", "6753", "6760", "6771", "6780", "6792", "6800", "6818", "6823", "6830", "6840", "6851",
			"6852", "6854", "6855", "6857", "6862", "6870", "6880", "6893", "6900", "6920", "6933", "6940", "6950",
			"6960", "6971", "6973", "6980", "6990", "7000", "7080", "7100", "7120", "7130", "7140", "7150", "7160",
			"7171", "7173", "7182", "7183", "7184", "7190", "7200", "7250", "7260", "7270", "7280", "7300", "7321",
			"7323", "7330", "7361", "7362", "7400", "7430", "7441", "7442", "7451", "7470", "7480", "7490", "7500",
			"7540", "7550", "7560", "7570", "7600", "7620", "7650", "7660", "7673", "7680", "7700", "7730", "7741",
			"7742", "7752", "7755", "7760", "7770", "7790", "7800", "7830", "7840", "7850", "7860", "7870", "7884",
			"7900", "7950", "7960", "7970", "7980", "7990", "8000", "8200", "8210", "8220", "8230", "8240", "8250",
			"8260", "8270", "8300", "8305", "8310", "8320", "8330", "8340", "8350", "8355", "8361", "8362", "8370",
			"8380", "8381", "8382", "8400", "8410", "8420", "8444", "8450", "8462", "8464", "8471", "8472", "8500",
			"8520", "8530", "8541", "8543", "8544", "8550", "8560", "8570", "8581", "8585", "8586", "8592", "8600",
			"8620", "8632", "8641", "8643", "8653", "8654", "8660", "8670", "8680", "8700", "8721", "8722", "8723",
			"8732", "8740", "8751", "8752", "8762", "8763", "8765", "8766", "8781", "8783", "8800", "8830", "8831",
			"8832", "8840", "8850", "8860", "8870", "8881", "8882", "8883", "8900", "8920", "8930", "8940", "8950",
			"8960", "8961", "8963", "8970", "8981", "8983", "8990", "9000", "9200", "9210", "9220", "9230", "9240",
			"9260", "9270", "9280", "9293", "9300", "9310", "9320", "9330", "9340", "9352", "9362", "9370", "9380",
			"9381", "9382", "9400", "9430", "9440", "9460", "9480", "9490", "9492", "9493", "9500", "9520", "9530",
			"9541", "9550", "9560", "9574", "9575", "9600", "9610", "9620", "9631", "9632", "9640", "9670", "9681",
			"9690", "9700", "9740", "9750", "9760", "9800", "9830", "9850", "9870", "9881", "9900", "9940", "9970",
			"9981", "9982", "9990" };

	private static HashMap<String, String> cities = new HashMap<>();
	static {
		cities.put("1000", "København K");
		cities.put("1499", "København K");
		cities.put("1500", "København V");
		cities.put("1799", "København V");
		cities.put("1800", "Frederiksberg C");
		cities.put("1999", "Frederiksberg C");
		cities.put("2000", "Frederiksberg");
		cities.put("2100", "Copenhagen Ø");
		cities.put("2200", "Copenhagen N");
		cities.put("2300", "Copenhagen S");
		cities.put("2400", "Copenhagen NV");
		cities.put("2450", "Copenhagen SV");
		cities.put("2500", "Valby");
		cities.put("2600", "Glostrup");
		cities.put("2605", "Brøndby");
		cities.put("2610", "Rødovre");
		cities.put("2620", "Albertslund");
		cities.put("2625", "Vallensbæk");
		cities.put("2630", "Taastrup");
		cities.put("2635", "Ishøj");
		cities.put("2640", "Hedehusene");
		cities.put("2650", "Hvidovre");
		cities.put("2660", "Brøndby Strand");
		cities.put("2665", "Vallensbæk Strand");
		cities.put("2670", "Greve Strand");
		cities.put("2680", "Solrød Strand");
		cities.put("2690", "Karlslunde");
		cities.put("2700", "Brønshøj");
		cities.put("2720", "Vanløse");
		cities.put("2730", "Herlev");
		cities.put("2740", "Skovlunde");
		cities.put("2750", "Ballerup");
		cities.put("2760", "Måløv");
		cities.put("2765", "Smørum");
		cities.put("2770", "Kastrup");
		cities.put("2791", "Dragør");
		cities.put("2800", "Kongens Lyngby");
		cities.put("2820", "Gentofte");
		cities.put("2830", "Virum");
		cities.put("2840", "Holte");
		cities.put("2850", "Nærum");
		cities.put("2860", "Søborg");
		cities.put("2870", "Dyssegård");
		cities.put("2880", "Bagsværd");
		cities.put("2900", "Hellerup");
		cities.put("2920", "Charlottenlund");
		cities.put("2930", "Klampenborg");
		cities.put("2942", "Skodsborg");
		cities.put("2950", "Vedbæk");
		cities.put("2960", "Rungsted Kyst");
		cities.put("2970", "Hørsholm");
		cities.put("2980", "Kokkedal");
		cities.put("2990", "Nivå");
		cities.put("3000", "Helsingør");
		cities.put("3050", "Humlebæk");
		cities.put("3060", "Espergærde");
		cities.put("3070", "Snekkersten");
		cities.put("3080", "Tikøb");
		cities.put("3100", "Hornbæk");
		cities.put("3120", "Dronningmølle");
		cities.put("3140", "Ålsgårde");
		cities.put("3150", "Hellebæk");
		cities.put("3200", "Helsinge");
		cities.put("3210", "Vejby");
		cities.put("3220", "Tisvildeleje");
		cities.put("3230", "Græsted");
		cities.put("3250", "Gilleleje");
		cities.put("3300", "Frederiksværk");
		cities.put("3310", "Ølsted");
		cities.put("3320", "Skævinge");
		cities.put("3330", "Gørløse");
		cities.put("3360", "Liseleje");
		cities.put("3370", "Melby, Denmark");
		cities.put("3390", "Hundested");
		cities.put("3400", "Hillerød");
		cities.put("3450", "Lillerød");
		cities.put("3460", "Birkerød");
		cities.put("3480", "Fredensborg");
		cities.put("3490", "Kvistgård");
		cities.put("3500", "Værløse");
		cities.put("3520", "Farum");
		cities.put("3540", "Lynge, Allerod");
		cities.put("3550", "Slangerup");
		cities.put("3600", "Frederikssund");
		cities.put("3630", "Jægerspris");
		cities.put("3650", "Ølstykke");
		cities.put("3660", "Stenløse, Denmark");
		cities.put("3670", "Veksø");
		cities.put("4000", "Roskilde");
		cities.put("4030", "Tune, Denmark");
		cities.put("4040", "Jyllinge");
		cities.put("4050", "Skibby");
		cities.put("4060", "Kirke Såby");
		cities.put("4070", "Kirke Hyllinge");
		cities.put("4100", "Ringsted");
		cities.put("4130", "Viby Sjælland");
		cities.put("4140", "Borup, Køge Municipality");
		cities.put("4160", "Herlufmagle");
		cities.put("4171", "Glumsø");
		cities.put("4173", "Fjenneslev");
		cities.put("4174", "Jystrup");
		cities.put("4180", "Sorø");
		cities.put("4190", "Munke Bjergby");
		cities.put("4200", "Slagelse");
		cities.put("4220", "Korsør");
		cities.put("4230", "Skælskør");
		cities.put("4241", "Vemmelev");
		cities.put("4242", "Boeslunde");
		cities.put("4243", "Rude");
		cities.put("4250", "Fuglebjerg");
		cities.put("4261", "Dalmose");
		cities.put("4262", "Sandved");
		cities.put("4270", "Høng");
		cities.put("4281", "Gørlev");
		cities.put("4291", "Ruds Vedby");
		cities.put("4293", "Dianalund");
		cities.put("4295", "Stenlille");
		cities.put("4296", "Nyrup");
		cities.put("4300", "Holbæk");
		cities.put("4320", "Lejre");
		cities.put("4330", "Hvalsø");
		cities.put("4340", "Tølløse");
		cities.put("4350", "Ugerløse");
		cities.put("4360", "Kirke Eskilstrup");
		cities.put("4370", "Store Merløse");
		cities.put("4390", "Vipperød");
		cities.put("4400", "Kalundborg");
		cities.put("4420", "Regstrup");
		cities.put("4440", "Mørkøv");
		cities.put("4450", "Jyderup");
		cities.put("4460", "Snertinge");
		cities.put("4470", "Svebølle");
		cities.put("4480", "Store Fuglede");
		cities.put("4490", "Jerslev");
		cities.put("4500", "Nykøbing Sjælland");
		cities.put("4520", "Svinninge");
		cities.put("4532", "Gislinge");
		cities.put("4534", "Hørve");
		cities.put("4540", "Fårevejle");
		cities.put("4550", "Asnæs");
		cities.put("4560", "Vig, Denmark");
		cities.put("4571", "Grevinge");
		cities.put("4572", "Nørre Asmindrup");
		cities.put("4573", "Højby");
		cities.put("4581", "Rørvig");
		cities.put("4583", "Sjællands Odde");
		cities.put("4591", "Føllenslev");
		cities.put("4592", "Sejerø");
		cities.put("4593", "Eskebjerg");
		cities.put("4600", "Køge");
		cities.put("4621", "Gadstrup");
		cities.put("4622", "Havdrup");
		cities.put("4623", "Lille Skensved");
		cities.put("4632", "Bjæverskov");
		cities.put("4640", "Faxe");
		cities.put("4652", "Hårlev");
		cities.put("4653", "Karise");
		cities.put("4654", "Faxe Ladeplads");
		cities.put("4660", "Store Heddinge");
		cities.put("4671", "Strøby");
		cities.put("4672", "Klippinge");
		cities.put("4673", "Rødvig Stevns");
		cities.put("4681", "Herfølge");
		cities.put("4682", "Tureby");
		cities.put("4683", "Rønnede");
		cities.put("4684", "Holmegaard");
		cities.put("4690", "Haslev");
		cities.put("4700", "Næstved");
		cities.put("4720", "Præstø");
		cities.put("4733", "Tappernøje");
		cities.put("4735", "Mern");
		cities.put("4736", "Karrebæksminde");
		cities.put("4750", "Lundby, Vordingborg");
		cities.put("4760", "Vordingborg");
		cities.put("4771", "Kalvehave");
		cities.put("4772", "Langebæk");
		cities.put("4773", "Stensved");
		cities.put("4780", "Stege, Denmark");
		cities.put("4791", "Borre, Denmark");
		cities.put("4792", "Askeby");
		cities.put("4793", "Bogø By");
		cities.put("4800", "Nykøbing Falster");
		cities.put("4840", "Nørre Alslev");
		cities.put("4850", "Stubbekøbing");
		cities.put("4862", "Guldborg");
		cities.put("4863", "Eskilstrup");
		cities.put("4871", "Horbelev");
		cities.put("4872", "Idestrup");
		cities.put("4873", "Væggerløse");
		cities.put("4874", "Gedser");
		cities.put("4880", "Nysted");
		cities.put("4891", "Toreby");
		cities.put("4892", "Kettinge");
		cities.put("4894", "Øster Ulslev");
		cities.put("4895", "Errindlev");
		cities.put("4900", "Nakskov");
		cities.put("4912", "Harpelunde");
		cities.put("4913", "Horslunde");
		cities.put("4920", "Søllested");
		cities.put("4930", "Maribo");
		cities.put("4941", "Bandholm");
		cities.put("4943", "Torrig");
		cities.put("4944", "Fejø");
		cities.put("4951", "Nørreballe");
		cities.put("4952", "Stokkemarke");
		cities.put("4953", "Vesterborg");
		cities.put("4960", "Holeby");
		cities.put("4970", "Rødby");
		cities.put("4983", "Dannemare");
		cities.put("4990", "Sakskøbing");
		cities.put("3700", "Rønne");
		cities.put("3720", "Aakirkeby");
		cities.put("3730", "Nexø");
		cities.put("3740", "Svaneke");
		cities.put("3751", "Østermarie");
		cities.put("3760", "Gudhjem");
		cities.put("3770", "Allinge");
		cities.put("3782", "Klemensker");
		cities.put("3790", "Hasle, Bornholm");
		cities.put("5000", "Odense C");
		cities.put("5200", "Odense V");
		cities.put("5210", "Odense NV");
		cities.put("5220", "Odense SØ");
		cities.put("5230", "Odense M");
		cities.put("5240", "Odense NØ");
		cities.put("5250", "Odense SV");
		cities.put("5260", "Odense S");
		cities.put("5270", "Odense N");
		cities.put("5290", "Marslev");
		cities.put("5300", "Kerteminde");
		cities.put("5320", "Agedrup");
		cities.put("5330", "Munkebo");
		cities.put("5350", "Rynkeby");
		cities.put("5370", "Mesinge");
		cities.put("5380", "Dalby");
		cities.put("5390", "Martofte");
		cities.put("5400", "Bogense");
		cities.put("5450", "Otterup");
		cities.put("5462", "Morud");
		cities.put("5463", "Harndrup");
		cities.put("5464", "Brenderup");
		cities.put("5466", "Asperup");
		cities.put("5471", "Søndersø");
		cities.put("5474", "Veflinge");
		cities.put("5485", "Skamby");
		cities.put("5491", "Blommenslyst");
		cities.put("5492", "Vissenbjerg");
		cities.put("5500", "Middelfart");
		cities.put("5540", "Ullerslev");
		cities.put("5550", "Langeskov");
		cities.put("5560", "Aarup");
		cities.put("5580", "Nørre Aaby");
		cities.put("5591", "Gelsted");
		cities.put("5600", "Faaborg");
		cities.put("5620", "Glamsbjerg");
		cities.put("5631", "Ebberup");
		cities.put("5642", "Millinge");
		cities.put("5683", "Haarby");
		cities.put("5690", "Tommerup");
		cities.put("5700", "Svendborg");
		cities.put("5750", "Ringe");
		cities.put("5771", "Stenstrup");
		cities.put("5772", "Kværndrup");
		cities.put("5792", "Årslev");
		cities.put("5800", "Nyborg");
		cities.put("5853", "Ørbæk");
		cities.put("5854", "Gislev");
		cities.put("5856", "Ryslinge");
		cities.put("5863", "Ferritslev");
		cities.put("5871", "Frørup");
		cities.put("5874", "Hesselager");
		cities.put("5881", "Skårup Fyn");
		cities.put("5882", "Vejstrup");
		cities.put("5883", "Oure");
		cities.put("5884", "Gudme");
		cities.put("5892", "Gudbjerg");
		cities.put("5900", "Rudkøbing");
		cities.put("5935", "Bagenkop");
		cities.put("5953", "Tranekær");
		cities.put("5960", "Marstal");
		cities.put("5970", "Ærøskøbing");
		cities.put("5985", "Søby");
		cities.put("6000", "Kolding");
		cities.put("6040", "Egtved");
		cities.put("6051", "Almind");
		cities.put("6052", "Viuf");
		cities.put("6064", "Jordrup");
		cities.put("6070", "Christiansfeld");
		cities.put("6091", "Bjert");
		cities.put("6092", "Sønder Stenderup");
		cities.put("6093", "Sjølund");
		cities.put("6094", "Hejls");
		cities.put("6100", "Haderslev");
		cities.put("6200", "Aabenraa");
		cities.put("6230", "Rødekro");
		cities.put("6240", "Løgumkloster");
		cities.put("6261", "Bredebro");
		cities.put("6270", "Tønder");
		cities.put("6280", "Højer");
		cities.put("6300", "Gråsten");
		cities.put("6310", "Broager");
		cities.put("6320", "Egernsund");
		cities.put("6330", "Padborg");
		cities.put("6340", "Kruså");
		cities.put("6360", "Tinglev");
		cities.put("6372", "Bylderup-Bov");
		cities.put("6392", "Bolderslev");
		cities.put("6400", "Sønderborg");
		cities.put("6430", "Nordborg");
		cities.put("6470", "Sydals");
		cities.put("6500", "Vojens");
		cities.put("6510", "Gram");
		cities.put("6520", "Toftlund");
		cities.put("6534", "Agerskov");
		cities.put("6535", "Branderup");
		cities.put("6541", "Bevtoft");
		cities.put("6560", "Sommersted");
		cities.put("6580", "Vamdrup");
		cities.put("6600", "Vejen");
		cities.put("6621", "Gesten");
		cities.put("6622", "Bække");
		cities.put("6623", "Vorbasse");
		cities.put("6630", "Rødding");
		cities.put("6640", "Lunderskov");
		cities.put("6650", "Brørup");
		cities.put("6660", "Lintrup");
		cities.put("6670", "Holsted");
		cities.put("6682", "Hovborg");
		cities.put("6683", "Føvling");
		cities.put("6690", "Gørding");
		cities.put("6700", "Esbjerg");
		cities.put("6705", "Esbjerg Ø");
		cities.put("6710", "Esbjerg V");
		cities.put("6715", "Esbjerg N");
		cities.put("6720", "Fanø");
		cities.put("6740", "Bramming");
		cities.put("6752", "Glejbjerg");
		cities.put("6753", "Agerbæk");
		cities.put("6760", "Ribe");
		cities.put("6771", "Gredstedbro");
		cities.put("6780", "Skærbæk");
		cities.put("6792", "Rømø");
		cities.put("6800", "Varde");
		cities.put("6818", "Årre");
		cities.put("6823", "Ansager");
		cities.put("6830", "Nørre Nebel");
		cities.put("6840", "Oksbøl");
		cities.put("6851", "Janderup");
		cities.put("6852", "Billum");
		cities.put("6854", "Henne");
		cities.put("6855", "Outrup");
		cities.put("6857", "Blåvand");
		cities.put("6862", "Tistrup");
		cities.put("6870", "Ølgod");
		cities.put("6880", "Tarm");
		cities.put("6893", "Hemmet");
		cities.put("6900", "Skjern");
		cities.put("6920", "Videbæk");
		cities.put("6933", "Kibæk");
		cities.put("6940", "Lem St.");
		cities.put("6950", "Ringkøbing");
		cities.put("6960", "HvideSande");
		cities.put("6971", "Spjald");
		cities.put("6973", "Ørnhøj");
		cities.put("6980", "Tim");
		cities.put("6990", "Ulfborg");
		cities.put("7000", "Fredericia");
		cities.put("7080", "Børkop");
		cities.put("7100", "Vejle");
		cities.put("7120", "Vejle Øst");
		cities.put("7130", "Juelsminde");
		cities.put("7140", "Stouby");
		cities.put("7150", "Barrit");
		cities.put("7160", "Tørring");
		cities.put("7171", "Uldum");
		cities.put("7173", "Vonge");
		cities.put("7182", "Bredsten");
		cities.put("7183", "Randbøl");
		cities.put("7184", "Vandel");
		cities.put("7190", "Billund");
		cities.put("7200", "Grindsted");
		cities.put("7250", "Hejnsvig");
		cities.put("7260", "Sønder Omme");
		cities.put("7270", "Stakroge");
		cities.put("7280", "SønderFelding");
		cities.put("7300", "Jelling");
		cities.put("7321", "Gadbjerg");
		cities.put("7323", "Give");
		cities.put("7330", "Brande");
		cities.put("7361", "Ejstrupholm");
		cities.put("7362", "Hampen");
		cities.put("7400", "Herning");
		cities.put("7430", "Ikast");
		cities.put("7441", "Bording");
		cities.put("7442", "Engesvang");
		cities.put("7451", "Sunds");
		cities.put("7470", "Karup");
		cities.put("7480", "Vildbjerg");
		cities.put("7490", "Aulum");
		cities.put("7500", "Holstebro");
		cities.put("7540", "Haderup");
		cities.put("7550", "Sørvad");
		cities.put("7560", "Hjerm");
		cities.put("7570", "Vemb");
		cities.put("7600", "Struer");
		cities.put("7620", "Lemvig");
		cities.put("7650", "Bøvlingbjerg");
		cities.put("7660", "Bækmarksbro");
		cities.put("7673", "Harboøre");
		cities.put("7680", "Thyborøn");
		cities.put("7700", "Thisted");
		cities.put("7730", "Hanstholm");
		cities.put("7741", "Frøstrup");
		cities.put("7742", "Vesløs");
		cities.put("7752", "Snedsted");
		cities.put("7755", "Bedsted Thy");
		cities.put("7760", "Hurup Thy");
		cities.put("7770", "Vestervig");
		cities.put("7790", "Thyholm");
		cities.put("7800", "Skive");
		cities.put("7830", "Vinderup");
		cities.put("7840", "Højslev");
		cities.put("7850", "Stoholm");
		cities.put("7860", "Spøttrup");
		cities.put("7870", "Roslev");
		cities.put("7884", "Fur");
		cities.put("7900", "Nykøbing Mors");
		cities.put("7950", "Erslev");
		cities.put("7960", "Karby");
		cities.put("7970", "Redsted Mors");
		cities.put("7980", "Vils");
		cities.put("7990", "Øster Assels");
		cities.put("8000", "Århus C");
		cities.put("8200", "Århus N");
		cities.put("8210", "Århus V");
		cities.put("8220", "Brabrand");
		cities.put("8230", "Åbyhøj");
		cities.put("8240", "Risskov");
		cities.put("8250", "Egå");
		cities.put("8260", "Viby");
		cities.put("8270", "Højbjerg");
		cities.put("8300", "Odder");
		cities.put("8305", "Samsø");
		cities.put("8310", "Tranbjerg");
		cities.put("8320", "Mårslet");
		cities.put("8330", "Beder");
		cities.put("8340", "Malling");
		cities.put("8350", "Hundslund");
		cities.put("8355", "Solbjerg");
		cities.put("8361", "Hasselager");
		cities.put("8362", "Hørning");
		cities.put("8370", "Hadsten");
		cities.put("8380", "Trige");
		cities.put("8381", "Tilst");
		cities.put("8382", "Hinnerup");
		cities.put("8400", "Ebeltoft");
		cities.put("8410", "Rønde");
		cities.put("8420", "Knebel");
		cities.put("8444", "Balle");
		cities.put("8450", "Hammel");
		cities.put("8462", "Harlev");
		cities.put("8464", "Galten");
		cities.put("8471", "Sabro");
		cities.put("8472", "Sporup");
		cities.put("8500", "Grenaa");
		cities.put("8520", "Lystrup");
		cities.put("8530", "Hjortshøj");
		cities.put("8541", "Skødstrup");
		cities.put("8543", "Hornslet");
		cities.put("8544", "Mørke");
		cities.put("8550", "Ryomgård");
		cities.put("8560", "Kolind");
		cities.put("8570", "Trustrup");
		cities.put("8581", "Nimtofte");
		cities.put("8585", "Glesborg");
		cities.put("8586", "Ørum Djurs");
		cities.put("8592", "Anholt");
		cities.put("8600", "Silkeborg");
		cities.put("8620", "Kjellerup");
		cities.put("8632", "Lemming");
		cities.put("8641", "Sorring");
		cities.put("8643", "Ans By");
		cities.put("8653", "Them");
		cities.put("8654", "Bryrup");
		cities.put("8660", "Skanderborg");
		cities.put("8670", "Låsby");
		cities.put("8680", "Ry");
		cities.put("8700", "Horsens");
		cities.put("8721", "Daugård");
		cities.put("8722", "Hedensted");
		cities.put("8723", "Løsning");
		cities.put("8732", "Hovedgård");
		cities.put("8740", "Brædstrup");
		cities.put("8751", "Gedved");
		cities.put("8752", "Østbirk");
		cities.put("8762", "Flemming");
		cities.put("8763", "Rask Mølle");
		cities.put("8765", "Klovborg");
		cities.put("8766", "Nørre Snede");
		cities.put("8781", "Stenderup");
		cities.put("8783", "Hornsyld");
		cities.put("8800", "Viborg");
		cities.put("8830", "Tjele");
		cities.put("8831", "Løgstrup");
		cities.put("8832", "Skals");
		cities.put("8840", "Rødkærsbro");
		cities.put("8850", "Bjerringbro");
		cities.put("8860", "Ulstrup");
		cities.put("8870", "Langå");
		cities.put("8881", "Thorsø");
		cities.put("8882", "Fårvang");
		cities.put("8883", "Gjern");
		cities.put("8900", "Randers C");
		cities.put("8920", "Randers NV");
		cities.put("8930", "Randers NØ");
		cities.put("8940", "Randers SV");
		cities.put("8960", "Randers SØ");
		cities.put("8950", "Ørsted");
		cities.put("8961", "Allingåbro");
		cities.put("8963", "Auning");
		cities.put("8970", "Havndal");
		cities.put("8981", "Spentrup");
		cities.put("8983", "Gjerlev");
		cities.put("8990", "Fårup");
		cities.put("9000", "Aalborg");
		cities.put("9200", "Aalborg SV");
		cities.put("9210", "Aalborg SØ");
		cities.put("9220", "Aalborg Øst");
		cities.put("9230", "Svenstrup");
		cities.put("9240", "Nibe");
		cities.put("9260", "Gistrup");
		cities.put("9270", "Klarup");
		cities.put("9280", "Storvorde");
		cities.put("9293", "Kongerslev");
		cities.put("9300", "Sæby");
		cities.put("9310", "Vodskov");
		cities.put("9320", "Hjallerup");
		cities.put("9330", "Dronninglund");
		cities.put("9340", "Asaa");
		cities.put("9352", "Dybvad");
		cities.put("9362", "Gandrup");
		cities.put("9370", "Hals");
		cities.put("9380", "Vestbjerg");
		cities.put("9381", "Sulsted");
		cities.put("9382", "Tylstrup");
		cities.put("9400", "Nørresundby");
		cities.put("9430", "Vadum");
		cities.put("9440", "Aabybro");
		cities.put("9460", "Brovst");
		cities.put("9480", "Løkken-Vrå");
		cities.put("9490", "Pandrup");
		cities.put("9492", "Blokhus");
		cities.put("9493", "Saltum");
		cities.put("9500", "Hobro");
		cities.put("9520", "Skørping");
		cities.put("9530", "Støvring");
		cities.put("9541", "Suldrup");
		cities.put("9550", "Mariager");
		cities.put("9560", "Hadsund");
		cities.put("9574", "Bælum");
		cities.put("9575", "Terndrup");
		cities.put("9600", "Aars");
		cities.put("9610", "Nørager");
		cities.put("9620", "Aalestrup");
		cities.put("9631", "Gedsted");
		cities.put("9632", "Møldrup");
		cities.put("9640", "Farsø");
		cities.put("9670", "Løgstør");
		cities.put("9681", "Ranum");
		cities.put("9690", "Fjerritslev");
		cities.put("9700", "Brønderslev");
		cities.put("9740", "Jerslev Jylland");
		cities.put("9750", "Østervrå");
		cities.put("9760", "Vrå");
		cities.put("9800", "Hjørring");
		cities.put("9830", "Tårs");
		cities.put("9850", "Hirtshals");
		cities.put("9870", "Sindal");
		cities.put("9881", "Bindslev");
		cities.put("9900", "Frederikshavn");
		cities.put("9940", "Læsø");
		cities.put("9970", "Strandby");
		cities.put("9981", "Jerup");
		cities.put("9982", "Ålbæk");
		cities.put("9990", "Skagen");
	};

	public static String getCity(String zipCode) {
		return cities.get(zipCode);
	}

	public static String getCity() {
		String zipCode = getRandomElementFromArray(zipCodes);

		return cities.get(zipCode);
	}

	public static String getCityWithZipCode() {

		String zipCode = getRandomElementFromArray(zipCodes);

		String city = cities.get(zipCode);

		return String.format("%s %s", zipCodes, city);
	}

	public static String getZipCode() {
		return getRandomElementFromArray(zipCodes);
	}
}
