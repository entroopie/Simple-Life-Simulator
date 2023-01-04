#pragma once
#include "Organism.h"
#include "World.h"

enum directions {
	UP,
	DOWN,
	LEFT,
	RIGHT,
};

class Animal : public Organism
{
public:
	Animal();
	Animal(string species, char sign, int posX, int posY, int strength, int initiative, World* world);
	void createChild(char sign, int y, int x);
	void breed(int y, int x);
	void fight(Organism* attacker);
	virtual void move(int distance);
	virtual void collision(Organism* attacker) override;
	virtual void action() override;
};

