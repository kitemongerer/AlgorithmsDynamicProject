import java.util.ArrayList;
import java.util.Arrays;
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
		
		ArrayList<Integer> tree = new ArrayList<Integer>();
		
		//Add top node
		tree.add(0);
		for (int i = 0; i < dataArr.size(); i++) {
			for (int j = 0; j < Math.pow(i, 2); j++) {
				//tree.add(tree.get(parent(j)) + 0);
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
	
}
	
	