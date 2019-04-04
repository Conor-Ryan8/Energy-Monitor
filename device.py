import sqlite3
import time
import _thread
import datetime
import socket
from datetime import date

#Database connection
conn = sqlite3.connect('EnergyDatabase.db')
c = conn.cursor()

#Function used by the device
def add_reading(reading):
    
    ts = time.time()
    timestamp = datetime.datetime.fromtimestamp(ts).strftime('%d-%m-%Y,%H:%M')
    d,t = timestamp.split(',')
    m = datetime.datetime.fromtimestamp(ts).strftime('%m')
    wk = date.today().isocalendar()[1]
    c.execute("INSERT INTO Readings (Data,Time,Date) VALUES (?,?,?,?,?)",(reading,t,d,wk,m))   
    conn.commit()

def get_current_load():
    
    #SQL COMMAND, sorts by date and time and only returns the 2 most recent results
    c.execute("SELECT Time FROM Readings ORDER BY Time DESC, Date DESC LIMIT 2")
    #Returns the result
    result = c.fetchall()
    print("SQL CURRENT LOAD RESULT:"+str(result))
    #Sorts the incoming data
    x, time1, y, time2, *rest = str(result).split("'")
    #Split the timestamp into hours and minutes                                              
    hours1, mins1 = time1.split(":")
    hours2, mins2 = time2.split(":")
    #Convert to INT and subtract to find time taken
    hours = int(hours1) - int(hours2)
    mins = int(mins1) - int(mins2)   
    #get total time taken in minutes
    totalmins = 60*hours + mins
    print("Last 1khw was consumed in "+str(totalmins)+" Minutes")
    #current load calculation formula
    currentload = 1000*(60/totalmins)
    print("Current Load is: "+str(int(currentload))+" Watts")
    
    return currentload

def get_today():
    
    #get current date
    ts = time.time()
    timestamp = datetime.datetime.fromtimestamp(ts).strftime('%d-%m-%Y,%H:%M')
    d, *rest = timestamp.split(',')
    
    #SQL COMMAND, sorts by date and time and only returns the most recent entry
    c.execute("SELECT * FROM Readings WHERE Date=? ORDER BY Time DESC LIMIT 1",(d,))
    result = c.fetchall()
    print("SQL GET TODAY RESULT1: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    recent = data.lstrip('[(')
    print("Most Recent Reading Taken Today: "+recent)
    
    #SQL COMMAND, sorts by date and time and only returns the earliest entry
    c.execute("SELECT * FROM Readings WHERE Date=? ORDER BY Time ASC LIMIT 1",(d,))
    result = c.fetchall()
    print("SQL GET TODAY RESULT2: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    first = data.lstrip('[(')
    print("Earliest Reading Taken Today: "+first)
    
    ##calculate units used and costs
    total = float(recent) - float(first)
    print("Units Used Today: "+str(total)+"kWh")
    cost = 0.16 * total
    print("Costs Today: €"+str(cost))
    
    return total, cost
    
def get_thisweek():
    
    #get current week
    wk = date.today().isocalendar()[1] 
    #SQL COMMAND, sorts by date and time and only returns the most recent entry
    c.execute("SELECT * FROM Readings WHERE Week=? ORDER BY Date DESC, Time DESC LIMIT 1",(wk,))
    result = c.fetchall()
    print("SQL GET THISWEEK RESULT1: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    recent = data.lstrip('[(')
    print("Most Recent Reading Taken This Week: "+recent)
    
    #SQL COMMAND, sorts by date and time and only returns the earliest entry
    c.execute("SELECT * FROM Readings WHERE Week=? ORDER BY Date ASC, Time ASC LIMIT 1",(wk,))
    result = c.fetchall()
    print("SQL GET THISWEEK RESULT2: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    first = data.lstrip('[(')
    print("Earliest Reading Taken This Week: "+first)
    
    ##calculate units used and costs
    total = float(recent) - float(first)
    print("Units Used This Week: "+str(total)+"kWh")
    cost = 0.16 * total
    print("Costs This Week: €"+str(cost))
    
    return total, cost

def get_thismonth():
    
    #get current month
    ts = time.time()
    m = datetime.datetime.fromtimestamp(ts).strftime('%m')
    #SQL COMMAND, sorts by date and time and only returns the most recent entry
    c.execute("SELECT * FROM Readings WHERE Month=? ORDER BY Date DESC, Time DESC LIMIT 1",(m,))
    result = c.fetchall()
    print("SQL GET THISMONTH RESULT1: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    recent = data.lstrip('[(')
    print("Most Recent Reading Taken This Month: "+recent)
    
    #SQL COMMAND, sorts by date and time and only returns the earliest entry
    c.execute("SELECT * FROM Readings WHERE Month=? ORDER BY Date ASC, Time ASC LIMIT 1",(m,))
    result = c.fetchall()
    print("SQL GET THISMONTH RESULT2: "+str(result))
    #extract the data
    data, *rest = str(result).split(",")
    first = data.lstrip('[(')
    print("Earliest Reading Taken This Month: "+first)
    
    ##calculate units used and costs
    total = float(recent) - float(first)
    print("Units Used This Month: "+str(total)+"kWh")
    cost = 0.16 * total
    print("Costs This Month: €"+str(cost))
    
    return total, cost
                  
#add_reading(19)
#get_current_load()
#et_today()
#get_thisweek()
#get_thismonth()

IP = '127.0.0.1'
Port = 5005
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((IP, Port))
print("socket ready")
    
while True:
        
    print("K00211753 - Energy Monitoring Device online")
    data = sock.recvfrom(1024)
    print (data)
