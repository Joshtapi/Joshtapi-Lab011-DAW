package com.miempresa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {

    @GetMapping("/verNumerosPrimos")
    public String verNumerosPrimos(@RequestParam("inicio") int inicio, @RequestParam("fin") int fin, Model model) {
        List<Integer> numerosPrimos = calcularNumerosPrimos(inicio, fin);
        model.addAttribute("numerosPrimos", numerosPrimos);
        return "resultados";
    }
    //Numeros Primos del 2 al 97
    private List<Integer> calcularNumerosPrimos(int inicio, int fin) {
        List<Integer> numerosPrimos = new ArrayList<>();
        
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                numerosPrimos.add(i);
            }
        }
        return numerosPrimos;
    }

    private boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    } 
    //Tabla de multiplicar 
    @GetMapping("/verTablaMultiplicar")
    public String verTablaMultiplicar(@RequestParam("numeroTabla") int numeroTabla, Model model) {
        List<NumeroMultiplicado> tablaMultiplicar = generarTablaMultiplicar(numeroTabla);
        model.addAttribute("numeroTabla", numeroTabla); // Agrega el número por el que se está multiplicando al modelo
        model.addAttribute("tablaMultiplicar", tablaMultiplicar);
        return "resultados2";
    }
    
    private List<NumeroMultiplicado> generarTablaMultiplicar(int numeroTabla) {
        List<NumeroMultiplicado> tablaMultiplicar = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int resultado = numeroTabla * i;
            boolean esPar = resultado % 2 == 0;
            tablaMultiplicar.add(new NumeroMultiplicado(resultado, esPar));
        }
        return tablaMultiplicar;
    }
    
    public class NumeroMultiplicado {
        private int resultado;
        private boolean esPar;
    
        public NumeroMultiplicado(int resultado, boolean esPar) {
            this.resultado = resultado;
            this.esPar = esPar;
        }
    
        public int getResultado() {
            return resultado;
        }
    
        public boolean isEsPar() {
            return esPar;
        }
    }           
}