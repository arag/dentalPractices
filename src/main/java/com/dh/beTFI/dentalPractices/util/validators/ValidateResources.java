package com.dh.beTFI.dentalPractices.util.validators;

import com.dh.beTFI.dentalPractices.model.Dentist;

public class ValidateResources {

    public static boolean invalidDentistData(Dentist dentistData) {
        return ValidateDataByType.invalidString(dentistData.getFirstname()) ||
                ValidateDataByType.invalidString(dentistData.getLastname()) ||
                ValidateDataByType.invalidInteger(dentistData.getProfessionalLicenseNumber());
    }

    public static boolean invalidId(Long id) {
        return id == null;
    }
}
