#ifndef AIRWORTHY_H

#include "Heap_PriorityQueue.h"
#include "Passenger.h"
#include <string>
#include <vector>

using namespace std;

class Airworthy
{
private:
  
   Heap_PriorityQueue<Passenger> previousPQ; 
	Heap_PriorityQueue<Passenger> randomPQ; 
	string preTime;
	string ranTime;
  
public:
 
   Airworthy();
	
	vector<Passenger> inputFiles(string inputFile);
                 
	vector<Passenger>  loadQueues(vector<Passenger>  pList);
	
	void runSim(string filename,vector<Passenger> pList);
   
};



#define AIRWORTHY_H
#endif
