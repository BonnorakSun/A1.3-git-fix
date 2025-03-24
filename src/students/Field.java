package students;
import java.util.Random;
import students.items.*;

/**
 * The field class represents 2D grid of Items.
 * It allows for planting, tilling, aging, and retrieving information about field.
 */
public class Field {
	
	private Item [][] grid;
	private int width;
	private int height;
	private Random random;
	
	// Constructor to initialize the field with given height and width
	public Field(int height, int width){
		
		this.width = width;
		this.height = height;
		this.grid = new Item[height][width];
		this.random = new Random();
		
		// fill the grid with soil 
		for(int i = 0; i < height; i++) {
			for(int j =0; j < width; j++) {
				grid[i][j] = new Soil();
			}
		}
	}
	
	// tick() - call tick() on every item in the field
	public void tick() {
		for (int i=0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				grid[i][j].tick();
				
				// 20% chance soil turns into weed
				if(grid[i][j] instanceof Soil && random.nextInt(100) < 20) {
					grid[i][j] = new Weed();
				}
				
				// If the items has died (age exceeds its lifetime), turn it into UntilledSoil
				
				if(grid[i][j].died()) {
					grid[i][j] = new UntilledSoil();
				}
			}
		}
	}
	
	//toString() - Create a numbered grid representation of the field 
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		// print the column numbers
		sb.append(" ");
		for(int i = 1; i < width; i++) {
			sb.append(i).append(" ");
		}
		sb.append("\n");
		
		// print the rows
		for(int i = 0; i < height; i++) {
			sb.append(i + 1).append(" ");
			for(int j = 0; j < width; j ++) {
				sb.append(grid[i][j].toString()).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	// till() - Till the given location to turn into new Soil
	public void till(int row, int col) {
		grid[row][col] = new Soil();
	}
	
	// get() - Returns a copy of the item at the give location 
	public Item get(int row, int col) {
		return grid[row][col];
	}
	
	//plant() - Store the given item at the Given location
	public void plant(int row, int col, Item item) {
		grid[row][col] = item;
	}
	
	//getValue() - Returns the total value of each item in the field 
	public int getValue() {
		int value = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				value += grid[i][j].getValue();
			}
		}
		return value;
	}
	
	// getSummary() - Return a string with quantities and overall value of the field 
	public String getSummary() {
		int appleCount = 0, grainCount = 0, soilCount = 0, untilledCount = 0, weedCount = 0;
		int totalApples = Apples.getGenerationCount();
		int totalGrain = Grain.getGenerationCount();
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if (grid[i][j] instanceof Apples) appleCount++;
				else if (grid[i][j] instanceof Grain) grainCount++;
				else if (grid[i][j] instanceof Soil) soilCount++;
				else if (grid[i][j] instanceof UntilledSoil) untilledCount++;
				else if (grid[i][j] instanceof Weed) weedCount++;
			}
		}
		
		return String.format(
				"Apples:   %d%n" +
				"Grain:    %d%n" +
				"Soil:    %d%n" +
				"Untilled:    %d%n" +
				"Weed:    %d%n" +
				"For a total of $%d%n" +
				"Total apples created:    %d%n" +
				"Total grain created:    %d%n" ,
				appleCount, grainCount, soilCount, untilledCount, weedCount, getValue(), totalApples, totalGrain
		);
	}
	
}
