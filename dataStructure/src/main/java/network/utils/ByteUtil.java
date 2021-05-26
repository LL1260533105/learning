package network.utils;

import network.contants.UDPContants;

import java.io.UnsupportedEncodingException;

/**
 * 字节类型 工具类
 */
public class ByteUtil {
    /**
     * double转换byte
     *
     * @param arr   byte[]
     * @param param double double类型的参数
     * @param index int
     */
    public static void double2Byte(byte[] arr, double param, int index) {
        int len = (index - 1) + 8;
        int arrLen = arr.length;
        boolean b = isOutOfArrLength(arrLen, len);
        Long l = Double.doubleToLongBits(param);
        if (b) {
            for (int i = 7; i >= 0; i--) {
                arr[index + i] = l.byteValue();
                l = l >> 8;
            }
        } else {
            // 如果当前数组长度小于转换的数组长度，就根据index截取转换的数组元素
            l = l >> (8 * index);
            for (int j = arrLen - index - 1; j >= 0; j--) {
                arr[index + j] = l.byteValue();
                l = l >> 8;
            }
        }
    }

    /**
     * float转换byte
     *
     * @param arr   byte[]
     * @param param float float类型的参数
     * @param index int
     */
    public static void float2Byte(byte[] arr, float param, int index) {
        int len = (index - 1) + 4;
        int arrLen = arr.length;
        boolean b = isOutOfArrLength(arrLen, len);
        int l = Float.floatToIntBits(param);
        if (b) {
            for (int i = 3; i >= 0; i--) {
                arr[index + i] = new Integer(l).byteValue();
                l = l >> 8;
            }
        } else {
            // 如果当前数组长度小于转换的数组长度，就根据index截取转换的数组元素
            l = l >> (8 * index);
            for (int j = arrLen - index - 1; j >= 0; j--) {
                arr[index + j] = new Integer(l).byteValue();
                l = l >> 8;
            }
        }
    }

    /**
     * 字符到字节转换
     *
     * @param arr   byte[]
     * @param ch    char char类型的参数
     * @param index int
     * @return
     */
    public static void char2Byte(byte[] arr, char ch, int index) {
        int len = (index - 1) + 4;
        boolean b = isOutOfArrLength(arr.length, len);
        if (b) {
            int temp = (int) ch;
            for (int i = 1; i >= 0; i--) {
                arr[index + i] = new Integer(temp & 0xff).byteValue();
                temp = temp >> 8;
            }
        }
    }

    /**
     * 转换long型为byte数组
     *
     * @param arr   byte[]
     * @param param long
     * @param index int
     */
    public static void long2Byte(byte[] arr, long param, int index) {
        int len = (index - 1) + 8;
        int arrLen = arr.length;
        boolean b = isOutOfArrLength(arrLen, len);
        if (b) {
            arr[index + 0] = (byte) ((param >> 56) & 0xff);
            arr[index + 1] = (byte) ((param >> 48) & 0xff);
            arr[index + 2] = (byte) ((param >> 40) & 0xff);
            arr[index + 3] = (byte) ((param >> 32) & 0xff);
            arr[index + 4] = (byte) ((param >> 24) & 0xff);
            arr[index + 5] = (byte) ((param >> 16) & 0xff);
            arr[index + 6] = (byte) ((param >> 8) & 0xff);
            arr[index + 7] = (byte) (param & 0xff);
        } else {
            // 如果当前数组长度小于转换的数组长度，就根据index截取转换的数组元素
            param = param >> (8 * index);
            for (int i = arrLen - index - 1; i >= 0; i--) {
                arr[index + i] = (byte) (param & 0xff);
                param = param >> 8;
            }
        }
    }

    /**
     * int类型转换成byte数组
     *
     * @param arr   byte[]
     * @param param int int类型的参数
     * @param index int
     */
    public static void int2Byte(byte[] arr, int param, int index) {
        int len = (index - 1) + 4;
        boolean b = isOutOfArrLength(arr.length, len);
        if (b) {
            arr[index + 0] = (byte) ((param >> 24) & 0xff);
            arr[index + 1] = (byte) ((param >> 16) & 0xff);
            arr[index + 2] = (byte) ((param >> 8) & 0xff);
            arr[index + 3] = (byte) (param & 0xff);
        }
    }

    /**
     * short类型转换成byte数组
     *
     * @param arr   byte[]
     * @param param short
     * @param index int
     */
    public static void short2Byte(byte[] arr, short param, int index) {
        int len = (index - 1) + 2;
        boolean b = isOutOfArrLength(arr.length, len);
        if (b) {
            arr[index + 0] = (byte) ((param >> 8) & 0xff);
            arr[index + 1] = (byte) (param & 0xff);
        }
    }

    /**
     * 字符串转换成byte数组
     *
     * @param arr   byte[]
     * @param str   String
     * @param index int
     */
    public static void string2Byte(byte[] arr, String str, int index) {
        try {
            byte[] bb = str.getBytes("GBK");
            int len = index + bb.length;
            boolean b = isOutOfArrLength(arr.length, len);
            if (b) {
                for (int i = 0; i < bb.length; i++) {
                    arr[index + i] = bb[i];
                }
            } else {
                // 如果当前数组长度小于转换的数组长度，就根据index截取转换的数组元素
                for (int j = 0; j < arr.length - index; j++) {
                    arr[index + j] = bb[j];
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断数组下标是否越界
     *
     * @param arrLength 数组总长度
     * @param index     数组偏移量
     * @return
     */
    public static boolean isOutOfArrLength(int arrLength, int index) {
        boolean b;
        if (arrLength > index) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }


    public static boolean startWith(byte[] a, byte[] b) {
        byte[] newByte = new byte[b.length];
        System.arraycopy(newByte, 0, a, 0, b.length );
        return String.valueOf(a).startsWith(String.valueOf(b));
    }

    public static void main(String[] args) {
        String str = UDPContants.head + "hello";
        System.out.println(ByteUtil.startWith(str.getBytes(), UDPContants.head.getBytes()));
    }
}
