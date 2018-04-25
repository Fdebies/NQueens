// Class: NQueens
//
// Author: Your Name Here
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.display.GridDisplay;

/**
 *  Environment-Based Applications:<br>
 *
 *    The NQueens class implements the N Queens problem.
 *
 *  @author Your Name (based on a template provided by Alyce Brady)
 *  @version 1 September 2002
 **/

public class NQueens
{
    // Instance Variables: Encapsulated data for EACH NQueens problem
    private Grid board;
    private GridDisplay display;
    int row = 0;
  // constructor

    /** Constructs an object that solves the N Queens Problem.
     *    @param n the number of queens to be placed on an
     *              <code>n</code> x <code>n</code> board
     *    @param d an object that knows how to display an 
     *              <code>n</code> x <code>n</code> board and
     *              the queens on it
     **/
    public NQueens(int n, GridDisplay d)
    {
        board = new BoundedGrid(n, n);
        display = d;
        display.setGrid(board);
        display.showGrid();
    }

  // methods

    /** Returns the number of queens to be placed on the board. **/
    public int numQueens()
    {
        return board.numObjects();   // replace this with something more useful
    }

    /** Solves (or attempts to solve) the N Queens Problem. **/
    public boolean solve()
    {

        while(row < board.numRows()) {
            this.placeQueen(row);
            row++;
            return false;
        }
        return true;   // replace this with something more useful
    }

    /** Attempts to place the <code>q</code>th queen on the board.
     *  (Precondition: <code>0 <= q < numQueens()</code>)
     *    @param q index of next queen to place
     **/
    private boolean placeQueen(int q)
    {
        if(q >= board.numRows())
        {
            return true;
        }
        for(int col = 0; col < board.numCols(); col++) {
            //System.out.print("PlaceLoc");
            Location loc = new Location(q, col);
            if(this.locationIsOK(loc)) {

                this.addQueen(loc);
                display.showGrid();
                if(placeQueen(q+1))
                {
                    return true;
                }
                else
                {
                    removeQueen(loc);
                }
            }
        }

        // Queen q is placed in row q.  The only question is
        // which column she will be in.  Try them in turn.
        // Whenever we find a column that could work, put her
        // there and see if we can place the rest of the queens.

        return false;
    }

    /** Determines whether a queen can be placed at the specified
     *  location.
     *    @param loc  the location to test
     **/
    private boolean locationIsOK(Location loc) {
        int col = loc.col();
        int row = loc.row();
        for (int i = loc.row(); i < board.numRows()-1; i++) {

            loc = new Location(i, col);

            if (!board.isEmpty(loc)) {
                return false;
            }

        }
        for (int i = loc.col(); i < board.numCols()-1; i++) {

            loc = new Location(row, i);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        for (int i = loc.row(); i >= 0; i--) {
            loc = new Location(i, col);
            if (!board.isEmpty(loc)) {
                return false;
            }

        }
        for (int i = loc.col(); i >= 0; i--) {
            loc = new Location(row, i);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        //downLeft
        for (int j = 0; j < 8; j++) {
            int rowd = row - j;
            int cold = col - j;
            if (rowd < 0 || cold < 0) {
                break;
            }

            loc = new Location(rowd, cold);
            if (!board.isEmpty(loc)) {
                return false;
            }

        }
        //leftUP
        for (int j = 0; j < 8; j++) {
            int rowd = row + j;
            int cold = col + j;
            if (rowd > board.numRows()-1 || cold > board.numCols()-1) {
                break;
            }

            loc = new Location(rowd, cold);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        for (int j = 0; j < 8; j++) {
            int rowd = row + j;
            int cold = col + j;
            if (rowd > board.numRows()-1 || cold > board.numCols()-1) {
                break;
            }

            loc = new Location(rowd, cold);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        //RightDOWN
        for (int j = 0; j < 8; j++) {
            int rowd = row - j;
            int cold = col + j;
            if (rowd < 0 || cold > board.numCols()-1) {
                break;
            }

            loc = new Location(rowd, cold);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        //RightUP
        for (int j = 0; j < 8; j++) {
            int rowd = row + j;
            int cold = col - j;
            if (rowd > board.numRows() -1 || cold < 0) {
                break;
            }

            loc = new Location(rowd, cold);
            if (!board.isEmpty(loc)) {
                return false;
            }
        }
        // Verify that another queen can't attack this location.
        // (Only queens in previous rows have been placed.)

        return true;
    }

    /** Adds a queen to the specified location.
     *    @param loc  the location where the queen should be placed
     **/
    private void addQueen(Location loc)
    {
        new Queen(board, loc);      // queens add themselves to the board
    }

    /** Removes a queen from the specified location.
     *    @param loc  the location where the queen should be removed
     **/
    private void removeQueen(Location loc)
    {
        if(!board.isEmpty(loc))
        {
            board.remove(loc);
            display.showGrid();
        }
        // replace this with something useful.
    }

}
