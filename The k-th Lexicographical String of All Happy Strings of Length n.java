class Solution {

    List<String> list = new ArrayList<>();

    public String getHappyString(int n, int k) {
        backtrack("", n);

        if (k > list.size()) {
            return "";
        }

        return list.get(k - 1);
    }

    private void backtrack(String current, int n) {

        if (current.length() == n) {
            list.add(current);
            return;
        }

        char[] chars = {'a', 'b', 'c'};

        for (char c : chars) {

            if (current.length() > 0 && current.charAt(current.length() - 1) == c) {
                continue;
            }

            backtrack(current + c, n);
        }
    }
}
