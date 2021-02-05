package com.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    int grafo[][];

    @BeforeEach
    void beforeEach(){

        grafo = new int[][] { { 0, 4, 8, 0, 0 },
                { 4, 0, 2, 5, 0 },
                { 8, 2, 0, 5, 9},
                { 0, 5, 5, 0, 4 },
                { 0, 0, 9, 4, 0 } };

    }

    @Test
    void pruebaUno(){
        Dijkstra prueba = new Dijkstra();
        prueba.dijkstra(grafo, 0, 4);
        assertEquals(13, prueba.dijkstra(grafo, 0, 4));
    }


}