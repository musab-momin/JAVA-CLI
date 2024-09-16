package com.taskify;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.taskify.enums.OperationsEnum;

public class Actions {
    OperationsEnum selectedOperation;
    Helper helper;

    public Actions(OperationsEnum selectedOperation) {
        this.selectedOperation = selectedOperation;
        this.helper = new Helper();
    }

    public void run() {
        initiateAction();
    }

    public void initiateAction() {

        if (selectedOperation == OperationsEnum.VIEW_TASK) {
            viewTasksList();
        } else if (selectedOperation == OperationsEnum.ADD_NEW_TASK) {
            addNewTask();
        } else if (selectedOperation == OperationsEnum.UPDATE_TASK) {
            updateTask();
        } else if (selectedOperation == OperationsEnum.QUIT) {
            quitApplication();
        }

    }

    public void viewTasksList() {
        String filePath = helper.getPublicDocumentPath() + "/task.json";
        System.out.println("filepath = " + filePath);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the JSON content
            JSONArray jsonArray = new JSONArray(content);

            // Iterate over the array of JSON objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extract the values
                String title = jsonObject.getString("title");
                String description = jsonObject.getString("description");
                String createdAt = jsonObject.getString("created_at");
                String status = jsonObject.getString("status");
                String completedAt = jsonObject.getString("completed_at");

                // Print the values
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Created At: " + createdAt);
                System.out.println("Status: " + status);
                System.out.println("Completed At: " + completedAt);
                System.out.println();

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ae) {
            ae.printStackTrace();
        }

    }

    public void addNewTask() {
        Map<String, String> taskInfo = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        try {
            // take input
            System.out.println("Type title for task: ");
            String title = reader.readLine();
            System.out.println("Type description for task: ");
            String desc = reader.readLine();

            // create object
            taskInfo.put("title", title);
            taskInfo.put("description", desc);
            taskInfo.put("status", "PENDING");
            taskInfo.put("created_at", helper.getCurrentDateTime());
            taskInfo.put("completed_at", "-");

            // Convert taskInfo map to JSON object
            JSONObject newTask = new JSONObject(taskInfo);

            String filePath = helper.getPublicDocumentPath();
            String filePathWithName = filePath + "/task.json";
            helper.validateDirectoryExists(filePath);

            File file = new File(filePathWithName);
            JSONArray tasksArray = null;

            if (file.exists() && file.length() != 0) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(filePathWithName))) {
                    StringBuilder fileContent = new StringBuilder();
                    String line;

                    while ((line = fileReader.readLine()) != null) {
                        fileContent.append(line);
                    }

                    // Parse the existing file content as JSONArray
                    tasksArray = new JSONArray(fileContent.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // If file doesn't exist or is empty, create a new JSONArray
                tasksArray = new JSONArray();
            }
            // Add the new task to the JSONArray
            tasksArray.put(newTask);

            try (FileWriter fileWriter = new FileWriter(filePathWithName)) {
                fileWriter.write(tasksArray.toString(4)); // Pretty print with an indent of 4 spaces
                System.out.println("Task successfully added.");
            }

        } catch (IOException ae) {
            ae.printStackTrace();
        }
    }

    public void updateTask() {
    }

    public void quitApplication() {
    }

    public void saveTask(Map<String, String> taskInfo) {

    }

}
