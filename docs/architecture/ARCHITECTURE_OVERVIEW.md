# Architecture Overview

This document provides a comprehensive overview of the project's architecture. The architecture is designed to be modular, scalable, and maintainable, promoting a clear separation of concerns through a multi-module setup.
The multi-module architecture is inspired by Clean Architecture principles, ensuring that each module has a specific responsibility and can be developed, tested, and maintained independently.
Another major bonus of this architecture is the improved build times, as changes in one module do not necessitate rebuilding the entire codebase.

## High-Level Diagram

```mermaid
graph BT
    subgraph FRM_DRV["FRAMEWORK & DRIVERS"]
        direction TB
        A1[android]
        A1[android]
        A2[iOS]
        A3[Desktop]
        A4[Web]
    end

    subgraph SHARED["SHARED LAYER"]
        direction TB
        C1[INFRASTRUCTURE]
        C2[PRESENTATION]
        C3[DATA]

        C1 --> C1_1[DATABASE]
        C1 --> C1_2[DI]
        C1 --> C1_3[NETWORK]
        C1 --> C1_4[UTILITIES]

        C2 --> C2_1[COMPONENTS]
        C2 --> C2_2[ICONS]

        C3 --> C3_1[DTO]
        C3 --> C3_2[MAPPER]
        C3 --> C3_3[REPOSITORY IMPLLEMENTATION]
    end

    subgraph FEATURE["FEATURE LAYER"]
        direction TB

        subgraph FEATURE_1["Feature 1"]
            direction TB
            F1_CORE[CORE feature]
            F1_DATA[DATA feature]
            F1_PRES[PRESENTATION feature]

            F1_CORE --> F1_C_DOM[DOMAIN]
            F1_C_DOM --> F1_C_DOM_M[model]
            F1_CORE --> F1_C_APP[APPLICATION]
            F1_C_APP --> F1_C_APP_U[usecase]

            F1_DATA --> F1_D_R[repoImpl]
            F1_DATA --> F1_D_D[dto]
            F1_DATA --> F1_D_DS[datasource]

            F1_PRES --> F1_P_S[screen]
            F1_PRES --> F1_P_ST[state]
        end

        subgraph FEATURE_N["Feature N"]
            direction LR
            FN_CORE[CORE]
            FN_DATA[DATA]
            FN_PRES[PRESENTATION]
        end
    end

    subgraph CORE["CORE LAYER"]
        direction TB

        subgraph B1["DOMAIN&nbsp;(Pure&nbsp;Business&nbsp;Logic)"]
            direction TB
            B1_1[MODEL]
            B1_2[REPOSITORY INTERFACES]
            B1_3[EXCEPTION]
        end

        subgraph B2["APPLICATION"]
            direction TB
            B2_1[USECASES]
            B2_2[ALGORITHMS]
        end

        B2 -.-> B1
    end

%% === High-Level Dependencies ===
    A1 -.-> FEATURE_1
    A2 -.-> FEATURE_1
    A3 -.-> FEATURE_N
    A4 -.-> FEATURE_N

    A1 -.-> SHARED
    A2 -.-> SHARED
    A3 -.-> SHARED
    A4 -.-> SHARED

    FEATURE_1 -.-> SHARED
    FEATURE_N -.-> SHARED

    FEATURE_1 -.-> B2
    FEATURE_N -.-> B2

    SHARED -.-> B2
    SHARED -.-> B1

    style SHARED fill:#FFF0D050,stroke:#333
    style CORE fill:#D0F0D050,stroke:#333
    style B1 fill:#90E09090,stroke:#333
    style B2 fill:#C0F0C090,stroke:#333
    style FEATURE fill:#FFD0D050,stroke:#333
    style FEATURE_1 fill:#FFE0E050,stroke:#555
    style FEATURE_N fill:#FFE0E050,stroke:#555
    style FRM_DRV fill:#D0E8FF50,stroke:#0C0C0C
```


