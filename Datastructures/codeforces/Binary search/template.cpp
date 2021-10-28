#include <bits/stdc++.h>
using namespace std;
//logorithimic complexity O(logn)
//iterative method
int binary_search(int arr[], int n, int key)
{
    int s = 0;
    int e = n - 1;
    int mid;
    while (s <= e)
    {
        //overflow condition
        mid = s + (e - s) / 2;
        if (arr[mid] == key)
        {
            return mid;
        }
        else if (arr[mid] > key)
        {
            e = mid - 1;
        }
        else
        {
            s = mid + 1;
        }
    }
}

//recursive approach

int binary_search(int arr[], int s, int e, int key)
{
    if (s >= e)
    {
        return -1;
    }
    int mid;
    mid = s + (e - s) / 2;
    if (arr[mid] == key)
    {
        return mid;
    }
    if (arr[mid] > key)
    {
       return binary_search(arr, s, mid - 1, key);
    }
    else
    {
       return binary_search(arr, mid + 1, e, key);
    }
}