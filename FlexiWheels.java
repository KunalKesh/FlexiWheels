package com.jspiders.map;
import java.util.*;

	// Abstract Class (Abstraction)
	abstract class RentalVehicle {
	    protected String vehicleNumber;
	    protected String model;
	    protected double rentPerDay;
	    protected boolean isRented;

	    public RentalVehicle(String vehicleNumber, String model, double rentPerDay) {
	        this.vehicleNumber = vehicleNumber;
	        this.model = model;
	        this.rentPerDay = rentPerDay;
	        this.isRented = false;
	    }

	    public abstract double calculateRent(int days); // Abstract Method

	    public void rentVehicle() {
	        if (!isRented) {
	            isRented = true;
	            System.out.println("✅ " + model + " ( " + vehicleNumber + " ) has been rented.");
	        } else {
	            System.out.println("❌ This vehicle is already rented.");
	        }
	    }

	    public void returnVehicle() {
	        if (isRented) {
	            isRented = false;
	            System.out.println("✅ " + model + " ( " + vehicleNumber + " ) has been returned.");
	        } else {
	            System.out.println("❌ This vehicle was not rented.");
	        }
	    }

	    public void displayDetails() {
	        System.out.println("\n[Vehicle Details]");
	        System.out.println("Vehicle Number: " + vehicleNumber);
	        System.out.println("Model: " + model);
	        System.out.println("Rent Per Day: ₹" + rentPerDay);
	        System.out.println("Available: " + (!isRented ? "Yes" : "No"));
	    }

	    public boolean isAvailable() {
	        return !isRented;
	    }

	    public String getVehicleNumber() {
	        return vehicleNumber;
	    }
	}

	// Car Class (Inheritance & Polymorphism)
	class Car extends RentalVehicle {
	    private boolean isLuxury;

	    public Car(String vehicleNumber, String model, double rentPerDay, boolean isLuxury) {
	        super(vehicleNumber, model, rentPerDay);
	        this.isLuxury = isLuxury;
	    }

	    @Override
	    public double calculateRent(int days) {
	        double totalRent = days * rentPerDay;
	        if (isLuxury) {
	            totalRent += 500; // Extra charge for luxury cars
	        }
	        return totalRent;
	    }
	}

	// Bike Class (Inheritance & Polymorphism)
	class Bike1 extends RentalVehicle {
	    public Bike1(String vehicleNumber, String model, double rentPerDay) {
	        super(vehicleNumber, model, rentPerDay);
	    }

	    @Override
	    public double calculateRent(int days) {
	        return days * rentPerDay;
	    }
	}

	// Rental System
	class VehicleRentalSystem {
	    private List<RentalVehicle> vehicles = new ArrayList<>();

	    public void addVehicle(RentalVehicle vehicle) {
	        vehicles.add(vehicle);
	    }

	    public void displayAvailableVehicles() {
	        System.out.println("\n🚗 Available Vehicles:");
	        for (RentalVehicle vehicle : vehicles) {
	            if (vehicle.isAvailable()) {
	                vehicle.displayDetails();
	            }
	        }
	    }

	    public void rentVehicle(String vehicleNumber, int days) {
	        for (RentalVehicle vehicle : vehicles) {
	            if (vehicle.getVehicleNumber().equals(vehicleNumber)) {
	                if (vehicle.isAvailable()) {
	                    vehicle.rentVehicle();
	                    System.out.println("💰 Rent Amount: ₹" + vehicle.calculateRent(days));
	                } else {
	                    System.out.println("❌ Vehicle is already rented.");
	                }
	                return;
	            }
	        }
	        System.out.println("❌ Vehicle not found.");
	    }

	    public void returnVehicle(String vehicleNumber) {
	        for (RentalVehicle vehicle : vehicles) {
	            if (vehicle.getVehicleNumber().equals(vehicleNumber)) {
	                vehicle.returnVehicle();
	                return;
	            }
	        }
	        System.out.println("❌ Vehicle not found.");
	    }
	}

	// Main Class
	public class VehicleRental {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        VehicleRentalSystem system = new VehicleRentalSystem();

	        // Adding Vehicles
	        system.addVehicle(new Car("CAR123", "Honda City", 1000, true));
	        system.addVehicle(new Car("CAR456", "Maruti Swift", 800, false));
	        system.addVehicle(new Bike1("BIKE789", "Yamaha R15", 500));
	        system.addVehicle(new Bike1("BIKE101", "Royal Enfield", 700));

	        while (true) {
	            System.out.println("\n===== Vehicle Rental System =====");
	            System.out.println("1️⃣ View Available Vehicles");
	            System.out.println("2️⃣ Rent a Vehicle");
	            System.out.println("3️⃣ Return a Vehicle");
	            System.out.println("4️⃣ Exit");
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    system.displayAvailableVehicles();
	                    break;
	                case 2:
	                    System.out.print("Enter Vehicle Number: ");
	                    String rentVehicleNum = sc.next();
	                    System.out.print("Enter Number of Days: ");
	                    int days = sc.nextInt();
	                    system.rentVehicle(rentVehicleNum, days);
	                    break;
	                case 3:
	                    System.out.print("Enter Vehicle Number to Return: ");
	                    String returnVehicleNum = sc.next();
	                    system.returnVehicle(returnVehicleNum);
	                    break;
	                case 4:
	                    System.out.println("🚗 Exiting... Thank you for using the Vehicle Rental System!");
	                    sc.close();
	                    return;
	                default:
	                    System.out.println("❌ Invalid Choice! Please try again.");
	            }
	        }
	    }
	}


