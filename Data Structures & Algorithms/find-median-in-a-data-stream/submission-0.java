class MedianFinder {
    // Max-Heap stores the smaller half of numbers (stores them in reverse order)
    private PriorityQueue<Integer> small;
    // Min-Heap stores the larger half of numbers (stores them in normal order)
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        // Collections.reverseOrder() turns a normal Min-Heap into a Max-Heap
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // Step 1: Add to small heap
        small.offer(num);
        
        // Step 2: Make sure every number in small is <= every number in large
        large.offer(small.poll());
        
        // Step 3: Keep the sizes balanced. 
        // 'small' is allowed to have 1 more element than 'large', but not less.
        if (small.size() < large.size()) {
            small.offer(large.poll());
        }
    }
    
    public double findMedian() {
        // If total number of elements is odd, the median is the top of 'small'
        if (small.size() > large.size()) {
            return small.peek();
        }
        // If even, it's the average of the tops of both heaps
        return (small.peek() + large.peek()) / 2.0;
    }
}