# Database Schema for Core Modules

<!-- cspell:words VARCHAR -->

This schema uses PostgreSQL for the core modules: Hospital Management & Administration, Patient-Centric Systems, and Clinical Decision Support. It includes encryption for sensitive data, audit trails, and sharding considerations.

## Key Tables

### Hospital Management & Administration
- **patients**: Basic patient info.
- **doctors**: Staff details.
- **appointments**: Scheduling.
- **beds**: Allocation.
- **resources**: Equipment/staff tracking.
- **billing**: Claims and payments.

### Patient-Centric Systems
- **ehr_records**: Health records (blockchain-linked).
- **monitoring_data**: Wearable/sensor data.
- **feedback**: Patient satisfaction.

### Clinical Decision Support
- **diagnoses**: AI-supported diagnoses.
- **recommendations**: Treatment suggestions.
- **ai_models**: Model metadata.

### Emergency & Critical Care
- **triage**: Patient priority scoring.
- **icu_monitoring**: Vital signs tracking.
- **emergency_alerts**: Notifications and responses.

### Mental Health & Wellness
- **therapy_sessions**: Chatbot interactions.
- **stress_data**: Wearable stress readings.
- **wellness_plans**: Personalized recommendations.

### Pharmacy & Medication
- **prescriptions**: Issued medications.
- **inventory**: Drug stock levels.
- **adherence_tracking**: Patient compliance data.

### Public Health & Epidemiology
- **outbreak_data**: Disease surveillance.
- **vaccination_records**: Immunization tracking.
- **epidemic_models**: Predictive simulations.

### Diagnostics & Lab Systems
- **lab_tests**: Test orders and results.
- **imaging_data**: Radiology scans.
- **ai_analysis**: Automated interpretations.

### Insurance & Billing
- **insurance_claims**: Claim submissions.
- **fraud_detection**: Anomaly flags.
- **reimbursements**: Payment processing.

### Future & Emerging Healthcare
- **ai_ethics_audits**: Bias and fairness checks.
- **metaverse_sessions**: Virtual consultations.
- **genomic_data**: DNA analysis for predictions.

## ER Diagram
```mermaid
erDiagram
    PATIENTS ||--o{ APPOINTMENTS : schedules
    PATIENTS ||--o{ EHR_RECORDS : has
    PATIENTS ||--o{ MONITORING_DATA : generates
    PATIENTS ||--o{ DIAGNOSES : receives
    PATIENTS ||--o{ RECOMMENDATIONS : gets
    PATIENTS ||--o{ BEDS : occupies
    PATIENTS ||--o{ BILLING : incurs
    PATIENTS ||--o{ TRIAGE : undergoes
    PATIENTS ||--o{ ICU_MONITORING : receives
    PATIENTS ||--o{ THERAPY_SESSIONS : participates
    PATIENTS ||--o{ STRESS_DATA : provides
    PATIENTS ||--o{ PRESCRIPTIONS : receives
    PATIENTS ||--o{ LAB_TESTS : undergoes
    PATIENTS ||--o{ VACCINATION_RECORDS : has
    PATIENTS ||--o{ INSURANCE_CLAIMS : submits
    DOCTORS ||--o{ APPOINTMENTS : conducts
    DOCTORS ||--o{ DIAGNOSES : provides
    DOCTORS ||--o{ RECOMMENDATIONS : suggests
    RESOURCES ||--o{ BEDS : includes
    RESOURCES ||--o{ APPOINTMENTS : uses
    AI_MODELS ||--o{ DIAGNOSES : supports
    AI_MODELS ||--o{ RECOMMENDATIONS : powers
    AI_MODELS ||--o{ TRIAGE : assists
    AI_MODELS ||--o{ THERAPY_SESSIONS : powers
    AI_MODELS ||--o{ LAB_TESTS : analyzes
    AI_MODELS ||--o{ OUTBREAK_DATA : predicts
    INVENTORY ||--o{ PRESCRIPTIONS : supplies
    LAB_TESTS ||--o{ IMAGING_DATA : includes
    INSURANCE_CLAIMS ||--o{ FRAUD_DETECTION : flags
    PATIENTS ||--o{ METAVERSE_SESSIONS : participates
    AI_MODELS ||--o{ AI_ETHICS_AUDITS : audited
    PATIENTS ||--o{ GENOMIC_DATA : has
```

