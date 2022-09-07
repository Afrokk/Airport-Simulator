//Afrasiyab Khan
//Student ID 110034991

//Including required libraries

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.io.*;

public class Airport {
    // Using two queues, each for takeoffs and landings.
    // Using ArrayList Data Structure for logging activity.
    private ArrayList<String> airportLog;
    private Queue<String> landingQ;
    private Queue<String> takeoffQ;

    // Constructor
    public Airport() {
        airportLog = new ArrayList<String>();
        landingQ = new LinkedList<String>();
        takeoffQ = new LinkedList<String>();
    }

    public void addTakeOff(String flightNumber) {
        // Adding the flight number to the Take-off queue.
        takeoffQ.add(flightNumber);
    }

    public void addLanding(String flightNumber) {
        // Adding the flight number to the Landing queue.
        landingQ.add(flightNumber);
    }

    public String handleNextAction() {
        String result = "";
        // If both queues are empty, returning appropriate result.
        if (takeoffQ.isEmpty() && landingQ.isEmpty()) {
            result = "No plane is waiting to land or take off.";
            return result;
        } else {
            // if only landing queue is empty:
            if (landingQ.isEmpty()) {
                // Popping the top of the take-off queue.
                result = takeoffQ.poll();
                if (result == null) {
                    result = "No planes waiting for takeoff.";
                    return result;
                } else {
                    airportLog.add("Flight " + result + " taken off.");
                    // Formatting result:
                    result = "Flight " + result + " is taking off";
                    return result;
                }
            } else {
                // Popping the top of landing queue.
                result = landingQ.poll();
                airportLog.add("Flight " + result + " landed.");
                // Formatting result:
                result = "Flight " + result + " is landing.";
                return result;
            }
        }
    }

    public String waitingPlanes() {
        String result = "";

        // If both queues are empty, returning appropriate result.
        if (landingQ.isEmpty() && takeoffQ.isEmpty()) {
            result = "No plane is in the landing and take-off queues.";
            return result;
        } else {
            // if take-off Queue is not empty, returning appropriate result
            if (!takeoffQ.isEmpty()) {
                System.out.printf("Planes waiting for take-off\n");
                System.out.printf("---------------------------\n");

                // Iterating over the takeoff queue.
                for (String s : takeoffQ) {
                    result += s + "\n";
                }
            }

            // if landing Queue is not empty, returning appropriate result
            if (!landingQ.isEmpty()) {
                System.out.printf("Planes waiting for landing:\n");
                System.out.printf("----------------------------\n");

                // Iterating over the landing queue.
                for (String s : landingQ) {
                    result += s + "\n";
                }
            }
        }
        return result;
    }

    public String log() {
        String result = "";
        if (airportLog.isEmpty()) {
            result = "No activity exists.";
            return result;
        } else {
            System.out.printf("List of the landing/take-off activities\n");
            System.out.printf("---------------------------------------\n");
            // Reversing the log arraylist, as we need to print the last activity first.
            Collections.reverse(airportLog);

            // Iterating over the log arraylist.
            for (int i = 0; i < airportLog.size(); i++) {
                result += airportLog.get(i) + "\n";
            }
            // Reversing the airportLog arrayList again, to set the sequence back.
            Collections.reverse(airportLog);
            return result;
        }
    }

    // Overloaded log function: it receives a string and writes the output of above
    // log function into a text file instead of showing it on screen
    public void log(String fileName) {
        try {
            // Creating a file object.
            File file = new File(fileName);
            // Creating to print streams. One to backup the original stream, one to output
            // to file.
            PrintStream origOutput = new PrintStream(System.out);
            PrintStream output = new PrintStream(file);

            // Writing the output of log function into the file.
            System.out.println("Writing the airport log to the file...");
            System.setOut(output);
            System.out.println(log());
            // Resetting PrintStream back to default.
            System.setOut(origOutput);
            System.out.println("Done.");

        }
        // Catching exception.
        catch (IOException e) {
            System.out.println("Error creating log file" + e.getMessage());
        }
    }
}