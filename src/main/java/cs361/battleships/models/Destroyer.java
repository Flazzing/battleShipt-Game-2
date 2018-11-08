package cs361.battleships.models;

public class Destroyer extends Ship {

    private Square captainsQuarters;
    private int length;

    public Destroyer()
    {
        super();
        this.captainsQuarters = new Square();
        this.length = 3;
    }
    public void setLocation()
    {
        this.captainsQuarters.setColumn(super.getOccupiedSquares().get(1).getColumn());
        this.captainsQuarters.setRow(super.getOccupiedSquares().get(1).getRow());
    }
    Square getCaptainsQuarters()
    {
        return this.captainsQuarters;
    }
}
