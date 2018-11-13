
#include "Airworthy.h"
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <cstdlib>
#include <cctype>
#include <stack>
#include <fstream>
#include <string>

using namespace std;



Airworthy::Airworthy()
{
   preTime = "0.0";
   ranTime = "0.0";
}
	
vector<Passenger> Airworthy::inputFiles(string inputFile)
{
   vector<Passenger> p; 
	int a = 0;  
	
   string lastName = " ";  
   string passengerType = " ";
   string assignedRow = " "; 
   string line = " ";  
	
   int h = 0; 
	

   cout << inputFile<<"<---"<<endl;
   ifstream in;
   in.open(inputFile);
    	
   if(!in){
      cout << "File Input error";
   }
    
   getline (in,line);
   cout << line << endl;
   stringstream ss(line); 	
   while( !(ss >> line).fail())
   {
      
       
      
      
      
       
      getline(ss,lastName, ' ');
      getline(ss,passengerType, ' ');
      getline(ss,assignedRow, ' ');
      
      cout <<lastName<<" "<< passengerType<<" " << assignedRow<< endl;
      h = atoi(assignedRow.c_str());
      
      Passenger p1;
      p1.setPassenger(a,lastName,passengerType[0],h);  
     
      p.push_back(p1);
   };
   in.close();
   return p; 

}
   
vector<Passenger> Airworthy::loadQueues(vector<Passenger> pList)
{
   int i = 0;
   int keycounter = 0; 
  //preboarding same for both Ques
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getPassengerType() == 'H')
      {
         previousPQ.add(pList[i]); 
         randomPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }
   }
	
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() <= 4 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         randomPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }
   }
   
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getPassengerType() == 'E' && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         randomPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }
      if(pList[i].getAssignedRow() == 10 || pList[i].getAssignedRow() == 11 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         randomPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }
   }
//--------General---Boarding-------

  //-----previousPQ
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() >= 23 && pList[i].getAssignedRow() <= 26 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }  
   }
	
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() >= 17 && pList[i].getAssignedRow() <= 22 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]);  
         keycounter++;
         pList[i].setKey(keycounter);
      }  
   }
	
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() >= 11 && pList[i].getAssignedRow() <= 16 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }  
   }
	
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() >= 5 && pList[i].getAssignedRow() <= 10 && pList[i].getKey() == 0)
      {
         previousPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }  
   }
   //------randomPQ
   for(i = 0; i < pList.size(); i++)
   {
      if(pList[i].getAssignedRow() >= 4 && pList[i].getKey() == 0)
      {
         randomPQ.add(pList[i]); 
         keycounter++;
         pList[i].setKey(keycounter);
      }  
   }
   return pList; 
}	

void Airworthy::runSim(string filename,vector<Passenger>  pList)
{
   int a = 0;
   string time = " ";
   double sec = 0.0;
   double min = 0.0;
   Passenger p1;
   Passenger p2;

   ofstream cout(filename.c_str());
  //-------cout--will--go--to--outputfile
   cout << "Original List in the file:" << endl;	
   for(int i = 0; i < pList.size(); i++)
   {
      cout << "   " << pList[i].getLastName()<<" "<< pList[i].getPassengerType()<<" "<< pList[i].getAssignedRow()<< endl;      
   }
  //---Runsim--on--Previous--PQ
   cout << " " << endl;
   cout << "Boarding in Previous Procedure ..." << endl;
   cout << "Last Name " << "Type " << "Row "<<" Key "<< endl;
   while(!previousPQ.isEmpty())
   {
      p2 = p1; 	
      p1 = previousPQ.peek();
      cout <<p1.getLastName()<<" "<< p1.getPassengerType()<<" " << p1.getAssignedRow()<<" "<< p1.getKey()<< endl;
      if(p1.getAssignedRow() >= p2.getAssignedRow())
      {
         sec = sec + 25;
      }
      else{
         sec = sec + 1;
      }
   	
      previousPQ.remove();
   
   }  
   min = sec/60; 
   preTime = static_cast<ostringstream*>( &(ostringstream() << min) )->str();
   cout <<" " << endl;
   cout <<"Total boarding time: "<< " "<<preTime[0]<<" "<<"minutes and"<< " "<<preTime[1]<<preTime[2]<<" "<< "secounds."<<endl; 
  
  //-- clear values
   p2.setAssignedRow(a); 
   sec = min = 0;
   
  
  //---Runsim--on--RandomPQ
   cout << " " << endl;
   cout <<" " << endl;
   cout << "Boarding in Random Procedure ..." << endl;
   cout << "Last Name " << "Type " << "Row "<<" Key "<< endl;
   while(!randomPQ.isEmpty())
   {
      p2 = p1; 	
      p1 = randomPQ.peek();
      cout <<p1.getLastName()<<" "<< p1.getPassengerType()<<" " << p1.getAssignedRow()<<" "<< p1.getKey()<< endl;
      if(p1.getAssignedRow() >= p2.getAssignedRow())
      {
         sec = sec + 25;
      }
      else{
         sec = sec + 1;
      }
   	
      randomPQ.remove();
   
   }  
   min = sec/60; 
   ranTime = static_cast<ostringstream*>( &(ostringstream() << min) )->str();
   cout <<" " << endl;
   cout <<"Total boarding time: "<< " "<<ranTime[0]<<" "<<"minutes and"<< " "<<ranTime[1]<<ranTime[2]<<" "<< "secounds."<<endl; 
  
   cout.close(); 
}   
