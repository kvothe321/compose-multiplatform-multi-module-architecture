```mermaid
graph BT
    subgraph FRM_DRV["FRAMEWORK & DRIVERS"]
        direction TB
        A1[android]
        A1[android]
        A2[ios]
        A3[desktop]
        A4[web]
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

        C2 --> C2_1[components]
        C2 --> C2_2[icons]

        C3 --> C3_1[dto]
        C3 --> C3_2[mapper]
        C3 --> C3_3[repoimpl]
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
