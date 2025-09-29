# Data Flow Diagrams

## Patient Journey Data Flow
This diagram shows the flow of patient data from registration to discharge, integrating hospital management, patient-centric systems, and clinical decision support.

```mermaid
flowchart TD
    A[Patient Registers via UI/App] --> B[Hospital Mgmt Service: Validate & Store Patient Data]
    B --> C[Patient-Centric Service: Create EHR Record]
    C --> D[Appointment Scheduled with AI Conflict Resolution]
    D --> E[Admission: Bed Allocation with Occupancy Prediction]
    E --> F[Monitoring: Wearable/IoT Data Ingested]
    F --> G[Clinical Decision Support: AI Diagnosis & Recommendations]
    G --> H[Doctor Reviews & Approves AI Suggestions]
    H --> I[Treatment Executed, Data Updated in EHR]
    I --> J[Billing Processed with AI Error Detection]
    J --> K[Discharge: Follow-up Scheduled]
    K --> L[Post-Discharge Monitoring & Feedback Analysis]
    L --> M[Data Archived/Blockchain Stored]
```

## AI Decision Processes Data Flow
This diagram illustrates how AI processes clinical data for decision support, ensuring explainability and human oversight.

```mermaid
flowchart TD
    A[Clinical Data Input: Symptoms, Labs, Imaging] --> B[Data Preprocessing: Normalization, Encryption]
    B --> C[AI/ML Engine: Model Selection (e.g., Diagnosis Predictor)]
    C --> D[Inference via Local LLM/Ollama]
    D --> E[Generate Recommendations with Confidence Scores]
    E --> F[Bias & Fairness Check]
    F --> G[Explainable Output: Counterfactual Reasoning]
    G --> H[Human Doctor Review & Approval]
    H --> I[Integrate into EHR & Notify Care Team]
    I --> J[Feedback Loop: Update Model via Federated Learning]
    J --> K[Audit Log for Compliance]
```

## Emergency Response Data Flow
This diagram shows the flow for emergency triage and critical care.

```mermaid
flowchart TD
    A[Emergency Call/Arrival] --> B[Geolocation & Vital Signs Ingested]
    B --> C[AI Triage: Priority Scoring]
    C --> D[Alert Care Team & Allocate Resources]
    D --> E[ICU Monitoring: Real-Time Vitals]
    E --> F[AI Deterioration Prediction]
    F --> G[Intervention Alerts & Organ Matching]
    G --> H[Response Logged & Feedback Loop]
```

## Mental Health Interaction Data Flow
This diagram illustrates AI chatbot and wellness monitoring.

```mermaid
flowchart TD
    A[User Initiates Chat/Stress Data] --> B[Sentiment Analysis & Stress Detection]
    B --> C[AI Chatbot Response Generation]
    C --> D[Therapy Session Logged]
    D --> E[Personalized Wellness Plan]
    E --> F[Progress Tracking & Intervention]
    F --> G[Feedback for Model Improvement]
```

## Pharmacy Dispensing Data Flow
This diagram shows medication management and adherence.

```mermaid
flowchart TD
    A[Prescription Issued] --> B[Inventory Check & Reorder if Needed]
    B --> C[Drug Interaction Check via AI]
    C --> D[Dispense Medication]
    D --> E[Adherence Tracking via Sensors/Apps]
    E --> F[Reminders & Alerts for Non-Adherence]
    F --> G[Compliance Data Analyzed for Insights]
```

## Public Health Surveillance Data Flow
This diagram shows outbreak monitoring and epidemic prediction.

```mermaid
flowchart TD
    A[Health Data Aggregated from Hospitals] --> B[Anonymized Outbreak Data Stored]
    B --> C[AI Epidemic Predictor: Analyze Trends]
    C --> D[Vaccination Campaigns Planned]
    D --> E[Contact Tracing & Quarantine Alerts]
    E --> F[Model Updated with New Data]
    F --> G[Public Health Dashboard Updated]
```

## Diagnostics Workflow Data Flow
This diagram illustrates lab testing and AI imaging analysis.

```mermaid
flowchart TD
    A[Test Ordered via UI] --> B[Lab Test Scheduled & Sample Collected]
    B --> C[Results Processed & Stored]
    C --> D[AI Imaging Analysis for Anomalies]
    D --> E[Doctor Reviews AI Findings]
    E --> F[Diagnosis Updated in EHR]
    F --> G[Follow-up Recommendations Generated]
```

## Insurance Claims Processing Data Flow
This diagram shows claim submission and fraud detection.

```mermaid
flowchart TD
    A[Claim Submitted by Provider] --> B[AI Fraud Detection: Anomaly Check]
    B --> C[Claim Validated & Processed]
    C --> D[Reimbursement Calculated]
    D --> E[Payment Issued to Provider]
    E --> F[Audit Trail Logged]
    F --> G[Feedback for AI Model Training]
```

## Key Data Flows
- **Real-Time Streams**: Monitoring data via Kafka for anomaly detection.
- **Batch Processing**: Historical data for predictive analytics.
- **Security**: All flows encrypted; access via ABAC.
- **Interoperability**: FHIR/HL7 for external data exchange.