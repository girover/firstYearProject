package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the Car table in the database.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/Car.java">Class Code On Github</a>
 */
public class Car extends Entity {

	private int id;
	private String brand;
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
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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
	public boolean makeFromResultSet(ResultSet result) {

		try {
			id = result.getInt("id");
			brand = result.getString("brand");
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
			price = result.getByte("sold");
			price = result.getInt("price");
			description = result.getString("description");

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car:\n");
		sb.append(String.format("brand : %s\n",getBrand()));
		sb.append(String.format("model : %s\n", getModel()));
		sb.append(String.format("year : %s\n", getYear()));
		sb.append(String.format("color : %s\n", getColor()));
		sb.append(String.format("transmission : %s\n", getTransmission()));
		sb.append(String.format("fuel type : %s\n", getFuelType()));
		sb.append(String.format("engine size : %s\n", getEngineSize()));
		sb.append(String.format("horsepower : %s\n", getHorsepower()));
		sb.append(String.format("seats : %s\n", getSeats()));
		sb.append(String.format("doors : %s\n", getDoors()));
		sb.append(String.format("price : %s\n", getPrice()));
		sb.append(String.format("vin : %s\n", getVin()));
		sb.append(String.format("sold : %s\n", getSold()));
		sb.append(String.format("description : %s\n", getDescription()));
		
		return sb.toString();
	}

}
