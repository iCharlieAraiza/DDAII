package com.coding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {
    Calculadora calculadora;

    @BeforeEach
    void beforeEach(){
         calculadora = new Calculadora();
    }

    @Test
    void comprobarPromedio(){
        assertEquals(5.0, calculadora.getPromedio(2, 4, 6, 8));
    }

    @Test
    void comprobarPromedioDos(){
        assertEquals(4.325, calculadora.getPromedio(2.3,2,10,3));
    }

    @Test
    void comprobarVarianza(){
        assertEquals(1.7, calculadora.getVarianza(2 ,2, 3, 2,5) );
    }

}