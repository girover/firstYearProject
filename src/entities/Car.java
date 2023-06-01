package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.factory.EntityFactory;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the Car table in the database.
 *
 * @version 1.0
 * 
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 * 
 */
public class Car extends Entity {

	private int id;
	private static final String BRAND = "Ferrari";
	private String model;
	private int year;
	private String color;
	private int mileage;
	private String transmission;
	private String fuelType;
	private float engineSize;
	private int horsepower;
	private int seats;
	private int doors;
	private int price;
	private String vin;
	private float kmPerLiter;
	private byte sold;
	private String description;

	public Car() {
		setTable("car");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBrand() {
		return BRAND;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public float getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(float engineSize) {
		this.engineSize = engineSize;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public byte getSold() {
		return sold;
	}

	public void setSold(byte sold) {
		this.sold = sold;
	}
	
	public float getKmPerLiter() {
		return kmPerLiter;
	}

	public void setKmPerLiter(float kmPerLiter) {
		this.kmPerLiter = kmPerLiter;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean mapFromResultSet(ResultSet result) {

		try {
			id = result.getInt("id");
			model = result.getString("model");
			year = result.getInt("year");
			color = result.getString("color");
			mileage = result.getInt("mileage");
			transmission = result.getString("transmission");
			fuelType = result.getString("fuelType");
			engineSize = result.getFloat("engineSize");
			horsepower = result.getInt("horsepower");
			seats = result.getInt("seats");
			doors = result.getInt("doors");
			sold = result.getByte("sold");
			vin = result.getString("VIN");
			price = result.getInt("price");
			description = result.getString("description");

			setExist(true);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<String[]> getAsCsvList(){
		
		String[] headers = {"ID","Brand","Model","Year","Color","Transmission","Fuel Type","Engine Size","Km/L","Horsepower","Seats","Doors","Price","VIN","Description"};
		String[] values = {
							Integer.toString(getId())
							,getBrand()
							,getModel()
							,Integer.toString(getYear())
							,getColor()
							,getTransmission()
							,getFuelType()
							,Double.toString(getEngineSize())
							,Double.toString(getKmPerLiter())
							,Double.toString(getHorsepower())
							,Integer.toString(getSeats())
							,Integer.toString(getDoors())
							,Double.toString(getPrice())
							,getVin()
							,getDescription()
						  };
		ArrayList<String[]> data = new ArrayList<>();
		data.add(headers);
		data.add(values);
		
		return data;
	}
	
	private String formatString(String key, Object value) {
		return String.format("%-20s : %-20s\n", key, value);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car:\n");
		sb.append(formatString("ID", getId()));
		sb.append(formatString("brand", getBrand()));
		sb.append(formatString("model", getModel()));
		sb.append(formatString("year", getYear()));
		sb.append(formatString("color", getColor()));
		sb.append(formatString("transmission", getTransmission()));
		sb.append(formatString("fuel type", getFuelType()));
		sb.append(formatString("engine size", getEngineSize()));
		sb.append(formatString("horsepower", getHorsepower()));
		sb.append(formatString("seats", getSeats()));
		sb.append(formatString("doors", getDoors()));
		sb.append(formatString("price", getPrice()));
		sb.append(formatString("VIN", getVin()));
		sb.append(formatString("sold", getSold()));
		sb.append(formatString("description", getDescription()));
		
		return sb.toString();
	}
}
