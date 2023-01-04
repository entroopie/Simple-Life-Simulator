#include "SowThistle.h"

SowThistle::SowThistle() {
}

SowThistle::SowThistle(int posX, int posY, World* world)
	: Plant("SowThistle", '%', posX, posY, 0, 0, world) {
}

void SowThistle::action() {
	for (int i = 0; i < 3; i++) sow();
}