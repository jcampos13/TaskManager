package project3;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

/** 
 * MainP class-blueprint for panel object
 * @author julian
 */
public class MainP extends JPanel implements ActionListener
{
    /** The heap object in the panel*/
    Heap<Task> heap=new Heap<Task>();
    /** The list that displays all tasks*/
    private JList lb;
    /** status (visibility of task text field*/
    private boolean isDisplayed;
    /** The array that stores the items in heap*/
    private String []fullDisplay; 
    /** The array where the names of the items will be stored*/
    private String []nameDisplay;
    /** The labels used in the GUI*/
    private JLabel taskList,add, date, time, newTask, current, complete, postpne;
    /** The buttons used in the GUI*/
    private JButton addBtn, quit,submit, completed, postpone;
    /** The text fields in the GUI*/
    private JTextField month, day, year, task, hour, minutes, currentTask, completeBy;
    
    /** 
    * Constructor-creates objects of MainP type with, reads tasks from file and initializes the GUI components
    */  
    MainP()
    {
        readTask();
        fullDisplay=heap.toString().split("\n");
        nameDisplay=new String[fullDisplay.length];
        
        for (int i=0;i<fullDisplay.length;i++)
        {
            nameDisplay[i]=fullDisplay[i].split(",")[0];
        }
        
        this.setLayout(null);
        
        completeBy=new JTextField();
        completeBy.setBounds(220, 75, 200, 30);
        completeBy.setBackground(Color.white);
        completeBy.setEditable(false);
        
        currentTask=new JTextField();
        currentTask.setBounds(220, 25, 200, 30);
        currentTask.setBackground(Color.white);
        currentTask.setEditable(false);
        
        taskList=new JLabel("Tasks:");
        taskList.setBounds(20,0,50,30);
        
        if(heap.size()==0)
        {
            lb=new JList();
            currentTask.setText("");
            completeBy.setText("");
        }
        
        else
        {
            lb=new JList(nameDisplay); 
            currentTask.setText(heap.getCurrent().getName());
            completeBy.setText(heap.getCurrent().getDueDate());
        }
        
        lb.setBounds(20, 30, 180, 350);
        
        quit=new JButton("Quit");
        quit.setFont(new Font("Dialog",Font.BOLD, 10));
        quit.setBounds(60, 400, 100, 30);
        quit.setBackground(Color.white);
        quit.addActionListener(this);
        
        current=new JLabel("Current Task:");
        current.setBounds(220, 0, 100, 30);
                
        complete=new JLabel("Complete By:");
        complete.setBounds(220, 50, 100, 30);              
               
        completed=new JButton("Completed!");
        completed.setBounds(225, 120, 90, 30);
        completed.setFont(new Font("Dialog",Font.BOLD, 9));
        completed.setBackground(Color.white);
        completed.addActionListener(this);
        
        addBtn=new JButton("Add Task");
        addBtn.setBackground(Color.white);
        addBtn.setFont(new Font("Dialog",Font.BOLD, 9));
        addBtn.setBounds(325, 120, 90, 30);
        addBtn.addActionListener(this);
        
        postpone=new JButton("Postpone");
        postpone.setBackground(Color.white);
        postpone.setFont(new Font("Dialog",Font.BOLD, 9));
        postpone.setBounds(325, 120, 90, 30);
        postpone.setVisible(false);
        postpone.addActionListener(this);
        
        add=new JLabel("Add Task:");
        add.setBounds(220, 170, 100, 30);
        add.setVisible(false);
        
        postpne=new JLabel("Postpone Task:");
        postpne.setBounds(220, 170, 100, 30);
        
        date=new JLabel("Date:");
        date.setBounds(230, 200, 100, 30);
        
        time=new JLabel("Time");
        time.setBounds(230,240,100,30);      
        
        newTask=new JLabel("Task:");
        newTask.setBounds(230, 280, 100, 30);
        newTask.setVisible(false);
        
        month=new JTextField("MM");
        month.setBounds(270, 200, 40, 30);
        
        day=new JTextField("DD");
        day.setBounds(320, 200, 40, 30);
        
        year=new JTextField("YYYY");
        year.setBounds(370, 200, 40, 30);
        
        hour=new JTextField("HH");
        hour.setBounds(270, 240, 40, 30);
        
        minutes=new JTextField("MM");
        minutes.setBounds(320, 240, 40, 30);
        
        task=new JTextField("New Task");
        task.setBounds(270, 280, 150, 30);
        task.setVisible(false);
        
        isDisplayed=task.isDisplayable();
        
        submit=new JButton("Submit");
        submit.setFont(new Font("Dialog",Font.BOLD, 10));
        submit.setBounds(325, 325, 70, 30);
        submit.setBackground(Color.white);
        submit.addActionListener(this);

        this.add(taskList);
        this.add(lb);
        this.add(submit);
        this.add(current);
        this.add(complete);
        this.add(currentTask);
        this.add(completeBy);
        this.add(completed);
        this.add(postpone);
        this.add(add);
        this.add(date);
        this.add(time);
        this.add(newTask);
        this.add(month);
        this.add(day);
        this.add(year);
        this.add(hour);
        this.add(minutes);
        this.add(task);
        this.add(quit);    
        this.add(addBtn);
        this.add(postpne);
    }
    
