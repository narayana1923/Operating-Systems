package java;
import java.util.Scanner;

class FCFS {
    int n, burstTime[], processID[], waitingTime[], turnAroundTIme[], AT[];
    float averageTurnAroundTime, averageWaitingTime;

    FCFS(Scanner scan) {
        System.out.print("Enter number of processes: ");
        n = scan.nextInt();
        burstTime = new int[n];
        waitingTime = new int[n];
        turnAroundTIme = new int[n];
        AT = new int[n];
        processID = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time of process - " + (i + 1) + " : ");
            AT[i] = scan.nextInt();
            System.out.print("Enter burst time of process - " + (i + 1) + " : ");
            burstTime[i] = scan.nextInt();
            processID[i] = i + 1;
        }
    }

    void sort() {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (AT[min] > AT[j]) {
                    min = j;
                }
            }
            int temp = burstTime[min];
            burstTime[min] = burstTime[i];
            burstTime[i] = temp;
            temp = processID[min];
            processID[min] = processID[i];
            processID[i] = temp;
            temp = AT[min];
            AT[min] = AT[i];
            AT[i] = temp;
        }
    }

    void calculate() {
        waitingTime[0] = 0;
        turnAroundTIme[0] = burstTime[0];
        averageTurnAroundTime = turnAroundTIme[0];
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1] - AT[i];
            turnAroundTIme[i] = burstTime[i] + waitingTime[i];
            averageWaitingTime += waitingTime[i];
            averageTurnAroundTime += turnAroundTIme[i];
        }
    }

    void printDetails() {
        System.out.println("PROCSESS Arrival Time burstTime waitingTime turnAroundTIme");
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("%d\t\t%d\t %d  %d  %d", processID[i], AT[i], burstTime[i], waitingTime[i], turnAroundTIme[i]));
        }
        System.out.println("Avg watiing time & turn around time are " + (averageWaitingTime / n) + " " + (averageTurnAroundTime / n));
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        FCFS w = new FCFS(scan);
        w.sort();
        w.calculate();
        w.printDetails();
    }
}