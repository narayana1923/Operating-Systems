#include <iostream>
using namespace std;

class RoundRobin
{
public:
    int n, q, *BT, *PID, *WT, *TAT;
    float awt=0,atat=0;
    RoundRobin(int n) : n(n)
    {
        BT = new int[n];
        WT = new int[n];
        TAT = new int[n];
        PID = new int[n];
        for (int i = 0; i < n; i++)
        {
            cout << "Enter burst time of process - " << i + 1 << " : ";
            cin >> BT[i];
            PID[i] = i + 1;
        }
        cout<<"Enter time quantum: ";
        cin>>q;
    }
    void calculate(){
        int *a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=BT[i];
            WT[i]=0;
        }
        int sum=0;
        do{
            sum=0;
            for(int i=0;i<n;i++){
                if(a[i]>q){
                    a[i]-=q;
                    for(int j=0;j<n;j++){
                        if(j!=i && a[j]!=0) WT[j]+=q;
                    }
                    
                }else{
                    for(int j=0;j<n;j++){
                        if(j!=i && a[j]!=0) WT[j]+=a[i];
                    }
                    a[i]=0;
                }
            }
            for(int j=0;j<n;j++){
                sum+=a[j];
            }
        }while(sum!=0);
        for(int i=0;i<n;i++){
            TAT[i]=WT[i]+BT[i];
            atat+=TAT[i];
            awt+=WT[i];
        }
    }
    void print_details(){
        cout<<"\nProcess\tBT\tWT\tTAT\n";
        for(int i=0;i<n;i++){
            printf("%d\t%d\t%d\t%d\n",PID[i],BT[i],WT[i],TAT[i]);
        }
        cout<<"Avg waiting time and TAT are: "<<awt/n<<" "<<atat/n;
    }
    ~RoundRobin(){
        delete [] BT;
        delete [] WT;
        delete [] TAT;
        delete [] PID;
        cout<<"\nDeleted\n";
    }
};

int main()
{
    int n;
    cout << "Enter number of processes: ";
    cin >> n;
    RoundRobin r(n);
    r.calculate();
    r.print_details();
    return 0;
}