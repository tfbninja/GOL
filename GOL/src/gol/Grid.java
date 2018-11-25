package gol;

/**
 *
 * @author Tim Barber
 */
public class Grid {

    private int width;
    private int length;
    private int[][] playArea;
    private int[][] lastPlayArea;

    public Grid() {
        this.width = 10;
        this.length = 10;
        this.playArea = new int[this.width][this.length];
        this.lastPlayArea = this.playArea;
    }

    public Grid(int width, int length) {
        this.width = width;
        this.length = length;
        this.playArea = new int[this.width][this.length];
        this.lastPlayArea = this.playArea;
    }

    public int getWidth() {
        return this.width;
    }

    public int getLength() {
        return this.length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int[][] getPlayArea() {
        return this.playArea;
    }

    public void setCell(int x, int y, boolean value) {
        this.lastPlayArea = this.playArea;
        if (value) {
            this.playArea[y][x] = 1;
        } else {
            this.playArea[y][x] = 0;
        }
    }

    public void setPlayArea(int[][] newPlayArea) {
        this.playArea = newPlayArea;
    }

    public void nextGen() { //change return type (maybe)
        int[][] oldPlayArea = this.playArea;
        int[][] newPlayArea = new int[this.width][this.length];
        for (int y = 0; y < this.width; y++) {
            for (int x = 0; x < this.length; x++) {
                int neighbors = 0;

                //check neighbors (with bounds checks)
                if (x > 0) {
                    if (y > 0) {
                        if (oldPlayArea[x - 1][y - 1] == 1) { // if upper left
                            neighbors++;
                        }
                    }
                    if (oldPlayArea[x - 1][y] == 1) { // if left
                        neighbors++;
                    }
                    if (y < (this.length - 1)) {
                        if (oldPlayArea[x - 1][y + 1] == 1) { // if lower left
                            neighbors++;
                        }
                    }
                }
                if (x < (this.width - 1)) {
                    if (y > 0) {
                        if (oldPlayArea[x + 1][y - 1] == 1) { // if upper right
                            neighbors++;
                        }
                    }
                    if (oldPlayArea[x + 1][y] == 1) { // if right
                        neighbors++;
                    }
                    if (y < (this.length - 1)) {
                        if (oldPlayArea[x + 1][y + 1] == 1) { // if lower right
                            neighbors++;
                        }
                    }
                }
                if (y > 0) {
                    if (oldPlayArea[x][y - 1] == 1) { // if upper
                        neighbors++;
                    }
                }
                if (y < (this.length - 1)) { // if lower
                    if (oldPlayArea[x][y + 1] == 1) {
                        neighbors++;
                    }
                }
                // now that we have neighbors, we can determine cell state
                if (neighbors < 2) {
                    newPlayArea[x][y] = 0;
                } else if (neighbors == 2) {
                    newPlayArea[x][y] = oldPlayArea[x][y];
                } else if (neighbors == 3) {
                    newPlayArea[x][y] = 1;
                } else {
                    newPlayArea[x][y] = 0;
                }
            }
        }
        this.playArea = newPlayArea;
        this.lastPlayArea = oldPlayArea;
    }

    @Override
    public String toString() {
        String output = "";
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.length; y++) {
                output += String.valueOf(this.playArea[x][y]);
                output += " ";
            }
            output += "\n";
        }
        return output;
    }
}
/*
 * The MIT License
 *
 * Copyright (c) 2018 Tim Barber.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
