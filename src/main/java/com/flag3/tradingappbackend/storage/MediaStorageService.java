package com.flag3.tradingappbackend.storage;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class MediaStorageService {

    private final String bucketName;

    private final Storage storage;

    public MediaStorageService(@Value("${tradingapp.gcs.bucket}") String bucketName, Storage storage) {
        this.bucketName = bucketName;
        this.storage = storage;
    }

    public String upload(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        BlobInfo blobInfo;

        try {
            blobInfo = storage.createFrom(
                    BlobInfo
                            .newBuilder(bucketName, fileName)
                            .setContentType(file.getContentType())
                            .setAcl(List.of(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)))
                            .build(),
                    file.getInputStream()
            );
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file to GCS");
        }

        return blobInfo.getMediaLink();
    }

}
