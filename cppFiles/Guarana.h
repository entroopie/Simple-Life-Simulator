#pragma once
#include "Plant.h"

class Guarana : public Plant
{
public:
	Guarana();
	Guarana(int posX, int posY, World* world);
	void collision(Organism* attacker) override;
};

