#include "God.h"
#include "Wolf.h"
#include "Sheep.h"
#include "Fox.h"
#include "Turtle.h"
#include "Antelope.h"
#include "Grass.h"
#include "Belladona.h"
#include "Guarana.h"
#include "SowThistle.h"
#include "Sosnowsky.h"
#include "Human.h"
#include <fstream>

#define TYPES 10

enum cases {
	sheep,
	wolf,
	fox,
	anthelope,
	guarana,
	sosnowsky,
	grass,
	sowthistle,
	turtle,
	belladona
};

enum cases2 {
	NAME,
	X,
	Y,
	STRENGTH,
	INIT,
	AGE,
	COOLDWN
};

enum cases3 {
	HEIGHTT,
	WIDTHH,
	TURN
};

God::God(World** world) : world{ world } {
}

void God::placeHumanRandomly() {
	int ry, rx;
	while (true) {
		ry = rand() % (*world)->getHeight();
		rx = rand() % (*world)->getWidth();
		if ((*world)->getField(ry, rx) != nullptr) {
			(*world)->createOrganism(new Human(ry, rx, (*world)));
			break;
		}
	}
}

void God::create() {
	int random;
	for (int i = 1; i < (*world)->getWidth(); i++) {
		for (int j = 1; j < (*world)->getHeight(); j++) {
			random = rand() % 20;
			if (random == 0) {
				random = rand() % 10;
				switch (random) {
				case sheep:
					(*world)->createOrganism(new Sheep(i, j, (*world)));
					break;
				case wolf:
					(*world)->createOrganism(new Wolf(i, j, (*world)));
					break;
				case belladona:
					(*world)->createOrganism(new Belladona(i, j, (*world)));
					break;
				case sowthistle:
					(*world)->createOrganism(new SowThistle(i, j, (*world)));
					break;
				case grass:
					(*world)->createOrganism(new Grass(i, j, (*world)));
					break;
				case turtle:
					(*world)->createOrganism(new Turtle(i, j, (*world)));
					break;
				case guarana:
					(*world)->createOrganism(new Guarana(i, j, (*world)));
					break;
				case sosnowsky:
					(*world)->createOrganism(new Sosnowsky(i, j, (*world)));
					break;
				case anthelope:
					(*world)->createOrganism(new Antelope(i, j, (*world)));
					break;
				case fox:
					(*world)->createOrganism(new Fox(i, j, (*world)));
					break;
				}
			}
		}
	}
}

void God::loadOrganism(string name, int posY, int posX, int strength, int initiative, int age, int cooldown) {
	if (name == "Wolf") {
		(*world)->createOrganism(new Wolf(posX, posY, (*world)));
	}
	else if (name == "Sheep") {
		(*world)->createOrganism(new Sheep(posX, posY, (*world)));
	}
	else if (name == "Fox") {
		(*world)->createOrganism(new Fox(posX, posY, (*world)));
	}
	else if (name == "Antelope") {
		(*world)->createOrganism(new Antelope(posX, posY, (*world)));
	}
	else if (name == "Turtle") {
		(*world)->createOrganism(new Turtle(posX, posY, (*world)));
	}
	else if (name == "Grass") {
		(*world)->createOrganism(new Grass(posX, posY, (*world)));
	}
	else if (name == "SowThistle") {
		(*world)->createOrganism(new SowThistle(posX, posY, (*world)));
	}
	else if (name == "Belladona") {
		(*world)->createOrganism(new Belladona(posX, posY, (*world)));
	}
	else if (name == "Guarana") {
		(*world)->createOrganism(new Guarana(posX, posY, (*world)));
	}
	else if (name == "Sosnowsky's Hogweed") {
		(*world)->createOrganism(new Sosnowsky(posX, posY, (*world)));
	}
	else if (name == "Human") {
		(*world)->createOrganism(new Human(posX, posY, (*world)));
	}
	Organism* org = (*world)->getField(posY, posX);
	if (org != nullptr) {
		org->setAge(age);
		org->setCooldown(cooldown);
		org->setStrength(strength);
		org->setInitiative(initiative);
	}
	if (name == "Human") {

	}
}

void God::save() {
	fstream file, data;
	file.open("save.txt", ios::trunc);
	data.open("world.txt", ios::trunc);
	data.close();
	file.close();
	file.open("save.txt", ios::out);
	data.open("world.txt", ios::out);
	data << (*world)->getHeight() << ";" << (*world)->getWidth() << ";" << (*world)->getTurn() << endl;
	for (int i = 0; i < (*world)->getPopulation(); i++) {
		file << (*world)->getOrganism(i)->getInformation() << endl;
	}
	file.close();
}

void God::load() {
	fstream file, data;
	file.open("save.txt", ios::in);
	data.open("world.txt", ios::in);
	int next = 0;
	string temp;
	string name = "", y = "", x = "", strength = "", initiative = "", age = "", cooldown = "";
	string height = "", width = "", turn = "", population = "";
	while (getline(data, temp)) {
		next = 0;
		for (int i = 0; i < temp.length(); i++) {
			if (temp[i] == ';') {
				i++;
				next++;
			}
			switch (next) {
			case HEIGHTT:
				height += temp[i];
				break;
			case WIDTHH:
				width += temp[i];
				break;
			case TURN:
				turn += temp[i];
				break;
			}
		}
	}
	delete (*world);
	(*world) = new World(atoi(height.c_str()), atoi(width.c_str()));
	(*world)->setTurn(atoi(turn.c_str()));
	while (getline(file, temp)) {
		next = 0;
		name = "";
		y = "";
		x = "";
		strength = "";
		initiative = "";
		age = "";
		cooldown = "";
		for (int i = 0; i < temp.length(); i++) {
			if (temp[i] == ';') {
				i++;
				next++;
			}
			switch (next) {
			case NAME:
				name += temp[i];
				break;
			case X:
				x += temp[i];
				break;
			case Y:
				y += temp[i];
				break;
			case STRENGTH:
				strength += temp[i];
				break;
			case INIT:
				initiative += temp[i];
				break;
			case AGE:
				age += temp[i];
				break;
			case COOLDWN:
				cooldown += temp[i];
				break;
			}
		}
		this->loadOrganism(name, atoi(y.c_str()), atoi(x.c_str()), atoi(strength.c_str()), atoi(initiative.c_str()), atoi(age.c_str()), atoi(cooldown.c_str()));
	}
	file.close();
	data.close();
}