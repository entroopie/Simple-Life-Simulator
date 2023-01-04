#pragma once
#include "World.h"

class Organism
{
protected:
	int age;
	int strength;
	int initiative;
	int cooldown;
	int posX;
	int posY;
	char sign;
	string species;
	World* world;
public:
	Organism();
	Organism(string species, char sign, int posX, int posY, int strength, int initiative, World* world);

	virtual void action() = 0;
	virtual void collision(Organism* attacker) = 0;
	char draw();
	void advanceAge();
	void advanceCooldown();
	int getX();
	int getY();
	int getInitiative();
	int getAge();
	int getStrength();
	int getCooldown();
	string getSpecies();
	void setCooldown(int number);
	void setY(int number);
	void setX(int number);
	void setAge(int number);
	void setInitiative(int number);
	void setStrength(int number);
	void gainStrength();
	string getInformation();
};

bool compareOrganisms(Organism* first, Organism* second);


