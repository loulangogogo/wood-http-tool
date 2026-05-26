# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**wood-http-tool** is a lightweight Java 8 HTTP client library wrapping OkHttp 4.x. It is published to Maven Central under `io.github.loulangogogo:wood-http-tool`. The library depends on `water-tool` (another internal library by the same author) for utility functions and exception handling.

## Code Architecture

```
src/main/java/io/github/loulangogogo/
├── ask/                          # Core request building blocks
│   ├── WoodHttpClient.java       # OkHttpClient config with builder (timeouts, logging)
│   ├── WoodHttpRequestUrl.java   # URL + query param assembly
│   ├── WoodHttpRequestHeader.java # Request header assembly
│   ├── WoodHttpRequestMethod.java # HTTP method + body assembly
│   ├── WoodHttpRequestBody.java  # Request body creation (JSON, multipart)
│   ├── HttpRequestTool.java      # Core API: request() and uploadFile() entry points
│   └── HttpResponseTool.java     # Response parsing (string, bytes, input stream)
├── tool/                         # Convenience API for end users
│   ├── HttpTool.java             # Facade with nested GET/POST/PUT/DELETE classes
│   ├── HttpToolGet.java          # GET helper methods (toStr, toByteArray, etc.)
│   ├── HttpToolPost.java         # POST helper methods + uploadFile overloads
│   ├── HttpToolPut.java          # PUT helpers
│   └── HttpToolDelete.java       # DELETE helpers
├── enums/
│   └── HttpMethod.java           # Enum: GET, POST, HEAD, PUT, DELETE, PATCH
└── exception/
    └── WoodRequestException.java # RuntimeException wrapping request failures
```

**Two usage APIs:**
1. `HttpTool.GET.toStr(url)` — simplified facade, method-specific nested classes
2. `HttpRequestTool.request(url, HttpMethod, headers, params, body)` — full control with method enum

## Commands

```bash
# Build
mvn clean package

# Run tests
mvn test

# Run a single test
mvn test -Dtest=HttpRequestToolTest#request01

# Change version
mvn versions:set -DnewVersion=1.2.1

# Publish to Maven Central
mvn deploy
```

## Key Details

- **Java 8** target (source/target compiler level)
- **OkHttp 4.12.0** as the underlying HTTP client
- **water-tool 0.0.6** provides `AssertTool`, `ObjectTool`, `IoTool`, and `BaseException`
- **SLF4J + Log4j2** for logging; `slf4j-simple` is `provided` scope (consumer must supply logger)
- **JUnit 4** for tests
- Published to **Maven Central** via `central-publishing-maven-plugin` with GPG signing
- Default timeout: **120 seconds**; logging disabled by default
