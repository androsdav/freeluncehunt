package com.adidyk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Duplicate {

    // 1,2,3,4,1,1,4,3,2,5
    public List<Integer> removeDuplicate(List<Integer> input) {
        HashSet<Integer> set = new HashSet<>(input);
        return new ArrayList<>(set);
    }

}
