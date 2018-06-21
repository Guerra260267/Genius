package Genius;

import java.util.Objects;

public class User implements Comparable<User> {
    private String name;
    private int currentScore;

    public User(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return currentScore == user.currentScore &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, currentScore);
    }


    @Override
    public int compareTo(User user) {
        if (this.currentScore > user.getCurrentScore()) {
            return -1;
        }
        if (this.currentScore< user.getCurrentScore()) {
            return 1;
        }
        return 0;
    }
}
