#include "Sheep.h"

Sheep::Sheep() {
}

Sheep::Sheep(int posX, int posY, World* world)
	: Animal("Sheep", '^', posX, posY, 4, 4, world) {
}