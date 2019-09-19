/*
package timetable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformCossover {
	public List<Exam> sd = new ArrayList<>();
	
	public static Indvi crossover(Indvi indvi1,Indvi indvi2,GA ga)
	{Indvi offspring=new Indvi(ga.getBase().Copy());
	int periodID;
	int roomID;
	
	int indexRoomId=0;
	for(int i=0;i<indvi1.getPro().getExams().size();i++)
		{offspring.getChrom().add(new Gene());
		 Random select=new Random();
		
			if(select.nextInt(2)==0)
			{
			
				
				periodID=indvi1.getPro().getExams().get(i).getPeriod().getID();	
				roomID=indvi1.getPro().getExams().get(i).getRoom().getID();
				for(int j=0;j<offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();j++)
					if(roomID==offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(j).getID())
						indexRoomId=j;
				if(schedule(periodID,indexRoomId)==false)
					{periodID=indvi2.getPro().getExams().get(i).getPeriod().getID();	
				     roomID=indvi2.getPro().getExams().get(i).getRoom().getID();
				      for(int j=0;j<offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().size();j++)
					     if(roomID==offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(j).getID())
						
					    	 indexRoomId=j;
				     }
				else if (schedule(periodID,indexRoomId)==false)
				System.out.println("ggg");
					    	 		    	  	  	    		    	 
				    	  
			}
			
		    
		}
			
		
		return offspring;
	}


public  static boolean schedule(int periodID, int roomID)
{ 
/*
	if(offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getFreeSize()<=offspring.getPro().getExams().get(i).getSize()&& offspring.getPro().getPeriods().get(periodID).get_allperiodRoomS().get(roomID).getRoomExclusive()==false)
for(int k=0;k<offspring.getPro().getPeriods().get(periodID).get_allPeriodExamS().size();k++)
		if(!offspring.getPro().getExams().get(i).ConflictWith.contains(offspring.getPro().getPeriods().get(periodID).get_allPeriodExamS().get(k))

	
	*/
/*return true;
}
}
*/