#include "Fox.h"

Fox::Fox() {
}

Fox::Fox(int posX, int posY, World* world)
	: Animal("Fox", 'v', posX, posY, 3, 7, world) {
}

void Fox::move(int distance) {
	bool dirSet = false;
	int direction;
	int ty = posY;
	int tx = posX;
		while (true) {
			ty = posY;
			tx = posX;
			direction = rand() % 4;
			switch (direction) {
			case UP:
				if (posY - distance >= 0) {
					dirSet = true;
					ty -= distance;
				}
				break;
			case DOWN:
				if (posY + distance < world->getHeight()) {
					dirSet = true;
					ty += distance;
				}
				break;
			case LEFT:
				if (posX - distance >= 0) {
					dirSet = true;
					tx -= distance;
				}
				break;
			case RIGHT:
				if (posX + distance < world->getWidth()) {
					dirSet = true;
					tx += distance;
				}
				break;
			}
			if (dirSet) {
				if (world->getField(ty, tx) != nullptr) {
					if (this->getStrength() >= world->getField(ty, tx)->getStrength()) {
						world->getField(ty, tx)->collision(this);
						break;
					}
					else {
						if (world->getField(ty - distance, tx) != nullptr && world->getField(ty + distance, tx) != nullptr && world->getField(ty, tx - distance) != nullptr
							&& world->getField(ty, tx + distance) != nullptr) {
							string temp = this->getSpecies() + " is not able to move!";
							world->addComment(temp);
							break;
						}
						else {
							dirSet = false;
						}
					}
				}
				else {
					world->setOrganism(posY, posX, nullptr);
					this->setY(ty);
					this->setX(tx);
					world->setOrganism(ty, tx, this);
					break;
				}
			}
		}
}