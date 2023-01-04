#include "Antelope.h"

Antelope::Antelope() {
}

Antelope::Antelope(int posX, int posY, World* world)
	: Animal("Antelope", 'H', posX, posY, 4, 4, world) {
}

void Antelope::collision(Organism* attacker) {
	if (this->species == attacker->getSpecies() && this->age > 2 && attacker->getAge() > 2
		&& this->cooldown == 0 && attacker->getCooldown() == 0) {
		this->setCooldown(5);
		attacker->setCooldown(5);
		breed(attacker->getY(), attacker->getX());
	}
	else if (this->species != attacker->getSpecies()) {
		int flee = rand() % 2;
		if (!flee) fight(attacker);
		else {
			string temp = this->getSpecies() + " FLED from " + attacker->getSpecies() + "!";
			world->addComment(temp);
			world->setOrganism(this->getY(), this->getX(), nullptr);
			this->setY(attacker->getY());
			this->setX(attacker->getX());
			move(2);
		}
	}
}

void Antelope::action() {
	move(2);
}