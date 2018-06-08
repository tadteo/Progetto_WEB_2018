/*
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
 */
package it.unitn.disi.cinema.dataaccess.Beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matteo
 */
public class Disposizione {

    public List<String> dispo;

    public Disposizione() {
        this.dispo = new ArrayList();
    }

    ;
	
	public void add(int pos, String testo) {
        dispo.add(pos, testo);
    }

    ;
	
	public void set(int pos, String testo) {
        dispo.set(pos, testo);
    }

    ;
	
	
	public String get(int pos) {
        return dispo.get(pos);
    }
;
}
