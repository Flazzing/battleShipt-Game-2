package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty private List<Square> occupiedSquares;
	@JsonProperty private String kind;
	private int length;

    public Ship() {
    	occupiedSquares = new ArrayList<>();
    }

	public Ship(String kind) {
		occupiedSquares = new ArrayList<>();
        this.kind = kind;
		switch (kind) {
			case "MINESWEEPER":
				this.length = 2;
				break;
			case "DESTROYER":
				this.length = 3;
				break;
			case "BATTLESHIP":
				this.length = 4;
				break;
		}
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
	int getLength() {
    	return this.length;
	}
	void setLength(int x){this.length=x;}
}
