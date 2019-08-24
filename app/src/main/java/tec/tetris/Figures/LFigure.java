package tec.tetris.Figures;

import tec.tetris.Board;

public class LFigure extends AbstractFigure {

    private int rotation;

    public LFigure(){
        coordenates = new int[][] {{2,2},{1,2},{3,2},{3,1}};
        rotation = 0;
    }

    @Override
    public void rotate() {
        switch(rotation){
            case 0:
                coordenates[1][0]++;
                coordenates[1][1]--;

                coordenates[2][0]--;
                coordenates[2][1]++;

                coordenates[3][1]+=2;
                rotation++;
                break;
            case 1:
                coordenates[1][0]++;
                coordenates[1][1]++;

                coordenates[2][0]--;
                coordenates[2][1]--;

                coordenates[3][0]-=2;
                rotation++;
                break;
            case 2:
                coordenates[1][0]--;
                coordenates[1][1]++;

                coordenates[2][0]++;
                coordenates[2][1]--;

                coordenates[3][1]-=2;
                rotation++;
            case 3:
                coordenates[1][0]--;
                coordenates[1][1]--;

                coordenates[2][0]++;
                coordenates[2][1]++;

                coordenates[3][0]+=2;
                rotation = 0;
        }
    }

    @Override
    public boolean collidesRotation(int[][] board) {
        switch(rotation){
            case 0:
                if (    board [coordenates[1][0]+1][coordenates[1][1]-1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[2][0]-1][coordenates[2][1]+1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[3][0]][coordenates[3][1]+2] == Board.OCCUPIED_SPACE  )
                    return true;
                break;
            case 1:
                if (    board [coordenates[1][0]+1][coordenates[1][1]+1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[2][0]-1][coordenates[2][1]-1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[3][0]-2][coordenates[3][1]] == Board.OCCUPIED_SPACE  )
                    return true;
                break;
            case 2:
                if (    board [coordenates[1][0]-1][coordenates[1][1]+1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[2][0]+1][coordenates[2][1]-1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[3][0]][coordenates[3][1]-2] == Board.OCCUPIED_SPACE  )
                    return true;
                break;
            case 3:
                if (    board [coordenates[1][0]-1][coordenates[1][1]-1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[2][0]+1][coordenates[2][1]+1] == Board.OCCUPIED_SPACE &&
                        board [coordenates[3][0]+2][coordenates[3][1]] == Board.OCCUPIED_SPACE  )
                    return true;
                break;
        }
        return false;
    }
}
