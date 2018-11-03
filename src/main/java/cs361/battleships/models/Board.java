package cs361.battleships.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Ship> ships;
    private List<Result> attacks;
    private List<Square> sonars;
    private int rows;
    private int cols;
    private int numSonars;
	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Board() {
	    this.rows = 10;
	    this.cols = 10;
	    this.numSonars = 2;
	    this.ships = new ArrayList<Ship>();
		this.attacks = new ArrayList<Result>();
		this.sonars = new ArrayList<Square>();
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public boolean placeShip(Ship ship, int x, char y, boolean isVertical) {
	    int length;
	    ArrayList<Square> targetSquares = new ArrayList<>();
	    // Check that type of ship hasn't already been placed
		for (Ship s : this.ships) {
			if (s.getKind().equals(ship.getKind())) {
				return false;
			}
		}
	    // Get ships length
        length = ship.getLength();

	    // Add all target squares to list based on length of ship
		for (int i = 0 ; i < length; i++) {
			if (isVertical) {
			    targetSquares.add(new Square(x + i, y));
			} else {
				// Convert char to int, add one, convert back
				targetSquares.add(new Square(x, (char)((int)y + i)));
			}
		}
		// Check if the ship is in bounds
		for (Square sq : targetSquares) {
			// Not possible for the row to be less than 0 due to how placement works
			if (sq.getRow() > this.rows) {
				return false;
			}
			// Check column based on ascii value
			if (((int)sq.getColumn() - 65) >= this.cols) {
				return false;
			}
		}
		// Check that the square isn't already occupied
	    for (Ship s : this.ships) {
			for (Square occ : s.getOccupiedSquares()) {
				for (Square target : targetSquares) {
					if (occ.getRow() == target.getRow() && occ.getColumn() == target.getColumn()) {
						return false;
					}
				}
			}
		}
		ship.setOccupiedSquares(targetSquares);
		this.ships.add(ship);
		return true;
	}

	/*
	DO NOT change the signature of this method. It is used by the grading scripts.
	 */
	public Result attack(int x, char y) {
	    Result result = new Result();
        Square attackLoc = new Square(x, y);
        result.setLocation(attackLoc);

        // Check if valid attack
        for (Result res : this.attacks) {
            if (res.getLocation().getRow() == attackLoc.getRow() && res.getLocation().getColumn() == attackLoc.getColumn()) {
                result.setResult(AtackStatus.INVALID);
                return result;
            }
        }
        // Continue if attack is valid
		boolean hit = false;
	    for (Ship ship : this.ships) {
	        // Check if the attack hits a ship
            for (Square occSquare : ship.getOccupiedSquares()) {
                if (occSquare.getRow() == attackLoc.getRow() && occSquare.getColumn() == attackLoc.getColumn()) {
                    result.setShip(ship);
                    result.setLocation(attackLoc);
                    hit = true;
                    break;
                }
            }

            if (hit) {

				int hitCount = 0; //keeps track number of hits
				int shipLength = 0;
				for( Square s1 : result.getShip().getOccupiedSquares()){ //goes through each square in a ship
					shipLength++;
					if ( attackLoc.getColumn() == s1.getColumn() //if you just attacked it, increment hitcount
							&& attackLoc.getRow() == s1.getRow() ){
						hitCount++;
					}
					for( Result r : attacks){ //goes through each tile already attacked

						if ( s1.getColumn() == r.getLocation().getColumn() //if it was attacked and is in ship increment hitcount
								&& s1.getRow() == r.getLocation().getRow() ){
							hitCount++;

						}
					}
				}

				if(hitCount == shipLength){ //if every tile in the ship has been hit, it was sunk or game is over
					int sunkShips = 0;
					for(Result r : attacks){ //go through each attack, count up the number of sunk ships
						if( r.getResult() == AtackStatus.SUNK){
							sunkShips++;
						}
					}
					if(sunkShips > 1){ //if sunk ships was already at 2, now at three with current sink, end game
						result.setResult(AtackStatus.SURRENDER);
					} else { //otherwise, just sink the ship
						result.setResult(AtackStatus.SUNK);
					}

				} else { //if you didnt sink a ship, its just a hit
					result.setResult(AtackStatus.HIT);
				}

            } else {
                result.setResult(AtackStatus.MISS);
            }
        }
        this.attacks.add(result);
        return result;
	}

	public boolean sonar(int x, char y, int numSonars) {
		if (!checkSonarCondition() || numSonars == 0)
			return false;
	    int radius = 2;
	    List<Square> squares = new ArrayList<>();
	    // Add circle pattern to squares
	    for (int i = 0; i < radius+1; i++) {
			squares.add(new Square(x+i, y));
			squares.add(new Square(x-i, y));
			squares.add(new Square(x, (char)((int)y+i)));
			squares.add(new Square(x, (char)((int)y-i)));
			squares.add(new Square(x-1, (char)((int)y-1)));
			squares.add(new Square(x-1, (char)((int)y+1)));
			squares.add(new Square(x+1, (char)((int)y-1)));
			squares.add(new Square(x+1, (char)((int)y+1)));
		}
	    for (Square s : squares) {
	    	// Check row and validity
			if (s.getRow() <= this.rows && s.getRow() > 0 && ((int)s.getColumn() - 65) < this.cols && ((int)s.getColumn() - 65) >= 0) {
				this.sonars.add(s);
			}
		}
		return true;
	}

	private boolean checkSonarCondition() {
	    for (Result r : this.attacks) {
	    	if (r.getResult() == AtackStatus.SUNK) {
	    		return true;
			}
		}
		return false;
	}

	public List<Ship> getShips() {
		return this.ships;
	}

	public void setShips(List<Ship> ships) {
	    this.ships = ships;
	}

	public List<Result> getAttacks() {
	    return this.attacks;
	}

	public void setAttacks(List<Result> attacks) {
	    this.attacks = attacks;
	}

	public List<Square> getSonars() {
		return this.sonars;
	}

	public void setSonars(List<Square> sonars) {
		this.sonars = sonars;
	}

	public int getRows() {
	    return this.rows;
    }

    public int getCols() {
	    return this.cols;
    }
}