## SQL Schema
```sql
-- Patients Table
CREATE TABLE patients (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    dob DATE,
    contact_info JSONB, -- Encrypted
    national_id VARCHAR(255) UNIQUE, -- Encrypted
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

-- Doctors Table
CREATE TABLE doctors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255),
    schedule JSONB,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Appointments Table
CREATE TABLE appointments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    doctor_id UUID REFERENCES doctors(id),
    date_time TIMESTAMP,
    status VARCHAR(50),
    ai_conflict_resolved BOOLEAN DEFAULT FALSE
);

-- Beds Table
CREATE TABLE beds (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    room_number VARCHAR(50),
    occupied BOOLEAN DEFAULT FALSE,
    patient_id UUID REFERENCES patients(id),
    occupancy_prediction JSONB
);

-- Resources Table
CREATE TABLE resources (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(100), -- e.g., equipment, staff
    availability JSONB,
    predictive_maintenance JSONB
);

-- EHR Records Table (Blockchain-linked)
CREATE TABLE ehr_records (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    record_type VARCHAR(100),
    data JSONB, -- Encrypted, immutable via blockchain
    blockchain_hash VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Monitoring Data Table
CREATE TABLE monitoring_data (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    device_type VARCHAR(100),
    readings JSONB,
    timestamp TIMESTAMP DEFAULT NOW(),
    anomaly_alert BOOLEAN DEFAULT FALSE
);

-- Diagnoses Table
CREATE TABLE diagnoses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    doctor_id UUID REFERENCES doctors(id),
    ai_supported BOOLEAN DEFAULT TRUE,
    diagnosis_text TEXT,
    confidence_score DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Recommendations Table
CREATE TABLE recommendations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    diagnosis_id UUID REFERENCES diagnoses(id),
    recommendation TEXT,
    ai_generated BOOLEAN DEFAULT TRUE,
    approved_by_doctor BOOLEAN DEFAULT FALSE
);

-- AI Models Table
CREATE TABLE ai_models (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    version VARCHAR(50),
    accuracy DECIMAL(3,2),
    last_trained TIMESTAMP,
    federated BOOLEAN DEFAULT TRUE
);

-- Billing Table
CREATE TABLE billing (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    amount DECIMAL(10,2),
    status VARCHAR(50),
    ai_error_detected BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Feedback Table
CREATE TABLE feedback (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    rating INT,
    comments TEXT,
    sentiment_score DECIMAL(3,2), -- AI-analyzed
    created_at TIMESTAMP DEFAULT NOW()
);

-- Triage Table
CREATE TABLE triage (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    priority_score INT,
    symptoms TEXT,
    ai_predicted BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT NOW()
);

-- ICU Monitoring Table
CREATE TABLE icu_monitoring (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    vital_signs JSONB,
    alerts JSONB,
    timestamp TIMESTAMP DEFAULT NOW()
);

-- Emergency Alerts Table
CREATE TABLE emergency_alerts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    alert_type VARCHAR(100),
    response_status VARCHAR(50),
    geolocation JSONB,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Therapy Sessions Table
CREATE TABLE therapy_sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    session_data TEXT,
    sentiment_analysis DECIMAL(3,2),
    ai_response TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Stress Data Table
CREATE TABLE stress_data (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    readings JSONB,
    intervention_suggested BOOLEAN DEFAULT FALSE,
    timestamp TIMESTAMP DEFAULT NOW()
);

-- Wellness Plans Table
CREATE TABLE wellness_plans (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    plan_details TEXT,
    ai_generated BOOLEAN DEFAULT TRUE,
    progress DECIMAL(3,2)
);

-- Prescriptions Table
CREATE TABLE prescriptions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    medication VARCHAR(255),
    dosage VARCHAR(100),
    adherence_rate DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Inventory Table
CREATE TABLE inventory (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    medication VARCHAR(255),
    stock_level INT,
    expiry_date DATE,
    reorder_alert BOOLEAN DEFAULT FALSE
);

-- Adherence Tracking Table
CREATE TABLE adherence_tracking (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    prescription_id UUID REFERENCES prescriptions(id),
    taken BOOLEAN,
    timestamp TIMESTAMP DEFAULT NOW()
);

-- Outbreak Data Table
CREATE TABLE outbreak_data (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    disease VARCHAR(255),
    location VARCHAR(255),
    cases INT,
    ai_prediction DECIMAL(3,2),
    timestamp TIMESTAMP DEFAULT NOW()
);

-- Vaccination Records Table
CREATE TABLE vaccination_records (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    vaccine VARCHAR(255),
    dose_number INT,
    administered_date DATE,
    efficacy DECIMAL(3,2)
);

-- Epidemic Models Table
CREATE TABLE epidemic_models (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model_name VARCHAR(255),
    parameters JSONB,
    prediction_results JSONB,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Lab Tests Table
CREATE TABLE lab_tests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    test_type VARCHAR(255),
    results JSONB,
    ai_interpretation TEXT,
    created_at TIMESTAMP DEFAULT NOW()
);

-- Imaging Data Table
CREATE TABLE imaging_data (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    lab_test_id UUID REFERENCES lab_tests(id),
    image_url VARCHAR(500),
    dicom_data JSONB,
    ai_analysis TEXT
);

-- AI Analysis Table
CREATE TABLE ai_analysis (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    imaging_id UUID REFERENCES imaging_data(id),
    findings TEXT,
    confidence DECIMAL(3,2),
    reviewed BOOLEAN DEFAULT FALSE
);

-- Insurance Claims Table
CREATE TABLE insurance_claims (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    claim_amount DECIMAL(10,2),
    status VARCHAR(50),
    fraud_score DECIMAL(3,2),
    created_at TIMESTAMP DEFAULT NOW()
);

-- Fraud Detection Table
CREATE TABLE fraud_detection (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    claim_id UUID REFERENCES insurance_claims(id),
    flags JSONB,
    ai_detected BOOLEAN DEFAULT TRUE,
    reviewed BOOLEAN DEFAULT FALSE
);

-- Reimbursements Table
CREATE TABLE reimbursements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    claim_id UUID REFERENCES insurance_claims(id),
    amount DECIMAL(10,2),
    processed_date DATE,
    status VARCHAR(50)
);

-- AI Ethics Audits Table
CREATE TABLE ai_ethics_audits (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model_id UUID REFERENCES ai_models(id),
    bias_score DECIMAL(3,2),
    fairness_report TEXT,
    audited_at TIMESTAMP DEFAULT NOW()
);

-- Metaverse Sessions Table
CREATE TABLE metaverse_sessions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    session_type VARCHAR(100), -- e.g., consultation, surgery
    vr_data JSONB,
    duration INT, -- minutes
    created_at TIMESTAMP DEFAULT NOW()
);

-- Genomic Data Table
CREATE TABLE genomic_data (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    patient_id UUID REFERENCES patients(id),
    dna_sequence TEXT, -- Encrypted
    risk_predictions JSONB,
    analyzed_at TIMESTAMP DEFAULT NOW()
);
```

## Security Notes
- Sensitive fields (e.g., contact_info, national_id, ehr_data) are encrypted using PostgreSQL's pgcrypto.
- Audit triggers for all tables to log changes.
- Sharding by patient_id for scalability.