#include "Grass.h"

Grass::Grass() {
}

Grass::Grass(int posX, int posY, World* world)
	: Plant("Grass", 'M', posX, posY, 0, 0, world) {
}