package test;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] test = {1,2,3,4,5,6,7,8,9,10,10,11,12,13};
		
		int testP = 0;
		int testN = 1;
		for(int i=0;i<test.length;i++){
			testP=test[i];
			if(testP==testN)
				continue;
			
			testN=test[i];
			System.out.println(testN);
		}
	}

}
