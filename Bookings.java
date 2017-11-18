package application;

public class Bookings {
	
	private String Purpose1;
	private String RoomN;
	private String DateN;
	private String TimeN;
	
	/**
	 * Bookings class constructor
	 * @param Purpose1
	 * @param RoomN
	 * @param DateN
	 * @param TimeN
	 */
	public Bookings(String Purpose1, String RoomN,String DateN, String TimeN)
	{
		this.Purpose1 = Purpose1;
		this.RoomN = RoomN;
		this.DateN = DateN;
		this.TimeN = TimeN;
	}
	/**
	 * The getter functions of all the class instances.
	 * @return
	 */
	public String getPurpose1()
	{
		return Purpose1;
	}
	public String getRoomN()
	{
		return RoomN;
	}
	public String getDateN()
	{
		return DateN;
	}
	public String getTimeN()
	{
		return TimeN;
	}
}
