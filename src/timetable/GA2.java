package timetable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GA2 {
	private int popSize=1;
	private double crossoverRatio;
	private double elitismRatio;
	private List<Indvi> elitismList=new ArrayList<>();;
	private double mutationRatio;
	private int NoMutatedExam;
	private int maxIdenticalSol;
	private int NoGenrations;
	private int generations;
	private Problem base;
	public List<Indvi>  population= new ArrayList<>();
	public ArrayList<Integer> bestResults = new ArrayList<>();
	public ArrayList<Integer> worstResults = new ArrayList<>();
	public int attempts =0;
	private int maxAttempts= 1000;
// set methods =================================================================================================	
public void setPopSize(int size)
{
	popSize=size;
}

public void setGeneration(int generation)
{
	this.generations = generation;
}

public void setElitismRatio(double ratio)
{
	elitismRatio=ratio;
}

public void setCrossoverRatio(double ratio)
{
	crossoverRatio=ratio;
}


public void setMutationRatio(double ratio)
{
	mutationRatio=ratio;
}

public void setNoMutatedExam(int No)
{
	NoMutatedExam=No;
}

public void setMaxIdentical(int No)
{
	maxIdenticalSol=No;
}

public void addElitismList(Indvi sol)
{
	elitismList.add(sol);
}

public void removeElitismList(int index)
{
	elitismList.remove(index);
}

public void setMaxAttempts(int maxAttempts){
	this.maxAttempts = maxAttempts;
}
// get methods =================================================================================================

public int getPopSize()
{
	return popSize;
}

public double getElitismRatio()
{
	return elitismRatio;
}

public double getCrossoverRatio()
{
	return crossoverRatio;
}


public double getMutationRatio()
{
	return mutationRatio;
}

public int getNoMutatedExam()
{
	return NoMutatedExam;
}

public int getMaxIdentical()
{
	return maxIdenticalSol;
}


public Indvi getElitismList(int index)
{
return 	elitismList.get(index);
}

public List<Indvi> getAllElitismList()
{
return 	elitismList;
}


public Problem getBase() 
{
return base;	
}
// build generations =============================================================================================
public boolean run() throws FileNotFoundException, UnsupportedEncodingException{
	Scanner d=new Scanner(System.in);
	
 base=new Problem((byte)8);

 //base.print_allExamsInfo();
 
 boolean done=false;
	for(int i=0; i<popSize;i++) {
		done=false;
		population.add(new Indvi(base.Copy()));
		
		while(done==false & attempts <= maxAttempts){ 	

			attempts++;
			
			
			//done=BuildTable.Build(population.get(i).getPro());
			done=BuildTableRandomly.Build(population.get(i).getPro());
			//population.get(i).getPro().Print_allInformationOFSpecificPeriod(0);
			if(done==true)
			{population.get(i).buildChrom();
			//population.get(i).printChrom();
			}
			if(done==false) {
				population.remove(i);
				population.add(new Indvi(base.Copy()));
			}
			else 
			{            	
				Fitness.setFit(population.get(i));
				if(population.get(i).getfitValue()==0)
					return true;
			}
		}
	}
if(attempts == maxAttempts)
	System.out.println("*** Infeasible solution***");
	Fitness.setRatio(population);
	Fitness.setInterval(population);
	for(int i=0;i<(int)popSize*elitismRatio;i++)
		elitismList.add(population.get(i));
	
	
	System.out.println("Initial population");
	//printPop(population);
	PrintSolution.printing(population.get(0).getPro());
/* this mark comment recently 
	for(int i=0;i<generations;i++)
	{List<Indvi> temp=new ArrayList<>();
	temp.clear();
		temp=Nextpop.generate(population,this);
		population.clear();
		for(int j=0;j<temp.size();j++)
			{
			population.add(temp.get(j));
			}
		//today System.out.println("**** next generation ******");
		Fitness.orderingIncresinglyPop(population);
        
		if(population.get(0).getfitValue()==0)
		{
			//printPop(population);
			PrintSolution.printing(population.get(0).getPro());
			return true;
		}
		
		
		printPop(population);
	}
		//PrintSolution.printing(population.get(0).getPro());
	// for testing
		/*
		int sizeExamS=0;
		for(int s=0;s<population.get(0).getPro().getExams().size();s++)
			sizeExamS+=population.get(0).getPro().getExams().get(s).getSize();
		System.out.println("Size for All ExamS is ="+sizeExamS);
		
		int SizeCapcitesRommS=0;
        int SizeFreeCapcitesRommS=0;
        int NoExaminAllPeriods=0;
        int NoExamsinAllRooms=0;
        int NoExclusiveRooms=0;
		for(int p=0;p<population.get(0).getPro().getPeriods().size();p++)
		{NoExaminAllPeriods+=population.get(0).getPro().getPeriods().get(p).get_allPeriodExamS().size();
			for(int pr=0;pr<population.get(0).getPro().getPeriods().get(p).get_allperiodRoomS().size();pr++)
			{
	            if(population.get(0).getPro().getPeriods().get(p).get_allperiodRoomS().get(pr).getRoomExclusive()==true)
	            	NoExclusiveRooms++;
				SizeCapcitesRommS+=population.get(0).getPro().getPeriods().get(p).get_allperiodRoomS().get(pr).getCapacity();
				SizeFreeCapcitesRommS+=population.get(0).getPro().getPeriods().get(p).get_allperiodRoomS().get(pr).getFreeSize();
				NoExamsinAllRooms+=population.get(0).getPro().getPeriods().get(p).get_allperiodRoomS().get(pr).getRoomExams().size();
			}		
		}
		
		System.out.println("Number of Exclusive Room= "+NoExclusiveRooms);
		System.out.println("Size Capacities for All room= "+SizeCapcitesRommS);
		System.out.println("Size Free Capacities for All room= "+SizeFreeCapcitesRommS);
		System.out.println("Number of Exams in all Periods= "+NoExaminAllPeriods);
		System.out.println("Number of Exams in all rooms = "+NoExamsinAllRooms);
 */
		
		
		//PrintInfoPeriods(population.get(0));
		
		//PrintInfoPeriods(population.get(1));
		
	/*	int bestValue = Integer.MAX_VALUE;
		int worstValue = Integer.MIN_VALUE;
		for (Indvi ind : population) {
			if (ind.getfitValue() < bestValue)
				bestValue = ind.getfitValue().intValue(); 

			if (ind.getfitValue() > worstValue)
				worstValue = ind.getfitValue().intValue();

		}
		bestResults.add(bestValue);
		worstResults.add(worstValue);
	 System.out.println("Best: " + bestValue + ", Worst: "+ worstValue);
	 
		System.out.println(population.get(0).getfitValue());
	}
	
	int sum=0;
for(int i=0;i<population.get(0).getPro().getPeriods().size();i++)
	for(int j=0;j<population.get(0).getPro().getPeriods().get(i).get_allperiodRoomS().size();j++)
		sum+=population.get(0).getPro().getPeriods().get(i).get_allperiodRoomS().get(j).getRoomExams().size();
System.out.println("suuuummmmmmmmmmmmmmmmmm"+sum);
		
	XYLineChartExample.draw(bestResults, worstResults);
*/
	Print_StandardD();
	return false;
}

public void printPop(List<Indvi> pop)
{
	for(int i=0;i<pop.size();i++)
		System.out.println(" Sol#"+i+" Cost value= "+pop.get(i).getfitValue()+
		           " Inverse Cost value="+pop.get(i).getInverseFitValue()+
		           " Ratio="+ pop.get(i).getfitRatio()+
		           " Interval="+pop.get(i).getInterval());	
}

public void printBestResults(){
	for (Integer d : bestResults)
		System.out.print(d + " ");
	System.out.println();
}


public void printWorstResults(){
	for (Integer d : worstResults)
		System.out.print(d + " ");
	System.out.println();

}
public static void PrintInfoPeriods(Indvi indvi){
	for(int i=0;i<indvi.getPro().getPeriods().size();i++)
		{
		System.out.print("**Period#("+indvi.getPro().getPeriods().get(i).getID()+"):=>>");
		for(int j=0;j<indvi.getPro().getPeriods().get(i).get_allPeriodExamS().size();j++)
		System.out.print(indvi.getPro().getPeriods().get(i).get_allPeriodExamS().get(j)+", ");
		System.out.println();
		for(int k=0;k<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().size();k++)
			{System.out.print("roomNO("+k+"):");
			  for(int n=0;n<indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(k).getRoomExams().size();n++)
			  System.out.print(indvi.getPro().getPeriods().get(i).get_allperiodRoomS().get(k).getRoomExams().get(n).getID()+", ");
			  System.out.println();
			}	
		}
}
public  void Print_StandardD() {
	double average,sum=0,square=0,sd=0,size;
	for (int i=0; i<population.size();i++)
	{
		sum+=population.get(i).getfitValue();
		System.out.println("Cost value of Sol#"+i+"=="+population.get(i).getfitValue());
	}
	size=population.size();
	average=sum/size;
	
	
	for (int i=0; i<population.size();i++)
	{
	   square = square + Math.pow((population.get(i).getfitValue()-average), 2);	
	}
	
	sd=Math.sqrt(square/(size-1.0));
	System.out.println("average="+average);
	System.out.println("standardD="+sd);
}
}
