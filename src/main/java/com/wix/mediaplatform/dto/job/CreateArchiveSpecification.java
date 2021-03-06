package com.wix.mediaplatform.dto.job;


public class CreateArchiveSpecification implements Specification {

    private Source[] sources;

    private Destination destination;

    private String archiveType;

    public CreateArchiveSpecification() {}

    public Source[] getSources() {
        return sources;
    }

    public Destination getDestination() {
        return destination;
    }

    public String getArchiveType() { return archiveType; }

    @Override
    public String toString() {
        return "CreateArchiveSpecification{" +
                "source=" + sources +
                ", destination=" + destination +
                ", archiveType=" + archiveType +
                '}';
    }
}
