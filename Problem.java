package CS.Version1_L;

/* 5 different types of problems (each is an unique subclass)
1. Kinetic Friction
2. Slowing Down
3. Single Object
4. Starting Friction
5. Wall Friction 
*/

//csf means coefficient of static friction
//ckf means coeffiicent of kinetic friction

public class Problem {
    public static final double gStrength = 9.8; // gravitational field strength

    public static final int KINETIC_FRICTION = 0;
    public static final int SLOWING_DOWN = 1;
    public static final int SINGLE_OBJECT = 2;
    public static final int STARTING_FRICTION = 3;
    public static final int WALL_FRICTION = 4;

    String problem;
    Fbd fbd;
    double answer;
    String fullSolution;
    int type;

    // Constructor
    Problem(int type) {
        this.type = type;
    }

    // Setter methods
    public void setProblem(String p) {
        problem = p;
    }

    public void setFbd(double l, double r, double t, double b) {
        fbd = new Fbd(l, r, t, b);
    }

    public void setFbd(double l, double r, double t, double b, String lf, String rf, String tf, String bf) {
        fbd = new Fbd(l, r, t, b, lf, rf, tf, bf);
    }

    public void setAnswer(double a) {
        answer = a;
    }

    public void setFullSolution(String fs) {
        fullSolution = fs;
    }

    // Getter methods
    public String getProblem() {
        return problem;
    }

    public Fbd getFbd() {
        return fbd;
    }

    public double getAnswer() {
        return answer;
    }

    public String getSolution() {
        return fullSolution;
    }

    public boolean getAns() {
        return false;
    }

    public String getStringAns() {
        return "";
    }

    // Checking methods
    public boolean checkAnswer(double userAnswer) {
        // Checks if user answer is within 0.5% of actual anwswer
        if (userAnswer <= 1.005 * answer && userAnswer >= 0.995 * answer) {
            return true;
        }
        return false;
    }

    public boolean checkAns(boolean userAnswer) {
        return false;
    }

}
