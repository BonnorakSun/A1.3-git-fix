package students.items;

public class Greenhouse {

	private boolean isBuilt;
	 private int positionX = -1;
	    private int positionY = -1;
	public Greenhouse() {
		this.isBuilt = false; // greenhouse is not built initially
	}
	
	// Builds the greenHouse 
	public void build() {
		if (!isBuilt) {
			isBuilt = true;
		}
	}
	
	public void setBuilt(boolean isBuilt) {
		this.isBuilt = isBuilt;
	}
	
	public boolean isBuilt() {
		return isBuilt;
	}
	
	 public int getPositionX() {
	        return positionX;
	    }

	    public int getPositionY() {
	        return positionY;
	    }

	    public void setPosition(int x, int y) {
	        this.positionX = x;
	        this.positionY = y;
	    }
	
	public String toString() {
		return "Greenhouse";
	}
}
