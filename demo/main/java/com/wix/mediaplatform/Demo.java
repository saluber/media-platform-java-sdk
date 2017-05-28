package com.wix.mediaplatform;

import com.google.gson.Gson;
import com.wix.mediaplatform.dto.job.Destination;
import com.wix.mediaplatform.dto.job.FileImportJob;
import com.wix.mediaplatform.dto.job.Job;
import com.wix.mediaplatform.dto.metadata.FileDescriptor;
import com.wix.mediaplatform.dto.request.ImportFileRequest;
import com.wix.mediaplatform.dto.request.SearchJobsRequest;
import com.wix.mediaplatform.dto.response.SearchJobsResponse;
import com.wix.mediaplatform.exception.UnauthorizedException;
import com.wix.mediaplatform.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

class Demo {

    private final MediaPlatform mediaPlatform;

    private final Gson gson = MediaPlatform.getGson(true);

    Demo(MediaPlatform mediaPlatform) {
        this.mediaPlatform = mediaPlatform;
    }

    void importFile() throws IOException, UnauthorizedException, URISyntaxException, InterruptedException {
        ImportFileRequest importFileRequest = new ImportFileRequest()
                .setSourceUrl("https://static.wixstatic.com/media/f31d7d0cfc554aacb1d737757c8d3f1b.jpg")
                .setDestination(new Destination()
                        .setDirectory("/demo/import/" + UUID.randomUUID().toString())
                        .setAcl("public"));
        Job job = mediaPlatform.fileManager().importFile(importFileRequest);

        boolean ready = false;
        int attempt = 0;
        while (!ready && attempt < 60) {
            job = mediaPlatform.jobManager().getJob(job.getId());
            attempt++;
            System.out.print(attempt + " ");

            if ("success".equals(job.getStatus()) || "error".equals(job.getStatus())) {
                ready = true;
            }
            Thread.sleep(1000);
        }

        String url = new Image((FileDescriptor) job.getResult().getPayload())
                .setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com")
                .smartCrop(400, 300)
                .toUrl();
        System.out.println("SEE IMPORTED IMAGE @ " + url);
    }

    void uploadImage() throws IOException, UnauthorizedException, URISyntaxException {

        String id = UUID.randomUUID().toString();

        File file = new File(this.getClass().getClassLoader().getResource("files/golan.jpg").getFile());
        FileDescriptor[] files = mediaPlatform.fileManager().uploadFile("/demo/upload/" + id + ".golan.jpg","image/jpeg", "golan.jpg", file, null);
        Image image = new Image(files[0]).setHost("https://images-wixmp-410a67650b2f46baa5d003c6.wixmp.com");
        image.crop(200, 300, 0, 0, 2);
        System.out.println("CROPPED IMAGE @ " + image.toUrl());
        image.smartCrop(200, 300);
        System.out.println("SMART CROPPED IMAGE @ " + image.toUrl());
    }

    void listJobs() throws IOException, UnauthorizedException, URISyntaxException {
        SearchJobsResponse response = mediaPlatform.jobManager().searchJobs(new SearchJobsRequest()
                .setType(FileImportJob.job_type)
                .setPageSize(3)
        );

        System.out.println(gson.toJson(response));
    }
}
