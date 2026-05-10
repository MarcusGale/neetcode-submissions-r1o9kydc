class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        // build graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            graph.get(pre).add(course);
            indegree[course]++;
        }

        // start with courses having no prerequisites
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            order[idx++] = curr;

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // if not all courses processed → cycle exists
        if (idx != numCourses) {
            return new int[0];
        }

        return order;
    }
}