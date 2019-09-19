package timetable;

import java.util.ArrayList;
import java.util.List;
public class Constraint{


	public Exam e1;
	public Exam e2;

 private  List<Constraint> listExclusion = new ArrayList<>();// contains all exams that must be not timetabled in the same period
 private  List<Constraint> listAfter = new ArrayList<>();//contains all Exams that  must be timetabled after certain exams
 private  List<Constraint> listCoin = new ArrayList<>();// contains all exams that must be timetabled in the same period
 private  List<Exam> listRoomExclusive = new ArrayList<>();//contains all Exams that must be timetabled in a room by itself(no other exam can be scheduled with it in the same room)
 private  List<Exam> listFrontLoad = new ArrayList<>();// contains all Large exams that must be timetabled in the nonLast periods
 
 Constraint (Exam e1, Exam e2){
		this.e1=e1;
		this.e2=e2;
		
	}
 
 Constraint (){}
 
 
 public void add_listExclusion(Constraint  Exlu)
{
	listExclusion.add(Exlu);
}

public void add_listAfter(Constraint  After)
{
	listAfter.add(After);
}

public void add_listCoin( Constraint Coin)
{
	listCoin.add(Coin);
}

public void add_ListRoomExclusive(Exam e)
{
	listRoomExclusive.add(e);
}

public void add_ListFrontLoad(Exam e)
{
	listFrontLoad.add(e);
}


public List<Constraint> get_ListExclusion()
{
	return listExclusion;
}

public List<Constraint> get_ListAfter()
{
	return listAfter;
}


public List<Constraint> get_ListCoin()
{
	return listCoin;
}

public List<Exam> get_ListRoomExclisive()
{
	return listRoomExclusive;
}

public List<Exam> get_ListFrontLoad()
{
	return listFrontLoad;
}

}
