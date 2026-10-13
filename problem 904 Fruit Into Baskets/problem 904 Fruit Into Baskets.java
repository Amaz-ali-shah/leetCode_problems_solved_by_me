class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            int fruit = fruits[right];
            count.put(fruit, count.getOrDefault(fruit, 0) + 1);

            // Shrink window until we have at most 2 fruit types
            while (count.size() > 2) {
                int leftFruit = fruits[left];
                count.put(leftFruit, count.get(leftFruit) - 1);

                if (count.get(leftFruit) == 0) {
                    count.remove(leftFruit);
                }

                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }
}
