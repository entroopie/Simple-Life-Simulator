#pragma once
#include "Plant.h"

class Sosnowsky : public Plant
{
public:
	Sosnowsky();
	Sosnowsky(int posX, int posY, World* world);
	void collision(Organism* attacker) override;
	void action() override;
};

