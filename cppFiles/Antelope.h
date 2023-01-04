#pragma once
#include "Animal.h"

class Antelope : public Animal
{
public:
	Antelope();
	Antelope(int posX, int posY, World* world);
	virtual void collision(Organism* attacker) override;
	virtual void action() override;
};

