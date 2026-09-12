package com.airtribe.meditrack;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.test.TestRunner;
import com.airtribe.meditrack.util.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final DoctorService doctorService = new DoctorService();
    private static final PatientService patientService = new PatientService();
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final AppointmentNotifier notifier = new AppointmentNotifier();
    private static final Scanner scanner = new Scanner(System.in);

    static {
        System.out.println("MediTrack application configuration initialized.");
    }

    public static void main(String[] args) {
        if (args.length > 0 && "--loadData".equalsIgnoreCase(args[0])) {
            loadData();
        }

        seedDemoDataIfEmpty();
        notifier.addObserver(new ConsoleNotificationObserver());

        System.out.println("=================================");
        System.out.println("       MEDITrack - JAVA OOP      ");
        System.out.println("=================================");

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addPatient();
                    case "2" -> listPatients();
                    case "3" -> searchPatients();
                    case "4" -> addDoctor();
                    case "5" -> listDoctors();
                    case "6" -> searchDoctors();
                    case "7" -> createAppointment();
                    case "8" -> listAppointments();
                    case "9" -> cancelAppointment();
                    case "10" -> generateBill();
                    case "11" -> recommendDoctor();
                    case "12" -> analytics();
                    case "13" -> saveData();
                    case "14" -> TestRunner.runAll();
                    case "0" -> {
                        System.out.println("Goodbye.");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add Patient");
        System.out.println("2. List Patients");
        System.out.println("3. Search Patient");
        System.out.println("4. Add Doctor");
        System.out.println("5. List Doctors");
        System.out.println("6. Search Doctor");
        System.out.println("7. Create Appointment");
        System.out.println("8. List Appointments");
        System.out.println("9. Cancel Appointment");
        System.out.println("10. Generate Bill");
        System.out.println("11. AI Doctor Recommendation");
        System.out.println("12. Streams Analytics");
        System.out.println("13. Save CSV Data");
        System.out.println("14. Run Manual Tests");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void addPatient() {
        String name = input("Name: ");
        int age = Integer.parseInt(input("Age: "));
        String phone = input("Phone: ");
        String email = input("Email: ");
        String blood = input("Blood group: ");
        String address = input("Address: ");

        Patient patient = patientService.addPatient(name, age, phone, email, blood, address);
        System.out.println("Created: " + patient);
    }

    private static void listPatients() {
        patientService.getAllPatients().forEach(System.out::println);
    }

    private static void searchPatients() {
        System.out.println("Search by: 1-ID 2-Name 3-Age 4-General");
        String type = input("Choice: ");
        switch (type) {
            case "1" -> System.out.println(patientService.searchPatient(input("ID: ")));
            case "2" -> patientService.searchPatient(input("Name: "), true)
                    .forEach(System.out::println);
            case "3" -> patientService.searchPatient(Integer.parseInt(input("Age: ")))
                    .forEach(System.out::println);
            case "4" -> patientService.search(input("Keyword: "))
                    .forEach(System.out::println);
            default -> System.out.println("Invalid.");
        }
    }

    private static void addDoctor() {
        String name = input("Name: ");
        int age = Integer.parseInt(input("Age: "));
        String phone = input("Phone: ");
        String email = input("Email: ");
        Specialization specialization = Specialization.valueOf(
                input("Specialization " + Arrays.toString(Specialization.values()) + ": ")
                        .toUpperCase());
        double fee = Double.parseDouble(input("Fee: "));

        Doctor doctor = doctorService.addDoctor(name, age, phone, email, specialization, fee);
        System.out.println("Created: " + doctor);
    }

    private static void listDoctors() {
        doctorService.getAllDoctors().forEach(System.out::println);
    }

    private static void searchDoctors() {
        doctorService.search(input("Keyword: ")).forEach(System.out::println);
    }

    private static void createAppointment() {
        String patientId = input("Patient ID: ");
        String doctorId = input("Doctor ID: ");
        String date = input("Date (yyyy-MM-dd HH:mm): ");
        String notes = input("Notes: ");

        Patient patient = patientService.getPatient(patientId);
        Doctor doctor = doctorService.getDoctor(doctorId);

        if (patient == null || doctor == null) {
            throw new IllegalArgumentException("Patient or doctor not found");
        }

        Appointment appointment = appointmentService.createAppointment(
                patient, doctor, DateUtil.parse(date), notes);
        notifier.notifyObservers(appointment);
        System.out.println("Created: " + appointment);
    }

    private static void listAppointments() {
        appointmentService.getAllAppointments().forEach(System.out::println);
    }

    private static void cancelAppointment() {
        String id = input("Appointment ID: ");
        appointmentService.cancelAppointment(id);
        System.out.println("Appointment cancelled.");
    }

    private static void generateBill() {
        String patientId = input("Patient ID: ");
        double base = Double.parseDouble(input("Base amount: "));

        Bill bill = BillFactory.createStandardBill(
                patientId, base, new StandardBillingStrategy());

        System.out.println("Generated: " + bill);
        if (input("Mark paid? (y/n): ").equalsIgnoreCase("y")) {
            bill.markPaid();
        }

        BillSummary summary = new BillSummary(
                bill.getBillId(), bill.getPatientId(),
                bill.getTotal(), bill.isPaid() ? "PAID" : "PENDING");

        System.out.println("Summary: " + summary);
    }

    private static void recommendDoctor() {
        String symptoms = input("Enter symptoms: ");
        List<Doctor> recommendations =
                AIHelper.recommendDoctors(symptoms, doctorService.getAllDoctors());

        if (recommendations.isEmpty()) {
            System.out.println("No matching doctor found.");
        } else {
            recommendations.forEach(System.out::println);
        }
    }

    private static void analytics() {
        System.out.println("Average doctor fee: " + doctorService.averageFee());

        Map<String, Long> appointmentsPerDoctor = appointmentService.getAllAppointments()
                .stream()
                .collect(Collectors.groupingBy(
                        a -> a.getDoctor().getName(),
                        Collectors.counting()));

        System.out.println("Appointments per doctor: " + appointmentsPerDoctor);
    }

    private static void saveData() {
        CSVUtil.writePatients(Paths.get(Constants.PATIENT_FILE),
                patientService.getAllPatients());
        CSVUtil.writeDoctors(Paths.get(Constants.DOCTOR_FILE),
                doctorService.getAllDoctors());
        System.out.println("Patient and doctor data saved to CSV.");
    }

    private static void loadData() {
        CSVUtil.readPatients(Paths.get(Constants.PATIENT_FILE))
                .forEach(patientService::addPatient);

        CSVUtil.readDoctors(Paths.get(Constants.DOCTOR_FILE))
                .forEach(doctorService::addDoctor);

        System.out.println("CSV data loaded.");
    }

    private static void seedDemoDataIfEmpty() {
        if (doctorService.getAllDoctors().isEmpty()) {
            doctorService.addDoctor(new Doctor("D100", "Dr. Anil", 45,
                    "9000000001", "anil@meditrack.com",
                    Specialization.CARDIOLOGY, 1200));
            doctorService.addDoctor(new Doctor("D101", "Dr. Priya", 38,
                    "9000000002", "priya@meditrack.com",
                    Specialization.DERMATOLOGY, 900));
            doctorService.addDoctor(new Doctor("D102", "Dr. Ravi", 42,
                    "9000000003", "ravi@meditrack.com",
                    Specialization.ORTHOPEDICS, 1000));
            doctorService.addDoctor(new Doctor("D103", "Dr. Meera", 35,
                    "9000000004", "meera@meditrack.com",
                    Specialization.GENERAL_MEDICINE, 700));
        }

        if (patientService.getAllPatients().isEmpty()) {
            patientService.addPatient("Arun", 30, "8000000001",
                    "arun@example.com", "O+", "Bangalore");
            patientService.addPatient("Kavya", 28, "8000000002",
                    "kavya@example.com", "A+", "Bangalore");
        }
    }

    private static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
