class Solution {

    public int maxStability(int n, int[][] edges, int k) {

        int maxS = 0;
        for (int[] e : edges) maxS = Math.max(maxS, e[2]);

        int left = 1, right = maxS * 2;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canBuild(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;

            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    boolean canBuild(int n, int[][] edges, int k, int target) {

        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        java.util.List<int[]> normal = new java.util.ArrayList<>();
        java.util.List<int[]> upgrade = new java.util.ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {
                if (s < target) return false;
                if (!dsu.union(u, v)) return false;
                used++;
            } else {
                if (s >= target)
                    normal.add(e);
                else if (s * 2 >= target)
                    upgrade.add(e);
            }
        }

        for (int[] e : normal) {
            if (dsu.union(e[0], e[1])) used++;
        }

        for (int[] e : upgrade) {
            if (used == n - 1) break;
            if (dsu.union(e[0], e[1])) {
                upgrades++;
                used++;
            }
        }

        if (upgrades > k) return false;

        return used == n - 1;
    }
}
