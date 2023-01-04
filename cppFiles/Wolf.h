#pragma once
#include "Animal.h"

class Wolf : public Animal
{
public:
	Wolf();
	Wolf(int posX, int posY, World* world);
};

