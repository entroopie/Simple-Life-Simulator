#pragma once
#include "Organism.h"
#include "World.h"

class Plant : public Organism
{
public:
	Plant();
	Plant(string species, char sign, int posX, int posY, int strength, int initiative, World* world);
	void createPlant(char sign, int y, int x);
	void sow();
	virtual void collision(Organism* attacker) override;
	virtual void action() override;
};

