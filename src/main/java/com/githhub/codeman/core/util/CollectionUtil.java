

package com.githhub.codeman.core.util;


import java.util.*;

public final class CollectionUtil {
    public static final List EMPTY_LIST = Collections.emptyList();

    private CollectionUtil() {
    }

    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }


    public static <T> List<T> conditionList(List<T> list, ICondition<T> condition) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        } else {
            List<T> resultList = new ArrayList();
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
                T t = (T) var3.next();
                if (condition.condition(t)) {
                    resultList.add(t);
                }
            }

            return resultList;
        }
    }
    public static String[] listToArray(List<String> stringList) {
        String[] strings = new String[stringList.size()];
        return (String[])stringList.toArray(strings);
    }













    public static List<Integer> fill(int size, int initValue) {
        List<Integer> list = Guavas.newArrayList(size);

        for(int i = 0; i < size; ++i) {
            list.add(i + initValue);
        }

        return list;
    }



    public static <T> List<T> distinct(Collection<T> collection) {
        if (isEmpty(collection)) {
            return Collections.emptyList();
        } else {
            return collection instanceof Set ? new ArrayList(collection) : new ArrayList(new LinkedHashSet(collection));
        }
    }




    public static <T> List<T> list() {
        return Collections.emptyList();
    }

    public static <T> List<T> list(T t) {
        return Collections.singletonList(t);
    }

    public static <T> List<T> list(T... ts) {
        return new ArrayList(Arrays.asList(ts));
    }

    public static <T> List<T> copy(List<T> list) {
        return new ArrayList(list);
    }

    public static <T> T head(List<T> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    public static <T> T tail(List<T> list) {
        return isEmpty(list) ? null : list.get(list.size() - 1);
    }

    public static <T> List<T> append(List<T> list, T t) {
        if (list == null) {
            list = new ArrayList();
        }

        ((List)list).add(t);
        return (List)list;
    }




}
