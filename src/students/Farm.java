package students;
import java.util.Scanner;
import students.seasons.*;
import students.weather.*;

import java.util.Random;
import students.fertilizeSystem.Fertilizer;
import students.items.*;

public class Farm {
	
	private Field field;
	private int balance;
	private Scanner scanner;
	
	private Season currentSeason;
	private Weather currentWeather;
	private int turnCounter = 0;
	
	private Greenhouse greenhouse; // add greenhouse instance
	
	// Constructor initializes the field, balance, season, weather and scanner 
	public Farm(int fieldWidth, int fieldHeight, int startingFunds){
		this.field = new Field(fieldWidth, fieldHeight);
		this.balance = startingFunds;
		this.scanner = new Scanner(System.in);
		this.currentSeason = Season.SPRING;
		this.currentWeather = Weather.SUNNY;
		this.greenhouse = new Greenhouse(); // Initialize greenhouse
	}
	
	// main game loop
	public void run(){
		boolean running = true;
		while (running) {
			displayFarm(); // display the current farm state
			System.out.println("--------------------");
			System.out.println("Enter your next action: \n"
					+ " t x y: till \n"
					+ " h x y: harvest \n"
					+ " p x y: plant \n"
					+ " f x y: fertilize \n"
					+ "b x y: build greenhouse \n" // option to build greenHouse
					+ " s: field summary \n"
					+ " w: skip the user's turn with age progressing \n"
					+ " q: quit");
			System.out.println("--------------------");
			String input = scanner.nextLine().trim();
			// Quit the game
			if (input.equals("q")) 
			{
				running = false;
			}
			// Display a summary of the field 
			else if (input.equals("s")) 
			{
				System.out.println(field.getSummary());
			}
			// Display the value of the field
			else if (input.equals("w")) 
			{
				field.tick(currentSeason, greenhouse);
			}
			// Check for tilling, planting, and harvesting actions
			else if (input.matches("[tphfb] \\d+ \\d+")) 
			{
				handleAction(input);
			}
			else 
			{
				System.out.println("Invalid input. Please try again.");
			}
			
			turnCounter++;
			if(turnCounter % 5 == 0) {
				handleSeasonChange();
			}
			if (turnCounter % 2 == 0) {
				handleWeatherEffects();
			}
		}
		
		System.out.println("Quit!" + " \n");
	}
	
	// Displays the current field state and balance 
	private void displayFarm() {
		System.out.println(field.toString());
		System.out.println("--------------------");
		for(int x = 0; x < field.getHeight(); x++) {
			for(int y = 0; y < field.getWidth(); y++) {
				Item item = field.get(x, y);
				if (item != null && !(item instanceof Soil) && !(item instanceof Weed) && !(item instanceof UntilledSoil) ) {
	                System.out.println(item.getClass().getSimpleName() +" Age: " + item.getAge());
	            }
			}
		}
		System.out.println("Bank balance: $" + balance);
		System.out.println("Current Season: " + currentSeason);
		System.out.println("Current Weather: " + currentWeather);
	}
	
	// handles user actions such as tilling, harvesting, planting, fertilize
	private void handleAction(String input) {
		String[] parts = input.split(" ");
		char action = parts[0].charAt(0);
		int x = Integer.parseInt(parts[1]) - 1;
		int y = Integer.parseInt(parts[2]) - 1;
		
		// validate coordinates 
		if (!field.isValidPosition(x, y)) {
			System.out.println("Invalid coordinates. Try again.");
			return;
		}
		
		// perform the corresponding action 
		switch (action) {
		case 't': // till the land 
			field.till(x, y);
			break;
		case 'h': // harvest crops 
			balance += field.harvest(x,y);
			break;
		case 'p': // Plant crops
			handlePlanting(x,y, currentSeason, greenhouse);
			break;
		case 'f': // fertilize crops
			fertilizeCrops(x,y);
			break;
		case 'b': // Build GreenHouse
			handleBuildGreenhouse(x,y);
			break;	
		}
		field.getValue(); // Display updated field value
	}
	
