
package timetable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Nextpop {
	
	//private static List<Indvi> preAndCurrentPop=new ArrayList<>();	

private static List<Indvi> nextpop=new ArrayList<>();	
;	

private static List<Indvi> twoPopulations=new ArrayList<>();
private static Scanner rn=new Scanner(System.in);
private static Scanner show=new Scanner(System.in);
private static byte [] counterforEachIndvi=new byte[GA.getPopSize()];
public static List<Indvi> generate(List<Indvi> pop, GA ga) throws FileNotFoundException, UnsupportedEncodingException
{ //preAndCurrentPop.clear();
	
	GA.getAllElitismList().clear();
    nextpop.clear();
    twoPopulations.clear();
    twoPopulations.addAll(pop);
  //  GA.printPop(twoPopulations);
//******************************************* mutation types ***********************************************************    
moderateMutate(pop,ga); 
periodBasedMutation(pop);
deepMutate(pop);
deepPeriodBasedMutate(pop);
heavyMutate(pop,ga); 
orderingIncresinglyPopByfitness(pop);
buildElitismListBeforeMutationS(pop);// prepare ElitisM list for the LightMutation and lightMutateForBestSols
//GA.printPop(GA.getAllElitismList());
//show.next();
lightMutate(GA.getAllElitismList(),ga);
lightMutateForBestSols(GA.getAllElitismList(),ga);
//*******************************************end of mutation types ***********************************************************

for(int i=0;i<twoPopulations.size();i++)// to compute the fitness of new generations
Fitness.setFit(twoPopulations.get(i));	// to compute the fitness of new generations
Fitness.setRatio(twoPopulations);// to compute the fitness of new generations
Fitness.setInterval(twoPopulations);// to compute the fitness of new generations
//System.out.println("twoPopulations="+twoPopulations.size());
//GA.printPop(twoPopulations);
//show.next();

buildElitismListAfterMutationS(twoPopulations);
//System.out.println("population of Elitism");
//GA.printPop(GA.getAllElitismList());

//System.out.println("twoPopulations="+twoPopulations.size());
//GA.printPop(twoPopulations);
nextpop.addAll(GA.getAllElitismList());


buildNextpop_(twoPopulations);
//System.out.println("nextpop Population***************************");
//GA.printPop(nextpop);
//for(int i=0;i<nextpop.size();i++)// to compute the fitness of new generations
//Fitness.setFit(nextpop.get(i));	// to compute the fitness of new generations
Fitness.setRatio(nextpop);// to compute the fitness of new generations
Fitness.setInterval(nextpop);// to compute the fitness of new generations
//System.out.println("nextpop after   ordering  ="+nextpop.size());
//GA.printPop(nextpop);
//show.next();

//*************************************************************used to check*************************************************************************************
	//for(short i=0;i<nextpop.size();i++)
	//if(CheckHardConstraints.checkAllHC(nextpop.get(i).getPro())==true)
	//	System.out.println("feaasible");
//*****************************************************************************************************************************************************************
	//nextpop.get(9).getPro().print_allExamsInfo();
/*	
	System.out.println("the size of nextpop is="+nextpop.size());
	for(short dd=0;dd<nextpop.get(9).getPro().getExams().size();dd++)
	{	System.out.println("the number of 2R violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).get2RViolation());
	System.out.println("the number of 2D violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).get2DViolation());
	System.out.println("the number of PS violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getPSViolation());
	System.out.println("the number of PP violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getPPViolation());
	System.out.println("the number of RP violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getRPViolation());
	System.out.println("the number of NM violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getNMViolation());
	System.out.println("the number of FL violation for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getFLViolation());
	System.out.println("the number of total penalty for exam number("+nextpop.get(9).getPro().getExams().get(dd).getID()+")="+nextpop.get(9).getPro().getExams().get(dd).getTotalPenalty());
	}
	
*/
	
	// we can compute the fitness for any solution by three other two methods -- by summation exams penalties or by by summation exams penalties
	return nextpop;
}


public static void moderateMutate(List <Indvi> pop, GA ga) throws FileNotFoundException, UnsupportedEncodingException
{ 	
	short NoSolsMutated=(short)(GA.getPopSize()*0.5);
	
for(short i=0;i<NoSolsMutated;i++)
	{ 
	  boolean mutated=false;

	  while(mutated==false)
		  
	  {Indvi indviMutated;
	  indviMutated=Selection.selectRoulette(pop);
		  mutated=Mutation.ordinaryMutation(indviMutated,ga);
	   if(mutated==true)
	     {
		   twoPopulations.add(indviMutated)	;
	     }
	  // else
		//  System.out.println("************  Mutation Field  ************");

      }
 
    }
}


public static void heavyMutate(List <Indvi> allPrevPop, GA ga) throws FileNotFoundException, UnsupportedEncodingException
{   double probability=0;
    short popSize=(short) allPrevPop.size();
	for( short index=0;index<popSize;index++)
       {probability=((index)/(allPrevPop.size()*1.0));
     // System.out.println("probability="+probability);
      // show.next();
	     if(probability>Math.random())
	        {boolean mutated=false;
	       
             //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
           Indvi indviMutated;
	                 indviMutated=Selection.selectByindviID(allPrevPop,index);
		             mutated=Mutation.heavyMutation(indviMutated,ga);
	                 if(mutated==true)
	                    {//compute how many this individual has been selected  
		                 twoPopulations.add(indviMutated);
		                // System.out.println("************ heavy Mutation success  ************");
		                }
	 
	               //   else
		                //System.out.println("************ heavy Mutation Field  ************");

                     }
 
                   }
	        }




public static void lightMutate(List <Indvi> allPrevPop, GA ga) throws FileNotFoundException, UnsupportedEncodingException
{  double probability=0;
short popSize=(short) allPrevPop.size();
for( short index=0;index<popSize;index++)
   {probability=((allPrevPop.size()-index)/(allPrevPop.size()*1.0));
  // System.out.println("probability="+probability);
  // show.next();
     if(probability>Math.random())
        {boolean mutated=false;
       
         //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
       Indvi indviMutated;
                 indviMutated=Selection.selectByindviID(allPrevPop,index);
	             mutated=Mutation.lightMutation(indviMutated,ga);
                 if(mutated==true)
                    {//compute how many this individual has been selected  
	                 twoPopulations.add(indviMutated);
	               //  System.out.println("************ light Mutation success  ************");
	                }
 
                 // else
	            //    System.out.println("************ light Mutation Field  ************");

                 }

               }
}





public static void lightMutateForBestSols(List <Indvi> Best, GA ga) throws FileNotFoundException, UnsupportedEncodingException
{  short popSize=(short) Best.size();
	short max=(short) (GA.getMutationTimes()+1);
	//System.out.println("ddddd"+GA.getMutationTimes());
	//show.next();
	//short times=rn.nextShort(max);
	short counter=0;
	while(counter<max)
	{//System.out.println("helllo world");
	double probability=0;

for( short index=0;index<popSize;index++)
   {probability=((GA.getPopSize()-index)/(GA.getPopSize()*1.0));
   //System.out.println("probability="+probability);
  // show.next();
     if(probability>Math.random())
        {boolean mutated=false;
       
         //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
       Indvi indviMutated;
                 indviMutated=Selection.selectByindviID(Best,index);
	             mutated=Mutation.lightMutation(indviMutated,ga);
                 if(mutated==true)
                    {//compute how many this individual has been selected  
	                 twoPopulations.add(indviMutated);
	                // System.out.println("************ light for best sols Mutation success  ************");
	                 
	                }
 
                //  else
	               // System.out.println("************ light for best sols Mutation Field  ************");

                 }

               }

	counter++;
	}
}










public static void periodBasedMutation(List<Indvi> allPrevPop) throws FileNotFoundException, UnsupportedEncodingException
{ 
	
	
	double probability=0;
    short popSize=(short) allPrevPop.size();
	for( short index=0;index<popSize;index++)
       {probability=((index)/(allPrevPop.size()*1.0));
      // System.out.println("probability="+probability);
      // show.next();
	     if(probability>Math.random())
	        {boolean mutated=false;
	       
             //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
           Indvi indviMutated;
	                 indviMutated=Selection.selectByindviID(allPrevPop,index);
		             mutated=Mutation.periodBaesdMutation(indviMutated);
	                 if(mutated==true)
	                    {//compute how many this individual has been selected  
		                 twoPopulations.add(indviMutated);
		                // System.out.println("************ period based Mutation success  ************");
		                }
	 
	                 // else
		              //  System.out.println("************ period based Field  ************");

                     }
 
                   }
	
	
	
   
}

public static void deepMutate(List <Indvi> allPrevPop) throws FileNotFoundException, UnsupportedEncodingException
{ 
	
	double probability=0;
	short popSize=(short) allPrevPop.size();
	for( short index=0;index<popSize;index++)
	   {probability=((allPrevPop.size()-index)/(allPrevPop.size()*1.0));
	   //System.out.println("probability="+probability);
	  // show.next();
	     if(probability>Math.random())
	        {boolean mutated=false;
	       
	         //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
	       Indvi indviMutated;
	                 indviMutated=Selection.selectByindviID(allPrevPop,index);
		             mutated=Mutation.deepMutation(indviMutated);
	                 if(mutated==true)
	                    {//compute how many this individual has been selected  
		                 twoPopulations.add(indviMutated);
		                // System.out.println("************ light Mutation success  ************");
		                }
	 
	                //  else
		               // System.out.println("************ light Mutation Field  ************");

	                 }

	               }
	}
	
	



public static void deepPeriodBasedMutate(List <Indvi> allPrevPop) throws FileNotFoundException, UnsupportedEncodingException
{ 

	
	double probability=0;
    short popSize=(short) allPrevPop.size();
	for( short index=0;index<popSize;index++)
       {probability=((index)/(allPrevPop.size()*1.0));
     //  System.out.println("probability="+probability);
      // show.next();
	     if(probability>Math.random())
	        {boolean mutated=false;
	       
             //  System.out.println("number of solutiuons that will be mutated= "+NoSolsMutated);
           Indvi indviMutated;
	                 indviMutated=Selection.selectByindviID(allPrevPop,index);
		             mutated=Mutation.deepPeriodBasedMutation(indviMutated);
	                 if(mutated==true)
	                    {//compute how many this individual has been selected  
		                 twoPopulations.add(indviMutated);
		             //    System.out.println("************Deeep period based Mutation success  ************");
		                }
	 
	                 // else
		              //  System.out.println("************ Deeep  period based Field  ************");

                     }
 
                   }
	
	
	
//make mutation for all individuals and just for the exams having highest penalty


	
}


public static void buildElitismListBeforeMutationS(List<Indvi> twoPopulations)
{ 


GA.getAllElitismList().clear();
	System.out.println("buildElitismList buildElitismList ="+GA.getAllElitismList().size());
	short listSize=GA.getElitismSize();
	short index=0;
	while(index<listSize)
	{Indvi selectedIndvi; 
	selectedIndvi=twoPopulations.get(index);
//System.out.println("hello  world");
	GA.addElitismList(selectedIndvi);
	index++;
	}
	
	
	//for( short i=0;i<GA.getElitismSize();i++)
		//System.out.println(" leitism"+GA.getElitismList(i).get_IDforIndvi()+"=="+GA.getElitismList(i).getfitValue()+"selected Times="+GA.getElitismList(i).getSelectedTimes());
}

public static void buildElitismListAfterMutationS(List<Indvi> twoPopulations)
{ 


GA.getAllElitismList().clear();
	System.out.println("buildElitismList buildElitismList ="+GA.getAllElitismList().size());
	short listSize=GA.getElitismSize();
	short index=0;
	while(index<listSize)
	{Indvi selectedIndvi; 
	selectedIndvi=twoPopulations.get(0);
//System.out.println("hello  world");
	GA.addElitismList(selectedIndvi);
	for(short i=0;i<twoPopulations.size();i++)
	{ if(twoPopulations.get(i).get_IDforIndvi()==selectedIndvi.get_IDforIndvi())
		{//System.out.println("here we are");
	    twoPopulations.remove(i);
	    i--;}
	}
	index++;
	}
	
	
	//for( short i=0;i<GA.getElitismSize();i++)
		//System.out.println(" leitism"+GA.getElitismList(i).get_IDforIndvi()+"=="+GA.getElitismList(i).getfitValue()+"selected Times="+GA.getElitismList(i).getSelectedTimes());
}
/*
public static void elitism(GA ga)
{ 

    for(int i=0;i<ga.getAllElitismList().size();i++)
	{twoPopulations.add(new Indvi(ga.getElitismList(i).getPro(),ga.getElitismList(i).getfitValue(),ga.getElitismList(i).getInverseFitValue(),ga.getElitismList(i).getfitRatio(),ga.getElitismList(i).getInterval(),ga.getElitismList(i).get_IDforIndvi()));
	  
	  }}
*/


/*
public static void buildNextpop(List<Indvi> twoPopulations){

	byte maxRepeated=GA.getMaxRepeated();
	System.out.println("repeated Max="+maxRepeated);
	byte counter=0;
	short popSize=(short) (GA.getPopSize()-GA.getElitismSize());
	System.out.println("popsize="+popSize);
	System.out.println("the size of the next pop is="+nextpop.size());	
	for( short ss=0;ss<nextpop.size();ss++)
        System.out.println(nextpop.get(ss).get_IDforIndvi());
	
	for(short ff=0;ff<counterforEachIndvi.length;ff++)
		System.out.println("counterforEachIndvi ["+ff+"]="+counterforEachIndvi[ff]);
	show.next();

	while(counter<popSize)
	{Indvi selectedIndvi; 
selectedIndvi=Selection.selectRoulette(twoPopulations);



if(counterforEachIndvi[selectedIndvi.get_IDforIndvi()]!=0)
  {boolean repeated=false;
 
   for(short i=0;i<nextpop.size();i++)
   {   
	   System.out.println("nextpopID="+nextpop.get(i).get_IDforIndvi());
	   System.out.println("selectedIndviID="+selectedIndvi.get_IDforIndvi());
	   System.out.println("nextpopValue="+nextpop.get(i).getfitValue());
	   System.out.println("selectedIndviVLUE="+selectedIndvi.getfitValue());
	   show.next();
	   // System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
	   if(nextpop.get(i).get_IDforIndvi()==selectedIndvi.get_IDforIndvi() & nextpop.get(i).getfitValue()==selectedIndvi.getfitValue())
        {repeated =true;
        
        System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
         System.out.println("repeated Solution");
         break;
        }
   }  
      if(repeated==false)
        {if(counterforEachIndvi[selectedIndvi.get_IDforIndvi()]<maxRepeated)
            {nextpop.add(selectedIndvi);
             counterforEachIndvi[selectedIndvi.get_IDforIndvi()]+=1;
             counter++;
            }
        }
        	
        	

}
else	
    {nextpop.add(selectedIndvi);
     counterforEachIndvi[selectedIndvi.get_IDforIndvi()]+=1;
     counter++;
     }
}
	

}
	
*/

public static void buildNextpop_(List<Indvi> twoPopulations){



	byte counter=0;
	short popSize=(short) (GA.getPopSize()-GA.getElitismSize());
	
	//show.next();

	while(counter<popSize)
	{Indvi selectedIndvi; 
selectedIndvi=Selection.selectRoulette(twoPopulations);



         nextpop.add(selectedIndvi);
    counter++;
    for(short i=0;i<twoPopulations.size();i++)
	{ if(twoPopulations.get(i).get_IDforIndvi()==selectedIndvi.get_IDforIndvi())
		{//System.out.println("here we are");
	    twoPopulations.remove(i);
	    i--;}
	}
        	
	}
}





//check for existence of the solution in the elitism list

/*times
public static void rest(List<Indvi> pop, GA ga)
{
int restSize=0;
restSize=pop.size()-nextpop.size();



Indvi indvi;
int size=0;

while(size!=restSize)
{
indvi=Selection.selectRoulette(pop);
//2000if(!isEqual(nextpop,indvi,ga.getMinDiversity(),ga.getMaxIdentical()))
{nextpop.add(indvi);
size++;
//show.next();

}
}

// System.out.println("the size of population is ["+ nextpop.size()+"] where ("+(nextpop.size()-size)+") by mutation and ("+size+") added by roulette wheel selection" );
}

public static boolean isEqual(List<Indvi> listPop, Indvi indvi, int minDiversity, int maxIdentical)
{
		if(minDiversity>0) {// this mean if the difference is zero so no need to maintain the diversity, then return false
	int countIdentical=-1;
int diff=0;

for(int i=0;i<listPop.size();i++)
	{boolean equal=true;
	 diff=0;
	 for(int j=0;j<listPop.get(i).getPro().getExams().size();j++)
		 {if(listPop.get(i).getPro().getExams().get(j).getPeriod().getID()!=indvi.getPro().getExams().get(j).getPeriod().getID())
		 //|| listPop.get(i).getPro().getExams().get(j).getRoom().getID()!=indvi.getPro().getExams().get(j).getRoom().getID()	)
		    {diff++;
		   	if(diff==minDiversity)
				{equal= false;
				}
		   }
    	  }
	  if(equal==true)
		countIdentical++;
	  if(equal==true && countIdentical==maxIdentical)
		{  // System.out.println("the individual ("+indvi.get_IDforIndvi()+") has "+countIdentical+"similar solutions in this population");
		  return true;
		}
	  }

 //System.out.println("adding this individual["+ indvi.get_IDforIndvi()+"]has reached ("+diff+") that <= maximum number of differnces ("+minDiversity+").");
//GA.PrintInfoPeriods(indvi);
//GA.PrintIndviInfo(indvi);
return false;}
	
	else
		return false;}
//}
/*
public static void computeDifferencesVSbestSolforEachIndvi(List<Indvi> nextpopDiff)
{short diff=0;
	for(short i=0;i<nextpopDiff.size();i++)
		{diff=0;
		for(short j=0;j<nextpopDiff.get(0).getPro().getExams().size();j++)
		{
			if(nextpopDiff.get(0).getPro().getExams().get(j).getPeriod().getID()!=nextpopDiff.get(i).getPro().getExams().get(j).getPeriod().getID())
				diff++;
		}
		nextpopDiff.get(i).setNoDiffenecesWithBestSol(diff);
		}	
	
}

*/

/*
public static void orderingIndividualsByDifferencesVsBest(List<Indvi> nextpopDiff) {

	boolean smaller=false;
	int index=0;
		for(int i=1;i<nextpopDiff.size();i++)
	{
			
			index=i;
		
			for(int j=i-1;j>=0;j--)
	{if(nextpopDiff.get(i).getNoDiffenecesWithBestSol()<nextpopDiff.get(j).getNoDiffenecesWithBestSol())
				{
		smaller=true;
				index=j;
				}

	else
	{	break;
	}

			
	}
	if(smaller==true)
			{nextpopDiff.add(index,nextpopDiff.get(i));
			nextpopDiff.remove(i+1);
			smaller=false;
	}}
	
}



*/


/*
public static void rebuild_Elitism_List(GA ga,List<Indvi> nextpopDiff ) {
	ga.getAllElitismList().clear();
	int deciles=(int) (Math.floor(nextpopDiff.size()/10));
	//System.out.println("the deciles is"+ deciles);
	//show.next();
	int indexOfbestIndi=0;
	long BestFitnessValue=nextpopDiff.get(deciles).getfitValue();
	ga.addElitismList(new Indvi(nextpopDiff.get(0).getPro(),  nextpopDiff.get(0).getfitValue(),nextpopDiff.get(0).getInverseFitValue(),
			nextpopDiff.get(0).getfitRatio(),nextpopDiff.get(0).getInterval(),nextpopDiff.get(0).get_IDforIndvi(), nextpopDiff.get(0).getSelection_times())); //the best individual in the first decile is already known "in the top of the list"
	
	
	for(int j=deciles;j<nextpopDiff.size();j++)
		{if(nextpopDiff.get(j).getfitValue()<=BestFitnessValue)
			{BestFitnessValue=nextpopDiff.get(j).getfitValue();
			indexOfbestIndi=j;}
         if(j%deciles==0)
         {
        	ga.addElitismList(new Indvi(nextpopDiff.get(indexOfbestIndi).getPro(),  nextpopDiff.get(indexOfbestIndi).getfitValue(),nextpopDiff.get(indexOfbestIndi).getInverseFitValue(),
        			nextpopDiff.get(indexOfbestIndi).getfitRatio(),nextpopDiff.get(indexOfbestIndi).getInterval(),nextpopDiff.get(indexOfbestIndi).get_IDforIndvi(), nextpopDiff.get(indexOfbestIndi).getSelection_times()));
        	if((j+1)<nextpopDiff.size())
        	BestFitnessValue=nextpopDiff.get(j+1).getfitValue();
         }
			}
}

*/

//for Elitism

/*times
public static boolean isEqualElitism(List<Indvi> listPop, Indvi indvi, int minDiversity, int maxIdentical)
{
	
	

	if(minDiversity>0) {// this mean if the difference is zero so no need to maintain the diversity, then return false
	int countIdentical=-1;
int diff=0;

for(int i=0;i<listPop.size();i++)
	{boolean equal=true;
	 diff=0;
	 for(int j=0;j<listPop.get(i).getPro().getExams().size();j++)
		 {if(listPop.get(i).getPro().getExams().get(j).getPeriod().getID()!=indvi.getPro().getExams().get(j).getPeriod().getID())
		 //|| listPop.get(i).getPro().getExams().get(j).getRoom().getID()!=indvi.getPro().getExams().get(j).getRoom().getID()	)
		    {diff++;
		   	if(diff==minDiversity)
				{equal= false;
				}
		   }
    	  }
	  if(equal==true)
		countIdentical++;
	  if(equal==true && countIdentical==maxIdentical)
		{System.out.println("the individual ("+indvi.get_IDforIndvi()+") has "+countIdentical+"similar solutions in this population");
		  return true;
		}
	  }

System.out.println("adding this individual["+ indvi.get_IDforIndvi()+"]has reached ("+diff+") that <= maximum number of differnces ("+minDiversity+").");
GA.PrintInfoPeriods(indvi);
GA.PrintIndviInfo(indvi);
return false;}
	
	else
		return false;
	
	
	
	
}

//for Crossover
public static boolean isEqual(Indvi indvi1, Indvi indvi2)
{
int diff=0;
int minNodiff=3;
	

		for(int j=0;j<indvi2.getPro().getExams().size();j++)
		
			if(indvi2.getPro().getExams().get(j).getPeriod().getID()!=indvi1.getPro().getExams().get(j).getPeriod().getID())
			{//|| listPop.get(i).getPro().getExams().get(j).getRoom().getID()!=indvi.getPro().getExams().get(j).getRoom().getID()	)
		diff++;
		if(diff==minNodiff)
				return false;
			}
return true;
}



/*
public static List<Indvi> generateNextPop(int sizePop,int max)
{
//orderingIncresinglyPop(preAndCurrentPop);
//binary selection***********************************

List<Indvi> binaryList=new ArrayList<>();
int size=0;
while(size!=sizePop)
{binaryList.clear();
 for(int j=0;j<2;j++)
	 binaryList.add(Selection.selectRandomly(preAndCurrentPop));
 orderingIncresinglyPop(binaryList);
	if(!isEqual(nextpop,binaryList.get(0)))
	{nextpop.add(new Indvi(binaryList.get(0).getPro(),binaryList.get(0).getfitValue(),binaryList.get(0).getInverseFitValue(),binaryList.get(0).getfitRatio(),binaryList.get(0).getInterval()));
	size++;

	}}
*///





//end of binary selection ***********************************



//best selection**********************************
/*int size=0;
while(size!=sizePop)
{

for(int i=0;i<sizePop;i++)
	if(!isEqual(nextpop,preAndCurrentPop.get(i),max))
	{nextpop.add(preAndCurrentPop.get(i));
	size++;
	*/
	//}
//end of best selection**************************
/*}
Fitness.setRatio(nextpop);
Fitness.setInterval(nextpop);
return nextpop;


}
*/


public static List<Indvi>  orderingIncresinglyPopByfitness(List<Indvi> pop)
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

return pop;
}





