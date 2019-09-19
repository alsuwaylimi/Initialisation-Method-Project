package timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Selection {

//*******************************************************************************************************************************************
public  static Indvi selectRoulette(List<Indvi> pop)
{
	
	Random rn = new Random();
	long index;
	index = rn.nextInt(Fitness.getTotalIntervals());
	for(int i=0;i<pop.size();i++)
	{
		if(index<=pop.get(i).getInterval())
			return new Indvi(pop.get(i).getPro(),pop.get(i).getfitValue(),pop.get(i).getInverseFitValue(),
		              pop.get(i).getfitRatio(),pop.get(i).getInterval(),pop.get(i).get_IDforIndvi());
		
	}
	System.out.print("*********  An error has occured  **********");
	return new Indvi(pop.get(0).getPro(),  pop.get(0).getfitValue(),pop.get(0).getInverseFitValue(),
            pop.get(0).getfitRatio(),pop.get(0).getInterval(),pop.get(0).get_IDforIndvi());
}

//*******************************************************************************************************************************************
public  static Indvi selectRandomly(List<Indvi> pop)
{
	Random rn = new Random();
	int index;
	index = rn.nextInt(pop.size());
			return new Indvi(pop.get(index).getPro(),  pop.get(index).getfitValue(),pop.get(index).getInverseFitValue(),
		              pop.get(index).getfitRatio(),pop.get(index).getInterval(),pop.get(index).get_IDforIndvi());
	
	
}
//**********************************************************************************************************************************************
public  static Indvi selectByindviID(List<Indvi> pop,int index)
{
	
	
		
			return new Indvi(pop.get(index).getPro(),pop.get(index).getfitValue(),pop.get(index).getInverseFitValue(),
		              pop.get(index).getfitRatio(),pop.get(index).getInterval(),pop.get(index).get_IDforIndvi());
		
}


}
