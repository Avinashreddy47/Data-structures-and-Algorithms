#include <iostream>
using namespace std;
int main()
{
    int n, b;
    cin >> n >> b;
    b = 240 - b;
    int x = 5, sum = 1;
    while (b > 0 && n > 0)
    {
        b = b - x * sum;
        if (b < 0)
            break;
        n--;
        cout<<b<<" ";
        sum++;
    }
    cout << sum - 1 << "\n";
}
