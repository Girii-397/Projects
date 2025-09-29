# Class/Module Structures with UML Diagrams

This document provides UML class diagrams for the core modules using Spring Boot entities, services, and controllers.

## Hospital Management Module
```mermaid
classDiagram
    class Patient {
        +UUID id
        +String name
        +Date dob
        +String contactInfo
        +register()
        +update()
    }
    class Doctor {
        +UUID id
        +String name
        +String specialty
        +scheduleAppointment()
    }
    class Appointment {
        +UUID id
        +UUID patientId
        +UUID doctorId
        +DateTime dateTime
        +resolveConflict()
    }
    class Bed {
        +UUID id
        +String roomNumber
        +boolean occupied
        +predictOccupancy()
    }
    class HospitalService {
        +registerPatient()
        +allocateBed()
        +processBilling()
    }
    Patient --> Appointment
    Doctor --> Appointment
    HospitalService --> Patient
    HospitalService --> Bed
```

## Patient-Centric Module
```mermaid
classDiagram
    class EHRRecord {
        +UUID id
        +UUID patientId
        +String recordType
        +String data
        +storeBlockchain()
    }
    class MonitoringData {
        +UUID id
        +UUID patientId
        +String deviceType
        +String readings
        +detectAnomaly()
    }
    class PatientService {
        +getEHR()
        +ingestData()
        +analyzeFeedback()
    }
    Patient --> EHRRecord
    Patient --> MonitoringData
    PatientService --> EHRRecord
```

## Clinical Decision Support Module
```mermaid
classDiagram
    class Diagnosis {
        +UUID id
        +UUID patientId
        +String diagnosisText
        +float confidence
        +explain()
    }
    class Recommendation {
        +UUID id
        +UUID diagnosisId
        +String recommendation
        +boolean approved
        +generate()
    }
    class AIModel {
        +UUID id
        +String name
        +float accuracy
        +infer()
    }
    class ClinicalService {
        +diagnose()
        +recommend()
        +checkInteractions()
    }
    Diagnosis --> Recommendation
    ClinicalService --> AIModel
    ClinicalService --> Diagnosis
```

## Emergency & Critical Care Module
```mermaid
classDiagram
    class Triage {
        +UUID id
        +UUID patientId
        +int priorityScore
        +String symptoms
        +performTriage()
    }
    class ICUMonitoring {
        +UUID id
        +UUID patientId
        +String vitalSigns
        +detectDeterioration()
    }
    class EmergencyAlert {
        +UUID id
        +UUID patientId
        +String type
        +sendAlert()
    }
    class EmergencyService {
        +triagePatient()
        +monitorICU()
        +dispatchAlert()
    }
    Patient --> Triage
    Patient --> ICUMonitoring
    EmergencyService --> Triage
```

## Mental Health & Wellness Module
```mermaid
classDiagram
    class TherapySession {
        +UUID id
        +UUID patientId
        +String sessionData
        +String aiResponse
        +generateResponse()
    }
    class StressData {
        +UUID id
        +UUID patientId
        +String readings
        +analyzeStress()
    }
    class WellnessPlan {
        +UUID id
        +UUID patientId
        +String plan
        +trackProgress()
    }
    class MentalHealthService {
        +chatWithBot()
        +monitorStress()
        +createPlan()
    }
    Patient --> TherapySession
    Patient --> StressData
    MentalHealthService --> TherapySession
```

## Pharmacy & Medication Module
```mermaid
classDiagram
    class Prescription {
        +UUID id
        +UUID patientId
        +String medication
        +String dosage
        +checkInteractions()
    }
    class Inventory {
        +UUID id
        +String medication
        +int stockLevel
        +reorderIfNeeded()
    }
    class AdherenceTracking {
        +UUID id
        +UUID prescriptionId
        +boolean taken
        +sendReminder()
    }
    class PharmacyService {
        +issuePrescription()
        +manageInventory()
        +trackAdherence()
    }
    Patient --> Prescription
    Prescription --> AdherenceTracking
    PharmacyService --> Inventory
    ```
    
    ## Public Health & Epidemiology Module
    ```mermaid
    classDiagram
        class OutbreakData {
            +UUID id
            +String disease
            +String location
            +int cases
            +predictOutbreak()
        }
        class VaccinationRecord {
            +UUID id
            +UUID patientId
            +String vaccine
            +int doseNumber
            +trackImmunity()
        }
        class EpidemicModel {
            +UUID id
            +String modelName
            +runSimulation()
        }
        class PublicHealthService {
            +monitorOutbreaks()
            +manageVaccinations()
            +simulateEpidemics()
        }
        Patient --> VaccinationRecord
        PublicHealthService --> OutbreakData
    ```
    
    ## Diagnostics & Lab Systems Module
    ```mermaid
    classDiagram
        class LabTest {
            +UUID id
            +UUID patientId
            +String testType
            +String results
            +orderTest()
        }
        class ImagingData {
            +UUID id
            +UUID labTestId
            +String imageUrl
            +analyzeImage()
        }
        class AIAnalysis {
            +UUID id
            +UUID imagingId
            +String findings
            +float confidence
            +reviewFindings()
        }
        class DiagnosticsService {
            +processLabTest()
            +analyzeImaging()
            +generateReport()
        }
        Patient --> LabTest
        LabTest --> ImagingData
        DiagnosticsService --> AIAnalysis
    ```
    
    ## Insurance & Billing Module
    ```mermaid
    classDiagram
        class InsuranceClaim {
            +UUID id
            +UUID patientId
            +float claimAmount
            +String status
            +submitClaim()
        }
        class FraudDetection {
            +UUID id
            +UUID claimId
            +String flags
            +detectFraud()
        }
        class Reimbursement {
            +UUID id
            +UUID claimId
            +float amount
            +processPayment()
        }
        class InsuranceService {
            +validateClaim()
            +checkFraud()
            +issueReimbursement()
        }
        Patient --> InsuranceClaim
        InsuranceClaim --> FraudDetection
        InsuranceService --> Reimbursement
    ```
    
    ## Sequence Diagram for Patient Registration
```mermaid
sequenceDiagram
    participant UI
    participant HospitalService
    participant PatientRepo
    participant DB
    UI->>HospitalService: registerPatient(request)
    HospitalService->>PatientRepo: save(patient)
    PatientRepo->>DB: INSERT
    DB-->>PatientRepo: success
    PatientRepo-->>HospitalService: patient
    HospitalService-->>UI: response
```

These structures use Hibernate for ORM, with repositories and services following Spring patterns.