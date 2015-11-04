import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dynamic {
	
	public static void main(String[] args) {
		Integer[] available = {10, 1, 7, 7};		
		Integer[] proc = {8, 4, 2, 1};
		
		List<Integer> tree = createSolution(Arrays.asList(available), Arrays.asList(proc));
		printSolution(tree);
		
		Integer[] available2 = {1, 2, 3, 4, 5};		
		Integer[] proc2 = {8, 6, 5, 2, 1};
		
		tree = createSolution(Arrays.asList(available2), Arrays.asList(proc2));
		printSolution(tree);
		
	}
	
	// Build solution tree
	private static List<Integer> createSolution(List<Integer> available, List<Integer> proc) {
		// Number of days to execute
		int days = available.size();
		int num_leafs = ((Double)Math.pow(2.,(double)days)).intValue();
		int num_nodes = (2 * num_leafs) - 1;
		
		Integer[] working_tree = new Integer[num_nodes];
		
		// To keep track of place in processing array for each node
		int[] wt_proc =  new int[num_nodes];
		
		working_tree[0] = 0;
		
		// Node 0 is just a placeholder, doesn't process
		wt_proc[0] = -1;
		
		for(int i = 1; i < num_nodes; i++) {
			int parent = getParent(i);
			int cur_day = ((Double)Math.floor(Math.log(i) / Math.log(2))).intValue() - 1;
			if(i % 2 == 0) {
				int np_ctr = wt_proc[parent];
				if(np_ctr + 1 < proc.size())
					np_ctr++;
				
				working_tree[i]= Math.min(available.get(cur_day), proc.get(np_ctr)) + working_tree[parent];
				wt_proc[i] = np_ctr;
			} else {
				working_tree[i] = working_tree[parent];
				wt_proc[i] = -1;
			}
		}
		
		return Arrays.asList(working_tree);
	}


	// Traverse solution tree leaves to find the max processed
	static int findSolution(List<Integer> working_tree) {
		int index_value = working_tree.get(1);
		int largest_index = 1;
		
		for(int index = working_tree.size() / 2; index < working_tree.size(); index++)
			if(working_tree.get(index) >= index_value) {
				largest_index = index;
				index_value = working_tree.get(index);
			}
		return largest_index;
	}
	
	static List<Integer> getPath(List<Integer> working_tree, int solutionNode) {
		List<Integer> solution = new ArrayList<Integer>();
		int node = solutionNode;
		
		// Get decision bath from leaf to node
		while(node > 0) {
			if (node % 2 == 0) {
				solution.add(1);
			} else {
				solution.add(0);
			}
			node = getParent(node);
		}
		
		// Reverse array to get decision path from node to leaf
		for(int i = 0; i < solution.size() / 2; i++) {
		    int temp = solution.get(i);
		    solution.set(i, solution.get(solution.size() - i - 1));
		    solution.set(solution.size() - i - 1, temp);
		}
		
		return solution;
	}
	
	private static void printSolution(List<Integer> tree) {
		int solutionNode = findSolution(tree);
		System.out.printf("Solution: %d\r\n", tree.get(solutionNode));
		System.out.printf("Decisions: ");
		for(Integer value : getPath(tree, solutionNode)) {
			if (value == 0) {
				System.out.printf("Reboot, ");
			} else {
				System.out.printf("Process, ");
			}
		}
		System.out.println("\n");
	}
	
	// Return the parent of a node
	static int getParent(int child) {
		return (child - 1) / 2;
	}
}





	