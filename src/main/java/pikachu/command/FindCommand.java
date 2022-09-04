package pikachu.command;

import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command{
    private final String input;

    public FindCommand(String fullCommand) {
        input = fullCommand;
    }

    /**
     * Executes the find command to find relevant content in task list.
     *
     * @param tasks Task list containing all the tasks.
     * @param ui User interface of Pikachu task manager.
     * @param storage Storage space with functions that update and take out tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Initialise variables
        String keyword = input.substring(5);
        StringBuilder output = new StringBuilder();
        List<Task> result = new ArrayList<>();

        //Find all the related tasks
        for (Task taskie: tasks.taskList) {
            if (taskie.getDescription().contains(keyword)) {
                result.add(taskie);
            }
        }

        //Produce output
        if (result.isEmpty()) {
            System.out.println("Pi...cannot find...");
        } else {
            for (Task task: result) {
                output.append(result.indexOf(task)+1).append('.').append(task).append('\n');
            }
            output.deleteCharAt(output.length() - 1);
            System.out.println(String.valueOf(output));
        }

    }

    /**
     * Returns whether to exit for pikachu task manager.
     *
     * @return false as this command does not exit the bot.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

