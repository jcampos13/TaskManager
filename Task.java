package project3;

/** 
 * Task class-blueprint for task object
 * @author julian
 */
public class Task implements Comparable<Task> 
{
    /** The name of the task*/
    private String taskName; 
    /** The due date of the task*/
    private String dueDate;
    
    /** 
    * Constructor-creates objects of Task type (no parameters)
    */
    public Task(){}
    
    /** 
    * Constructor-creates objects of Task type 
    * @param taskName is the name assigned to the task
    * @param dueDate is the date assigned to the task
    */
    public Task(String taskName, String dueDate)
    {
        this.dueDate=dueDate;
        this.taskName=taskName;
    }
    
    /** 
    * returns the name of the task
    * @return the task name
    */    
    public String getName()
    {
        return taskName;
    }
    
    /** 
    * returns the due date of the task
    * @return the due date
    */      
    public String getDueDate()
    {
        return dueDate;
    }
    
    /** 
    * compares if two tasks are in order
    * @param t the task that will be compared
    * @return -1 if items are in order or 1 if out of order. 
    */      
    @Override
    public int compareTo(Task t)
    {                  
        //information of task passed in
        String dateTime=t.dueDate;
        String[] dateNoSpace1=dateTime.split(" ");
        String[] dateOnly1=dateNoSpace1[0].split("/");
        String[] timeOnly1=dateNoSpace1[1].split(":");
        
        //information of task being compared
        String[] dateNoSpace=this.dueDate.split(" ");
        String[] dateOnly=dateNoSpace[0].split("/");
        String[] timeOnly=dateNoSpace[1].split(":");
        
        //compares tasks by...
        
        //years
        if(dateOnly1[2].equalsIgnoreCase(dateOnly[2])) 
        {     
            //months
            if(dateOnly1[0].equalsIgnoreCase(dateOnly[0]))
            {
                //days
                if(dateOnly1[1].equalsIgnoreCase(dateOnly[1]))
                {
                    //hours
                    if(timeOnly1[0].equalsIgnoreCase(timeOnly[0]))
                    {
                        //minutes
                        if(timeOnly1[1].equalsIgnoreCase(timeOnly[1]))
                        {
                            //names
                            if(this.taskName.compareTo(t.taskName)>0)
                            {                             
                                return -1;
                            } 
                            else return 1;
                        }
                        //if minutes are not equal, find the soonest one
                        else if(Integer.parseInt(timeOnly1[1])<Integer.parseInt(timeOnly[1]))
                        {
                            return -1;   
                        }   
                        else return 1;
                    }
                    //if hours are not equal, find the soonest one
                    else if(Integer.parseInt(timeOnly1[0])<Integer.parseInt(timeOnly[0]))
                    {
                        return -1;   
                    }                 
                    else return 1;
                }
                //if days are not equal, find the soonest one
                else if(Integer.parseInt(dateOnly1[1])<Integer.parseInt(dateOnly[1]))
                {
                    return -1;   
                }
                else return 1;
            }
            //if months are not equal, find the soonest one
            else if(Integer.parseInt(dateOnly1[0])<Integer.parseInt(dateOnly[0]))
            {
                return -1;   
            }     
            else return 1;
        }
        //if years are not equal, find the soonest one   
        else if(Integer.parseInt(dateOnly1[2])<Integer.parseInt(dateOnly[2]))
        {
            return -1;
        }
        else return 1;
    }
    
    /** 
    * returns the name and due date as one single string
    * @return the concatenated string
    */    
    @Override
    public String toString()
    {
        return taskName + "," + dueDate + "";
    }
}
