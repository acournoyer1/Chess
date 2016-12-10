package Model;

public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean withinBounds()
	{
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(!(o instanceof Location)) return false;
		Location l = (Location)o;
		return this.x == l.x && this.y == l.y;
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
	
	public Location up()
	{
		return new Location(x-1, y);
	}
	
	public Location down()
	{
		return new Location(x+1, y);
	}
	
	public Location left()
	{
		return new Location(x, y-1);
	}
	
	public Location right()
	{
		return new Location(x, y+1);
	}
}
