#include "Wolf.h"

Wolf::Wolf() {
}

Wolf::Wolf(int posX, int posY, World* world)
	: Animal("Wolf", '>', posX, posY, 9, 5, world) {
}