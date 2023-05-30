package streams;

import streams.solutions.ObjectiveOne;
import streams.solutions.ObjectiveThree;
import streams.solutions.ObjectiveTwo;

public class Main {

    public static void main(String[] args) {
        /*
        Note:
        After completing the task, I realized it would have been better to add a List<City> field to each country.
        I thought about this solution in the beginning, but I thought the logic of mapping cities to their countries
        would be more complicated.
        After solving, I believe it would have been easier and more natural.
        I would like to discuss this more with the mentors at the next meeting to hear their opinions.
         */

        ObjectiveOne.solve();
        ObjectiveTwo.solve();
        ObjectiveThree.solve();

    }

}