package structure.recursion;

import java.util.ArrayList;

/**
 * maze 迷宫
 */
class SaveLoc {
  public int i;
  public int j;
  public SaveLoc(int i, int j) {
    this.i = i;
    this.j = j;
  }

  @Override
  public String toString() {
    return "SaveLoc [i=" + i + ", j=" + j + "]";
  }
}

public class Maze {
  private static int minRoad = Integer.MAX_VALUE;
  private static ArrayList<SaveLoc> route = new ArrayList<SaveLoc>();
  public static void main(String[] args) {
    int[][] maze = new int[8][7];
    for (int i = 0; i < 8; i++) {
      maze[i][0] = -1;
      maze[i][6] = -1;
    }
    for (int i = 0; i < 7; i++) {
      maze[0][i] = -1;
      maze[7][i] = -1;
    }
    maze[2][1] = -1;
    maze[2][2] = -1;

    maze[4][2] = -1;
    maze[4][3] = -1;
    maze[4][4] = -1;
    maze[4][5] = -1;
    print(maze);
    System.out.println("------aa----------------------");
    // findWay(maze, 1, 1);
    
    findWay(maze, 1, 1, 0, new ArrayList<SaveLoc>());
    System.out.println(minRoad);
    // print(maze);
    System.out.println(route);
  }

  public static void print(int[][] numbers) {
    for(int i = 0; i<numbers.length; i++) {
      for(int item : numbers[i]) {
        System.out.print(item + "\t");
      }
      System.out.println();
    }
  }
  /***
   * 
   * @param maze 地图二维数组
   * @param i row
   * @param j column
   * @param step 第几步
   * @param temp 暂存路径
   */
  public static void findWay(int[][] maze, int i, int j, int step, ArrayList<SaveLoc> temp) {
    if(i == 6 && j == 5) {
      // minRoad = Math.min(minRoad, step);
      if(minRoad > step) {
        minRoad = step;
        route = new ArrayList<>(temp);
        route.add(new SaveLoc(i, j));
      }
      return;
    }
    if(maze[i][j] != 0) {
      return;
    }
    maze[i][j] = step;
    temp.add(new SaveLoc(i, j));
    findWay(maze, i+1, j, step+1, temp);
    findWay(maze, i-1, j, step+1, temp);
    findWay(maze, i, j+1, step+1, temp);
    findWay(maze, i, j-1, step+1, temp);
    temp.remove(temp.size()-1);
    maze[i][j] = 0;
  }

  public static boolean findWay(int[][] maze, int i, int j) {
    //i == 6 && j == 5
    if(i == 6 && j == 5) {
      return true;
    } else {
      if(maze[i][j] == 0) {
        maze[i][j] = 2;
        if(findWay(maze, i-1, j)) {
          return true;
        }else
        if(findWay(maze, i, j+1)) {
          return true;
        }else
        if(findWay(maze, i+1, j)) {
          return true;
        } else
        if(findWay(maze, i, j-1)) {
          return true;
        } else {
          maze[i][j] = 3;
          return false;
        }
      } else {
        return false;
      }
    }
  }
}
