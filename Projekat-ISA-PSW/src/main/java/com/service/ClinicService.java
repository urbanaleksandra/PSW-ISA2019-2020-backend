package com.service;

import com.dto.ClinicDTO;
import com.model.*;
import com.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClinicService implements ClinicServiceInterface {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicAdministratorService clinicAdministratorService;

    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    EmailService emailService;

    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public Clinic findById(long id) {
        return clinicRepository.findById(id).get();
    }

    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    public void delete(Clinic clinic) {
        clinicRepository.delete(clinic);
    }

    public List<Clinic> getSearchClinics(String date, String type) {

        List<MedicalStaff> doctors = medicalStaffService.findByRole("doctor");
        List<Long> doctorsId = new ArrayList<>();

        for (MedicalStaff doctor : doctors) {
            doctorsId.add(doctor.getId()); //id svih doktora
        }

        List<Long> doctoriKojiSuZauzeti = new ArrayList<>(); //zauzeti doktori za taj datum(njihov id)
        for (Appointment app : appointmentService.findByFinished(false)) {
            if (app.getDate().split("T")[0].equals(date)) {
                if (!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())) {
                    doctoriKojiSuZauzeti.add(app.getDoctor().getId());
                }
            }
        }

        if (doctoriKojiSuZauzeti.size() != 0) {
            for (Long id : doctoriKojiSuZauzeti) {
                doctorsId.remove(id); //obrisem id doktora koji su zauzeti, ostanu samo slobodni u doctorsId
            }
        }

        List<Clinic> clinics = new ArrayList<>();
        for (MedicalStaff doctor : doctors) {
            for (Long id : doctorsId) {
                if (!type.equals("-1")) {
                    if (doctor.getId() == id && ((Doctor) doctor).getAppointmentType().getName().equals(type)) { //proverim i za tip
                        if (!clinics.contains(clinicRepository.findById(((Doctor) doctor).getClinic().getId()))) {
                            clinics.add(clinicRepository.findById(((Doctor) doctor).getClinic().getId()).get());
                        }

                    }
                } else {
                    if (doctor.getId() == id) { //proverim i za tip
                        if (!clinics.contains(clinicRepository.findById(((Doctor) doctor).getClinic().getId()))) {
                            clinics.add(clinicRepository.findById(((Doctor) doctor).getClinic().getId()).get());
                        }

                    }

                }

            }
        }
        return clinics;
    }


    public List<Doctor> getSearchDoctor(String date, String imeKlinike, String tipPregleda){

        List<MedicalStaff> doctors = medicalStaffService.findByRole("doctor");
        List<Long> doctorsId = new ArrayList<>();
        for (MedicalStaff doctor:doctors) {
            doctorsId.add(doctor.getId()); //id svih doktora
        }

        List<Long> doctoriKojiSuZauzeti = new ArrayList<>(); //zauzeti doktori za taj datum(njihov id)
        for(Appointment app:appointmentService.findByFinished(false)){
            if(app.getDate().split("T")[0].equals(date)){
                if(!doctoriKojiSuZauzeti.contains(app.getDoctor().getId())){
                    doctoriKojiSuZauzeti.add(app.getDoctor().getId());
                }
            }
        }

        System.out.println("88888usao " +"+++"+  date +"+++"+ imeKlinike +"+++"+ tipPregleda);
        if(doctoriKojiSuZauzeti.size() != 0){
            for (Long id:doctoriKojiSuZauzeti) {
                doctorsId.remove(id); //obrisem id doktora koji su zauzeti, ostanu samo slobodni u doctorsId
            }
        }
        Clinic klinika = clinicRepository.findByName(imeKlinike);

        List<Doctor> ret = new ArrayList<>();
        if(klinika.getDoctors().size() != 0) {
            Set<Doctor> doctorsInClinic = klinika.getDoctors();

            for (Doctor doc : doctorsInClinic) {
                for (Long id : doctorsId) {
                    if (!tipPregleda.equals("-1")) {
                        if (doc.getId() == id && doc.getAppointmentType().getName().equals(tipPregleda)) {
                            ret.add(doc);
                        }
                    } else {
                        if (doc.getId() == id) {
                            ret.add(doc);
                        }
                    }

                }
            }
        }else{
            ret = null;
        }
        return ret;

    }

    @Transactional(rollbackFor = {RuntimeException.class},readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public Clinic changeInfo(ClinicDTO newClinic, String name){
        Clinic c = (Clinic) clinicRepository.findByName(name);
        if(c != null){
            c.setLongitude(newClinic.getLongitude());
            c.setLat(newClinic.getLat());
            c.setAddress(newClinic.getAddress());
            c.setDescription(newClinic.getDescription());
            c.setName(newClinic.getName());
            clinicRepository.save(c);

            for(ClinicAdministrator ca : c.getClinicAdministrator()) {



                try {
                    emailService.sendNotificaitionAsyncc3(ca);
                } catch (Exception e) {
                    System.out.println("nije poslata poruka");
                }
            }

            return  c;

        }
        else{
            // Patient patient = new Patient(patientNovi.getUsername(), patientNovi.getPassword(), patientNovi.getFirstName(), patientNovi.getLastName(), patientNovi.getEmail(), patientNovi.getAddress(), patientNovi.getCity(), patientNovi.getCountry(), patientNovi.getMobileNumber(), patientNovi.getJmbg());
                return null;
            // patientService.save(patient);
        }

    }

}
