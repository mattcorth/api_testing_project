# 📊 API Test Automation Framework Presentation

> **Project Demonstration Slide Deck**  
> **Target Date**: Friday 17th July, 2026 at 10:00 AM  
> **Duration**: Maximum 10 Minutes  
> **Presenter Mapping**: Allocated sections for each team member to speak.

---

## 🛝 Slide 1: Project Title & Overview
*   **Slide Title**: 🚀 Enterprise-Grade API Test Automation Framework
*   **Sub-title**: Decoupled Integration & Unit Testing Architecture for `automationexercise.com`
*   **Visual Suggestion**: Logo badges of Java, Maven, RestAssured, JUnit 5, and Mockito.
*   **Speaker**: **Matthew Corthorne** (Project Lead)
*   **Key Talking Points**:
    *   Welcome and introduction to the Sprint 1 deliverables.
    *   Aim: Deliver a highly maintainable, type-safe API testing framework verifying live end-point health, schema bindings, and offline processing logic.

---

## 🛝 Slide 2: Meet the Team & Core Roles
*   **Slide Title**: 👥 Team Structure & Agile Alignment
*   **Content**: Roster of the 7 QA & Automation engineers:
    *   **Matthew Corthorne** – Project Lead & Lead Architect
    *   **Oan Zia** – CI/CD & DevOps Lead
    *   **Piravien** – Test Case Designer
    *   **Zeenia Haji** – Senior QA Engineer
    *   **Aerhan Srirangan** – API Integration Specialist
    *   **Salah Ali** – Scrum Master
    *   **Kamaron Daley** – Automation Developer
*   **Speaker**: **Salah Ali** (Scrum Master)
*   **Key Talking Points**:
    *   Clean role division to ensure simultaneous work on client, POJOs, unit tests, and CI/CD pipelines.

---

## 🛝 Slide 3: Project Goal & Objectives
*   **Slide Title**: 🎯 Scope, Bounds & Objectives
*   **Content**:
    *   Target: `https://automationexercise.com/api`
    *   Verify at least 3 distinct endpoints (Products, Brands, Product Search).
    *   Enforce exhaustive assertions on all response payload keys.
    *   Include both Happy Path (GET, matching POST/PUT) and Sad Path (unsupported methods, missing parameters).
*   **Speaker**: **Zeenia Haji** (Senior QA Engineer)
*   **Key Talking Points**:
    *   Focusing not just on HTTP status codes (e.g. 200 OK) but verifying the *entire payload response* (JSON fields mapping, data types, and values).

---

## 🛝 Slide 4: Agile Management & Definition of Done
*   **Slide Title**: 📋 Sprint Board & Definition of Done (DoD)
*   **Content**:
    *   Agile Scrum planning using a structured 6-column board (`PROJECT_BOARD.md`).
    *   Notes column detailing the project goals and our rigid DoD.
    *   Tickets created for every task: framework setup, POJO modelling, clients, unit/integration test suites, and documentation.
*   **Speaker**: **Salah Ali** (Scrum Master)
*   **Key Talking Points**:
    *   Following the Scrum framework: daily stand-ups and progress tracking.
    *   Highlighting our Definition of Done: compilation success, 100% test pass rate, clear documentation, and clean Git commits.

---

## 🛝 Slide 5: User Stories & Scenarios
*   **Slide Title**: 👤 User Stories & GIVEN-WHEN-THEN Scenarios
*   **Content**:
    *   **User Story 1**: Products Retrieval (Happy Path GET / Sad Path POST)
    *   **User Story 2**: Brands Retrieval (Happy Path GET / Sad Path PUT)
    *   **User Story 3**: Product Keyword Search (Happy Path POST with parameter / Sad Path POST missing parameter)
*   **Speaker**: **Piravien** (Test Case Designer)
*   **Key Talking Points**:
    *   Writing user stories from the user perspective to guide automation.
    *   Using GIVEN-WHEN-THEN syntax to map test assertions directly to requirements.

---

## 🛝 Slide 6: Framework Architecture
*   **Slide Title**: 🏗️ Architecture & Package Layout
*   **Content**:
    *   Decoupled architecture: separation of Model (POJOs), Client (RestAssured calls), Service (Logic), and Tests.
    *   Maven directory layout separating source from test configurations.
