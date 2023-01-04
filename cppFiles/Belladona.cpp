#include "Belladona.h"

Belladona::Belladona() {
}

Belladona::Belladona(int posX, int posY, World* world)
	: Plant("Belladona", 'P', posX, posY, 10, 0, world) {
}

void Belladona::collision(Organism* attacker) {
	string temp = this->getSpecies() + " POISONED to death " + attacker->getSpecies() + "!";
	world->addComment(temp);
	world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
	world->setOrganism(posY, posX, nullptr);
	world->removeOrganism(attacker);
	world->removeOrganism(this);
}