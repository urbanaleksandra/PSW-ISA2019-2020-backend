package com.contoller;


import com.dto.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.*;
import com.model.RequestAppointment;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentsController {

    @Autowired
    private RequestAppointmentService requestAppointmentService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SurgeryService surgeryService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestApp(@RequestBody AppointmentDTO appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);

        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
        appointment1.setMedicalRecord(mr);
        requestAppointmentService.save(appointment1);


        try {
            emailService.sendNotificaitionAsync3();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/add-requestApp-from-patient", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<RequestAppointment> addRequestAppFromPatient(@RequestBody AppointmentDTO appointment){

        RequestAppointment appointment1 = new RequestAppointment(appointment.getPatient(),appointment.getDate(),appointment.getDescription(),appointment.getDuration());
        //requestAppointmentService.save(appointment1);

        Patient pa = patientService.findByUsername(appointment.getPatient());
        System.out.println(pa.getUsername());
        Long paID = pa.getId();

        MedicalRecord mr = medicalRecordService.findByPatientId(paID);
//        mr.addRequestAppointment(appointment1);
//        medicalRecordService.save(mr);
//        System.out.println(mr.getId());
        appointment1.setMedicalRecord(mr);
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        appointment1.setType(appointment.getType());
        requestAppointmentService.save(appointment1);


        try {
            emailService.sendNotificaitionAsync4();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentRequests", method= RequestMethod.GET)
    public List<RequestAppointment> getAppointmentReq(){

        return requestAppointmentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointments/{username}", method= RequestMethod.GET)
    public List<Appointment> getAppointments(@PathVariable String username){

        List<Appointment> ret = new ArrayList<>();
        List<Appointment> sviPregledi = appointmentService.findAll();
        for(Appointment a : sviPregledi){
            if(a.isFinished()){
                ret.add(a);
            }
        }

        return ret;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/all-patient-appointment/{username}", method= RequestMethod.GET)
    public List<Appointment> getAllAppointments(@PathVariable String username){

        List<Appointment> apps = appointmentService.findAll();
        List<Appointment> ret = new ArrayList<>();
        try{
            for(Appointment app:apps){
                if(app.getPatient().equals(username))
                    ret.add(app);
            }
        }catch (Exception e){
            System.out.println("neka polja su prazna");
        }

        return ret;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getAppointmentsMR/{username}", method= RequestMethod.GET)
    public List<Appointment> getAppointmentsMR(@PathVariable String username){

        return appointmentService.findAll();
    }

    //preuzimanje svih appointmenta za kalendar
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/getAllAppointments/{doctor}", method= RequestMethod.GET)
    public List<CalendarEventsDTO> getAllAppointmentsDoctor(@PathVariable String doctor) throws ParseException {
        List<Appointment> lista = appointmentService.findAll();
        List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();
        List<Surgery> surgeries = surgeryService.findAll();

        for (Appointment app: lista) {
            if (app.getDoctor().getUsername().equals(doctor)) {
                String title = "";
                try{
                    Patient patient = patientService.findByUsername(app.getPatient());
                    String finished = "";
                    if(app.isFinished())
                        finished = "FINISHED";
                    else
                        finished = "AVAILABLE";
                    title = app.getId() + "\n" + "appointment" + "\n" + app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName()+ "\n" + finished;
                }catch (Exception e){
                    title = app.getDescription() + "\nthere is no patient yet." ;
                }

                String color = "green";

                //dodavanje duration
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date = dateFormat.parse(app.getDate());
                long millis = date.getTime();
                millis += app.getDuration() * 60 * 60 * 1000;
                String endDate = dateFormat.format(millis);

                CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
                eventsDTOS.add(eventsDTO);
            }

        }
        Doctor doc = doctorService.findByUsername(doctor);
        for (Surgery s : surgeries){
            if(s.getDoctor().contains(doc)){
                String title = "";
                try{
                    Patient patient = patientService.findByUsername(s.getPatient());

                    title = s.getId() + "\n" + "surgery" + "\n" +  s.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
                }catch (Exception e){
                    title = s.getDescription() + "\nthere is no patient yet." ;
                }

                String color = "purple";

                //dodavanje duration
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                Date date = dateFormat.parse(s.getDate());
                long millis = date.getTime();
                millis += s.getDuration() * 60 * 60 * 1000;
                String endDate = dateFormat.format(millis);

                CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, s.getDate(), endDate, s.getId(), color);
                eventsDTOS.add(eventsDTO);
            }
        }

        return eventsDTOS;
    }

    //DODATI OVU FJU KAD SE DODA KLINIKA U NURSE
//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value="/api/getAllAppointments/{nurse}", method= RequestMethod.GET)
//    public List<CalendarEventsDTO> getAllAppointmentsNurse(@PathVariable String nurse) throws ParseException {
//        List<Appointment> lista = appointmentService.findAll();
//        List<CalendarEventsDTO> eventsDTOS = new ArrayList<CalendarEventsDTO>();
//        List<Surgery> surgeries = surgeryService.findAll();
//        Nurse nur = nurseService.findByUsername(nurse);
//        for (Appointment app: lista) {
//            //ovde poredim da li je to klinika u kojoj se nalazi ta med sestra
//            if (app.getDoctor().getClinic().getId() == nur.getClinic ) {
//                String title = "";
//                try{
//                    Patient patient = patientService.findByUsername(app.getPatient());
//                    title = app.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
//                }catch (Exception e){
//                    title = app.getDescription() + "\nthere is no patient yet." ;
//                }
//
//                String color = "green";
//
//                //dodavanje duration
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//                Date date = dateFormat.parse(app.getDate());
//                long millis = date.getTime();
//                millis += app.getDuration() * 60 * 60 * 1000;
//                String endDate = dateFormat.format(millis);
//
//                CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, app.getDate(), endDate, app.getId(), color);
//                eventsDTOS.add(eventsDTO);
//            }
//
//        }
//
//        for (Surgery s : surgeries){
//            //isto proveravam kliniku
//            if(s.getDoctor().getClinic().getId() == nur.getClinic)){
//                String title = "";
//                try{
//                    Patient patient = patientService.findByUsername(s.getPatient());
//                    title = s.getDescription() + "\n" + patient.getFirstName() + " " + patient.getLastName();
//                }catch (Exception e){
//                    title = s.getDescription() + "\nthere is no patient yet." ;
//                }
//
//                String color = "purple";
//
//                //dodavanje duration
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//                Date date = dateFormat.parse(s.getDate());
//                long millis = date.getTime();
//                millis += s.getDuration() * 60 * 60 * 1000;
//                String endDate = dateFormat.format(millis);
//
//                CalendarEventsDTO eventsDTO = new CalendarEventsDTO(title, s.getDate(), endDate, s.getId(), color);
//                eventsDTOS.add(eventsDTO);
//            }
//        }
//
//        return eventsDTOS;
//    }



    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/set-app-report", method= RequestMethod.POST)
    public void reportAppointment(@RequestBody ReportAppointmentDTO reportAppointmentDTO){
        System.out.println("USAOOOOO U SET REPORT");
        Appointment app2 = new Appointment();
        Appointment app = appointmentService.findById(reportAppointmentDTO.getAppointment().getId());
        app2.setId(reportAppointmentDTO.getAppointment().getId());
        app.setInfo(reportAppointmentDTO.getAppointment().getInfo());

        Diagnosis diagnosis = diagnosisService.findById(reportAppointmentDTO.getDiagnosis().getId());
        app.setDiagnosis(diagnosis);

        Recipe recipe = new Recipe().builder()
                .authenticated(false)
                .description(reportAppointmentDTO.getRecipe().getDescription())
                .appointment(app).build();

        Set<Drug> drugs = new HashSet<>();
        for (Long id : reportAppointmentDTO.getRecipe().getDrugs()){
                Drug drug = drugService.findById(id);
               drugs.add(drug);
        }
        recipe.setDrug(drugs);
        app.setRecipe(recipe);
        System.out.println(app);
        Recipe r = recipeService.save(recipe);
        System.out.println(app);
        Appointment app1 = appointmentService.save(app);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-old-app-report/{doctor}", method= RequestMethod.GET)
    private List<Appointment> getOldAppointments(@PathVariable String doctor){
        List<Appointment> appointments = appointmentService.findAll();
        List<Appointment> ret = new ArrayList<>();
        System.out.println("usao u get old app report");
        for (Appointment app : appointments){
            if(app.getDoctor().getUsername().equals(doctor) && app.isFinished())
                ret.add(app);
           }

        return ret;
    }

    @RequestMapping(value="/getAlreadyCreatedAppointments", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAlreadyCreatedAppointments() throws ParseException {
        List<Appointment> lista = appointmentService.findAll();
        List<Appointment> ret = new ArrayList<>();

        for(Appointment a : lista){
            if(a.getPatient() == null){
                ret.add(a);
            }
        }

        return ret;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/api/get-report-info/{id}", method= RequestMethod.GET)
    private ReportAppointmentDTO getOldAppointmentsInfo(@PathVariable Long id){
        Appointment appointment = appointmentService.findById(id);
        ReportAppointmentDTO reportAppointmentDTO = new ReportAppointmentDTO();

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(appointment.getPatient());
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setInfo(appointment.getInfo());
        reportAppointmentDTO.setAppointment(appointmentDTO);

        DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
        Diagnosis d = diagnosisService.findById(appointment.getDiagnosis().getId());
        diagnosisDTO.setDescription(d.getDescription());
        diagnosisDTO.setName(d.getName());
        diagnosisDTO.setId(d.getId());
        reportAppointmentDTO.setDiagnosis(diagnosisDTO);

        RecipeDTO recipeDTO = new RecipeDTO();
        Recipe r = recipeService.findById(appointment.getRecipe().getId());
        recipeDTO.setDescription(r.getDescription());
        recipeDTO.setId(r.getId());
        Set<Drug> drugs = r.getDrug();
        List<Long> recipeDrug = new ArrayList<>();
        for (Drug drug:drugs) {
            System.out.println(drug);
            recipeDrug.add(drug.getId());
        }
        recipeDTO.setDrugs(recipeDrug);
        reportAppointmentDTO.setRecipe(recipeDTO);
        System.out.println(reportAppointmentDTO);
        return reportAppointmentDTO;
    }

    

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/scheduleApp", method=RequestMethod.POST,  produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Appointment> scheduleApp(@RequestBody AppointmentDTO appointment){

        Appointment appointment1 = new Appointment(appointment.getId(), appointment.getPatient(), appointment.getDate(), appointment.getDescription(), appointment.getDuration());
        appointment1.setType(appointment.getType());
        appointment1.setDoctorUsername(appointment.getDoctorUsername());
        System.out.println(appointment.getId());

        Patient patient = patientService.findByUsername(appointment.getPatient());

        //appointment1.setMedicalRecord(medicalRecordService.findByPatientId(patient.getId()));
        appointmentService.save(appointment1);

        try {
            emailService.sendNotificaitionAsync4();
        }catch( Exception e ){
            System.out.println("nije poslata poruka");
        }

        return new ResponseEntity<>(appointment1, HttpStatus.OK);

    }


}
