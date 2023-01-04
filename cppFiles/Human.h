#pragma once
#include "Animal.h"

class Human : public Animal
{
private:
	bool potion = false;
public:
	Human();
	Human(int posX, int posY, World* world);
	void strengthPotion();
	int convertDirection(char dir);
	void move(int direction) override;
	void action() override;
};

