package com.dh.beTFI.dentalPractices.util.validators;

import com.dh.beTFI.dentalPractices.model.Address;
import com.dh.beTFI.dentalPractices.model.Dentist;
import com.dh.beTFI.dentalPractices.model.Patient;

import java.util.regex.Pattern;

public class ValidateResources {
    private static boolean invalidEmail(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return !Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public static boolean invalidDentistData(Dentist dentistData) {
        return ValidateDataByType.invalidString(dentistData.getFirstname()) ||
                ValidateDataByType.invalidString(dentistData.getLastname()) ||
                ValidateDataByType.invalidInteger(dentistData.getProfessionalLicenseNumber());
    }

    public static boolean invalidAddressData(Address addressData) {
        return ValidateDataByType.invalidString(addressData.getStreet()) ||
                ValidateDataByType.invalidInteger(addressData.getNumber()) ||
                ValidateDataByType.invalidString(addressData.getCity()) ||
                ValidateDataByType.invalidString(addressData.getProvince());
    }

    public static boolean invalidPatientData(Patient patientData) {
        return  ValidateDataByType.invalidInteger(patientData.getDni()) ||
                ValidateDataByType.invalidString(patientData.getLastname()) ||
                ValidateDataByType.invalidString(patientData.getFirstname()) ||
                        invalidEmail(patientData.getEmail());
    }

    public static boolean invalidId(Long id) {
        return id == null;
    }
}
