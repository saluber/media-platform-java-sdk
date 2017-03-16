package com.wix.mediaplatform.dto.download;

public class DownloadUrlRequest {

    private Integer ttl;

    private Attachment attachment;

    private String onExpireRedirectTo;

    public DownloadUrlRequest() {
    }

    public Integer getTtl() {
        return ttl;
    }

    public DownloadUrlRequest setTtl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public DownloadUrlRequest setAttachment(Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public String getOnExpireRedirectTo() {
        return onExpireRedirectTo;
    }

    public DownloadUrlRequest setOnExpireRedirectTo(String onExpireRedirectTo) {
        this.onExpireRedirectTo = onExpireRedirectTo;
        return this;
    }

    public class Attachment {
        private String filename;

        public Attachment() {
        }

        public String getFilename() {
            return filename;
        }

        public Attachment setFilename(String filename) {
            this.filename = filename;
            return this;
        }
    }
}
