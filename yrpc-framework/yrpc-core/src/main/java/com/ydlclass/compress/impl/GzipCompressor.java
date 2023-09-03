package com.ydlclass.compress.impl;

import com.ydlclass.compress.Compressor;
import com.ydlclass.exceptions.CompressException;
import io.netty.handler.codec.compression.CompressionException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 使用gzip算法进行压缩的具体实现
 * @author it楠老师
 * @createTime 2023-07-05
 */
@Slf4j
public class GzipCompressor implements Compressor {

    private static final int BUFFER_SIZE = 1024 * 4;
    
    @Override
    public byte[] compress(byte[] bytes) {
    
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(baos);
        ) {
            gzipOutputStream.write(bytes);
            gzipOutputStream.finish();
            byte[] result = baos.toByteArray();
            if(log.isDebugEnabled()){
                log.debug("对字节数组进行了压缩长度由【{}】压缩至【{}】.",bytes.length,result.length);
            }
            return result;
        } catch (IOException e){
            log.error("对字节数组进行压缩时发生异常",e);
            throw new CompressException(e);
        }

    }
    
    @Override
    public byte[] decompress(byte[] bytes) {
        if (bytes == null) {
            throw new CompressionException("我们试图解压缩一个字节数组，但它为空");
        }
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                GZIPInputStream degzip = new GZIPInputStream(new ByteArrayInputStream(bytes));
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int n;
            while ((n = degzip.read(buffer)) > -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (IOException e) {
            throw new CompressionException("解压异常", e);
        }
    }
}
