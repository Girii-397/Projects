package com.healthcare.hospital;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient register(Patient patient) {
        // Validation and biometric check logic
        Patient saved = patientRepository.save(patient);
        // Generate QR code for the patient ID
        try {
            saved.setQrCode(generateQRCode(saved.getId().toString()));
        } catch (Exception e) {
            // Handle exception, perhaps log
        }
        return patientRepository.save(saved);
    }

    private String generateQRCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public Patient getById(UUID id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}