#include "Human.h"
#include <conio.h>

#define MOVEUP 115
#define MOVEDOWN 119
#define MOVELEFT 97
#define MOVERIGHT 100
#define SPECIAL 55

Human::Human() {
}

Human::Human(int posX, int posY, World* world)
	: Animal("Human", 'T', posX, posY, 5, 4, world) {
}

void Human::strengthPotion() {
	string temp = "Human wyzerowal the whole strength potion and his strength increased to 10!";
	world->addComment(temp);
	this->setCooldown(10 - this->strength);
	this->strength = 10;
	this->potion = true;
}

void Human::move(int direction) {
	int ty, tx;
	ty = posY;
	tx = posX;
	switch (direction) {
	case UP:
		if (posY + 1 < world->getHeight()) {
			ty++;
		}
		break;
	case DOWN:
		if (posY - 1 >= 0) {
			ty--;
		}
		break;
	case LEFT:
		if (posX - 1 >= 0) {
			tx--;
		}
		break;
	case RIGHT:
		if (posX + 1 < world->getWidth()) {
			tx++;
		}
		break;
	default:
		string temp = "Human doesn't move!";
		world->addComment(temp);
		break;
	}
	if (world->getField(ty, tx) != nullptr) {
		world->getField(ty, tx)->collision(this);
	}
	else {
		world->setOrganism(posY, posX, nullptr);
		this->setY(ty);
		this->setX(tx);
		world->setOrganism(ty, tx, this);
	}
}

int Human::convertDirection(char dir) {
	switch (dir) {
	case MOVEUP:
		return UP;
		break;
	case MOVEDOWN:
		return DOWN;
		break;
	case MOVELEFT:
		return LEFT;
		break;
	case MOVERIGHT:
		return RIGHT;
		break;
	}
}

void Human::action() {
	if (this->cooldown == 0 && this->potion) {
		string temp = "The strength potion worn off! Strength is back to normal.";
		world->addComment(temp);
		this->strength--;
		this->setCooldown(5);
		this->potion = false;
	}
	else if (this->potion) this->strength--;
	char movement = _getch();
	if (movement == 'p' && this->cooldown == 0 && !this->potion) strengthPotion();
	else move(convertDirection(movement));
}