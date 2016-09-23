package com.zzz.draw.bean;

import com.zzz.draw.encoder.ObjectEncoder;

import java.io.File;

/**
 * Created by dell_2 on 2016/9/23.
 */
public class SendFile {

    private String fileName;

    private byte[] bytes;

    public SendFile() {

    }

    public SendFile(File file) {
        fileName = file.getName();
        bytes = ObjectEncoder.fileToBytes(file);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
