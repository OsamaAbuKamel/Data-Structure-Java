public class Maze {
    public static void main(String[] args) {
        int matrix[][] = {
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
                { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
                { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
                { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
        };
        int shortestDistance = getShortestPathLength(matrix, 0, 9);
        if (shortestDistance != -1) {
            System.out.println("The shortest path from source to destination " +
                    "has length " + shortestDistance);
        } else {
            System.out.println("Destination cannot be reached from source");
        }
    }

    public static int getShortestPathLength(int[][] matrix, int destX, int destY) {
        return getShortestPathLength(matrix, 0, 0, destX, destY);
    }

    // Wrapper over calculateShortestPath() function
    private static int getShortestPathLength(int[][] matrix, int sourceX, int sourceY, int destX, int destY) {
        // base case: invalid input
        if (matrix == null || matrix.length == 0 || matrix[sourceX][sourceY] == 0 || matrix[destX][destY] == 0) {
            return -1;
        }
        // `rowCount × colCount` matrix
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int shortestDistance;
        // construct a `rowCount × colCount` matrix to keep track of visited cells
        boolean[][] visitedCells = new boolean[rowCount][colCount];
        shortestDistance = calculateShortestPath(matrix, visitedCells, sourceX, sourceY, destX, destY,
                Integer.MAX_VALUE, 0);
        if (shortestDistance != Integer.MAX_VALUE) {
            return shortestDistance;
        }
        return -1;
    }

    // Find the shortest possible route in a matrix `matrix` from source cell
    // (sourceX, sourceY)
    // to destination cell (destX, destY).
    // `shortestDistance` stores the length of the shortest path from source to a
    // destination
    // found so far, and `currentDistance` maintains the length of the path from a
    // source cell
    // to the current cell (sourceX, sourceY).
    public static int calculateShortestPath(int[][] matrix, boolean[][] visitedCells,
            int sourceX, int sourceY, int destX, int destY, int shortestDistance, int currentDistance) {
        // if the destination is found, update `shortestDistance`
        if (sourceX == destX && sourceY == destY) {
            return Integer.min(currentDistance, shortestDistance);
        }
        // mark the cell at (sourceX, sourceY) as visited
        visitedCells[sourceX][sourceY] = true;
        // move to the cell below the current cell
        if (isValidMove(matrix, visitedCells, sourceX + 1, sourceY)) {
            shortestDistance = calculateShortestPath(matrix, visitedCells, sourceX + 1, sourceY, destX, destY,
                    shortestDistance, currentDistance + 1);
        }
        // move to the cell to the right of the current cell
        if (isValidMove(matrix, visitedCells, sourceX, sourceY + 1)) {
            shortestDistance = calculateShortestPath(matrix, visitedCells, sourceX, sourceY + 1, destX, destY,
                    shortestDistance, currentDistance + 1);
        }
        // move to the cell above the current cell
        if (isValidMove(matrix, visitedCells, sourceX - 1, sourceY)) {
            shortestDistance = calculateShortestPath(matrix, visitedCells, sourceX - 1, sourceY, destX, destY,
                    shortestDistance, currentDistance + 1);
        }
        // move to the cell to the left of the current cell
        if (isValidMove(matrix, visitedCells, sourceX, sourceY - 1)) {
            shortestDistance = calculateShortestPath(matrix, visitedCells, sourceX, sourceY - 1, destX, destY,
                    shortestDistance, currentDistance + 1);
        }
        // backtrack: unmark the cell at (sourceX, sourceY) as visited
        visitedCells[sourceX][sourceY] = false;
        return shortestDistance;
    }

    // Check if the current position can move to the target position (targetX,
    // targetY).
    // The function returns false if the cell is invalid, has value 0, or already
    // visited
    private static boolean isValidMove(int[][] matrix, boolean[][] visitedCells, int targetX, int targetY) {
        return (targetX >= 0 && targetX < matrix.length && targetY >= 0 && targetY < matrix[0].length) &&
                matrix[targetX][targetY] == 1 && !visitedCells[targetX][targetY];
    }
}
