package timetable;



public class PrintResults {
	
	private String instanceName;
	private String approach;
	private String costValue;
	private String time;
	
	PrintResults(){}
	
	
	PrintResults(String instanceName,String approach, String costValue, String time){
		
		this.instanceName= instanceName;
		this.approach= approach;
		this.costValue= costValue;
		this.time=time;	
	}
	
	public void setInstanceName(String instanceName){
		
		this.instanceName= instanceName;
	}
	
	public void setApporoach (String approach){
		
		this.approach=approach;
	}
	
	public void setCostValue(String costValue){
		
		this.costValue=costValue;
	}
	
	public void setTime(String time){
		
		this.time=time;
	}
	
	public String getInstanceName(){
		
		return instanceName;
	}
	
	public String getApproahc(){
		
		return approach;
	}
	
	public String getCostValue(){
		return costValue;
	}
	
	public String getTime(){
		return time;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}