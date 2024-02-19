//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.githhub.codeman.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Guavas {
    public static <E> List<E> newArrayList(int size) {
        return new ArrayList(size);
    }

    public static <E> List<E> newArrayList(E... elems) {
        if (ArrayUtil.isEmpty(elems)) {
            return new ArrayList();
        } else {
            List<E> list = newArrayList(elems.length);
            list.addAll(Arrays.asList(elems));
            return list;
        }
    }

}
