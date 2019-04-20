import sqlite3
import time
import _thread
import datetime
import socket
from datetime import date

#Database connection
conn = sqlite3.connect('Consumption_Database.db')
c = conn.cursor()

#Function used by the monitoring application
def Add_Reading(Data):
    
    #Check if the record already exists
    c.execute("SELECT * FROM ConsumptionData WHERE Reading=?",(Data,))
    Result = c.fetchall()

    #If the record does not exist
    if not Result:
        
        #Get relevent time data
        ts = time.time()
        Timestamp = datetime.datetime.fromtimestamp(ts).strftime('%M,%H,%d,%m,%Y')
        
        #Split up the time data
        Min, Hour, Day, Month, Year = Timestamp.split(',')
        
        #SQL COMMAND, Inserts the new entry with the generated timestamp data
        c.execute("INSERT INTO ConsumptionData (Reading,Minute,Hour,Day,Month,Year) VALUES (?,?,?,?,?,?)",(Data,Min,Hour,Day,Month,Year))   
        conn.commit()
        
        #Prints Success status message
        print("Record < "+str(Data)+" > entered into Database at: "+ str(Hour)+":"+str(Min))
    
    else:
        
        #Prints Error status message
        print("Duplicate Record Exists: "+str(Result))
 
#Current Load in watts Function
def Current_Load():
    
    #SQL COMMAND, sorts by most recent and only returns  2 results
    c.execute("SELECT Minute,Hour FROM ConsumptionData ORDER BY Year DESC, Month DESC, Day DESC, Hour DESC, Minute DESC LIMIT 2")
    Result = c.fetchall()
    
    #Sorts the data
    MostRecent = Result[0]
    SecondRecent= Result[1]
    print("Most Recent Entry: "+str(MostRecent))
    print("Second Recent Entry: "+str(SecondRecent))
    
    #Unpacks the Most recent entry   
    X, Y = str(MostRecent).split(", ")
    M1 = X[1:3]
    H1 = Y[0:2]
    print("Most Recent Timestamp: "+H1+":"+M1)
    
    #Unpacks the Second recent entry 
    X, Y = str(SecondRecent).split(", ")
    M2 = X[1:3]
    H2 = Y[0:2]
    print("Second Recent Timestamp: "+H2+":"+M2)
  
    #Calculate Time Taken
    Hours = int(H1) - int(H2)
    Minutes = int(M1) - int(M2)
    TimeTaken = Hours*60 + Minutes
    print("Time Taken: "+str(TimeTaken)+" Minutes")
 
    #Current Load Formula
    CurrentLoad = 1000*(60/TimeTaken)
    print("Current Load is: "+str(int(CurrentLoad))+" Watts")
  
    #Return the Result
    return CurrentLoad

#Total kWh used today function
def Consumption_Today():
    
    #Get relevent time data
    ts = time.time()
    Timestamp = datetime.datetime.fromtimestamp(ts).strftime('%d,%m,%Y')
    
    #Split up the time data
    Day, Month, Year = Timestamp.split(',')
    
    #SQL COMMAND, returns all entries today
    c.execute("SELECT * FROM ConsumptionData WHERE Day=? AND Month=? AND Year=?",(Day,Month,Year,))
    Result = c.fetchall()
    
    #Calculate Consumption Today
    Consumption = len(Result)
    print(str(Consumption)+"kWh Used Today")
    
    #Return the Result
    return Consumption

#Total kWh used today function
def Consumption_This_Month():
    
    #Get relevent time data
    ts = time.time()
    Timestamp = datetime.datetime.fromtimestamp(ts).strftime('%m,%Y')
    
    #Split up the time data
    Month, Year = Timestamp.split(',')
    
    #SQL COMMAND, returns all entries this month
    c.execute("SELECT * FROM ConsumptionData WHERE Month=? AND Year=?",(Month,Year,))
    Result = c.fetchall()
    
    #Calculate Consumption Today
    Consumption = len(Result)
    print(str(Consumption)+"kWh Used This Month")
    
    #Return the Result
    return Consumption
 
#Consumption for hours of a day function
def Day_Graph(Day,Month,Year):
    
    #Error Checking for day and month range
    if Day in range(1,31) and Month in range(1,12):
        
        #SQL COMMAND, returns the hour data from all entries today
        c.execute("SELECT Hour FROM ConsumptionData WHERE Day=? AND Month=? AND Year=?",(Day,Month,Year,))
        Result = c.fetchall()
        
        ##Creates an empty String
        Data = ""
        
        ##Cycles through the hours of the day
        for Hour in range(0,24):
            
            #Counts the number of entries in the specified hour
            Count = Result.count((Hour,))
            
            #checks if the final hour has been reached
            if Hour == 23:                
                Data = Data + str(Count)      
            #if not, continue apending the string
            else:                
                Data = Data + str(Count) + ", "
      
    #prints Error Message
    else:       
        print("Incorrect Date Entered: "+ str(Day)+"/"+str(Month)+"/"+str(Year))
     
    #Convert to a string and display the data
    Consumption = "("+str(Data)+")" 
    print("Hourly Consumption for "+str(Day)+"/"+str(Month)+"/"+str(Year)+" is "+Consumption)
    
    #Return the Result
    return Consumption
 
#Consumption for days of a month function
def Month_Graph(Month,Year):
    
    #Error Checking for month range
    if Month in range(1,12):
        
        #SQL COMMAND, returns the day data from all entries this month
        c.execute("SELECT Day FROM ConsumptionData WHERE Month=? AND Year=?",(Month,Year,))
        Result = c.fetchall()
        
        ##Creates an empty String
        Data = ""
        
        ##Cycles through the days of the month
        for Day in range(1,32):
            
            #Counts the number of entries in the specified day
            Count = Result.count((Day,))
              
            #checks if the final day has been reached
            if Day == 31:                
                Data = Data + str(Count)
            #if not, continue apending the string
            else:                
                Data = Data + str(Count) + ", "
     
    #prints Error Message
    else:       
        print("Incorrect Date Entered: "+ str(Month)+"/"+str(Year))
    
    #Convert to a string and display the data
    Consumption = "("+str(Data)+")" 
    print("Daily Consumption for "+str(Month)+"/"+str(Year)+" is "+Consumption)
    
    #Return the Result
    return Consumption

print("K00211753 - Energy Monitoring Device online")

##########USED FOR TESTING FUNCTIUONS##########
#Add_Reading(17)
#c.execute("SELECT * FROM ConsumptionData")
#All_Data = c.fetchall()
#print("All Records: "+str(All_Data)+"\n")
#Current_Load()
#Consumption_Today()
#Consumption_This_Month()
#Day_Graph(20,4,2019)
#Month_Graph(4,2019)
