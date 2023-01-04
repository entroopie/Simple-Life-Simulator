#pragma once
#include "Animal.h"

class Sheep : public Animal 
{
public:
	Sheep();
	Sheep(int posX, int posY, World* world);
};

