import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dynamic {
	final static ArrayList<ArrayList<Integer>> exampleData = new ArrayList<ArrayList<Integer>>();
	final static ArrayList<ArrayList<Integer>> exampleProc = new ArrayList<ArrayList<Integer>>();
	
	
	static {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		Integer[] vals = {1, 2, 3, 4, 5};
		tmp.addAll(Arrays.asList(vals));
		exampleData.add(tmp);
		
		tmp = new ArrayList<Integer>();
		Integer[] vals2 = {8, 6, 5, 2, 1};
		tmp.addAll(Arrays.asList(vals2));
		exampleProc.add(tmp);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> dataArr;
		ArrayList<Integer> procArr;
		
		if (args.length > 0) {
			dataArr = exampleData.get(Integer.valueOf(args[0]));
			procArr = exampleProc.get(Integer.valueOf(args[0]));
		} else {
			dataArr = createDataArray();
			procArr = createProcessingArray(dataArr.size());
		}
		
		//Integer[] vals = {1, 2, 3, 4, 5};		
		//Integer[] vals2 = {8, 6, 5, 2, 1};
		Integer[] vals = {10, 1, 7, 7};		
		Integer[] vals2 = {8, 4, 2, 1};
		
		List<Integer> tree = createSolution(Arrays.asList(vals), Arrays.asList(vals2));
		
		System.out.print("Solution: ");
		for(Integer value : getPath(tree)) {
			if (value == 0) {
				System.out.printf("Reboot, ");
			} else {
				System.out.printf("Process, ");
			}
		}
			
		
	}
	
	private static int parent(int index) {
		return (index - 1) / 2;
	}
	
	private static ArrayList<Integer> createDataArray() {
		ArrayList<Integer> dataArr = new ArrayList<Integer>();
		Random generator = new Random();
		int size = generator.nextInt(256);
		
		for (int i = 0; i < size; i++) {
			dataArr.add(generator.nextInt(256));
		}
		return dataArr;
	}
	
	private static ArrayList<Integer> createProcessingArray(int size) {
		ArrayList<Integer> procArr = new ArrayList<Integer>();
		Random generator = new Random();
		
		procArr.add(generator.nextInt(256));
		for (int i = 1; i < size; i++) {
			// Needs to be smaller than previous int so make sure it is
			int bound = procArr.get(procArr.size() - 1);
			if (bound <= 1) {
				Integer[] emptyArr = new Integer[size - i];
				Arrays.fill(emptyArr, 0);
				procArr.addAll(Arrays.asList(emptyArr));
				break;
			}
			
			procArr.add(generator.nextInt(bound));
		}
		return procArr;
	}
	
	
	private static List<Integer> createSolution(List<Integer> available, List<Integer> proc)
	{
		int days = available.size();
		int num_leafs = ((Double)Math.pow(2.,(double)days)).intValue();
		int num_nodes = (2 * num_leafs) - 1;
		
		Integer[] working_tree = new Integer[num_nodes];
		int[] wt_proc =  new int[num_nodes];
		
		working_tree[0] = 0;
		wt_proc[0] = -1;
		
		for(int i = 1; i < num_nodes; i++)
		{
			int parent = (i-1) / 2;
			int cur_day = ((Double)Math.floor(Math.log(i) / Math.log(2))).intValue() - 1;
			if(i % 2 == 0)
			{
				int np_ctr = wt_proc[parent];
				if(np_ctr + 1 < proc.size())
					++np_ctr;
				
				
				working_tree[i]= Math.min(available.get(cur_day),
						proc.get(np_ctr))
						+ working_tree[parent];
				System.out.printf("Index: %d\t%d\t%d\t%d\r\n", i, cur_day, working_tree[i], proc.get(np_ctr));
				wt_proc[i] = np_ctr;
			} else {
				working_tree[i] = working_tree[parent];
				wt_proc[i] = -1;
			}
			
		}
		
		return Arrays.asList(working_tree);
	}


	static int findSolution(List<Integer> working_tree)
	{
		int index_value = working_tree.get(1);
		int largest_index = 1;
		
		for(int index = working_tree.size() / 2; index < working_tree.size(); ++index)
			if(working_tree.get(index) >= index_value)
			{
				largest_index = index;
				index_value = working_tree.get(index);
			}
		return largest_index;
	}
	
	static List<Integer> getPath(List<Integer> working_tree)
	{
		List<Integer> solution = new ArrayList<Integer>();
		int node = findSolution(working_tree);
		//solution.add(node);
		
		while(node > 0) {
			if (node % 2 == 0) {
				solution.add(1);
			} else {
				solution.add(0);
			}
			node = getParent(node);
		}
		return solution;
	}
	
	static int getParent(int child)
	{
		return (child - 1) / 2;
	}
}





	