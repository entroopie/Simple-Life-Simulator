#include "Guarana.h"

Guarana::Guarana() {
}

Guarana::Guarana(int posX, int posY, World* world)
	: Plant("Guarana", 'I', posX, posY, 0, 0, world) {
}

void Guarana::collision(Organism* attacker) {
	string temp = this->getSpecies() + " has been EATEN and BUFFED " + attacker->getSpecies() + "strength by 3!";
	world->addComment(temp);
	world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
	world->setOrganism(posY, posX, attacker);
	attacker->setY(posY);
	attacker->setX(posX);
	world->removeOrganism(this);
	attacker->gainStrength();
}