package cs361.battleships.models;

public class Battleship extends Ship {

    private Square captainsQuarters;
    private int length;

    public Battleship (){
        super();
        this.captainsQuarters = new Square();
        this.length = 4;
    }

    public void setLocation(){
        this.captainsQuarters.setColumn(super.getOccupiedSquares().get(2).getColumn());
        this.captainsQuarters.setRow(super.getOccupiedSquares().get(2).getRow());
    }

    public Square getCaptainsQuarters() {
        return this.captainsQuarters;
    }

}
