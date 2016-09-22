package com.zzz.draw.bean;

import com.zzz.draw.util.ByteToPointsUtil;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by dell_2 on 2016/9/21.
 */
public class Message {

    public final static int  MSG = 1;
    public final static int  POINT = 2;
    private int type;
    private Object body;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream out  = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        if(type==POINT){
            dataOut.writeInt(type);
            addPointsBytes(dataOut);
        }
        return out.toByteArray();
    }

    private void addPointsBytes(DataOutputStream out) throws IOException {
        List<Point> pointList = (List<Point>) body;
        for (Point point : pointList) {
            out.writeInt(point.x);
            out.writeInt(point.y);
        }
    }

    public static Message parseForm( byte[] bytes) throws IOException {
        DataInputStream in = ByteToPointsUtil.dataInputStream(bytes);
        Message message = new Message();
        message.setType(in.readInt());
        if(message.getType()==POINT){
            int len = (bytes.length - 4)/8;
            message.setBody(ByteToPointsUtil.decode(in,len));
        }
        return message;
    }

}
