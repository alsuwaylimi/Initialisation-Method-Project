package timetable;

import java.util.ArrayList;

public class Indvi {
private long fitValue;
private double inverseFitValue;
private double fitRatio;
private long interval;
private Problem Pro;
private ArrayList<Gene> Chrom=new ArrayList<>();
private short id=0;
private int fitnessCalculatedByexamSPenalty=0;



// constructors =================================================================================================
Indvi(){}

Indvi ( Problem Pro)
{   
	this.Pro=Pro.Copy();
	this.id=-1;
}

Indvi (Problem Pro, long fitValue,double inverseFitValue, double fitRatio, long interval, short Id)
{
	this.Pro=Pro.Copy();
	this.fitValue=fitValue;
	this.inverseFitValue=inverseFitValue;
	this.fitRatio=fitRatio;
	this.interval=interval;
	this.id=Id;

}

// set methods ==================================================================================================
public void setIDforIndiv(short Id)
{
	this.id=Id;
}


public void setFitValue(long fitValue)
{
	this.fitValue=fitValue;
	if(fitValue!=0)
	this.inverseFitValue=Math.round((1.0/fitValue)*1000000);
	
	
}

public void setInterval(long interval)
{
	this.interval=interval;
}



public void setInverseFitValue(double inverseFitValue)
{
	this.inverseFitValue=inverseFitValue;
}

public void setFitRatio(double fitRatio)
{
	this.fitRatio=fitRatio;
}

public void set(Problem Pro)
{
	this.Pro=Pro.Copy();
}

public void setfitnessCalculatedByexamSPenalty(int x)
{
	fitnessCalculatedByexamSPenalty=x;
}


public void calculatefitnessByexamSPenalty()
{for(short i=0;i<this.Pro.getExams().size();i++)
	this.fitnessCalculatedByexamSPenalty+=this.Pro.getExams().get(i).getTotalPenalty();;
}


public void buildChrom()
{
for(int i=0;i<Pro.getExams().size();i++)
	Chrom.add(new Gene(Pro.getExams().get(i).getPeriod().getID(),Pro.getExams().get(i).getRoom().getID()));
}


public void addExam(Gene gene)
{
	Chrom.add(gene);
}






public Gene getGene(int index)
{
	return Chrom.get(index);
}


// get methods ==================================================================================================
public short get_IDforIndvi()
{
    return id;
}


public Long getfitValue()
{
    return fitValue;
}

public double getInverseFitValue()
{
	return inverseFitValue;
}


public double getfitRatio()
{     return fitRatio;
    
    
}




public long getInterval()
{
	return interval;
}


public int getFitnessByexamS_Penalty() {
	this.fitnessCalculatedByexamSPenalty=0;
	this.calculatefitnessByexamSPenalty();
	return this.fitnessCalculatedByexamSPenalty;
}

public Problem getPro()
{
    return Pro;
}


public ArrayList<Gene> getChrom()
{
    return Chrom;
}
public void printChrom()
{
	for(int i=0;i<Chrom.size();i++)
		System.out.println("Exam NO("+i+")="+Chrom.get(i).getPeriodID()+", "+Chrom.get(i).getRoomID());
}

}
