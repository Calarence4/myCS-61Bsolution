package game2048logic;

import game2048rendering.Side;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        if(board[r][c]==0){//空元素
            return 0;
        }
        int countElements =0;//(r,c)上方的元素个数
        for(int i=r-1;i>=0;i--){//计算上方元素,不会越界
            if(board[i][c]!=0){
                countElements++;
            }
        }
        int emptyTiles = r-countElements;
        int goatRow=r-emptyTiles;
        if(goatRow-1>=Math.max(0,minR)&&board[goatRow-1][c]==board[r][c]){//合并
            goatRow -=1;
            board[goatRow][c]*=2;
            board[r][c]=0;
            return 1+goatRow;
        }
        if(emptyTiles==0||goatRow < minR){//无法移动
            return 0;
        }
        board[goatRow][c]=board[r][c];
        board[r][c]=0;
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int minR=0;
        for(int i=0;i<4;i++){
            minR=moveTileUpAsFarAsPossible(board,i,c,minR);
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for(int i=0;i<4;i++){
            tiltColumn(board,i);
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.EAST) {
            MatrixUtils.rotateLeft(board);
            tiltUp(board);
            MatrixUtils.rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            MatrixUtils.rotateRight(board);
            tiltUp(board);
            MatrixUtils.rotateLeft(board);
            return;
        } else if (side == Side.SOUTH) {
            MatrixUtils.rotateLeft(board);
            MatrixUtils.rotateLeft(board);
            tiltUp(board);
            MatrixUtils.rotateRight(board);
            MatrixUtils.rotateRight(board);
            return;
        } else {
            tiltUp(board);
            return;
        }
    }
}
