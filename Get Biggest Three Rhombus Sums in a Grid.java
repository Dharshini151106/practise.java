import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                
                set.add(grid[r][c]);

                int maxSize = Math.min(Math.min(r, m - 1 - r), Math.min(c, n - 1 - c));

                for (int k = 1; k <= maxSize; k++) {

                    int sum = 0;

                    int x = r - k;
                    int y = c;
         
                    for (int i = 0; i < k; i++)
                        sum += grid[x + i][y + i];
                  
                    for (int i = 0; i < k; i++)
                        sum += grid[x + k + i][y + k - i];
                   
                    for (int i = 0; i < k; i++)
                        sum += grid[x + 2 * k - i][y - i];
                   
                    for (int i = 0; i < k; i++)
                        sum += grid[x + k - i][y - k + i];

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] res = new int[size];

        for (int i = 0; i < size; i++) {
            res[i] = set.pollLast(); // largest first
        }

        return res;
    }
}