    /** 
    * Performs functionality for swing components
    * @param e the element that triggered the action
    */
    @Override
    public void actionPerformed(ActionEvent e)
    {      
        //removes root from heap
        if(e.getSource()==completed)
        {
            int error=JOptionPane.ERROR_MESSAGE;            
            if(heap.isEmpty()==false)
            {
                heap.removeItem();
                String []updated=heap.toString().split("\n");
                nameDisplay=new String [updated.length];
                for (int i=0;i<updated.length;i++)
                {
                    nameDisplay[i]=updated[i].split(",")[0];
                }
                lb.setListData(nameDisplay);              
                if(heap.size()!=0)
                {
                    currentTask.setText(heap.getCurrent().getName());
                    completeBy.setText(heap.getCurrent().getDueDate());
                }
            }
            else if(heap.isEmpty()==true)
            {
                JOptionPane.showMessageDialog(this, "Nothing to remove on file", "Warning", error);
                currentTask.setText("");
                completeBy.setText("");
            }
        }
        
        //toggles to postpone form
        if(e.getSource()==postpone)
        {          
            JButton hidePostpone=(JButton)e.getSource();
            hidePostpone.setVisible(false);
            addBtn.setVisible(true);
            task.setVisible(false);
            postpne.setVisible(true);
            isDisplayed=false;
            newTask.setVisible(false);
            add.setVisible(false);                      
        }
        
        //exits and saves to file
        if(e.getSource()==quit)
        {
            writeToFile();
            System.exit(0);
        }
        
        //toggles to add form
        if(e.getSource()==addBtn)
        {
            JButton hideAdd=(JButton)e.getSource();
            add.setVisible(false);
            postpne.setVisible(true);
            hideAdd.setVisible(false);
            postpone.setVisible(true);
            postpne.setVisible(false);
            add.setVisible(true);
            task.setVisible(true);
            isDisplayed=true;
            newTask.setVisible(true);
        }
        
        //adds new task
        if(e.getSource()==submit && isDisplayed==true)
        {
            String yyyy, dd, mm, hh, m, taskName;       
            yyyy=year.getText();
            dd=day.getText();
            mm=month.getText();
            taskName=task.getText();
            hh=hour.getText();
            m=minutes.getText();
            int error=JOptionPane.ERROR_MESSAGE;
            
            if(m.matches("^\\d+") && mm.matches("^\\d+") && hh.matches("^\\d+") && dd.matches("^\\d+") && yyyy.matches("^\\d+") && String.valueOf(yyyy).length()==4 && String.valueOf(dd).length()==2 && String.valueOf(hh).length()==2 && String.valueOf(m).length()==2 && String.valueOf(mm).length()==2 && task.getText().isEmpty()==false)
            {
                int chckDay=Integer.parseInt(dd);
                int chckMonth=Integer.parseInt(mm);
                int chckYear=Integer.parseInt(yyyy);
                int chckHrs=Integer.parseInt(hh);
                int chckMins=Integer.parseInt(m);
                
                if(chckDay>=0 && chckDay<=31 && chckMonth>0 && chckMonth<=12 && chckYear>190 && chckMins>=0 && chckMins<=60 && chckHrs>=0 && chckHrs<=23)
                {
                    Task added=new Task(taskName,mm+"/"+dd+"/"+yyyy+" "+hh+":"+m);
                    heap.addItem(added);                  
                    currentTask.setText(heap.getCurrent().getName());
                    completeBy.setText(heap.getCurrent().getDueDate());
                    String []updated=heap.toString().split("\n");
                    nameDisplay=new String [updated.length];
                    for (int i=0;i<updated.length;i++)
                    {
                        nameDisplay[i]=updated[i].split(",")[0];
                    }
                    lb.setListData(nameDisplay);
                    task.setVisible(false);
                    add.setVisible(false);
                    postpne.setVisible(true);
                    newTask.setVisible(false);
                    addBtn.setVisible(true);
                    postpone.setVisible(false);
                    isDisplayed=false;
                    year.setText("YYYY");
                    month.setText("MM");
                    day.setText("DD");   
                    hour.setText("HH");
                    minutes.setText("MM");
                    task.setText("");
                }
                else JOptionPane.showMessageDialog(this, "Invalid Input", "Warning", error);
            }
            else JOptionPane.showMessageDialog(this, "Invalid Input", "Warning", error);
        }
        
        //postpones task
        else if(e.getSource()==submit && isDisplayed==false)
        {      
            String yyyy, dd, mm, hh, m;       
            yyyy=year.getText();
            dd=day.getText();
            mm=month.getText();
            hh=hour.getText();
            m=minutes.getText();
            int error=JOptionPane.ERROR_MESSAGE;
            
            if(m.matches("^\\d+") && mm.matches("^\\d+") && hh.matches("^\\d+") && dd.matches("^\\d+") && yyyy.matches("^\\d+") && String.valueOf(yyyy).length()==4 && String.valueOf(dd).length()==2 && String.valueOf(hh).length()==2 && String.valueOf(m).length()==2 && String.valueOf(mm).length()==2)
            {
                int chckDay=Integer.parseInt(dd);
                int chckMonth=Integer.parseInt(mm);
                int chckYear=Integer.parseInt(yyyy);
                int chckHrs=Integer.parseInt(hh);
                int chckMins=Integer.parseInt(m);
                
                if(chckDay>=0 && chckDay<=31 && chckMonth>0 && chckMonth<=12 && chckYear>190 && chckMins>=0 && chckMins<=60 && chckHrs>=0 && chckHrs<=23)
                {
                    Task added=new Task(currentTask.getText(),mm+"/"+dd+"/"+yyyy+" "+hh+":"+m);
                    heap.removeItem();
                    heap.addItem(added);
                    String []updated=heap.toString().split("\n");
                    nameDisplay=new String [updated.length];
                    for (int i=0;i<updated.length;i++)
                    {
                        nameDisplay[i]=updated[i].split(",")[0];
                    }
                    lb.setListData(nameDisplay);
                    currentTask.setText(heap.getCurrent().getName());
                    completeBy.setText(heap.getCurrent().getDueDate());
                    year.setText("YYYY");
                    month.setText("MM");
                    day.setText("DD");   
                    hour.setText("HH");
                    minutes.setText("MM");
                }
                else JOptionPane.showMessageDialog(this, "Invalid Input", "Warning", error);
            }
            else JOptionPane.showMessageDialog(this, "Invalid Input", "Warning", error);
        }
    }
    
    /** 
    * Reads the task list from the text file and into the heap
    */
    public void readTask()
    {
        try
        {           
            Scanner scan=new Scanner(new File("taskList.txt"));        
            do
            {
                String line=scan.nextLine();
                String tokens[]=line.split(",");
                if(line.isEmpty())
                {
                    
                }
                else
                {
                    heap.addItem(new Task(tokens[0],tokens[1]));  
                }
            }       
            while(scan.hasNextLine());
            scan.close();       
        }        
        catch(FileNotFoundException fnf)
        {
            System.out.println("The file was not found! ERROR:"+fnf);
        }        
    }
    
    /** 
    * writes the tasks to a text file when user quits
    */
    public void writeToFile()
    {
        try
        {
            PrintWriter writer=new PrintWriter(new File("taskList.txt"));           
            String saved=heap.toString();
            String toFile=saved.trim();
            writer.println(toFile);
            System.out.println(heap.toString());
            writer.close();
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("ERROR:"+fnf);
        }            
    }      

}
