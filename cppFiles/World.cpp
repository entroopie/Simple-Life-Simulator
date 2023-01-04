#include "World.h"
#include "Organism.h"
#include <algorithm>
#include <stdio.h>

World::World(int height, int width) :
	height(height), width(width) {
	turn = 0;
	population = 0;
	howManyComments = 0;
	board = new Organism** [height];
	commentator = new string[height * width];
	for (int i = 0; i < height; i++)
		board[i] = new Organism * [width];
	for (int i = 0; i < height; i++) 
		for (int j = 0; j < width; j++)
			board[i][j] = nullptr;
}

int World::getHeight() {
	return height;
}

int World::getWidth() {
	return width;
}

Organism* World::getField(int y, int x) {
	if (y >= 0 && y < height && x >= 0 && x < width) return board[y][x];
}

Organism* World::getOrganism(int index) {
	return organisms[index];
}

int World::getPopulation() {
	return population;
}

void World::setHeight(int number) {
	this->height = number;
}

void World::setWidth(int number) {
	this->width = number;
}

void World::setPopulation(int number) {
	this->population = number;
}

void World::setTurn(int number) {
	this->turn = number;
}

void World::drawWorld() {
	int temp = -1;
	cout << "  ";
	for (int i = 0; i < width; i++) {
		if (i % 10 == 0) temp++;
		cout << temp;
	}
	cout << endl;
	cout << "  ";
	for (int i = 0; i < width; i++) cout << i % 10;
	cout << endl;
	for (int i = 0; i < height; i++) {
		if (i < 10) cout << "0" << i;
		else cout << i;
		for (int j = 0; j < width; j++) {
			if (board[i][j] != nullptr) {
				cout << board[i][j]->draw();
			}
			else cout << " ";
		}
		cout << endl;
	}
	for (int i = 0; i < howManyComments; i++) {
		cout << commentator[i] << endl;
	}
	howManyComments = 0;
}

void World::makeTurn() {
	turn++;
	for (int i = 0; i < population; i++) {
		organisms[i]->advanceAge();
		organisms[i]->advanceCooldown();
		organisms[i]->action();
	}
}

void World::addComment(string comment) {
	commentator[howManyComments] = comment;
	howManyComments++;
}

void World::setOrganism(int y, int x, Organism* organism) {
	board[y][x] = organism;
}

void World::createOrganism(Organism* organism) {
	organisms.push_back(organism);
	setOrganism(organism->getY(), organism->getX(), organism);
	population++;
	sort(organisms.begin(), organisms.end(), compareOrganisms);
}

void World::removeOrganism(Organism* organism) {
	for (int i = 0; i < population; i++) {
		if (organisms[i] == organism) {
			organisms.erase(organisms.begin() + i);
			population--;
			break;
		}
	}
	delete organism;
	sort(organisms.begin(), organisms.end(), compareOrganisms);
}

int World::getTurn() {
	return this->turn;
}

World::~World() {
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			delete board[i][j];
		}
	}
	delete[] commentator;
	organisms.clear();
}