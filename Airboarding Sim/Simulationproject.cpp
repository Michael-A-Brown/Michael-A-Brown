#include "Passenger.h"
#include "Airworthy.h"


#include <iostream>


using namespace std;

void d(); 

int main (void)
{
   Airworthy a100;
   Airworthy a85;
   Airworthy a70;
	string a = "results100.txt";
	string b = "results85.txt";
	string c = "results70.txt";
   
   d();
   vector<Passenger> h; 
   h = a100.inputFiles(a);
   a100.loadQueues(h);
   a100.runSim(a,h);
   
   h = a85.inputFiles(a);
   a85.loadQueues(h);
   a85.runSim(a,h);

   h = a70.inputFiles(a);
   a70.loadQueues(h);
   a70.runSim(a,h);
   

}

void d() 
{
   cout << "Hello User" << endl;
   cout << " " << endl;
   cout << " This program runs a simulated boarding procedure. To look at the results check your folder for the outputted files!" << endl;
}
