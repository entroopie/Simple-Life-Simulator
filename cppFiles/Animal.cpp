#include "Animal.h"
#include "Wolf.h"
#include "Sheep.h"
#include "Fox.h"
#include "Turtle.h"
#include "Antelope.h"

Animal::Animal() : Organism() {
}

Animal::Animal(string species, char sign, int posX, int posY, int strength, int initiative, World* world)
	: Organism(species, sign, posX, posY, strength, initiative, world) {
}

void Animal::createChild(char sign, int y, int x) {
	switch (sign) {
	case '^':
		world->createOrganism(new Sheep(x, y, world));
		break;
	case '>':
		world->createOrganism(new Wolf(x, y, world));
		break;
	case 'v':
		world->createOrganism(new Fox(x, y, world));
		break;
	case 'O':
		world->createOrganism(new Turtle(x, y, world));
		break;
	case 'H':
		world->createOrganism(new Antelope(x, y, world));
		break;
	}
}

void Animal::breed(int y, int x) {
	bool setDir = false;
	int direction;
	while (true) {
		if (world->getField(y - 1, x) != nullptr && world->getField(y + 1, x) != nullptr && world->getField(y, x - 1) != nullptr
			&& world->getField(y, x + 1) != nullptr) {
			break;
		}
		direction = rand() % 4;
		switch (direction) {
		case UP:
			if (y - 1 >= 0 && world->getField(y - 1, x) == nullptr) { 
				setDir = true;
				y--;
			}
			break;
		case DOWN:
			if (y + 1 < world->getHeight() && world->getField(y + 1, x) == nullptr) {
				setDir = true;
				y++;
			}
			break;
		case LEFT:
			if (x - 1 >= 0 && world->getField(y, x - 1) == nullptr) {
				setDir = true;
				x--;
			}
			break;
		case RIGHT:
			if (x + 1 < world->getWidth() && world->getField(y, x + 1) == nullptr) {
				setDir = true;
				x++;
			}
			break;
		}
		if (setDir) {
			string temp = this->species + " has made a new child!";
			world->addComment(temp);
			createChild(this->draw(), y, x);
			break;
		}
	}
}

void Animal::fight(Organism* attacker) {
	if (this->getStrength() <= attacker->getStrength()) {
		string temp = this->getSpecies() + " LOST the fight with " + attacker->getSpecies() + "!";
		world->addComment(temp);
		world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
		world->setOrganism(posY, posX, attacker);
		attacker->setY(posY);
		attacker->setX(posX);
		world->removeOrganism(this);
	}
	else {
		string temp = this->getSpecies() + " WON the fight with " + attacker->getSpecies() + "!";
		world->addComment(temp);
		world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
		world->removeOrganism(attacker);
	}
}

void Animal::collision(Organism* attacker) {
	if (this->species == attacker->getSpecies() && this->age > 2 && attacker->getAge() > 2
		&& this->cooldown == 0 && attacker->getCooldown() == 0 && this->species != "Human") {
		this->setCooldown(5);
		attacker->setCooldown(5);
		breed(attacker->getY(), attacker->getX());
	}
	else if (this->species != attacker->getSpecies()) fight(attacker);
}

void Animal::action() {
	this->move(1);
}

void Animal::move(int distance) {
	bool dirSet = false;
	int direction;
	int ty = posY;
	int tx = posX;
	while (true) {
		direction = rand() % 4;
		switch (direction) {
		case 0:
			if (posY - distance >= 0) {
				dirSet = true;
				ty -= distance;
			}
			break;
		case 1:
			if (posY + distance < world->getHeight()) {
				dirSet = true;
				ty += distance;
			}
			break;
		case 2:
			if (posX - distance >= 0) {
				dirSet = true;
				tx -= distance;
			}
			break;
		case 3:
			if (posX + distance < world->getWidth()) {
				dirSet = true;
				tx += distance;
			}
			break;
		}
		if (dirSet) {
			if (world->getField(ty, tx) != nullptr) {
				world->getField(ty, tx)->collision(this);
			}
			else {
				world->setOrganism(posY, posX, nullptr);
				this->setY(ty);
				this->setX(tx);
				world->setOrganism(ty, tx, this);
			}
			break;
		}
	}
}