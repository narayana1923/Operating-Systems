package java;
public class BankersAlgorithm {
	int p = 5, r = 3;
	int need[][] = new int[p][r];
	int max[][], alloc[][], avail[];
	int safeSequence[] = new int[p];

	void intialize() {
		alloc = new int[][] { { 0, 1, 0 },
				{ 2, 0, 0 },
				{ 3, 0, 2 },
				{ 2, 1, 1 },
				{ 0, 0, 2 } };
		max = new int[][] { { 7, 5, 3 },
				{ 3, 2, 2 },
				{ 9, 0, 2 },
				{ 2, 2, 2 },
				{ 4, 3, 3 } };

		avail = new int[] { 3, 3, 2 };
	}

	void isSafe() {
		int count = 0;
		boolean visited[] = new boolean[p];
		int work[] = new int[r];
		for (int i = 0; i < r; i++)
			work[i] = avail[i];
		while (count < p) {
			boolean flag = false;
			for (int i = 0; i < p; i++) {
				if (visited[i] == false) {
					int j;
					for (j = 0; j < r; j++) {
						if (need[i][j] > work[j])
							break;
					}
					if (j == r) {
						safeSequence[count++] = i;
						visited[i] = true;
						flag = true;
						for (j = 0; j < r; j++) {
							work[j] += alloc[i][j];
						}
					}
				}
			}
			if (flag == false)
				break;
		}
		if (count < p)
			System.out.println("The system is unsafe");
		else {
			System.out.println("safe sequence:");
			for (int i = 0; i < p; i++) {
				System.out.print("P" + safeSequence[i]);
				if (i != p - 1)
					System.out.print("- >");
			}
		}
	}

	void calculate() {
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < r; j++) {
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}
	}

	public static void main(String args[]) {
		BankersAlgorithm obj = new BankersAlgorithm();
		obj.intialize();
		obj.calculate();
		obj.isSafe();
	}
}
