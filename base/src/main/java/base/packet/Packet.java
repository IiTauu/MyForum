package base.packet;


import base.request.ClientRequest;
import base.request.RequestType;
import base.response.ResponseType;
import base.response.ServerResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class Packet {
    private static final int MAGIC_NUMBER = 0xCAFEBABE;

    public static ByteBuf encode(ClientRequest request) {
        ByteBuf result = Unpooled.buffer();
        result.writeInt(MAGIC_NUMBER);
        result.writeByte(request.getRequestCode());
        byte[] requestBytes = Serializer.serialize(request);
        result.writeInt(requestBytes.length);
        result.writeBytes(requestBytes);
        return result;
    }

    public static ByteBuf encode(ServerResponse response) {
        ByteBuf result = Unpooled.buffer();
        result.writeInt(MAGIC_NUMBER);
        result.writeByte(response.getResponseCode());
        byte[] responseBytes = Serializer.serialize(response);
        result.writeInt(responseBytes.length);
        result.writeBytes(responseBytes);
        return result;
    }

    public static ClientRequest decodeRequest(ByteBuf buffer) {
        int headLength = 10;
        if (buffer.readableBytes() < headLength) {
            return null;
        }
        int magicNumber = buffer.readInt();
        var requestType = RequestType.getRequestType(buffer.readByte());
        int requestLength = buffer.readInt();
        if (buffer.readableBytes() < requestLength) {
            return null;
        }
        byte[] requestBytes = new byte[requestLength];
        buffer.readBytes(requestBytes);

        if (requestType != null) {
            return Serializer.deserialize(requestBytes, ClientRequest.class);
        }
        return null;
    }

    public static ServerResponse decodeResponse(ByteBuf buffer) {
        int headLength = 10;
        if (buffer.readableBytes() < headLength) {
            return null;
        }
        int magicNumber = buffer.readInt();
        var responseType = ResponseType.getResponseType(buffer.readByte());
        int responseLength = buffer.readInt();
        byte[] responseBytes = new byte[responseLength];
        buffer.readBytes(responseBytes);
        if (responseType != null) {
            return Serializer.deserialize(responseBytes, responseType);
        }
        return null;
    }
}
