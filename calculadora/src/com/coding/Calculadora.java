package com.coding;

public class Calculadora implements Estadistica{

    @Override
    public double getPromedio(double... num) {
        double sum = 0;
        int cont = 0;

        for (double a : num) {
            sum +=a;
            cont++;
        }
        return sum/cont;
    }

    @Override
    public double getPromedio(int... num) {
        double sum = 0;
        int cont = 0;

        for (int a : num) {
            sum +=a;
            cont++;
        }
        return sum/cont;
    }

    @Override
    public double getVarianza(double... num) {
        double sum = 0;
        int cont = 0;
        for (double a : num) {
            sum +=a;
            cont++;
        }

        double mean = sum/cont, temp = 0;
        for(double a :num)
            temp += (a-mean)*(a-mean);
        return temp/(num.length-1);
    }
}
