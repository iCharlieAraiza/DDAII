#include <iostream>
#include <vector>

#define INT_MAX 10000

using namespace std;

void DijkstrasTest();

int main() {
  DijkstrasTest();
  return 0;
}

class Nodo;
class Arista;

void Dijkstras();
vector<Nodo*>* NodosAdyacentesRestantes(Nodo* nodo);
Nodo* ExtractSmallest(vector<Nodo*>& nodes);
int Distancia(Nodo* nodo1, Nodo* nodo2);
bool Contiente(vector<Nodo*>& nodes, Nodo* nodo);
void ImprimirMinimoCaminoA(Nodo* destino);

vector<Nodo*> nodes;
vector<Arista*> aristas;

class Nodo {
   public:
  Nodo(char id)
    : id(id), prev(NULL), distanciaDesdeInicio(INT_MAX) {
    nodes.push_back(this);
  }

   public:
  char id;
  Nodo* prev;
  int distanciaDesdeInicio;
};

class Arista {
   public:
  Arista(Nodo* nodo1, Nodo* nodo2, int distancia)
    : nodo1(nodo1), nodo2(nodo2), distancia(distancia) {
    aristas.push_back(this);
  }
  bool Conecta(Nodo* nodo1, Nodo* nodo2) {
    return (
      (nodo1 == this->nodo1 &&
       nodo2 == this->nodo2) ||
      (nodo1 == this->nodo2 &&
       nodo2 == this->nodo1));
  }

   public:
  Nodo* nodo1;
  Nodo* nodo2;
  int distancia;
};

///////////////////
void DijkstrasTest() {
  Nodo* a = new Nodo('a');
  Nodo* b = new Nodo('b');
  Nodo* c = new Nodo('c');
  Nodo* d = new Nodo('d');
  Nodo* e = new Nodo('e');
  Nodo* f = new Nodo('f');
  Nodo* g = new Nodo('g');

  Arista* e1 = new Arista(a, c, 1);
  Arista* e2 = new Arista(a, d, 2);
  Arista* e3 = new Arista(b, c, 2);
  Arista* e4 = new Arista(c, d, 1);
  Arista* e5 = new Arista(b, f, 3);
  Arista* e6 = new Arista(c, e, 3);
  Arista* e7 = new Arista(e, f, 2);
  Arista* e8 = new Arista(d, g, 1);
  Arista* e9 = new Arista(g, f, 1);

  a->distanciaDesdeInicio = 0;  // set start nodo
  Dijkstras();
  ImprimirMinimoCaminoA(f);
}

///////////////////

void Dijkstras() {
  while (nodes.size() > 0) {
    Nodo* minimo = ExtractSmallest(nodes);
    vector<Nodo*>* nodosAdyacentes =
      NodosAdyacentesRestantes(minimo);

    const int size = nodosAdyacentes->size();
    for (int i = 0; i < size; ++i) {
      Nodo* adyacente = nodosAdyacentes->at(i);
      int distancia = Distancia(minimo, adyacente) +
               minimo->distanciaDesdeInicio;

      if (distancia < adyacente->distanciaDesdeInicio) {
        adyacente->distanciaDesdeInicio = distancia;
        adyacente->prev = minimo;
      }
    }
    delete nodosAdyacentes;
  }
}

Nodo* ExtractSmallest(vector<Nodo*>& nodes) {
  int size = nodes.size();
  if (size == 0) return NULL;
  int smallestPosition = 0;
  Nodo* minimo = nodes.at(0);
  for (int i = 1; i < size; ++i) {
    Nodo* current = nodes.at(i);
    if (current->distanciaDesdeInicio <
      minimo->distanciaDesdeInicio) {
      minimo = current;
      smallestPosition = i;
    }
  }
  nodes.erase(nodes.begin() + smallestPosition);
  return minimo;
}


vector<Nodo*>* NodosAdyacentesRestantes(Nodo* nodo) {
  vector<Nodo*>* nodosAdyacentes = new vector<Nodo*>();
  const int size = aristas.size();
  for (int i = 0; i < size; ++i) {
    Arista* arista = aristas.at(i);
    Nodo* adyacente = NULL;
    if (arista->nodo1 == nodo) {
      adyacente = arista->nodo2;
    } else if (arista->nodo2 == nodo) {
      adyacente = arista->nodo1;
    }
    if (adyacente && Contiente(nodes, adyacente)) {
      nodosAdyacentes->push_back(adyacente);
    }
  }
  return nodosAdyacentes;
}

// Return distancia between two connected nodes
int Distancia(Nodo* nodo1, Nodo* nodo2) {
  const int size = aristas.size();
  for (int i = 0; i < size; ++i) {
    Arista* arista = aristas.at(i);
    if (arista->Conecta(nodo1, nodo2)) {
      return arista->distancia;
    }
  }
  return -1;  
}

bool Contiente(vector<Nodo*>& nodes, Nodo* nodo) {
  const int size = nodes.size();
  for (int i = 0; i < size; ++i) {
    if (nodo == nodes.at(i)) {
      return true;
    }
  }
  return false;
}


void ImprimirMinimoCaminoA(Nodo* destino) {
  Nodo* prev = destino;
  cout << "Distancia from start: "
     << destino->distanciaDesdeInicio << endl;
  while (prev) {
    cout << prev->id << " ";
    prev = prev->prev;
  }
  cout << endl;
}

vector<Arista*>* AristasAdyacentes(vector<Arista*>& Edges, Nodo* nodo);
void RemoverArista(vector<Arista*>& Edges, Arista* arista);

vector<Arista*>* AristasAdyacentes(vector<Arista*>& aristas, Nodo* nodo) {
  vector<Arista*>* adjacentEdges = new vector<Arista*>();

  const int size = aristas.size();
  for (int i = 0; i < size; ++i) {
    Arista* arista = aristas.at(i);
    if (arista->nodo1 == nodo) {
      cout << "adyacente: " << arista->nodo2->id << endl;
      adjacentEdges->push_back(arista);
    } else if (arista->nodo2 == nodo) {
      cout << "adyacente: " << arista->nodo1->id << endl;
      adjacentEdges->push_back(arista);
    }
  }
  return adjacentEdges;
}

void RemoverArista(vector<Arista*>& aristas, Arista* arista) {
  vector<Arista*>::iterator it;
  for (it = aristas.begin(); it < aristas.end(); ++it) {
    if (*it == arista) {
      aristas.erase(it);
      return;
    }
  }
}