#include <iostream>
using namespace std;

class SJF {
   public:
    int n, *BT, *WT, *TAT, *PID;
    float awt = 0, atat = 0;
    SJF(int n) : n(n) {
        BT = new int[n];
        WT = new int[n];
        TAT = new int[n];
        PID = new int[n];
        for (int i = 0; i < n; i++) {
            cout << "Enter burst time of process - " << i + 1
                 << " : ";
            cin >> BT[i];
            PID[i] = i + 1;
        }
    }
    void swap(int &x, int &y) {
        int temp = x;
        x = y;
        y = temp;
    }
    void sort() {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (BT[min] > BT[j]) min = j;
            }
            swap(BT[i], BT[min]);
            swap(PID[i], PID[min]);
        }
    }
    void calculate() {
        WT[0] = 0;
        TAT[0] = BT[0];
        atat = TAT[0];
        for (int i = 1; i < n; i++) {
            WT[i] = WT[i - 1] + BT[i - 1];
            TAT[i] = BT[i] + WT[i];
            atat += TAT[i];
            awt += WT[i];
        }
    }
    void print_details() {
        cout << "\nProcess\tBT\tWT\tTAT\n";
        for (int i = 0; i < n; i++) {
            printf("%d\t%d\t%d\t%d\n", PID[i], BT[i], WT[i], TAT[i]);
        }
        cout << "Avg waiting time and TAT are: " << awt / n << " "
             << atat / n;
    }
    ~SJF() {
        delete[] BT;
        delete[] WT;
        delete[] TAT;
        delete[] PID;
        cout << "\nDeleted\n";
    }
};

int main() {
    int n;
    cout << "Enter number of processes: ";
    cin >> n;
    SJF s(n);
    s.sort();
    s.calculate();
    s.print_details();
    return 0;
}