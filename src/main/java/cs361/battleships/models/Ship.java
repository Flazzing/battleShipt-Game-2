package cs361.battleships.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.ArrayList;

public class Ship {

	@JsonProperty protected List<Square> occupiedSquares;
	@JsonProperty protected String kind;

	// true = float; false = sunk
	@JsonProperty protected boolean floatstatus;
	@JsonProperty protected int length;
	@JsonProperty protected int armored;
	@JsonProperty protected Square captainsQuarters;

    public Ship() {
    	occupiedSquares = new ArrayList<>();
    	this.floatstatus = true;
    }

	public Ship(String kind) {
		occupiedSquares = new ArrayList<>();
		this.kind = kind;
	}

	public Ship(Ship other) {
        this.kind = other.getKind();
        this.length = other.getLength();
        this.armored = other.getArmored();
        this.captainsQuarters = other.getCaptainsQuarters();
        this.occupiedSquares = other.getOccupiedSquares();
        this.floatstatus = other.getFloatStatus();
	}
	void setFloatstatus(boolean floatstatus) {
		this.floatstatus = floatstatus;
	}

	boolean getFloatStatus(){return this.floatstatus;}

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
	int getArmored(){ return this.armored; }
	void setArmored(int a) { this.armored = a; }
	void setCaptainsQuarters(){ this.captainsQuarters = this.occupiedSquares.get(1); }
	Square getCaptainsQuarters() { return this.captainsQuarters; }
}
