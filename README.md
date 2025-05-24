# xml-to-json-parser

An application for parsing XML to JSON, utilizing gRPC for communication between the frontend and backend and Antlr for parsing.  
Envoy is used as a proxy to enable gRPC-Web support.  
The entire stack is orchestrated using Docker and Docker Compose.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Project Structure](#project-structure)

---

## Overview

This project provides a robust solution for converting XML data to JSON format. The system is split into multiple services:
- **Frontend (parser-client)** — written in Angular. Communicates with the backend via gRPC. Allows users to paste XML and get JSON.
- **Backend (parser-server)** — written in Kotlin. Handles the XML to JSON communication and utilizes the **Parser** library for parsing.
- **Envoy** — acts as a proxy to enable gRPC-Web communication.
- **Parser** — written in Java. Uses Antlr to handle the conversion logic.

---

## Architecture

```
[parser-client (Angular)]
|
gRPC-Web
|
[Envoy]
|
gRPC
|
[parser-server (Kotlin)]
|
(uses)
|
[parser (Java/Antlr)]
```

- **parser-client** (Angular): The frontend application. Sends requests using gRPC-Web.
- **Envoy**: Acts as a proxy, translating gRPC-Web requests from the browser (HTTP/1.1) to standard gRPC for the backend (HTTP/2).
- **parser-server** (Kotlin): The backend service. Receives gRPC requests, processes them, and uses the Parser library to get JSON.
- **parser** (Java/Antlr): A Java library using Antlr for XML parsing and conversion to JSON.

---

## Requirements

- [Docker](https://www.docker.com/)

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/piotrszczypior/xml-to-json-parser.git
cd xml-to-json-parser
```

### 2. Start services
``` bash 
docker-compose up --build
```

## Usage

Access the Frontend:
Open your browser and navigate to `http://localhost:4200`

## Project Structure

```
.
├── envoy/                # Envoy proxy configuration
├── parser-client/        # Frontend client (gRPC-Web)
├── parser-server/        # Backend server (gRPC)
├── parser/               # Library utilizing Antlr
├── docker-compose.yml    # Docker Compose configuration
└── .gitignore
```


