/*
1784. Check if Binary String Has at Most One Segment of Ones
Easy
Topics
premium lock icon
Companies
Hint
Given a binary string s ​​​​​without leading zeros, return true​​​ if s contains at most one contiguous segment of ones. Otherwise, return*/
class Solution {

    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
