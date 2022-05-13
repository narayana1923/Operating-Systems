#include <iostream>
using namespace std;

class Bankers{
    public:
        int p=5,r=3;
        int need[5][3];
        int alloc[5][3]={ { 0, 1, 0 },
				      { 2, 0, 0 },
				      { 3, 0, 2 },
				      { 2, 1, 1 },
				      { 0, 0, 2 } };
        int max[5][3]={ { 7, 5, 3 }, 
				    { 3, 2, 2 },
				    { 9, 0, 2 },
				    { 2, 2, 2 }, 
				    { 4, 3, 3 } }; 
        int avail[3]={3,3,2};
        int safeSequence[5];
        void calculate(){
            for(int i=0;i<p;i++){
                for(int j=0;j<r;j++){
                    need[i][j]=max[i][j]-alloc[i][j];
                }
            }
        }
        void isSafe(){
            int count=0;
            bool visited[5]={false};
            int work[3];
            for(int i=0;i<r;i++) work[i]=avail[i];
            while(count<p){
                bool flag=false;
                for(int i=0;i<p;i++){
                    if(visited[i]==false){
                        int j;
                        for(j=0;j<r;j++){
                            if(need[i][j]>work[j]) break;
                        }
                        if(j==r){
                            safeSequence[count++]=i;
                            flag=true;
                            visited[i]=true;
                            for(j=0;j<r;j++) work[j]+=alloc[i][j];
                        }
                    }
                }
                if(flag==false) break;
            }
            if(count<p) cout<<"\nunsafe\n";
            else{
                cout<<"\nSafe sequence is\n";
                for(int i=0;i<p;i++){
                    cout<<"p"<<safeSequence[i];
                    if(i!=p-1) cout<<"->";
                }
            }
        }
};

int main(){
    Bankers b;
    b.calculate();
    b.isSafe();
    return 0;
}