*   **Speaker**: **Matthew Corthorne** (Project Lead)
*   **Key Talking Points**:
    *   Keeping the client decoupled from assertions ensures that if an endpoint path changes, we only update it in one place (`ApiClient.java`).

---

## 🛝 Slide 7: The Model (POJO) Layer
*   **Slide Title**: 📦 POJO Response Mapping & Serialisation
*   **Content**:
    *   Mapped JSON schemas using Jackson annotations (`@JsonProperty`).
    *   Response wrappers: `ProductResponse` containing a list of `Product` objects, and `BrandResponse` containing `Brand` objects.
*   **Speaker**: **Aerhan Srirangan** (API Integration Specialist)
*   **Key Talking Points**:
    *   Using Jackson databinds allows us to deserialise raw JSON payloads directly into Java objects, enabling type-safe assertions and IDE autocompletion.

---

## 🛝 Slide 8: The Client Layer & Global Parsing
*   **Slide Title**: 🔌 ApiClient & REST-Assured Configuration
*   **Content**:
    *   Base URI configuration targeting `https://automationexercise.com/api`.
    *   **Vulnerability Resolution**: Custom HTML-to-JSON parser registration:
      ```java
      static {
          RestAssured.registerParser("text/html", Parser.JSON);
      }
      ```
*   **Speaker**: **Kamaron Daley** (Automation Developer)
*   **Key Talking Points**:
    *   Resolving the server's response content-type anomaly (`text/html` instead of `application/json`) by globally mapping the parser in the client.

---

## 🛝 Slide 9: Business Logic & Mockito Unit Tests
*   **Slide Title**: 🧪 ProductService & Offline Mockito Testing
*   **Content**:
    *   `ProductService` containing filtering logic (by brand, by maximum price).
    *   Mockito tests mocking `ApiClient` and `Response` objects.
*   **Speaker**: **Kamaron Daley** (Automation Developer)
*   **Key Talking Points**:
    *   Testing business logic offline without real network dependencies.
    *   Demonstrating the use of Mockito stubs (`Mockito.when().thenReturn()`) to assert calculations are correct under standard conditions.

---

## 🛝 Slide 10: Live Integration Testing
*   **Slide Title**: 🚦 Live Integration Test Executions
*   **Content**:
    *   `ProductsIntegrationTest`, `BrandsIntegrationTest`, and `SearchIntegrationTest`.
    *   Executing GET, POST, and PUT operations on live endpoints.
    *   Asserting HTTP status codes, JSON payload status codes, and error warning messages.
*   **Speaker**: **Zeenia Haji** (Senior QA Engineer)
*   **Key Talking Points**:
    *   Live testing checks the accuracy of live API servers and validates both expected query returns and error handling on invalid requests.

---

## 🛝 Slide 11: CI/CD Pipeline & Development Branching
*   **Slide Title**: 🔄 GitHub Actions & Git Flow Branching
*   **Content**:
    *   Triggers configured on branch updates to `main` and `dev`.
    *   GitHub Runner automatically spins up Ubuntu, installs JDK 21, resolves Maven packages, and executes `mvn clean test`.
*   **Speaker**: **Oan Zia** (CI/CD & DevOps Lead)
*   **Key Talking Points**:
    *   Using a development branch (`dev`) to isolate active changes.
    *   Automating quality gates: every commit pushes a test suite run, preventing broken builds from reaching main.

---

## 🛝 Slide 12: Security Recommendations & Handover
*   **Slide Title**: 🛡️ Security Audit & Future Extensions
*   **Content**:
    *   **Secrets Isolation**: Migrate API credentials to environment variables.
    *   **JUnit Parallelization**: Configure `junit-platform.properties` to execute tests concurrently.
    *   **Soft Assertions**: Implement `assertAll()` for complete payload validation reports.
    *   **Structured Loggers**: Wrap RestAssured logs in SLF4J / logback facades.
*   **Speaker**: **Oan Zia** (CI/CD & DevOps Lead)
*   **Key Talking Points**:
    *   Providing a clean handover: how another group can easily fork, run, and scale this testing framework safely.
