package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class CSVUtil {
    private CSVUtil() {
    }

    public static void writePatients(Path path, List<Patient> patients) {
        ensureParent(path);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("id,name,age,phone,email,bloodGroup,address");
            writer.newLine();
            for (Patient p : patients) {
                writer.write(csv(p.getId()) + "," + csv(p.getName()) + ","
                        + p.getAge() + "," + csv(p.getPhone()) + ","
                        + csv(p.getEmail()) + "," + csv(p.getBloodGroup()) + ","
                        + csv(p.getAddress()));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to save patients", e);
        }
    }

    public static List<Patient> readPatients(Path path) {
        List<Patient> result = new ArrayList<>();
        if (!Files.exists(path)) return result;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            boolean header = true;
            while ((line = reader.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);
                if (p.length < 7) continue;
                result.add(new Patient(p[0], p[1], Integer.parseInt(p[2]), p[3],
                        p[4], p[5], p[6]));
            }
            return result;
        } catch (IOException | RuntimeException e) {
            throw new UncheckedIOException(
                    new IOException("Unable to load patients: " + e.getMessage(), e));
        }
    }

    public static void writeDoctors(Path path, List<Doctor> doctors) {
        ensureParent(path);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("id,name,age,phone,email,specialization,fee");
            writer.newLine();
            for (Doctor d : doctors) {
                writer.write(csv(d.getId()) + "," + csv(d.getName()) + ","
                        + d.getAge() + "," + csv(d.getPhone()) + ","
                        + csv(d.getEmail()) + "," + d.getSpecialization() + ","
                        + d.getConsultationFee());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to save doctors", e);
        }
    }

    public static List<Doctor> readDoctors(Path path) {
        List<Doctor> result = new ArrayList<>();
        if (!Files.exists(path)) return result;

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            boolean header = true;
            while ((line = reader.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);
                if (p.length < 7) continue;
                result.add(new Doctor(p[0], p[1], Integer.parseInt(p[2]), p[3], p[4],
                        Specialization.valueOf(p[5]), Double.parseDouble(p[6])));
            }
            return result;
        } catch (IOException | RuntimeException e) {
            throw new UncheckedIOException(
                    new IOException("Unable to load doctors: " + e.getMessage(), e));
        }
    }

    private static String csv(String value) {
        return value == null ? "" : value.replace(",", " ");
    }

    private static void ensureParent(Path path) {
        try {
            Path parent = path.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
