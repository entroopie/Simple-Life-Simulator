#pragma once
#include "World.h"

class God {
private:
	World** world;
public:
	God(World** world);
	void placeHumanRandomly();
	void create();
	void loadOrganism(string name, int posY, int posX, int strength, int initiative, int age, int cooldown);
	void save();
	void load();
};

