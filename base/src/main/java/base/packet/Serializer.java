package base.packet;

import com.alibaba.fastjson2.JSON;

public class Serializer {
    public static byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    public static  <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }
}
