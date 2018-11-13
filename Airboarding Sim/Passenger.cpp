#include "Passenger.h"
#include <iostream>

using namespace std;

Passenger::Passenger()
{
   key = 0; 
   lastName = " "; 
   passengerType = ' '; 
   assignedRow = 0;	
}
//--
	
void Passenger::setPassenger( int& nKey,  string& nLastName,   char& nPassengerType, int& nAssignedRow)
{
   key = nKey; 
   lastName = nLastName; 
   passengerType = nPassengerType; 
   assignedRow = nAssignedRow;	

}
//--
	
void Passenger::setKey( int& nKey)
{
   key = nKey; 
}
	
void Passenger::setLastName( string& nLastName)
{
   lastName = nLastName; 
}
	
void Passenger::setPassengerType( char& nPassengerType)
{
   passengerType = nPassengerType;
}
	
void Passenger::setAssignedRow( int& nAssignedRow)
{
   assignedRow = nAssignedRow;
}
//--
	
int Passenger::getKey(void)
{
  return key;
}
	
string Passenger::getLastName(void)
{
	return lastName;
}
	
char Passenger::getPassengerType(void)
{
	return passengerType;
}
	
int Passenger::getAssignedRow(void)
{
	return assignedRow;
}
	
bool Passenger::operator> (const Passenger &right)
{
	return (key > right.key);
}
  
bool Passenger::operator< (const Passenger &right)
{
	return (key < right.key);
}