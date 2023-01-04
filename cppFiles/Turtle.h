#pragma once
#include "Animal.h"

class Turtle : public Animal
{
public:
	Turtle();
	Turtle(int posX, int posY, World* world);
	virtual void move(int distance) override;
	virtual void action() override;
	virtual void collision(Organism* attacker) override;
};

