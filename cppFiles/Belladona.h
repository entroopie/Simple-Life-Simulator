#pragma once
#include "Plant.h"

class Belladona : public Plant
{
public:
	Belladona();
	Belladona(int posX, int posY, World* world);
	void collision(Organism* attacker) override;
};

