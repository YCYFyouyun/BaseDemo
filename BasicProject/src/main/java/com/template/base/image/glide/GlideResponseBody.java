package com.template.base.image.glide;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class GlideResponseBody extends ResponseBody {
    private final HttpUrl url;
    private final ResponseBody responseBody;
    private final OnTransferListener listener;
    private BufferedSource bufferedSource;

    public GlideResponseBody(HttpUrl url, ResponseBody responseBody, OnTransferListener listener) {
        this.url = url;
        this.responseBody = responseBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null)
            bufferedSource = Okio.buffer(update(responseBody.source()));
        return bufferedSource;
    }

    private Source update(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                long fullLength = responseBody.contentLength();
                if (bytesRead == -1) {
                    //已经读取完成
                    totalBytesRead = fullLength;
                } else {
                    totalBytesRead += bytesRead;
                }
                listener.onProgressing(url, totalBytesRead, fullLength);
                return bytesRead;
            }
        };
    }

    public interface OnTransferListener {
        void onProgressing(HttpUrl url, long bytesRead, long contentLength);
    }
}
