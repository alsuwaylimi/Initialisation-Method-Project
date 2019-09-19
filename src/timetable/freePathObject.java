package timetable;

public class freePathObject {
private int ExamID;
private int NoFreeWith;
private boolean isChosen;

freePathObject(int ExamID)
{
this.ExamID=ExamID;
NoFreeWith=0;
isChosen=false;
}

public void setExamID(int ExamID)
{
	this.ExamID=ExamID;
}

public void setNoFreeConflict(int NoFreeWith)
{
	this.NoFreeWith=NoFreeWith;
}

public void decrementNoFreeConflict()
{
	NoFreeWith--;
}

public void incrementNoFreeConflict()
{
	NoFreeWith++;
}


public void setisChosen(boolean isChosen)
{
	this.isChosen=isChosen;
}

public int getExamID()
{
	return ExamID;
}

public int getNoFreeWith()
{
	return NoFreeWith;
}

public boolean getisChosen()
{return isChosen;
	
}

}
