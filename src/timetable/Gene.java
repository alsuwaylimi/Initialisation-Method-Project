package timetable;

public class Gene {
private int periodID;
private int roomID;

public Gene() {}

public Gene(int periodID,int roomID)
{
	this.periodID=periodID;
	this.roomID=roomID;
}
public void setPeriodID(int periodID)
{
	this.periodID=periodID;

}

public void setRoomID(int roomID)
{
	this.roomID=roomID;

}



public int getPeriodID()
{
	return periodID;
}

public int getRoomID() {
	return roomID;
}
}