## Architectural Layers

The architecture is divided into four main layers, each with a distinct responsibility. The dependencies flow inwards, from the platform-specific code towards the core business logic.

1.  **Framework & Drivers Layer**: The outermost layer, containing platform-specific implementations.
2.  **Feature Layer**: Contains individual, self-contained feature modules.
3.  **Shared Layer**: Provides common utilities and infrastructure for all features.
4.  **Core Layer**: Encapsulates the core business logic of the application.

---

### 1. Framework & Drivers Layer

```mermaid
graph BT
    subgraph FRM_DRV["FRAMEWORK & DRIVERS"]
        direction TB
        A1[android]
        A2[ios]
        A3[desktop]
        A4[web]
    end

    style FRM_DRV fill:#D0E8FF50,stroke:#0C0C0C
```

This layer represents the entry points for each target platform (Android, iOS, Desktop, Web).

-   **Responsibilities**:
    -   Initialize the application.
    -   Set up platform-specific configurations.
    -   Compose the UI by assembling screens from various feature modules.
    -   Provide platform-specific implementations for APIs defined in lower layers (if any).
-   **Dependencies**: This layer depends on the **Feature Layer** to display screens and the **Shared Layer** for common infrastructure like Dependency Injection setup.

---

### 2. Feature Layer

```mermaid
graph BT
    subgraph FEATURE["FEATURE LAYER"]
        direction TB

        subgraph FEATURE_1["Feature 1"]
            direction TB
            F1_CORE[CORE feature]
            F1_DATA[DATA feature]
            F1_PRES[PRESENTATION feature]

            F1_CORE --> F1_C_DOM[DOMAIN]
            F1_C_DOM --> F1_C_DOM_M[model]
            F1_CORE --> F1_C_APP[APPLICATION]
            F1_C_APP --> F1_C_APP_U[usecase]

            F1_DATA --> F1_D_R[repoImpl]
            F1_DATA --> F1_D_D[dto]
            F1_DATA --> F1_D_DS[datasource]

            F1_PRES --> F1_P_S[screen]
            F1_PRES --> F1_P_ST[state]
        end

        subgraph FEATURE_N["Feature N"]
            direction LR
            FN_CORE[CORE]
            FN_DATA[DATA]
            FN_PRES[PRESENTATION]
        end
    end

    style FEATURE fill:#FFD0D050,stroke:#333
    style FEATURE_1 fill:#FFE0E050,stroke:#555
    style FEATURE_N fill:#FFE0E050,stroke:#555
```

This layer encapsulates the individual features of the application. Each feature is a self-contained module or group of modules, promoting high cohesion and low coupling between features.

-   **Structure**: Each feature is broken down into three sub-modules:
    -   `PRESENTATION`: Contains the UI (screens, states) for the feature.
    -   `DATA`: Implements data handling logic, including repository implementations, DTOs, and data sources specific to the feature.
    -   `CORE`: Contains the feature's business logic (domain models, use cases). This can be seen as a feature-specific slice of the main Core Layer.
-   **Dependencies**: Feature modules depend on the **Shared Layer** for common components and the **Core Layer** for application-wide business rules and models.

---

### 3. Shared Layer

```mermaid
graph BT
    subgraph SHARED["SHARED LAYER"]
        direction TB
        C1[INFRASTRUCTURE]
        C2[PRESENTATION]
        C3[DATA]

        C1 --> C1_1[DATABASE]
        C1 --> C1_2[DI]
        C1 --> C1_3[NETWORK]
        C1 --> C1_4[UTILITIES]

        C2 --> C2_1[components]
        C2 --> C2_2[icons]

        C3 --> C3_1[dto]
        C3 --> C3_2[mapper]
        C3 --> C3_3[repoimpl]
    end

    style SHARED fill:#FFF0D050,stroke:#333
```

The Shared Layer contains code that is common across multiple features and platforms. This promotes reusability and consistency.

