# API Specifications for Microservices

This document specifies the REST and GraphQL APIs for the core microservices: Hospital Management, Patient-Centric, and Clinical Decision Support. All APIs use OAuth2 for authentication, JSON payloads, and HTTPS. Base URL: `/api/v1`.

## Hospital Management Service
Base: `/hospital`

### Endpoints
- **POST /patients**: Register patient.
  - Body: `{name, dob, contact_info, national_id}`
  - Response: `{id, status}`
- **GET /appointments/{id}**: Get appointment details.
  - Response: `{id, patient_id, doctor_id, date_time, status}`
- **POST /beds/allocate**: Allocate bed with prediction.
  - Body: `{patient_id, acuity_score}`
  - Response: `{bed_id, prediction_data}`
- **GET /resources/utilization**: Get resource KPIs.
  - Response: `{equipment_usage, staff_allocation, anomalies}`
- **POST /billing/claim**: Process claim.
  - Body: `{patient_id, amount, payer}`
  - Response: `{claim_id, status, ai_errors}`

## Patient-Centric Service
Base: `/patient`

### Endpoints
- **GET /ehr/{patient_id}**: Retrieve EHR.
  - Headers: Authorization
  - Response: `{records: [...], blockchain_hash}`
- **POST /monitoring/data**: Ingest wearable data.
  - Body: `{patient_id, device_type, readings}`
  - Response: `{alerts: [...]}`
- **GET /feedback/{patient_id}**: Get satisfaction data.
  - Response: `{rating, sentiment_score, comments}`

## Clinical Decision Support Service
Base: `/clinical`

### Endpoints
- **POST /diagnose**: AI diagnosis.
  - Body: `{symptoms, lab_data, imaging}`
  - Response: `{diagnosis, confidence, explanation}`
- **GET /recommendations/{patient_id}**: Get treatment recs.
  - Response: `{recommendations: [...], ai_generated}`
- **POST /drug-check**: Interaction check.
  - Body: `{drugs, patient_history}`
  - Response: `{interactions, risks}`

## Emergency & Critical Care Service
Base: `/emergency`

### Endpoints
- **POST /triage**: Perform AI triage.
  - Body: `{symptoms, vitals}`
  - Response: `{priority_score, recommendations}`
- **GET /icu/{patient_id}**: Get ICU monitoring data.
  - Response: `{vitals: [...], alerts: [...]}`
- **POST /alert**: Send emergency alert.
  - Body: `{patient_id, type, location}`
  - Response: `{alert_id, status}`

## Mental Health & Wellness Service
Base: `/mental`

### Endpoints
- **POST /chat**: Interact with AI chatbot.
  - Body: `{message, patient_id}`
  - Response: `{response, sentiment_score}`
- **GET /stress/{patient_id}**: Get stress data.
  - Response: `{readings: [...], interventions: [...]}`
- **GET /wellness/{patient_id}**: Get wellness plan.
  - Response: `{plan, progress}`

## Pharmacy & Medication Service
Base: `/pharmacy`

### Endpoints
- **POST /prescription**: Issue prescription.
  - Body: `{patient_id, medication, dosage}`
  - Response: `{prescription_id, interactions}`
- **GET /inventory/{medication}**: Check stock.
  - Response: `{stock_level, reorder_needed}`
- **POST /adherence**: Log adherence.
  - Body: `{prescription_id, taken}`
  - Response: `{status}`

## GraphQL Schema (Shared)
```graphql
type Query {
  patient(id: ID!): Patient
  appointments(patientId: ID): [Appointment]
  diagnoses(patientId: ID): [Diagnosis]
  triage(patientId: ID): [Triage]
  therapySessions(patientId: ID): [TherapySession]
  prescriptions(patientId: ID): [Prescription]
}

type Mutation {
  createAppointment(input: AppointmentInput): Appointment
  updateEHR(input: EHRInput): EHR
  performTriage(input: TriageInput): Triage
  chatWithBot(input: ChatInput): ChatResponse
  issuePrescription(input: PrescriptionInput): Prescription
}

type Patient {
  id: ID!
  name: String
  ehr: EHR
}

type Triage {
  id: ID!
  priorityScore: Int
  symptoms: String
}

type TherapySession {
  id: ID!
  sessionData: String
  aiResponse: String
}

type Prescription {
  id: ID!
  medication: String
  dosage: String
}

# Additional types for other entities
```

## Security
- All endpoints require Bearer token.
- Rate limiting: 100 req/min.
- CORS enabled for web/mobile clients.