#pragma once
#include "Animal.h"

class Fox : public Animal
{
public:
	Fox();
	Fox(int posX, int posY, World* world);
	virtual void move(int distance) override;
};

