#include <bits/stdc++.h>
using namespace std;
//function to find minimum swaps
int min_swaps(string s1, string s2)
{
    int i = 0, j = 0;
    int result = 0;
    int n = s1.length();
    if(s1.compare(s2) == 0){
        return 0;
    }
    while (i < n)
    {
        j = i;
        //iterate till characters of both the strings match
        while (s1[j] != s2[i])
        {
            j += 1;
        }
        //iterating until i=j
        //result will be j-i
        while (i < j)
        {
            char temp = s1[j];
            s1[j] = s1[j - 1];
            s1[j - 1] = temp;
            j -= 1;
            result += 1;
        }
        i += 1;
    }
    return result;
}
 
//driver code
 
int main()
{
    int n;
    cin>>n;
    string s1 ;
    cin>>s1;
    string s2 =s1;
    //function to reverse a string
    reverse(s2.begin(), s2.end());
    cout << min_swaps(s1, s2);
    return 0;
}