#include <iostream>
using namespace std;
class priority
{
public:
    int n, *PRI, *WT, *BT, *TAT, *PID;
    float awt=0,atat=0;
    priority(int n) : n(n)
    {
        BT = new int[n];
        WT = new int[n];
        TAT = new int[n];
        PID = new int[n];
        PRI = new int[n];
        for (int i = 0; i < n; i++)
        {
            cout << "Enter burst time of process - " << i + 1 << " : ";
            cin >> BT[i];
            cout << "Enter priority of process - " << i + 1 << " : ";
            cin >> PRI[i];
            PID[i] = i + 1;
        }
    }
    void swap(int &x,int &y){
        int temp=x;
        x=y;
        y=temp;
    }
    void sort(){
        for(int i=0;i<n-1;i++){
            int min=i;
            for(int j=i+1;j<n;j++){
                if(PRI[min]>PRI[j]) min=j;
            }
            swap(BT[i],BT[min]);
            swap(PRI[i],PRI[min]);
            swap(PID[i],PID[min]);
        }
    }
    void calculate(){
        WT[0]=0;
        TAT[0]=BT[0];
        atat=TAT[0];
        for(int i=1;i<n;i++){
            WT[i]=BT[i-1]+WT[i-1];
            TAT[i]=BT[i]+WT[i];
            awt+=WT[i];
            atat+=TAT[i];
        }
    }
    void print_details(){
        cout<<"\nProcess\tpriority\tBT\tWT\tTAT\n";
        for(int i=0;i<n;i++){
            printf("%d\t%d\t\t%d\t%d\t%d\n",PID[i],PRI[i],BT[i],WT[i],TAT[i]);
        }
        cout<<"Avg waiting time and TAT are: "<<awt/n<<" "<<atat/n;
    }
    ~priority(){
        delete [] BT;
        delete [] WT;
        delete [] TAT;
        delete [] PID;
        delete [] PRI;
        cout<<"\nDeleted\n";
    }
};
int main()
{
    int n;
    cout<<"Enter number of processes: ";
    cin>>n;
    priority p(n);
    p.sort();
    p.calculate();
    p.print_details();
    return 0;
}