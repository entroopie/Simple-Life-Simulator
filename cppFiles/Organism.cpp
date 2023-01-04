#include "Organism.h"
#include "World.h"

Organism::Organism() {
	species = "";
	sign = ' ';
	posX = 0;
	posY = 0;
	strength = 0;
	cooldown = 0;
	age = 0;
	initiative = 0;
	world = nullptr;
}

Organism::Organism(string species, char sign, int posX, int posY, int strength, int initiative, World* world)
	: species{ species }, sign{ sign }, posX{ posX }, posY{ posY }, strength{ strength }, initiative{ initiative }, world{ world } {
}

char Organism::draw() {
	return sign;
}

void Organism::advanceAge() {
	this->age++;
}

void Organism::advanceCooldown() {
	if (this->cooldown != 0) this->cooldown--;
}

int Organism::getX() {
	return posX;
}

int Organism::getY() {
	return posY;
}

int Organism::getInitiative() {
	return initiative;
}

int Organism::getAge() {
	return age;
}

int Organism::getCooldown() {
	return cooldown;
}

string Organism::getSpecies() {
	return species;
}

int Organism::getStrength() {
	return strength;
}

void Organism::setCooldown(int number) {
	this->cooldown = number;
}

void Organism::setY(int number) {
	this->posY = number;
}

void Organism::setX(int number) {
	this->posX = number;
}

void Organism::setAge(int number) {
	this->age = number;
}

void Organism::setStrength(int number) {
	this->strength = number;
}

void Organism::setInitiative(int number) {
	this->initiative = number;
}

void Organism::gainStrength() {
	this->strength += 3;
}

string Organism::getInformation() {
	string temp;
	temp = this->getSpecies() + ";" + to_string(this->getX()) + ";" + to_string(this->getY()) + ";" +
		to_string(this->getStrength()) + ";" + to_string(this->getInitiative()) + ";" + to_string(this->getAge()) + ";" + 
		to_string(this->getCooldown());
	return temp;
}

bool compareOrganisms(Organism* first, Organism* second) {
	if (first->getInitiative() > second->getInitiative()) return true;
	else if (first->getInitiative() < second->getInitiative()) return false;
	else if (first->getAge() > second->getAge()) return true;
	else return false;
}