public static List<Indvi>  orderingDecresinglyPopByPenalty(List<Indvi> pop)
{
boolean smaller=false;
int index=0;
	for(int i=1;i<pop.size();i++)
{
		
		index=i;
	
		for(int j=i-1;j>=0;j--)
{if(pop.get(i).getFitnessByexamS_Penalty()>pop.get(j).getFitnessByexamS_Penalty())
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

return pop;
}



public static List<Exam>  orderingDecresinglyIndviExamsByPenalty(List<Exam> exams)
{
boolean smaller=false;
int index=0;
	for(int i=1;i<exams.size();i++)
{
		
		index=i;
	
		for(int j=i-1;j>=0;j--)
{if(exams.get(i).getTotalPenalty()>exams.get(j).getTotalPenalty())
			{
	smaller=true;
			index=j;
			}

else
{	break;
}

		
}
if(smaller==true)
		{exams.add(index,exams.get(i));
		exams.remove(i+1);
		smaller=false;
}}

return exams;
}




public static List<Period>  orderingDecresinglyIndviPeriodsByPenalty(List<Period> periods)
{
boolean smaller=false;
int index=0;
	for(int i=1;i<periods.size();i++)
{
		
		index=i;
	
		for(int j=i-1;j>=0;j--)
{if(periods.get(i).getTotalExamsPenalty()>periods.get(j).getTotalExamsPenalty())
			{
	smaller=true;
			index=j;
			}

else
{	break;
}

		
}
if(smaller==true)
		{periods.add(index,periods.get(i));
		periods.remove(i+1);
		smaller=false;
}}

return periods;
}


public static List<Period>  orderingIncresinglyIndviPeriodssByID(List<Period> periods)
{
boolean smaller=false;
int index=0;
	for(int i=1;i<periods.size();i++)
{
		
		index=i;
	
		for(int j=i-1;j>=0;j--)
{if(periods.get(i).getID()<periods.get(j).getID())
			{
	smaller=true;
			index=j;
			}

else
{	break;
}

		
}
if(smaller==true)
		{periods.add(index,periods.get(i));
		periods.remove(i+1);
		smaller=false;
}}

return periods;
}



public static List<Exam>  orderingIncresinglyIndviExamsByExamID(List<Exam> pop)
{
boolean smaller=false;
int index=0;
	for(int i=1;i<pop.size();i++)
{
		
		index=i;
	
		for(int j=i-1;j>=0;j--)
{if(pop.get(i).getID()<pop.get(j).getID())
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

return pop;
}




/*
public static void uniCrossover(List<Indvi> pop,GA ga) throws FileNotFoundException
{
	int crossoverSize=0;
	crossoverSize=(int) (pop.size()*ga.getCrossoverRatio());
	Indvi Indvi1;
	Indvi Indvi2;
	for(int cross=0;cross<crossoverSize;cross++)
	{
		while(true) {
	 Indvi1=Selection.selectRoulette(pop);
	 
	 Indvi2=Selection.selectRoulette(pop);
	if(!isEqual(Indvi1,Indvi2))
	break;	
	
	
		}
	Indvi CrossedSols=UniformCossover.crossover(Indvi1, Indvi2, ga);
	
}
}


public static void crossover(List<Indvi> pop,GA ga) throws FileNotFoundException

{int crossoverSize=0;
crossoverSize=(int) (pop.size()*ga.getCrossoverRatio());
Indvi Indvi1;
Indvi Indvi2;
for(int cross=0;cross<crossoverSize;cross++)
{
	while(true) {
 Indvi1=Selection.selectRoulette(pop);
 
 Indvi2=Selection.selectRoulette(pop);
if(!isEqual(Indvi1,Indvi2))
break;	
	}
List<Indvi> CrossedSols=Crossover.crossover(Indvi1, Indvi2, ga);
//System.out.println("CrossedSols"+CrossedSols.size());
for(int j=0;j<CrossedSols.size();j++)
	Fitness.setFit(CrossedSols.get(j));	
boolean large=false;
int index=0;
{for(int i=1;i<CrossedSols.size();i++)		
	{index=i;
			for(int j=i-1;j>=0;j--)
	{if(CrossedSols.get(j).getfitValue()>CrossedSols.get(i).getfitValue())
				{
		large=true;
				index=j;
				}

	else
	{	break;
	}

			
	}
	if(large==true)
			{CrossedSols.add(index,CrossedSols.get(i));
			CrossedSols.remove(i+1);
			large=false;
	}}}

if(CrossedSols.size()>=1)
	nextpop.add(CrossedSols.get(0));

//testing

for(int i=0;i<CrossedSols.size();i++)	
	System.out.println("Cross"+i+"="+CrossedSols.get(i).getfitValue());

}
}
*/


}
