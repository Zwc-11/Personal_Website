package CS.Version1_L;

import java.util.Random;

//Chooses a random type of problem and assigns random values
public class RandomProblem {
    private Problem problem;
    private int type;

    // Problem Types
    public static final int KINETIC_FRICTION = 0;
    public static final int SLOWING_TO_STOP = 1;
    public static final int SINGLE_OBJECT = 2;
    public static final int STARTING_FRICTION = 3;
    public static final int WALL_FRICTION = 4;

    // List of items (each problem will choose a random one)
    public static final String[] items = { "block", "box", "book", "wagon", "lawnmower" };

    RandomProblem(int type) {
        this.type = type;

        Random rand = new Random();

        String object = items[rand.nextInt(items.length)];
        double mass = rand.nextInt(20) + 10;

        double ckf;
        double csf;

        switch (type) {
            case KINETIC_FRICTION:
                ckf = rand.nextInt(17) * 0.05 + 0.1;
                ckf = Math.round(ckf * 100.0) / 100.0;
                problem = new KineticFrictionProblem(object, mass, ckf);
                break;

            case SLOWING_TO_STOP:
                double vi = rand.nextInt(20) + 10;
                double vf;
                if (rand.nextBoolean()) {
                    vf = 0;
                } else {
                    vf = rand.nextInt(9);
                }
                double distance = rand.nextInt(30) + vi;
                problem = new SlowingDownProblem(object, mass, vi, vf, distance);
                break;

            case SINGLE_OBJECT:
                mass -= 5;
                ckf = rand.nextInt(17) * 0.05 + 0.1;
                ckf = Math.round(ckf * 100.0) / 100.0;
                double acceleration = rand.nextInt(25) * 0.1 + 1.0;
                acceleration = Math.round(acceleration * 100.0) / 100.0;
                problem = new SingleObjectProblem(object, mass, acceleration, ckf);
                break;

            case STARTING_FRICTION:
                csf = rand.nextInt(17) * 0.05 + 0.2;
                csf = Math.round(csf * 100.0) / 100.0;
                double appliedForce = (int) (9.8 * csf * mass * (rand.nextDouble() / 5.0 + 0.9));
                problem = new StartingFrictionProblem(object, mass, appliedForce, csf);
                break;

            case WALL_FRICTION:
                if (object.equals("lawnmower") || object.equals("wagon")) {
                    object = "textbook";
                }

                csf = rand.nextInt(12) * 0.05 + 0.45;
                csf = Math.round(csf * 100.0) / 100.0;
                problem = new WallFrictionProblem(object, mass, csf);
                break;
        }
    }

    public String getProblem() {
        return problem.getProblem();
    }

    public Fbd getFbd() {
        return problem.getFbd();
    }

    public double getAnswer() {
        return problem.getAnswer();
    }

    public String getSolution() {
        return problem.getSolution();
    }

    public boolean getAns() {
        return problem.getAns();
    }

    public String getStringAns() {
        return problem.getStringAns();
    }

    public boolean checkAnswer(double userAnswer) {
        return problem.checkAnswer(userAnswer);
    }

    public boolean checkAns(boolean userAnswer) {
        return problem.checkAns(userAnswer);
    }

    public int getType() {
        return type;
    }
}
