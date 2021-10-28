#include <iostream>
#include <bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, p;
        cin >> n >> p;
        // 10 10 1 1 1 1 1 1 1 1 1 1
        // 10 9
        // 1 10 10
        // 2 10 1 9                                  1
        // 3 10 1 2 7                                2
        // 4 10 1 2 3 4                              2
        // 5 10 1 2 2 2 3                            2
        // 6 10 1 1 2 2 2 2                          2
        // 7 10 1 1 1 1 2 2 2                        1
        // 8 10 1 1 1 1 1 1 2 2                      1
        // 9 10 1 1 1 1 1 1 1 1 2                    1
        // 10 10 1 1 1 1 1 1 1 1 1 1                 1
        // Inorder to maximise the median the condition which am considering is
        // minimising all the numbers 0 to m-1 numbers as 0 and rest m+1 numbers will be equalised
        // by making num=p/(m+1) and if any remainder remains it will be counted for last number in the array
        // thus making the array strictly increasing
        int m = n / 2 + 1;
        cout << p / m << "\n";
    }
    return 0;
}