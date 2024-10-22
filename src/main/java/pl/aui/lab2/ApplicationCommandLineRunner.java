package pl.aui.lab2;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.aui.lab2.domain.models.Drone;
import pl.aui.lab2.domain.services.DroneService;
import pl.aui.lab2.domain.services.ProducerService;
import pl.aui.lab2.infrastructure.DroneEntity;
import pl.aui.lab2.infrastructure.ProducerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    private final DroneService droneService;
    private final ProducerService producerService;
    private final List<String> commands = new ArrayList<>(Arrays.asList(
            "list_categories",
            "list_drones",
            "list_all",
            "add_drone",
            "delete_drone",
            "exit"
    ));

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            printAvailableCommands();
            String command = scanner.nextLine();

            switch (command) {
                case "list_categories":
                    printResults(producerService.getAll());
                    break;
                case "list_drones":
                    printResults(droneService.getAll());
                    break;
                case "list_all":
                    listAllElements();
                    break;
                case "add_drone":
                    boolean result = addDrone();
                    if (result) {
                        System.out.println("Drone added");
                    }
                    System.out.println();
                    break;
                case "delete_drone":
                    deleteDrone();
                    break;
                case "exit":
                    running = false;
                    break;
            }
        }

        System.exit(0);
    }

    private void deleteDrone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a drone to delete by typing its id (UUID)");
        System.out.println();
        List<DroneEntity> droneEntities = droneService.getAll();
        droneEntities.forEach(System.out::println);

        String id = scanner.nextLine();
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            System.out.println("UUID is not a valid UUID");
            return;
        }

        DroneEntity selectedDrone = droneService.getById(uuid).orElse(null);

        if (Objects.isNull(selectedDrone)) {
            System.out.println("Drone with provided id does not exist");
            return;
        }

        droneService.deleteById(uuid);
        System.out.println("Drone deleted");
    }

    private boolean addDrone() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter id (UUID) or leave this field blank and it will be automatically generated");
        String id = scanner.nextLine();
        UUID uuid;

        if (id.isBlank()) {
            uuid = UUID.randomUUID();
        } else {
            try {
                uuid = UUID.fromString(id);
            } catch (IllegalArgumentException e) {
                System.out.println("UUID is not a valid UUID");
                return false;
            }
        }

        System.out.println("Enter model (Not empty)");
        String model = scanner.nextLine();

        if (model.isBlank()) {
            System.out.println("Model is blank or invalid");
            return false;
        }

        System.out.println("Enter serialNumber (Not empty)");
        String serialNumber = scanner.nextLine();

        if (serialNumber.isBlank()) {
            System.out.println("Serial number is blank or invalid");
            return false;
        }

        System.out.println("Enter speed (double) (Not empty and greater than 0)");
        double speed;

        try {
            speed = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Speed is invalid");
            return false;
        }

        if (speed < 0) {
            System.out.println("Speed is invalid");
            return false;
        }

        System.out.println("Enter weight (double) (Not empty and greater than 0)");
        double weight;

        try {
            weight = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Weight is invalid");
            return false;
        }

        if (weight < 0) {
            System.out.println("Weight is invalid");
            return false;
        }

        System.out.println("Select one of the following producers by entering its name");
        List<ProducerEntity> producers = producerService.getAll();
        producers.forEach(System.out::println);

        String producerName = scanner.nextLine();
        ProducerEntity producer = producerService.getByName(producerName);

        if (Objects.isNull(producer)) {
            System.out.println("Producer is invalid");
            return false;
        }

        DroneEntity createdDrone = DroneEntity.builder()
                .id(uuid)
                .model(model)
                .serialNumber(serialNumber)
                .speed(speed)
                .weight(weight)
                .producer(producer)
                .build();

        droneService.create(createdDrone);
        return true;
    }

    private void listAllElements() {
        List<ProducerEntity> producers = producerService.getAll();
        producers.forEach(producer -> {
            System.out.println(producer);
            List<DroneEntity> drones = droneService.getAllByProducerName(producer.getName());
            drones.forEach(System.out::println);
        });
        System.out.println();
    }

    private void printAvailableCommands() {
        System.out.println("Available commands:");
        commands.forEach(System.out::println);
        System.out.println();
    }

    private <T> void printResults(List<T> items) {
        items.forEach(System.out::println);
        System.out.println();
    }
}
