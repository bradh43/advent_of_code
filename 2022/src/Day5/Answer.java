package src.Day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import src.Solution;

public class Answer extends Solution {

    public Answer() {
        super();
    }

    private static ArrayList<ContainerStack> readStackData(BufferedReader reader) throws IOException {

        ArrayList<ContainerStack> containerStacks = new ArrayList<>();

        String crateRegex = "\\s{4}|\\[(\\w)\\]";
        String crateIndexRegex = "(?:\\s+\\d+)+\\s*";

        boolean initContainers = true;

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            if (line.matches(crateIndexRegex)) {
                continue;
            }
            MatchResult[] containerMatches = Pattern.compile(crateRegex)
                .matcher(line)
                .results()
                .toArray(MatchResult[]::new);


            for (int i = 0; i < containerMatches.length; i++) {
                String containerString = containerMatches[i].group(1);
                

                if (initContainers) {
                    if(containerString == null) {
                        containerStacks.add(new ContainerStack());
                        continue;
                    }
                    containerStacks.add(new ContainerStack(containerString));
                } else {
                    if(containerString != null) {
                        containerStacks.get(i).push(containerString);
                    }
                }
            }
            initContainers = false;
        }

        for (ContainerStack containerStack : containerStacks) {
            containerStack.reverse();
        }
        return containerStacks;
    }

    private static ArrayList<Instruction> readInstructionData(BufferedReader reader) throws IOException {

        ArrayList<Instruction> instructionList = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            instructionList.add(new Instruction(line));
        }

        return instructionList;
    }

    private String processStackData(ArrayList<ContainerStack> containerStacks, ArrayList<Instruction> instructionList) {
        StringBuilder topContainers = new StringBuilder();

        for (Instruction instruction : instructionList) {
            for (int i = 0; i < instruction.crateCount; i++) {
                char container = containerStacks.get(instruction.origin-1).pop();
                containerStacks.get(instruction.destination-1).push(container);
            }
        }

        for (ContainerStack containerStack : containerStacks) {
            if (!containerStack.isEmpty()) {
                topContainers.append(containerStack.peek());
            }
        }

        return topContainers.toString();
    }

    private String processMultipleStackData(ArrayList<ContainerStack> containerStacks, ArrayList<Instruction> instructionList) {
        StringBuilder topContainers = new StringBuilder();

        for (Instruction instruction : instructionList) {

            ContainerStack temp = new ContainerStack();
            for (int i = 0; i < instruction.crateCount; i++) {
                char container = containerStacks.get(instruction.origin-1).pop();
                temp.push(container);
            }
            while (!temp.isEmpty()) {
                char container = temp.pop();
                containerStacks.get(instruction.destination-1).push(container);
            }
        }

        for (ContainerStack containerStack : containerStacks) {
            if (!containerStack.isEmpty()) {
                topContainers.append(containerStack.peek());
            }
        }

        return topContainers.toString();
    }

    @Override
    protected
    void solve(BufferedReader reader) throws IOException {

        ArrayList<ContainerStack> containerStacks = readStackData(reader);
        ArrayList<Instruction> instructionList = readInstructionData(reader);

        ArrayList<ContainerStack> conatinerStacks2 = new ArrayList<>();
        for (ContainerStack containerStack : containerStacks) {
            conatinerStacks2.add(containerStack.clone());
        }

        String topContainers = processStackData(containerStacks, instructionList);
        System.out.println("Part 1: " + topContainers); // NTWZZWHFV

        String topContainers2 = processMultipleStackData(conatinerStacks2, instructionList);
        System.out.println("Part 2: " + topContainers2); // BRZGFVBTJ
    }


}
