#pragma once
#include <iostream>
#include <vector>
#include <utility>
#include <string>
using namespace std;

#define HEIGHT 15
#define WIDTH 15

class Organism;

class World
{
private:
	int population;
	int height;
	int width;
	int howManyComments;
	int turn;
	string* commentator;
	vector<Organism*> organisms;
	Organism*** board;
public:
	World(int height = HEIGHT, int width = WIDTH);

	void drawWorld();
	void makeTurn();
	void addComment(string comments);
	int getHeight();
	int getWidth();
	Organism* getField(int y, int x);
	Organism* getOrganism(int index);
	int getPopulation();
	void setHeight(int number);
	void setWidth(int number);
	void setPopulation(int number);
	void setTurn(int number);
	void setOrganism(int y, int x, Organism* organism);
	void createOrganism(Organism* organism);
	void removeOrganism(Organism* organism);
	int getTurn();

	~World();
};

