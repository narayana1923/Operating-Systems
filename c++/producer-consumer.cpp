#include <iostream>
using namespace std;

class ProducerConsumer {
   public:
    int mutex = 1, full = 0, empty = 3, x = 0;
    void producer() {
        mutex = wait(mutex);
        full = signal(full);
        empty = wait(empty);
        cout << "\nProducer produced item - " << (++x) << endl;
        mutex = signal(mutex);
    }
    void consumer() {
        mutex = wait(mutex);
        empty = signal(empty);
        full = wait(full);
        cout << "\nConsumer consumer item - " << (x--) << endl;
        mutex = signal(mutex);
    }
    int wait(int s) {
        return --s;
    }
    int signal(int s) {
        return ++s;
    }
    void perform(int option) {
        if (option == 1) {
            if (mutex == 1 && empty != 0) {
                producer();
            } else {
                cout << "\nBuffer is full\n";
            }
        } else if (option == 2) {
            if (mutex == 1 && full != 0) {
                consumer();
            } else {
                cout << "\nBuffer is empty\n";
            }

        } else if (option == 3)
            cout << "\nProgram ends\n";
        else
            cout << "\ninvalid option\n";
    }
    void todo() {
        int option;
        do {
            cout << "\n1.Produce\n2.Consumer\n3.Exit\n";
            cout << "Enter your option: ";
            cin >> option;
            perform(option);
        } while (option != 3);
    }
};

int main() {
    ProducerConsumer p;
    p.todo();
    return 0;
}