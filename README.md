# xml-to-json-parser

A application for parsing XML to JSON, utilizing gRPC for communication between the frontend and backend and Antlr for parsing. 
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
- **Frontend (parser-client)** written in Angular - communicates with the backend via gRPC. Enables to paste XML to get JSON
- **Backend (parser-server)** written in Kotlin - handles the XML to JSON comunication and utilize library **Parser** for parsing.  
- **Envoy** acts as a proxy to enable gRPC-Web communication.
- **Parser** written in Java. Uses Antlr to handle conversion logic. 

---

## Architecture


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


