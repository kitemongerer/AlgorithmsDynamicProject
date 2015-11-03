import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dynamic {
	public static void main(String[] args) {
		ArrayList<Integer> dataArr = createDataArray();
		ArrayList<Integer> procArr = createProcessingArray(dataArr.size());
		
		
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
	
	