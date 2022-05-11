package com.estech.recyclerviewexample.model


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Repository {

    private var listaJugadores = ArrayList<Jugador>()

    fun crearLista(): ArrayList<Jugador> {
        val jugador1 = Jugador("Casillas", "Portero", 1)
        val jugador2 = Jugador("Puyol", "Defensa", 5)
        val jugador3 = Jugador("Sergio Ramos", "Defensa", 15)
        val jugador4 = Jugador("Iniesta", "Centrocampista", 8)
        val jugador5 = Jugador("Xavi", "Centrocampista", 6)
        val jugador6 = Jugador("Silva", "Mediapunta", 21)
        val jugador7 = Jugador("Villa", "Delantero", 7)
        val jugador8 = Jugador("Fernando torres", "Delantero", 9)

        listaJugadores = arrayListOf(
            jugador1, jugador2, jugador3, jugador4, jugador5,
            jugador6, jugador7, jugador8
        )
        return listaJugadores
    }
}