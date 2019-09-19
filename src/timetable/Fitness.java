package timetable;

import java.util.List;

public class Fitness {
private static int totalIntervals;// this variable will be used in Selection Class for random selection
	
public static void setTotalIntervals(int total)
{
	totalIntervals=total;
}
public static int getTotalIntervals()
{
return totalIntervals;	
}

public static void setFit(Indvi sol)
{
 Evaluate.Evaluate_Sol=sol.getPro();
 sol.setFitValue(Evaluate.overAll_Penalty());
 //System.out.println(sol.getfitValue());
}

public static void setRatio(List<Indvi> pop)
{
	double totalInvfitValues=0;

	for(int i=0;i<pop.size();i++)
		totalInvfitValues+=pop.get(i).getInverseFitValue();

	for(int i=0;i<pop.size();i++)
		pop.get(i).setFitRatio(Math.round(pop.get(i).getInverseFitValue()/totalInvfitValues*1000000));


}

public static void setInterval(List<Indvi> pop)
{
	totalIntervals=0;
	//ordering population individuals increasingly based on their fitness value
	orderingIncresinglyPop(pop);
	//compute interval for each individual
	for(int i=0;i<pop.size();i++)
	{totalIntervals+=pop.get(i).getfitRatio();
	pop.get(i).setInterval(totalIntervals);	
	
	}


}

public static void orderingIncresinglyPop(List<Indvi> pop)
{
	boolean smaller=false;
	int index=0;
		for(int i=1;i<pop.size();i++)
	{
			
			index=i;
		
			for(int j=i-1;j>=0;j--)
	{if(pop.get(i).getfitValue()<pop.get(j).getfitValue())
				{
		smaller=true;
				index=j;
				}

	else
	{	break;
	}

			
	}
	if(smaller==true)
			{pop.add(index,pop.get(i));
			pop.remove(i+1);
			smaller=false;
	}}
	
}

}
