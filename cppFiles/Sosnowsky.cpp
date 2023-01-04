#include "Sosnowsky.h"

Sosnowsky::Sosnowsky() {
}

Sosnowsky::Sosnowsky(int posX, int posY, World* world)
	: Plant("Sosnowsky's Hogweed", 'X', posX, posY, 10, 0, world) {
}

void Sosnowsky::collision(Organism* attacker) {
	string temp = this->getSpecies() + " POISONED to death " + attacker->getSpecies() + "!";
	world->addComment(temp);
	world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
	world->setOrganism(posY, posX, nullptr);
	world->removeOrganism(attacker);
	world->removeOrganism(this);
}

void Sosnowsky::action() {
	Plant* tmp;
	int west = max(posX - 1, 0);
	int east = min(posX + 1, world->getWidth() - 1);
	int north = max(posY - 1, 0);
	int south = min(posY + 1, world->getHeight() - 1);
	system("pause");

	if (world->getField(north, posX) != nullptr && world->getField(north, posX) != this && !(tmp = dynamic_cast<Plant*>(world->getField(north, posX)))) {
		string temp = this->getSpecies() + " DEVOURED " + world->getField(north, posX)->getSpecies() + "!";
		world->addComment(temp);
		world->removeOrganism(world->getField(north, posX));
		world->setOrganism(north, posX, nullptr);
	}
	if (world->getField(south, posX) != nullptr && world->getField(south, posX) != this && !(tmp = dynamic_cast<Plant*>(world->getField(south, posX)))) {
		string temp = this->getSpecies() + " DEVOURED " + world->getField(south, posX)->getSpecies() + "!";
		world->addComment(temp);
		world->removeOrganism(world->getField(south, posX));
		world->setOrganism(south, posX, nullptr);
	}
	if (world->getField(posY, west) != nullptr && world->getField(posY, west) != this && !(tmp = dynamic_cast<Plant*>(world->getField(posY, west)))) {
		string temp = this->getSpecies() + " DEVOURED " + world->getField(posY, west)->getSpecies() + "!";
		world->addComment(temp);
		world->removeOrganism(world->getField(posY, west));
		world->setOrganism(posY, west, nullptr);
	}
	if (world->getField(posY, east) != nullptr && world->getField(posY, east) != this && !(tmp = dynamic_cast<Plant*>(world->getField(posY, east)))) {
		string temp = this->getSpecies() + " DEVOURED " + world->getField(posY, east)->getSpecies() + "!";
		world->addComment(temp);
		world->removeOrganism(world->getField(posY, east));
		world->setOrganism(posY, east, nullptr);
	}
	sow();
}