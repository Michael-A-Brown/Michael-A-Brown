#ifndef PASSENGER_H

#include <string>
#include <fstream>

using namespace std;

class Passenger
{
private:
   int key; // variable that stores key for passenger object
   string lastName; 
   char passengerType; 
   int assignedRow;	 
public:
    
   Passenger();
   //--
	
   void setPassenger( int& nKey,  string& nLastName,  char& nPassengerType, int& nAssignedRow);
	//--
	
   void setKey( int& nKey);
	
   void setLastName( string& nLastName);
	
   void setPassengerType( char& nPassengerType);
	
   void setAssignedRow( int& nAssignedRow);
	//--
	
   int getKey(void);
   
   string getLastName(void);
   
   char getPassengerType(void);
   
   int getAssignedRow(void);
   
   bool operator> (const Passenger &right);
  
   bool operator< (const Passenger &right);
                                                     
}; // end Passenger





#define PASSENGER_H
#endif
