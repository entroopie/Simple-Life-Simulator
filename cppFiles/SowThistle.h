#pragma once
#include "Plant.h"

class SowThistle : public Plant
{
public:
	SowThistle();
	SowThistle(int posX, int posY, World* world);
	void action() override;
};

