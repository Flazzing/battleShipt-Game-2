package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	@JsonProperty private String kind;

    public Ship() {
    	occupiedSquares = new ArrayList<>();
    }

	public Ship(String kind) {
	    this.kind = kind;
	}

	List<Square> getOccupiedSquares() {
	    return this.occupiedSquares;
	}
	void setOccupiedSquares(ArrayList<Square> occupiedSquares) {
    	this.occupiedSquares = occupiedSquares;
	}
	String getKind() {
    	return this.kind;
	}
}
