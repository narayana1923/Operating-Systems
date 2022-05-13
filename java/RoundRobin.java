package java;
import java.util.Scanner;

class RoundRobin {
	int burstTime[], waitingTime[], turnAroundTIme[], processID[], a[];
	int q;
	float averageWaitingTime, averageTurnAroundTime;

	RoundRobin(Scanner scan, int n) {
		burstTime = new int[n];
		waitingTime = new int[n];
		turnAroundTIme = new int[n];
		processID = new int[n];
		a = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.printf("Brust time of process-%d: ", i + 1);
			burstTime[i] = scan.nextInt();
			processID[i] = i + 1;
		}
		System.out.print("Enter time quantum: ");
		q = scan.nextInt();
	}

	void calculate(int n) {
		a = new int[burstTime.length];
		for (int i = 0; i < n; i++)
			a[i] = burstTime[i];
		int sum = 0;
		do {
			for (int i = 0; i < n; i++) {
				if (a[i] > q) {
					a[i] = a[i] - q;
					for (int j = 0; j < n; j++) {
						if (j != i && a[j] != 0) {
							waitingTime[j] += q;
						}
					}
				} else {
					for (int j = 0; j < n; j++) {
						if (j != i && a[j] != 0) {
							waitingTime[j] += a[i];
						}
					}
					a[i] = 0;
				}
			}
			sum = 0;
			for (int k = 0; k < n; k++)
				sum += a[k];
		} while (sum != 0);
		for (int i = 0; i < n; i++) {
			turnAroundTIme[i] = waitingTime[i] + burstTime[i];
			averageTurnAroundTime += turnAroundTIme[i];
			averageWaitingTime += waitingTime[i];
		}
	}

	void printDetails(int n) {
		System.out.println("Process burstTime\t waitingTime\t turnAroundTIme");
		for (int i = 0; i < n; i++) {
			System.out.println(String.format("   %d\t%d\t %d\t %d\t", processID[i], burstTime[i], waitingTime[i], turnAroundTIme[i]));
		}
		System.out.println(String.format("Avg waiting & turnAroundTIme are %.2f , %.2f", averageWaitingTime / n, averageTurnAroundTime / n));
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of processes: ");
		int n = scan.nextInt();
		RoundRobin obj = new RoundRobin(scan, n);
		obj.calculate(n);
		obj.printDetails(n);
	}
}
