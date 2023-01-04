#include "Plant.h"
#include "Grass.h"
#include "Belladona.h"
#include "Guarana.h"
#include "SowThistle.h"
#include "Sosnowsky.h"

Plant::Plant() {
}

Plant::Plant(string species, char sign, int posX, int posY, int strength, int initiative, World* world)
	: Organism(species, sign, posX, posY, strength, initiative, world) {
}

void Plant::createPlant(char sign, int y, int x) {
	switch (sign) {
	case 'M':
		world->createOrganism(new Grass(x, y, world));
		break;
	case 'P':
		world->createOrganism(new Belladona(x, y, world));
		break;
	case 'I':
		world->createOrganism(new Guarana(x, y, world));
		break;
	case 'X':
		world->createOrganism(new Sosnowsky(x, y, world));
		break;
	case '%':
		world->createOrganism(new SowThistle(x, y, world));
		break;
	}
}

void Plant::collision(Organism* attacker) {
		string temp = this->getSpecies() + " has been EATEN by " + attacker->getSpecies() + "!";
		world->addComment(temp);
		world->setOrganism(attacker->getY(), attacker->getX(), nullptr);
		world->setOrganism(posY, posX, attacker);
		attacker->setY(posY);
		attacker->setX(posX);
		world->removeOrganism(this);
}

void Plant::sow() {
	int random = rand() % 50;
	if (random == 0) {
		int y = this->getY();
		int x = this->getX();
		bool setDir = false;
		int direction;
		while (true) {
			if (world->getField(y - 1, x) != nullptr && world->getField(y + 1, x) != nullptr && world->getField(y, x - 1) != nullptr
				&& world->getField(y, x + 1) != nullptr) {
				break;
			}
			direction = rand() % 4;
			switch (direction) {
			case 0:
				if (y - 1 >= 0 && world->getField(y - 1, x) == nullptr) {
					setDir = true;
					y--;
				}
				break;
			case 1:
				if (y + 1 < world->getHeight() && world->getField(y + 1, x) == nullptr) {
					setDir = true;
					y++;
				}
				break;
			case 2:
				if (x - 1 >= 0 && world->getField(y, x - 1) == nullptr) {
					setDir = true;
					x--;
				}
				break;
			case 3:
				if (x + 1 < world->getWidth() && world->getField(y, x + 1) == nullptr) {
					setDir = true;
					x++;
				}
				break;
			}
			if (setDir) {
				string temp = this->species + " has succesfully sown!";
				world->addComment(temp);
				createPlant(this->draw(), y, x);
				break;
			}
		}
	}
}

void Plant::action() {
	sow();
}

