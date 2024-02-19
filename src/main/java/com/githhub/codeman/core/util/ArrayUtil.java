//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.githhub.codeman.core.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ArrayUtil {
    public static final String[] STRING_EMPTY = new String[0];

    private ArrayUtil() {
    }

    public static boolean isEmpty(Object[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static <T> List<T> toList(T[] objects) {
        if (isEmpty(objects)) {
            return Collections.emptyList();
        } else {
            List<T> objectList = new ArrayList(objects.length);
            objectList.addAll(Guavas.newArrayList(objects));
            return objectList;
        }
    }

    public static Object[] toArray(List<?> objectList) {
        if (CollectionUtil.isEmpty(objectList)) {
            return new Object[0];
        } else {
            Object[] objects = new Object[objectList.size()];

            for(int i = 0; i < objects.length; ++i) {
                objects[i] = objectList.get(i);
            }

            return objects;
        }
    }




    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != -1;
    }

    public static boolean notContains(Object[] array, Object objectToFind) {
        return !contains(array, objectToFind);
    }

    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return -1;
        } else {
            if (startIndex < 0) {
                startIndex = 0;
            }

            int i;
            if (objectToFind == null) {
                for(i = startIndex; i < array.length; ++i) {
                    if (array[i] == null) {
                        return i;
                    }
                }
            } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
                for(i = startIndex; i < array.length; ++i) {
                    if (objectToFind.equals(array[i])) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }






    public static List toList(Object arrayObject, IHandler keyFunction) {
        if (ObjectUtil.isNull(arrayObject)) {
            return Collections.emptyList();
        } else {
            Class arrayClass = arrayObject.getClass();
            if (boolean[].class == arrayClass) {
                boolean[] booleans = (boolean[])((boolean[])arrayObject);
                return ArrayPrimitiveUtil.toList(booleans, keyFunction);
            } else if (short[].class == arrayClass) {
                short[] shorts = (short[])((short[])arrayObject);
                return ArrayPrimitiveUtil.toList(shorts, keyFunction);
            } else if (byte[].class == arrayClass) {
                byte[] bytes = (byte[])((byte[])arrayObject);
                return ArrayPrimitiveUtil.toList(bytes, keyFunction);
            } else if (int[].class == arrayClass) {
                int[] ints = (int[])((int[])arrayObject);
                return ArrayPrimitiveUtil.toList(ints, keyFunction);
            } else if (float[].class == arrayClass) {
                float[] floats = (float[])((float[])arrayObject);
                return ArrayPrimitiveUtil.toList(floats, keyFunction);
            } else if (double[].class == arrayClass) {
                double[] doubles = (double[])((double[])arrayObject);
                return ArrayPrimitiveUtil.toList(doubles, keyFunction);
            } else if (char[].class == arrayClass) {
                char[] chars = (char[])((char[])arrayObject);
                return ArrayPrimitiveUtil.toList(chars, keyFunction);
            } else if (long[].class == arrayClass) {
                long[] longs = (long[])((long[])arrayObject);
                return ArrayPrimitiveUtil.toList(longs, keyFunction);
            } else {
                Object[] objects = (Object[])((Object[])arrayObject);
                return toList(objects, keyFunction);
            }
        }
    }

    public static Object[] shift(Object[] array, int offset) {
        if (isEmpty(array)) {
            return array;
        } else {
            int arrayLength = array.length;
            int actualOffset = offset;
            if (offset < 0) {
                actualOffset = offset + arrayLength;
            }

            Object[] newArray = new Object[arrayLength];

            for(int i = 0; i < arrayLength; ++i) {
                int realIndex = (i + actualOffset) % arrayLength;
                newArray[i] = array[realIndex];
            }

            return newArray;
        }
    }
}