-   **Components**:
    -   `INFRASTRUCTURE`: Cross-cutting concerns like networking clients, database drivers, dependency injection setup, and general utilities.
    -   `PRESENTATION`: Reusable UI components (e.g., buttons, text fields) and shared icons/themes.
    -   `DATA`: Common data transfer objects (DTOs), mappers, and base repository implementations that can be shared across features.
-   **Dependencies**: This layer depends on the **Core Layer** to understand the business models it needs to support.

---

### 4. Core Layer

```mermaid
graph BT
    subgraph CORE["CORE LAYER"]
        direction TB

        subgraph B1["DOMAIN (Pure Business Logic)"]
            direction TB
            B1_1[model]
            B1_2[repository interfaces]
            B1_3[exception]
        end

        subgraph B2["APPLICATION"]
            direction TB
            B2_1[usecase]
            B2_2[algorithm]
        end

        B2 -.->|depends on| B1
    end

    style CORE fill:#D0F0D050,stroke:#333
    style B1 fill:#90E09090,stroke:#333
    style B2 fill:#C0F0C090,stroke:#333
```

This is the heart of the application, containing all the core business logic. It is completely independent of any UI, framework, or database, making it highly portable and testable.

-   **Sub-layers**:
    -   `DOMAIN`: The innermost part, containing pure business logic. It defines the business `models`, `repository interfaces`, and custom `exceptions`. It has no external dependencies.
    -   `APPLICATION`: Orchestrates the data flow to and from the `DOMAIN`. It contains `use cases` that encapsulate specific business operations and complex `algorithms`.
-   **Dependencies**: The `APPLICATION` sub-layer depends on the `DOMAIN` sub-layer. The Core Layer as a whole has no dependencies on any other layer, enforcing the "Dependency Rule" of Clean Architecture.


## Data Flow

The data flow is unidirectional, which, in theory, should make the application state predictable and easier to debug.

```mermaid
graph LR
    subgraph ":shared"
        subgraph "presentation"
            ViewModel -- Observes State --> UI(Composable UI)
        end
        subgraph "domain"
            UseCase -- Updates State --> ViewModel
            RepositoryInterface(Repository Interface) --> UseCase
        end
        subgraph "data"
            RepositoryImpl(Repository Implementation) --> RepositoryInterface
            DataSource(Remote/Local) --> RepositoryImpl
        end
    end

    UI -- Emits Events --> ViewModel
    ViewModel -- Calls --> UseCase
    UseCase -- Calls --> RepositoryInterface
```

1.  **UI Event**: The user interacts with the UI (e.g., clicks a button).
2.  **ViewModel**: The UI sends an event to the ViewModel.
3.  **Use Case**: The ViewModel calls a Use Case from the `domain` layer to execute a business action.
4.  **Repository**: The Use Case interacts with a Repository (via its interface) to get or save data.
5.  **Data Source**: The Repository implementation in the `data` layer fetches data from a remote or local data source.
6.  **State Update**: The Use Case returns data to the ViewModel, which updates its state.
7.  **UI Update**: The UI, observing the ViewModel's state, automatically recomposes to reflect the new state.

## Dependency direction and how to inject it

We use a dependency injection framework (Koin) to manage dependencies across the application. Dependencies are defined in the `:shared` module and can be injected into both shared code and platform-specific code.

```mermaid
graph TD
    subgraph DI Graph
        AppContainer
        ViewModelModules
        UseCaseModules
        RepositoryModules
    end

    AppContainer --> ViewModelModules
    AppContainer --> UseCaseModules
    AppContainer --> RepositoryModules

    ViewModelModules -- depends on --> UseCaseModules
    UseCaseModules -- depends on --> RepositoryModules
```

-   Dependencies are declared in modules within the `:shared` module.
-   The application entry point on each platform is responsible for initializing the DI container.
-   This allows for easy swapping of implementations for testing (e.g., providing a fake repository).
