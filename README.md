# JME Object Storage Example

This sample application shows how to deploy an application that uses an S3 object store with the
[jeap-spring-boot-object-storage-starter](https://github.com/jeap-admin-ch/jeap-spring-boot-starters/tree/main/jeap-spring-boot-object-storage-starter)
library.

## What this example demonstrates

- **Bucket management** (`BucketController`, `/api/buckets`) — create, list and delete S3 buckets. Only enabled
  when `jeap.s3.bucket-management.enabled=true` (bucket management is normally disabled/handled externally in
  deployed environments; it is enabled here for local experimentation).
- **File upload/download** (`FileController`, `/api/buckets/{bucketName}/files`) — put a file (multipart upload)
  or a randomly generated file into a bucket, list the objects in a bucket, download an object by key, and delete
  an object. Backed by `S3BucketObjectService`.

This library is versioned using [Semantic Versioning](http://semver.org/) and all changes are documented in
[CHANGELOG.md](./CHANGELOG.md) following the format defined in [Keep a Changelog](http://keepachangelog.com/).

## Prerequisites

To use this project, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Version 25.
2. **Docker**: For running the required infrastructure.

**Note:** Use the provided maven wrapper to build and run the project.

## Getting started

### Infrastructure

Before the examples can be started the infrastructure has to be started using docker

```shell
docker-compose -f docker/docker-compose.yml up
```

### Build

The project itself can be built with a simple

```shell
./mvnw install
```

### Start

Then the project can be started using

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Testing locally

After starting the application with an S3 (see below sections), two classes `BucketController` and `FileController`
providing basic CRUD functionality, which can be executed via Swagger or directly via cURL, etc.:

- Swagger UI: [http://localhost:8080/jme-object-storage-example/swagger-ui.html](http://localhost:8080/jme-object-storage-example/swagger-ui.html)

## Note

This repository is part of the open source distribution of JME. See [github.com/jme-admin-ch/jme](https://github.com/jme-admin-ch/jme)
for more information.

## License

This repository is Open Source Software licensed under the [Apache License 2.0](./LICENSE).
