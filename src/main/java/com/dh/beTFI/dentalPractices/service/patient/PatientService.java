package com.dh.beTFI.dentalPractices.service.patient;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Address;
import com.dh.beTFI.dentalPractices.repository.IAddressRepository;
import com.dh.beTFI.dentalPractices.repository.IPatientRepository;
import com.dh.beTFI.dentalPractices.model.Patient;
import com.dh.beTFI.dentalPractices.util.validators.ValidateResources;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {
    private static final Logger logger = Logger.getLogger(PatientService.class);
    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> getById(Long id) throws BadRequestException, ResourceNotFoundException {
        logger.info("\n========== LOOKING FOR PATIENT BY ID " + id);

        if (ValidateResources.invalidId(id)) {
            logger.error("\n========== INVALID ID: " + id);
            throw new BadRequestException("Id cannot be null");
        }

        Optional<Patient> patientFound = patientRepository.findById(id);

        if (patientFound.isEmpty()) {
            logger.error(String.format("\n========== PATIENT WITH ID %s NOT FOUND", id));
            throw new ResourceNotFoundException("Patient id " + id + " not found");
        }

        return patientFound;
    }

    @Override
    public Patient create(Patient patient) throws BadRequestException {
        if (ValidateResources.invalidPatientData(patient)) {
            logger.error(String.format("\n========== ERROR SAVING NEW PATIENT. PATIENT DATA: %s", patient.showPatientData()));
            throw new BadRequestException("Invalid Patient data. Data: " + patient.showPatientData());
        }

        if (patientRepository.findByDni(patient.getDni()).isPresent()) {
            String message = String.format("Patient whit DNI %s already exists", patient.getDni());
            logger.error("\n========== ".concat(message.toUpperCase()));
            throw new BadRequestException(message);
        }

        String loggerMessage = String.format("\n========== SAVING NEW PATIENT. PATIENT DATA: %s", patient.showPatientData());

        logger.info(loggerMessage);

        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) throws BadRequestException, ResourceNotFoundException {
        Optional<Patient> patientFound = getById(patient.getId());

        if (ValidateResources.invalidPatientData(patient)) {
            logger.error(String.format("\n========== ERROR UPDATING PATIENT. PATIENT DATA: %s", patient.showPatientData()));
            throw new BadRequestException("Invalid Patient data. Data: " + patient.showPatientData());
        }

        String loggerMessage = String.format("\n========== UPDATING PATIENT - OLD PATIENT DATA: %s", patientFound);

        logger.info(loggerMessage);

        return patientRepository.save(patient);
    }

    @Override
    public void delete(Long id) throws BadRequestException, ResourceNotFoundException {
        Optional<Patient> patientFound = getById(id);

        // remove patient's address
        Long addresId = patientFound.get().getAddress() != null ?
                patientFound.get().getAddress().getId() : null;

        logger.info("\n========== DELETE PATIENT - FIRST, REMOVE PATIENT'S ADDRESS. ADDRESS ID: " + addresId);

        if (ValidateResources.invalidId(addresId)) {
            logger.error("\n========== INVALID ADDRESS ID: " + addresId);
            throw new BadRequestException("Address id can not be null");
        }

        Optional<Address> addressFound = addressRepository.findById(addresId);

        if (addressFound.isEmpty()) {
            logger.error(String.format("\n========== ADDRESS WITH ID %s NOT FOUND. IT IS NOT POSSIBLE REMOVE PATIENT WITH ID ",
                    addresId, id));
            throw new ResourceNotFoundException("Address id " + addresId + " not found");
        }

        logger.info(String.format("\n========== REMOVING ADDRESS WITH ID %s", addresId));

        addressRepository.deleteById(addresId);

        logger.info(String.format("\n========== REMOVING PATIENT WITH ID %s", patientFound.get().getId()));

        patientRepository.deleteById(id);
    }
}
