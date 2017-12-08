package com.rengu.operationsoanagementsuite.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {
    // 计算文件的MD5值
    public static String getFileMD5(File file) throws IOException, NoSuchAlgorithmException {
        FileInputStream fileInputStream = new FileInputStream(file);
        MappedByteBuffer mappedByteBuffer = fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(mappedByteBuffer);
        BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
        return bigInteger.toString(16);
    }
}