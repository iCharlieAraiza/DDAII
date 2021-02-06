package com.coding;

public class Dijkstra {

    public int dijkstra(int[][] grafo, int nodoOrigen, int nodoFinal){
        int contNodo = grafo.length;
        boolean[] nodoVisitado = new boolean[contNodo];
        int[] distancia = new int[contNodo];

        for (int i = 0; i < contNodo; i++){
            nodoVisitado[i] = false;
            distancia[i] = Integer.MAX_VALUE;
        }
        distancia[nodoOrigen] = 0;
        for (int i = 0; i < contNodo; i++){
            int u = encontrarDistanciaMin(distancia, nodoVisitado);
            nodoVisitado[u] = true;
            for (int v =0 ; v < contNodo; v++){
                if(!nodoVisitado[v] && grafo[u][v] != 0 && (distancia[u] + grafo[u][v] < distancia[v])){
                    distancia[v] = distancia[u] + grafo[u][v];
                }
            }
        }

        for (int i = 0; i < distancia.length; i++){
            if(i==nodoFinal) {
                return distancia[i];
            }
        }
        return 0;
    }

    private int encontrarDistanciaMin(int[] distance, boolean[] visitedVertex) {
        int distanciaMin = Integer.MAX_VALUE;
        int distanciaMinNodo = -1;
        for (int i =0; i < distance.length; i++){
            if(!visitedVertex[i] && distance[i] < distanciaMin){
                distanciaMin = distance[i];
                distanciaMinNodo = i;
            }
        }
        return distanciaMinNodo;
    }

}

