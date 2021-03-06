package com.wix.mediaplatform.dto.job;

import com.wix.mediaplatform.dto.response.RestResponse;

import java.util.Arrays;

public abstract class Job {

    private String id;

    private Type type;

    private String issuer;

    private Status status;

    private String groupId;

    private Source[] sources;

    private String dateCreated;

    private String dateUpdated;

    public String getId() {
        return id;
    }

    public String getType() {
        return type.getValue();
    }

    public String getIssuer() {
        return issuer;
    }

    public String getStatus() {
        return status.name();
    }

    public String getGroupId() {
        return groupId;
    }

    public Source[] getSources() {
        return sources;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public abstract Specification getSpecification();

    public abstract RestResponse getResult();

    public Job setId(String id) {
        this.id = id;
        return this;
    }

    public Job setType(Type type) {
        this.type = type;
        return this;
    }

    public Job setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public Job setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Job setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Job setSources(Source[] sources) {
        this.sources = sources;
        return this;
    }

    public Job setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Job setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", issuer='" + issuer + '\'' +
                ", status=" + status +
                ", groupId='" + groupId + '\'' +
                ", sources=" + Arrays.toString(sources) +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                '}';
    }

    public enum Type {
        TRANSCODE("urn:job:av.transcode"),
        PACKAGE("urn:job:av.package"),
        ARCHIVE_CREATE("urn:job:archive.create"),
        ARCHIVE_EXTRACT("urn:job:archive.extract"),
        FILE_IMPORT("urn:job:import.file"),
        REPLICATION_ENABLE("urn:job:replication.enable"),
        REPLICATION_DISABLE("urn:job:replication.disable");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Type fromString(String typeString) {
            for (Type type: Type.values()) {
                if (type.getValue().equals(typeString)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid value for job type: " + typeString);
        }
    }

    public enum Status {
        pending("pending"),
        working("working"),
        success("success"),
        error("error");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Status fromString(String typeString) {
            return Status.valueOf(typeString);
        }
    }
}
