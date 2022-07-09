package com.dh.beTFI.dentalPractices.service.dentist;

import com.dh.beTFI.dentalPractices.exception.BadRequestException;
import com.dh.beTFI.dentalPractices.exception.ResourceNotFoundException;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.repository.IDentistRepository;
import com.dh.beTFI.dentalPractices.util.validators.ValidateResources;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService implements IDentistService {
    private static final Logger logger = Logger.getLogger(DentistService.class);
    @Autowired
    private final IDentistRepository dentistRepository;

    public DentistService(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public List<Dentist> getAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> getById(Long id) throws BadRequestException, ResourceNotFoundException  {
        logger.info("\n========== Looking for dentist by ID " + id);

        if (ValidateResources.invalidId(id)) {
            logger.error("\n========== Invalid id: " + id);
            throw new BadRequestException("Id is required");
        }

        Optional<Dentist> dentistFound = dentistRepository.findById(id);

        if (dentistFound.isEmpty()) {
            logger.error(String.format("\n========== Dentist with ID %s not found", id));
            throw new ResourceNotFoundException("Dentist id " + id + " not found");
        }

        return dentistFound;
    }

    @Override
    public Dentist create(Dentist dentist) throws BadRequestException {
        String loggerMessage = String.format("\n========== Saving new dentist - LICENSE NUMBER = %s",
                dentist.getProfessionalLicenseNumber());

        logger.info(loggerMessage);

        if (ValidateResources.invalidDentistData(dentist)) {
            logger.error(String.format("\n========== Error saving new dentist. Dentist data: %s", dentist.showDentistData()));
            throw new BadRequestException("INVALID DENTIST DATA");
        }

        if (dentistRepository.findByProfessionalLicenseNumber(dentist.getProfessionalLicenseNumber()).isPresent()) {
            String message = String.format("Dentist whit license number %s already exists", dentist.getProfessionalLicenseNumber());
            logger.error("\n========== ".concat(message));
            throw new BadRequestException(message);
        }

        return dentistRepository.save(dentist);
    }

    @Override
    public Dentist update(Dentist dentist) throws ResourceNotFoundException, BadRequestException {
        Optional<Dentist> dentistFound = getById(dentist.getId());

        // Si el getById me responde con dentist es porque existe si no debería haber saltado la excepción
        if (ValidateResources.invalidDentistData(dentist)) {
            logger.error(String.format("\n========== Error updating dentist. Dentist data: %s", dentist.showDentistData()));
            throw new BadRequestException("INVALID DENTIST DATA");
        }

        String loggerMessage = String.format("\n========== Updating Dentist - Old dentist data: %s", dentistFound);

        logger.info(loggerMessage);

        return dentistRepository.save(dentist);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, BadRequestException {
        Optional<Dentist> dentistFound = getById(id);

        logger.info(String.format("\n========== Removing Dentist with ID %s", dentistFound.get().getId()));

        dentistRepository.deleteById(id);
    }
}
