#include "Turtle.h"

Turtle::Turtle() {
}

Turtle::Turtle(int posX, int posY, World* world)
	: Animal("Turtle", 'O', posX, posY, 2, 1, world) {
}

void Turtle::collision(Organism* attacker) {
	if (this->species == attacker->getSpecies() && this->age > 2 && attacker->getAge() > 2
		&& this->cooldown == 0 && attacker->getCooldown() == 0) {
		this->setCooldown(5);
		attacker->setCooldown(5);
		breed(attacker->getY(), attacker->getX());
	}
	else if (this->species != attacker->getSpecies()) {
		if (attacker->getStrength() >= 5) fight(attacker);
		else {
			string temp = this->getSpecies() + " DEFLECTED the attack from " + attacker->getSpecies() + "!";
			world->addComment(temp);
		}
	}
}

void Turtle::action() {
	int	turtleMoves = rand() % 4;
	if (turtleMoves == 3) move(1);
	else {
		string temp = this->getSpecies() + " has decided NOT TO MOVE!";
		world->addComment(temp);
	}
}

void Turtle::move(int distance) {
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
