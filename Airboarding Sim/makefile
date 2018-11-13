# This is the makefile for the Passenger Database Example
Simulationproject.exe: Passenger.o Airworthy.o  PrecondViolatedExcep.o Simulationproject.o 
	g++ -std=gnu++11 -o Simulationproject.exe Passenger.o Airworthy.o  PrecondViolatedExcep.o Simulationproject.o 
Passenger.o: Passenger.cpp Passenger.h 
	g++ -std=gnu++11 -ggdb -c Passenger.cpp
Airworthy.o: Airworthy.cpp Airworthy.h Heap_PriorityQueue.h HeapInterface.h ArrayMaxHeap.h ArrayMaxHeap.h PrecondViolatedExcep.h Passenger.h
	g++ -std=gnu++11 -ggdb -c Airworthy.cpp
PrecondViolatedExcep.o: PrecondViolatedExcep.cpp PrecondViolatedExcep.h 
	g++ -std=gnu++11 -ggdb -c PrecondViolatedExcep.cpp
Simulationproject.o: Simulationproject.cpp Airworthy.h Heap_PriorityQueue.h HeapInterface.h ArrayMaxHeap.h ArrayMaxHeap.h  PrecondViolatedExcep.h Passenger.h
	g++ -std=gnu++11 -ggdb -c Simulationproject.cpp 