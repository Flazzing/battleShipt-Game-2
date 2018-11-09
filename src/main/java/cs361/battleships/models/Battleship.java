package cs361.battleships.models;

import java.util.ArrayList;

public class Battleship extends Ship {

    private Square captainquarters;


    public Battleship (){
        super();
        super.setLength(4);
        this.captainquarters = new Square();
        super.setFloatstatus(true);
    }

    public void setLocation(){
        this.captainquarters.setColumn(super.getOccupiedSquares().get(2).getColumn());
        this.captainquarters.setRow(super.getOccupiedSquares().get(2).getRow());
    }

    public Square getCaptainsQuarters() {
        return this.captainquarters;
    }

}
