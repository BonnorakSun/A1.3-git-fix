package students;
import java.util.Random;
import students.items.*;
import students.seasons.Season;

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
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	// tick() - call tick() on every item in the field
	public void tick(Season currentSeason, Greenhouse greenhouse) {
		for (int i=0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(grid[i][j] != null) {
					// check if it's winter and if the greenhouse is built
					if (currentSeason != Season.WINTER || greenhouse.isBuilt())
				grid[i][j].tick();
				}
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
		for(int i = 1; i <= width; i++) {
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
		if(row >= 0 && row < height && col >= 0 && col < width ) {
		return grid[row][col];
		}
		return null;
	}
	
	public void set(int x, int y, Item item) {
		if (isValidPosition(x,y)) {
			grid[x][y] = item;
		}
	}
	
	//plant() - Store the given item at the Given location
	public void plant(int row, int col, Item crop, Season currentSeason, Greenhouse greenhouse) {
		if (canPlant(row, col, currentSeason, greenhouse)) {
			grid[row][col] = crop;
		}else {
			System.out.println("Can't not plant here!");
		}
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
		  // Counters for different object types
	    int appleCount = countObjects(Apples.class);
	    int grainCount = countObjects(Grain.class);
	    int carrotCount = countObjects(Carrot.class);
	    int kiwiCount = countObjects(Kiwi.class);
	    int orangeCount = countObjects(Orange.class);
	    int tomatoCount = countObjects(Tomato.class);
	    int broccoliCount = countObjects(Broccoli.class);
	    int soilCount = countObjects(Soil.class);
	    int untilledCount = countObjects(UntilledSoil.class);
	    int weedCount = countObjects(Weed.class);

	    // Get the total Items ever created
	    int totalApples = Apples.getGenerationCount();
	    int totalGrain = Grain.getGenerationCount();
	    int totalCarrot = Carrot.getGenerationCount();
	    int totalKiwi = Kiwi.getGenerationCount();
	    int totalOrange = Orange.getGenerationCount();
	    int totalTomato = Tomato.getGenerationCount();
	    int totalBroccoli = Broccoli.getGenerationCount();
	    // Get the total value of all objects
	    int totalValue = getValue();

	    // Construct the summary string
	    String summary = "Apples: " + appleCount + "\n" +
	                     "Grain: " + grainCount + "\n" +
	                     "Carrot: " + carrotCount + "\n" +
	                     "Kiwi: " + kiwiCount + "\n" +
	                     "Orange: " + orangeCount + "\n" +
	                     "Tomato: " + tomatoCount + "\n" +
	                     "Broccoli: " + broccoliCount + "\n" +
	                     "Soil: " + soilCount + "\n" +
	                     "Untilled Soil: " + untilledCount + "\n" +
	                     "Weed: " + weedCount + "\n" +
	                     "Total Value: $" + totalValue + "\n" +
	                     "Total Apples Created: " + totalApples + "\n" +
	                     "Total Carrot Created: " + totalCarrot + "\n" +
	                     "Total Kiwi Created: " + totalKiwi + "\n" +
	                     "Total Orange Created: " + totalOrange + "\n" +
	                     "Total Tomato Created: " + totalTomato + "\n" +
	                     "Total Broccoli Created: " + totalBroccoli + "\n" +
	                     "Total Grain Created: " + totalGrain;

	    return summary;
	}

	/**
	 * Helper method to count objects of a specific type in the grid.
	 */
	private int countObjects(Class<?> type) {
	    int count = 0;
	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            if (type.isInstance(grid[row][col])) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
	
	public int harvest (int row, int col) {
		Item item = grid[row][col];
		
		// list of harvestable item types
		if (isHarvestable(item) && item.getValue() > 0)  {
			int value = item.getValue();
			grid[row][col] = new Soil(); // Reset to Soil after harvesting
			return value;
		}
		System.out.println("Nothing to harvest at this location.");
		return 0;
	}
	
	public boolean isHarvestable(Item item) {
		
		return item instanceof Apples || item instanceof Grain || item instanceof Carrot || item instanceof Kiwi || 
		item instanceof Broccoli || item instanceof Orange || item instanceof Tomato;
	}
	
	// validate coordinates 
	public boolean isValidPosition(int row, int col) {
		return row >= 0 && row < height && col >= 0 && col < width;
	}
	
	public boolean canPlant(int row, int col, Season currentSeason, Greenhouse greenhouse) {
		
		if (greenhouse.isBuilt()) {
			// Only allow planting at the greenhouse position
			if(!(row == greenhouse.getPositionX() && col == greenhouse.getPositionY())) {
				return false;
			}
		}
		// Prevent planting in Winter
		if (currentSeason == Season.WINTER && !greenhouse.isBuilt()) {
			return false;
		}
		// Ensure coordinates are within the field
		if (!isValidPosition(row,col)) {
			return false;
		}
		// Allow Planting only on soil
		return grid[row][col] instanceof Soil;
	}
	
	public void age () {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(grid[i][j] != null) {
				grid[i][j].tick(); // calling tick() for each item to age it
				}
			}
		}
	}
	
}
