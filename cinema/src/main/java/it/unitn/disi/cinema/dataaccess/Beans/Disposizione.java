/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.cinema.dataaccess.Beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matteo
 */
public class Disposizione{
	public List<String> dispo;
	
	public Disposizione(){
		this.dispo = new ArrayList();
	};
	
	public void add(int pos, String testo){
		dispo.add(pos, testo);
	};
	
	public void set( int pos, String testo){
		dispo.set(pos, testo);
	};
	
	
	public String get( int pos ){
		return dispo.get(pos);
	};
}