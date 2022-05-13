package java;
import java.util.Scanner;

public class MultiLevelQueueScheduling {
	int burstTime[], waitingTime[], processPriority[], processID[], turnAroundTIme[];
	float averageWaitingTime, averageTurnAroundTime;

	MultiLevelQueueScheduling(Scanner scan, int n) {
		burstTime = new int[n];
		waitingTime = new int[n];
		processID = new int[n];
		processPriority = new int[n];
		turnAroundTIme = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Enter burst time for process - " + (i + 1) + " : ");
			burstTime[i] = scan.nextInt();
			System.out.print("Enter system/user process (0/1): ");
			processPriority[i] = scan.nextInt();
			processID[i] = i + 1;
		}
	}

	void sort() {
		for (int i = 0; i < burstTime.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < burstTime.length; j++) {
				if (processPriority[min] > processPriority[j]) {
					min = j;
				}
			}
			int temp = burstTime[min];
			burstTime[min] = burstTime[i];
			burstTime[i] = temp;
			temp = processID[min];
			processID[min] = processID[i];
			processID[i] = temp;
			temp = processPriority[min];
			processPriority[min] = processPriority[i];
			processPriority[i] = temp;
		}
	}

	void calculate() {
		waitingTime[0] = 0;
		turnAroundTIme[0] = burstTime[0];
		averageTurnAroundTime = turnAroundTIme[0];
		for (int i = 1; i < burstTime.length; i++) {
			waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
			turnAroundTIme[i] = burstTime[i] + waitingTime[i];
			averageWaitingTime += waitingTime[i];
			averageTurnAroundTime += turnAroundTIme[i];
		}
	}

	void printDetails() {
		System.out.println("PROCSESS System/user burstTime waitingTime turnAroundTIme");
		for (int i = 0; i < burstTime.length; i++) {
			System.out.println(String.format("%d\t\t%d\t %d  %d  %d", processID[i], processPriority[i], burstTime[i], waitingTime[i], turnAroundTIme[i]));
		}
		System.out.println("Avg watiing time & turn around time are " + (averageWaitingTime / burstTime.length) + " " + (averageTurnAroundTime / burstTime.length));
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number of processes: ");
		int n = scan.nextInt();
		MultiLevelQueueScheduling obj = new MultiLevelQueueScheduling(scan, n);
		obj.sort();
		obj.calculate();
		obj.printDetails();
	}
}
