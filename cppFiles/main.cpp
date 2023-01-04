#include <iostream>
#include <conio.h>
#include <ctime>
#include "World.h"
#include "God.h"
#include "Organism.h"

using namespace std;

int main() {
	srand(time(NULL));

	int y, x;
	char nextTurn;
	World* world = nullptr;
	God* creator = nullptr;

	while (true) {
		cout << "Welcome to my simulation!" << endl;
		cout << "To start a new game, press N" << endl;
		cout << "To load the previous state (if exists) press L" << endl;
		nextTurn = _getch();
		system("cls");
		switch (nextTurn) {
		case 'n':
			cout << "Enter the size of the world [HEIGHT], [WIDTH]: ";
			cin >> y >> x;
			world = new World(y, x);
			creator = new God(&world);
			creator->create();
			creator->placeHumanRandomly();
			break;
		case 'l':
			world = new World();
			creator = new God(&world);
			creator->load();
			break;
		default:
			cout << "Invalid key, try again." << endl;
			break;
		}
		if (nextTurn == 'n' || nextTurn == 'l') break;
	}

	cout << "Human movement: press W - up, S - down, A - left, D - right" << endl;
	cout << "Human special ability: press P" << endl;
	cout << "Press Q to quit the simulation or press K to save the simulation!" << endl;
	cout << "Press any key to proceed..." << endl;

	nextTurn = _getch();

	system("cls");
	cout << "Author: Marcel Boxberger, 188571" << endl;
	cout << "Enter human movement (if alive), then any key to proceed." << endl;
	cout << "Turn no: " << world->getTurn() << endl;
	world->drawWorld();

	while (true) {
		nextTurn = _getch();
		switch (nextTurn) {
		case 'k':
			creator->save();
			cout << "State of the game saved successfully!" << endl;
			break;
		case 'q':
			return 0;
			break;
		default:
			world->makeTurn();
			system("cls");
			cout << "Author: Marcel Boxberger, 188571" << endl;
			cout << "Enter human movement (if alive), then any key to proceed." << endl;
			cout << "Turn no: " << world->getTurn() << endl;
			world->drawWorld();
			break;
		}
	}
	return 0;
} 