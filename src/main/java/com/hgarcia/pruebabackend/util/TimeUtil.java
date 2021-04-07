package com.hgarcia.pruebabackend.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public Long calcularHorasPedido(Date fechaPedido){

        Calendar fechaActual = Calendar.getInstance();

        Long diferenciaMilisegundos = Math.abs(fechaActual.getTimeInMillis() - fechaPedido.getTime());

        Long horas = TimeUnit.MILLISECONDS.toHours(diferenciaMilisegundos) + 1;

        System.out.println("horas: " + horas );
        
        return horas;
    }
    
}