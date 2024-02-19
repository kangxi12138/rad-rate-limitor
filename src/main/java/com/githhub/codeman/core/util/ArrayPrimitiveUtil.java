//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.githhub.codeman.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public final class ArrayPrimitiveUtil {
    public static final int[] INT_EMPTY = new int[0];
    public static final short[] SHORT_EMPTY = new short[0];
    public static final long[] LONG_EMPTY = new long[0];
    public static final float[] FLOAT_EMPTY = new float[0];
    public static final double[] DOUBLE_EMPTY = new double[0];
    public static final char[] CHAR_EMPTY = new char[0];
    public static final byte[] BYTE_EMPTY = new byte[0];
    public static final boolean[] BOOLEAN_EMPTY = new boolean[0];

    private ArrayPrimitiveUtil() {
    }

    public static boolean isEmpty(int[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(int[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(boolean[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(boolean[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(char[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(char[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(byte[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(byte[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(long[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(long[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(float[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(float[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(double[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(double[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(short[] objects) {
        return null == objects || objects.length <= 0;
    }

    public static boolean isNotEmpty(short[] objects) {
        return !isEmpty(objects);
    }

    public static <K> List<K> toList(boolean[] values, IHandler<? super Boolean, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            boolean[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                boolean value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(char[] values, IHandler<? super Character, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            char[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                char value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(byte[] values, IHandler<? super Byte, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            byte[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                byte value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(short[] values, IHandler<? super Short, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            short[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                short value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(int[] values, IHandler<? super Integer, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            int[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                int value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(float[] values, IHandler<? super Float, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            float[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                float value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(double[] values, IHandler<? super Double, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            double[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                double value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static <K> List<K> toList(long[] values, IHandler<? super Long, K> keyFunction) {
        if (isEmpty(values)) {
            return Collections.emptyList();
        } else {
            List<K> list = new ArrayList(values.length);
            long[] var3 = values;
            int var4 = values.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                long value = var3[var5];
                K key = keyFunction.handle(value);
                list.add(key);
            }

            return list;
        }
    }

    public static int indexOf(char[] chars, char c) {
        if (isEmpty(chars)) {
            return -1;
        } else {
            for(int i = 0; i < chars.length; ++i) {
                char cs = chars[i];
                if (cs == c) {
                    return i;
                }
            }

            return -1;
        }
    }

    public static boolean contains(char[] chars, char c) {
        if (isEmpty(chars)) {
            return false;
        } else {
            char[] var2 = chars;
            int var3 = chars.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                char cs = var2[var4];
                if (cs == c) {
                    return true;
                }
            }

            return false;
        }
    }

    public static int lastIndexOf(char[] chars, char c) {
        if (isEmpty(chars)) {
            return -1;
        } else {
            int lastIndex = -1;

            for(int i = 0; i < chars.length; ++i) {
                char cs = chars[i];
                if (cs == c) {
                    lastIndex = i;
                }
            }

            return lastIndex;
        }
    }

    public static List<Integer> allIndexOf(char[] chars, char c) {
        if (isEmpty(chars)) {
            return Collections.emptyList();
        } else {
            List<Integer> indexList = Guavas.newArrayList();

            for(int i = 0; i < chars.length; ++i) {
                char cs = chars[i];
                if (cs == c) {
                    indexList.add(i);
                }
            }

            return indexList;
        }
    }

    public static String getStringBeforeSymbol(char[] chars, int startIndex, char symbol) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean doubleQuotesStart = false;
        char preChar = ' ';

        for(int i = startIndex; i < chars.length; ++i) {
            char currentChar = chars[i];
            preChar = getPreChar(preChar, currentChar);
            if ('\\' != preChar && '"' == currentChar) {
                doubleQuotesStart = !doubleQuotesStart;
            }

            if (!doubleQuotesStart && symbol == currentChar) {
                return stringBuilder.toString();
            }

            stringBuilder.append(currentChar);
        }

        return stringBuilder.toString();
    }

    private static char getPreChar(char preChar, char currentChar) {
        return '\\' == preChar && '\\' == currentChar ? ' ' : currentChar;
    }

    public static <E> int[] toIntArray(List<E> list, IHandler<E, Integer> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return INT_EMPTY;
        } else {
            int size = list.size();
            int[] ints = new int[size];

            for(int i = 0; i < size; ++i) {
                ints[i] = (Integer)handler.handle(list.get(i));
            }

            return ints;
        }
    }

    public static <E> boolean[] toBooleanArray(List<E> list, IHandler<E, Boolean> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return BOOLEAN_EMPTY;
        } else {
            int size = list.size();
            boolean[] arrays = new boolean[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Boolean)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> char[] toCharArray(List<E> list, IHandler<E, Character> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return CHAR_EMPTY;
        } else {
            int size = list.size();
            char[] arrays = new char[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Character)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> byte[] toByteArray(List<E> list, IHandler<E, Byte> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return BYTE_EMPTY;
        } else {
            int size = list.size();
            byte[] arrays = new byte[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Byte)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> short[] toShortArray(List<E> list, IHandler<E, Short> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return SHORT_EMPTY;
        } else {
            int size = list.size();
            short[] arrays = new short[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Short)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> long[] toLongArray(List<E> list, IHandler<E, Long> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return LONG_EMPTY;
        } else {
            int size = list.size();
            long[] arrays = new long[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Long)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> float[] toFloatArray(List<E> list, IHandler<E, Float> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return FLOAT_EMPTY;
        } else {
            int size = list.size();
            float[] arrays = new float[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Float)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static <E> double[] toDoubleArray(List<E> list, IHandler<E, Double> handler) {
        if (CollectionUtil.isEmpty(list)) {
            return DOUBLE_EMPTY;
        } else {
            int size = list.size();
            double[] arrays = new double[size];

            for(int i = 0; i < size; ++i) {
                arrays[i] = (Double)handler.handle(list.get(i));
            }

            return arrays;
        }
    }

    public static int[] newArray(int... arrays) {
        return arrays;
    }

    public static boolean[] newArray(boolean... arrays) {
        return arrays;
    }

    public static char[] newArray(char... arrays) {
        return arrays;
    }

    public static short[] newArray(short... arrays) {
        return arrays;
    }

    public static long[] newArray(long... arrays) {
        return arrays;
    }

    public static byte[] newArray(byte... arrays) {
        return arrays;
    }

    public static float[] newArray(float... arrays) {
        return arrays;
    }

    public static double[] newArray(double... arrays) {
        return arrays;
    }
}
