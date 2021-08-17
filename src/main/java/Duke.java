import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int counter = 0;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t Hello! I'm Duke");
        getCommand();
    }

    private static void getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t What would you like me to do?\n");
        String command = scanner.nextLine();

        // Checks if user commands for specific commands
        if (command.toLowerCase().equals("bye")) {
            sayBye();
        } else if (command.toLowerCase().equals("list")) {
            showList();
        } else if(command.toLowerCase().contains("done")) {
            int taskDone = Character.getNumericValue(command.charAt(command.length() - 1)) - 1;
            finishTask(taskDone);
        } else if (command.toLowerCase().contains("todo")) {
            addTodo(command);
        } else if (command.toLowerCase().contains("deadline")) {
            addDeadline(command);
        } else if (command.toLowerCase().contains("event")) {
            addEvent(command);
        } else {
            otherCommand(command);
        }
    }

    // Function for when user inputs "bye"
    private static void sayBye() {
        // User command is bye, print bye message and programme stops
        System.out.println("\t Bye. Hope to see you again soon");
    }

    // Function for when user inputs "list"
    private static void showList() {
        // User command is list, print current list of commands and continues asking for commands
        if (counter == 0) {
            System.out.println("Nothing has been added to the list");
        }
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        getCommand();
    }

    // Function for when user inputs "done"
    private static void finishTask(int taskDone) {
        taskList[taskDone].markAsDone();
        System.out.println("Good job! This task has been completed:\n");
        System.out.println(taskList[taskDone].toString());
        getCommand();
    }

    private static void addTodo(String command) {
        // Take the substring containing the task description of the command
        String description = command.substring(5);
        System.out.println("\t added: " + description);
        ToDo t = new ToDo(description);
        taskList[counter] = t;
        counter++;
        getCommand();
    }

    private static void addDeadline(String command) {
        // Template of command: deadline {description} /by {byWhen}
        // Find the end of description, which is the start of byWhen
        int endOfDescription = command.indexOf("/");

        // Find substring containing task description
        String description = command.substring(9, endOfDescription);
        System.out.println("\t added: " + description);

        String byWhen = command.substring(endOfDescription);
        Deadline t = new Deadline(description, byWhen);
        taskList[counter] = t;
        counter++;
        getCommand();
    }

    private static void addEvent(String command) {
        // Template of command: event {description} /by {startTime}
        // Find the end of description, which is the start of startTime
        int endOfDescription = command.indexOf("/");

        // Find substring containing task description
        String description = command.substring(6, endOfDescription);
        System.out.println("\t added: " + description);

        String startTime = command.substring(endOfDescription);
        Event t = new Event(description, startTime);
        taskList[counter] = t;
        counter++;
        getCommand();
    }

    // Function for when user command is something else
    private static void otherCommand(String command) {
        // User command is something else, add command to command list and continue asking for commands
        System.out.println(command);
        getCommand();
    }
}
