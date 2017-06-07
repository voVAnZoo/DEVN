

import java.util.Random;

/**
 * Created by user on 6/6/17.
 */

public class mapGenerator {
    /*
    0 - nothing
    1 - wall
    2 - start
    3 - finish
     */
    public int maparr[][];
    private int height;
    private int width;
    private int startHeight = 1;
    private int startWidth = 1;
    private int finishHeight = 1;
    private int finishWidth = 1;


    public void clear(int a){
        int i;
        int j;
        for (i = 0; i < height; i++)
            for (j = 0; j < width; j++)
                maparr[i][j] = a;
    }

    public void init( int height, int width){
        this.height = height;
        this.width = width;
        maparr = new int[height][width];
        generate();

        int i;
        int j;
        int imageX;
        int imageY = 0;

        for (i = 0; i < height; i++)
        {
            imageX = 0;
            for (j = 0; j < width; j++) {
                switch (maparr[i][j]){
                    case 0:
                        System.out.print(0);
                        break;
                    case 1:
                        System.out.print(1);
                        break;
                    case 2:
                        System.out.print(2);
                        break;
                    case 3:
                        System.out.print(3);
                        break;
                    default:
                        System.out.print(4);
                        break;
                }

            }
            System.out.println();
        }

    }
    public void generate(){
        clear(1);
        Random rand = new Random();
        int startX = rand.nextInt(width);
        int startY = rand.nextInt(height);
        int finishX = rand.nextInt(width);
        int finishY = rand.nextInt(height);
        Turtle turtle = new Turtle(this);
        turtle.setX(startX);
        turtle.setY(startY);
        int i = 0;
        while(((turtle.getX() != finishX) || (turtle.getY() != finishY))){
            turtle.nextStep();
            i++;
        }
        int j;
        for (i = startY; i < startY + startHeight; i++)
            for (j = startX; j < startX + startWidth; j++)
                maparr[i][j] = 2;
        for (i = finishY; i < finishY + finishHeight; i++)
            for (j = finishX; j < finishX + finishWidth; j++)
                maparr[i][j] = 3;
    }


    private class Turtle{
        private mapGenerator gameMap;
        private int x = 0;
        private int y = 0;
        private int height = 1;
        private int width = 1;
        private int fheight;
        private int fwidth;
        Turtle(mapGenerator gameMap){
            this.gameMap = gameMap;
            fheight = gameMap.height;
            fwidth = gameMap.width;
        }
        public int getX(){ return x; }
        public int getY(){ return y; }
        public int getHeight(){ return height; }
        public int getWidth(){ return width; }
        public void setX(int value){ x = value; }
        public void setY(int value){ y = value; }
        public void setWidth(int value){ height = value; }
        public void setHeight(int value){ width = value; }
        /*
        1 - right
        -1 - left
        2 - bottom
        -2 - top
         */
        private int randomDirect(){
            Random rand = new Random();
            switch (rand.nextInt(4)){
                case 0:
                    return 1;
                case 1:
                    return -1;
                case 2:
                    return 2;
                case 3:
                    return -2;
                default:
                    return 1;
            }
        }
        private int choiceStepLength(int direct){
            int max = 0;
            switch (direct) {
                case 1:
                    max = fwidth - x - width;
                    break;
                case -1:
                    max = x;
                    break;
                case 2:
                    max = fheight - y - height;
                    break;
                case -2:
                    max = y;
                    break;
            }
            Random rand = new Random();
            return rand.nextInt(max);
        }
        private void rightStep(int length){
            int j;
            for (j = 0; j < length; j++)
                gameMap.maparr[y][x + j] = 0;
            x = x + length;
        }
        private void leftStep(int length){
            int j;
            for (j = 0; j < length; j++)
                gameMap.maparr[y][x - j] = 0;
            x = x - length;
        }
        private void topStep(int length){
            int j;
            for (j = 0; j < length; j++)
                gameMap.maparr[y - j][x] = 0;
            y = y - length;
        }
        private void bottomStep(int length){
            int j;
            for (j = 0; j < length; j++)
                gameMap.maparr[y + j][x] = 0;
            y = y + length;
        }
        public void nextStep(){
            int direct = randomDirect();
            int length = choiceStepLength(direct);
            switch (direct){
                case 1:
                    rightStep(length);
                    break;
                case -1:
                    leftStep(length);
                    break;
                case 2:
                    bottomStep(length);
                    break;
                case -2:
                    topStep(length);
                    break;
            }

        }
    }
}
