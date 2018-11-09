package cs361.battleships.models;

public class Battleship extends Ship {

    private Square captainquarters;


    public Battleship (){
        super();
        super.setLength(4);
        this.captainquarters = new Square();
    }

    public void setLocation(){
        this.captainquarters.setColumn(super.getOccupiedSquares().get(2).getColumn());
        this.captainquarters.setRow(super.getOccupiedSquares().get(2).getRow());
    }

    public Square getCaptainquarters() {
        return this.captainquarters;
    }

}