	// handles planting logic, including checking balance and selecting crops 
	private void handlePlanting (int x, int y, Season currentSeason,Greenhouse greenhouse) {
		// If the greenhouse is not built, or if it is built and the user is not planting at that position
		if (greenhouse.isBuilt() && !(x == greenhouse.getPositionX() && y == greenhouse.getPositionY())) {
			System.out.println("You can only plant in the greenhouse area!	");
			return;
		}
		if (!field.canPlant(x,y, currentSeason, greenhouse) && !(greenhouse.isBuilt() && currentSeason == Season.WINTER)) {
			System.out.println("Can not plant here. Either it's winter or this spot is unavailable.");
			return;
		}
		System.out.println("--------------------");
		// Display planting options 
		System.out.println("Enter:");
		System.out.println(" - 'a' to buy an apple for 2$");
		System.out.println("- 'g' to buy grain for 1$");
		System.out.println(" - 'c' to buy carrot for 2$");
		System.out.println("- 'b' to buy broccoli for 2$");
		System.out.println("- 'k' to buy kiwi for 4$");
		System.out.println("- 'o' to buy orange for 3$");
		System.out.println("- 't' to buy tomato for 2$");
		System.out.println("--------------------");
		String choice = scanner.nextLine().trim();
		// handle planting based on the user's choice 
		switch (choice) {
		// Plant Apple
			case "a": 
				if (balance >= 2) {
					field.plant(x, y, new Apples(), currentSeason, greenhouse); 
					balance -= 2; // deduct money
					System.out.println("You planted an apple!");
			} else {
					System.out.println("You don't have enough money to plant apples.");
			}
				break;
		// Plant Grain
			case "g":
					if (balance >= 1) {
					field.plant(x, y, new Grain(), currentSeason, greenhouse);
					balance -= 1;
					System.out.println("You planted Grain!");
			}else {
					System.out.println("You don't have enough money to plant grain.");
			}
				break;
		// Plant Carrot
			case "c":
				if(balance >= 2) {
					field.plant(x, y, new Carrot(), currentSeason, greenhouse);
					balance -= 2;
					System.out.println("You planted Carrot!");
			}else {
					System.out.println("You don't have enough money to plant carrot!");
			}
				break;
		// Plant Broccoli
			case "b":
					if (balance >= 2) {
					field.plant(x, y, new Broccoli(), currentSeason, greenhouse);	
					balance -= 2;
					System.out.println("You planted Broccoli!");
			}else {
					System.out.println("You don't have enough money to plant broccoli!");
			}
				break;
		// Plant Kiwi
			case "k":
					if(balance >= 4) {
					field.plant(x, y, new Kiwi(), currentSeason, greenhouse);
					balance -= 4;
					System.out.println("You planted Kiwi!");
			}else {
					System.out.println("You don't have enough money to plant kiwi!");
			}
				break;
		// plant Orange
			case "o":
					if(balance >= 3) {
					field.plant(x, y, new Orange(), currentSeason, greenhouse);
					balance -= 3;
					System.out.println("You planted Orange!");			
			}else {
					System.out.println("You don't have enough money to plant orange!");
			}
				break;
		// Plant Tomato
			case "t":
					if(balance >= 2) {
					field.plant(x, y, new Tomato(), currentSeason, greenhouse);
					balance -= 2;
					System.out.println("You planted Tomato!");
			}else {
					System.out.println("You don't have enough money to plant tomato!");
			}
				break;
			default:
					System.out.println("Invalid choice. Please choose a valid option.");
				break;
		}
	}
	// handle fertilizing 
	private void fertilizeCrops(int x, int y) {
		if (!field.isValidPosition(x, y)) {
			System.out.println("Invalid position for fertilizing");
			return;
		}
		
		Item item = field.get(x, y);
		if(item != null && !(item instanceof Soil) && !(item instanceof Weed) && !(item instanceof UntilledSoil)) {
			if(balance >= 1) {
				balance -= 1; // deduct money after fertilize crops
				Fertilizer fertilizer = new Fertilizer(2); // adjust the boost value here
				fertilizer.apply(item); // apply the fertilizer to the crops
				String itemName = item.getClass().getSimpleName();
				if(item.died()) { // if it fertilize and beyond the death age it will turn into untilledSoil 
					field.set(x, y, new UntilledSoil());
					System.out.println("Item at position (" + (x+1) + ", " + (y+1) + ") died and turned into UntilledSoil.");
				}else {
					System.out.println("Crops at position (" + (x+1) + "," + (y+1) + ") fertilized!");
					System.out.println(itemName + " ( Age: " + item.getAge() + ", Fertilized: " + (item.isFertilized() ? "Yes" : "No") + ")");
				}
			}else {
				System.out.println("You don't have enough money to fertilized!");
				}
		}else {
			System.out.println("No crops at position (" + (x+1) + "," + (y+1) + ") fertilized!");
			}
		}		
	
	// handle weather effects on crops
	private void handleWeatherEffects() {
			if(currentSeason == Season.WINTER) {
				// In Winter, we allow Snowy. Sunny, Rainy and Stormy 
				if (Math.random() < 0.50) { // 50% chance of Snowy during Winter.
					currentWeather = Weather.SNOWY;
				} else {
					// Exclude Snowy for this selection
					currentWeather = Weather.values()[new Random().nextInt(Weather.values().length - 1)];
				}
			}else {
					
				// In other seasons (Spring, Summer, Fall), exclude Snowy weather
				currentWeather = Weather.values()[new Random().nextInt(Weather.values().length - 1)];
			}
		
			// Apply weather effects
		switch (currentWeather) {
			case SUNNY:
				System.out.println("It's Sunny day!");
				break;
			case RAINY:
				System.out.println("It's Rainy day!");
				break;
			case STORMY:
				System.out.println("It's stormy! Be careful of crop damage.");
				
				break;
			case SNOWY:
					System.out.println("It's SNOWY! Crops cannot plant. It's need GreenHouse to plant Crops.");
					break;	
				}
				
	}
	
	// handle season change every 5 turns
	private void handleSeasonChange() {
		
		switch (currentSeason) {
			case SPRING:
				currentSeason = Season.SUMMER;
				break;
			case SUMMER:
				currentSeason = Season.FALL;
				break;
			case FALL:
				currentSeason = Season.WINTER;
				break;
			case WINTER:
				currentSeason = Season.SPRING;
				break;
		}	
		System.out.println("The season has changed to: " + currentSeason);
	}
	
	// Build greenHouse logic
	private void handleBuildGreenhouse(int x, int y) {
		if (balance >= 7) {
			balance -= 7;
			greenhouse.build();
			greenhouse.setPosition(x, y);
			System.out.println("You have built a greenhouse!");
			System.out.println("Greenhouse has builed at position (" + (x+1) + "," + (y+1) + ")!");
		}else {
			System.out.println("Not enough money to build GreenHouse!");
		}
	}
	
}
