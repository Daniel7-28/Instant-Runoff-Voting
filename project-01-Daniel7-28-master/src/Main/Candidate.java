package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.ArrayList;

/**
 * The Candidate Running for President
 * 
 * @author Juan D. Pérez Sepúlveda
 * @version 2.0
 * @since 2022-02-22
 */

public class Candidate {

	private String name;		// Name of a Candidate
	private int id;				// ID of a Candidate
	private boolean active;		// Active flag set true by default.

	public Candidate(String name, int id) {
		this.name = name;
		this.id = id;
		this.active = true;
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
