#pragma once
#include "Plant.h"

class Grass : public Plant
{
public:
	Grass();
	Grass(int posX, int posY, World* world);
};

